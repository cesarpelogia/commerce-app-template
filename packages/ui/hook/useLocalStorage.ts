import { useState, useEffect, useCallback } from 'react'

type SetValue<T> = T | ((val: T) => T)

/**
 * Hook para gerenciar dados no localStorage com sincronização automática
 * @param key Chave do localStorage
 * @param initialValue Valor inicial se não houver valor armazenado
 * @returns [value, setValue] - Tupla com o valor atual e função para atualizá-lo
 */
export function useLocalStorage<T>(
  key: string,
  initialValue: T
): [T, (value: SetValue<T>) => void] {
  // Estado interno para armazenar o valor
  const [storedValue, setStoredValue] = useState<T>(initialValue)
  const [mounted, setMounted] = useState(false)

  // Carrega valor do localStorage após hidratação
  useEffect(() => {
    setMounted(true)
    
    if (typeof window === 'undefined' || !window.localStorage) {
      return
    }

    try {
      const item = window.localStorage.getItem(key)
      if (item) {
        setStoredValue(JSON.parse(item))
      }
    } catch (error) {
      console.warn(`Erro ao ler do localStorage para chave "${key}":`, error)
    }
  }, [key])

  // Função para atualizar o valor
  const setValue = useCallback(
    (value: SetValue<T>) => {
      try {
        // Permite tanto valor direto quanto função updater
        setStoredValue((currentValue) => {
          const valueToStore = value instanceof Function ? value(currentValue) : value
          
          // Salva no localStorage apenas se estiver montado
          if (mounted && typeof window !== 'undefined' && window.localStorage) {
            window.localStorage.setItem(key, JSON.stringify(valueToStore))
            
            // Dispara evento personalizado para sincronizar outras instâncias
            setTimeout(() => {
              window.dispatchEvent(
                new CustomEvent('localStorage-change', {
                  detail: { key, value: valueToStore }
                })
              )
            }, 0)
          }
          
          return valueToStore
        })
      } catch (error) {
        console.warn(`Erro ao salvar no localStorage para chave "${key}":`, error)
        // Mesmo com erro, atualiza o estado local
        setStoredValue((currentValue) => {
          const valueToStore = value instanceof Function ? value(currentValue) : value
          return valueToStore
        })
      }
    },
    [key, mounted]
  )

  // Listener para mudanças do localStorage (sincronização entre abas)
  useEffect(() => {
    if (typeof window === 'undefined' || !window.localStorage) {
      return
    }

    const handleStorageChange = (e: StorageEvent) => {
      if (e.key === key && e.newValue !== null) {
        try {
          setStoredValue(JSON.parse(e.newValue))
        } catch (error) {
          console.warn(`Erro ao sincronizar localStorage para chave "${key}":`, error)
        }
      }
    }

    // Listener para evento personalizado (sincronização na mesma aba)
    const handleCustomStorageChange = (e: CustomEvent) => {
      if (e.detail.key === key) {
        setStoredValue(e.detail.value)
      }
    }

    window.addEventListener('storage', handleStorageChange)
    window.addEventListener('localStorage-change', handleCustomStorageChange as EventListener)

    return () => {
      window.removeEventListener('storage', handleStorageChange)
      window.removeEventListener('localStorage-change', handleCustomStorageChange as EventListener)
    }
  }, [key])

  return [storedValue, setValue]
}
