'use client';
import React, { useState, useEffect } from 'react';
import Image from 'next/image';
import Link from 'next/link';
import { set } from 'date-fns';


export type Theme = 'light' | 'dark' | 'system';


export function useDarkMode() {
  const [userPreference, setUserPreference] = useState<Theme>('system');
  const [isDarkMode, setIsDarkMode] = useState<boolean>(false);
  const [isInitialized, setIsInitialized] = useState(false);

  // Aplica/remover classe dark no html e body
  const applyThemeClass = (dark: boolean) => {
    const root = document.documentElement;
    if (dark) {
      root.classList.add('dark');
    } else {
      root.classList.remove('dark');
    }
  };

  // Detecta a preferência do sistema operacional
  const getSystemPreference = (): boolean => {
    if (typeof window === 'undefined') return false;
    return window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;
  };

  // Aplica imediatamente ao carregar (evita flash)
  useEffect(() => {
    const savedPreference = localStorage.getItem('theme') as Theme;
    let pref: Theme = 'system';
    if (savedPreference && ['light', 'dark', 'system'].includes(savedPreference)) {
      pref = savedPreference;
    }
    setUserPreference(pref);
    setIsInitialized(true);
    // Aplica imediatamente
    const dark =
      pref === 'dark' ? true :
      pref === 'light' ? false :
      getSystemPreference();
    setIsDarkMode(dark);
    applyThemeClass(dark);
  }, []);

  // Atualiza dark mode ao trocar preferência
  useEffect(() => {
    if (!isInitialized) return;
    const dark =
      userPreference === 'dark' ? true :
      userPreference === 'light' ? false :
      getSystemPreference();
    setIsDarkMode(dark);
    applyThemeClass(dark);
  }, [userPreference, isInitialized]);

  // Sincroniza entre abas/componentes
  useEffect(() => {
    const onStorage = (e: StorageEvent) => {
      if (e.key === 'theme') {
        const pref = (e.newValue as Theme) || 'system';
        setUserPreference(pref);
      }
    };
    window.addEventListener('storage', onStorage);
    return () => window.removeEventListener('storage', onStorage);
  }, []);

  // Função para alternar tema
  const toggleTheme = () => {
    const newPreference: Theme =
      userPreference === 'light' ? 'dark' :
      userPreference === 'dark' ? 'system' :
      'light';
    setUserPreference(newPreference);
    localStorage.setItem('theme', newPreference);
    // Dispara evento customizado para sincronizar
    window.dispatchEvent(new StorageEvent('storage', { key: 'theme', newValue: newPreference }));
  };

  // Função para escolher tema específico
  const setTheme = (theme: Theme) => {
    setUserPreference(theme);
    localStorage.setItem('theme', theme);
    window.dispatchEvent(new StorageEvent('storage', { key: 'theme', newValue: theme }));
  };

  return {
    isDarkMode,
    userPreference,
    toggleTheme,
    setTheme,
  };
}

export interface HeaderProps {
  isDarkMode: boolean;
  onToggleDarkMode: () => void;
  lastUpdate?: Date;
}

export function Header() {
  const { isDarkMode, userPreference, toggleTheme } = useDarkMode();
  return (
    <header
      className="w-full bg-white dark:bg-gray-900 shadow-sm border-b border-gray-200 dark:border-gray-800 fixed top-0 left-0 z-50"
      style={{ minHeight: 64 }}
    >
      <div className="max-w-7xl mx-auto flex items-center justify-between px-4 sm:px-8 h-16">
        {/* Esquerda: Logo, nome e menu */}
        <div className="flex items-center gap-6 min-w-0">
          <div className="flex items-center gap-2 min-w-0">
            <Image
              src="/logo.png"
              alt="Logo"
              width={32}
              height={32}
              className="object-contain w-8 h-8"
              priority
            />
            <span className="font-bold text-base sm:text-lg text-gray-900 dark:text-gray-100 whitespace-nowrap">TEMPLATE E-COMMERCE</span>
          </div>
          <nav className="flex items-center gap-3 ml-4">
            <Link href="/" className="text-sm font-medium text-gray-700 dark:text-gray-200 hover:text-blue-600 dark:hover:text-blue-400 transition-colors">Home</Link>
            <Link href="/cadastro" className="text-sm font-medium text-gray-700 dark:text-gray-200 hover:text-blue-600 dark:hover:text-blue-400 transition-colors">Cadastro</Link>
            <Link href="/produtos" className="text-sm font-medium text-gray-700 dark:text-gray-200 hover:text-blue-600 dark:hover:text-blue-400 transition-colors">Produtos</Link>
            <Link href="/contato" className="text-sm font-medium text-gray-700 dark:text-gray-200 hover:text-blue-600 dark:hover:text-blue-400 transition-colors">Contato</Link>
          </nav>
        </div>
        {/* Centro: Título grande */}
        <div className="flex-1 flex justify-center min-w-0">
          <h1 className="text-xl sm:text-2xl font-extrabold text-gray-900 dark:text-white truncate text-center">Sua Empresa Aqui</h1>
        </div>
        {/* Direita: Botão de tema */}
        <div className="flex items-center justify-end min-w-0">
          <button
            className="bg-gray-100 dark:bg-gray-800 hover:bg-gray-200 dark:hover:bg-gray-700 px-3 py-2 rounded text-sm text-gray-700 dark:text-gray-200 flex items-center space-x-2 border border-gray-300 dark:border-gray-700 transition-colors"
            onClick={toggleTheme}
            title={`Modo atual: ${userPreference === 'light' ? 'Claro' : userPreference === 'dark' ? 'Escuro' : 'Automático'} - Clique para alternar`}
          >
            <span className="text-base">
              {userPreference === 'light' ? '☀️' :
                userPreference === 'dark' ? '🌙' :
                '🔄'}
            </span>
            <span className="text-xs font-medium hidden sm:inline">
              {userPreference === 'light' ? 'Claro' : userPreference === 'dark' ? 'Escuro' : 'Auto'}
            </span>
          </button>
        </div>
      </div>
    </header>
  );
}