# 🛒 E-commerce Turborepo

> Sistema de e-commerce moderno construído com Turborepo, Next.js, TypeScript e Docker

[![TypeScript](https://img.shields.io/badge/TypeScript-5.8.3-blue)](https://www.typescriptlang.org/)
[![Next.js](https://img.shields.io/badge/Next.js-15.4.2-black)](https://nextjs.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green)](https://spring.io/projects/spring-boot)
[![Turborepo](https://img.shields.io/badge/Turborepo-2.5.5-red)](https://turbo.build/)
[![Docker](https://img.shields.io/badge/Docker-Compose-blue)](https://docs.docker.com/compose/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)](https://www.postgresql.org/)

## 🚀 Sobre o Projeto

Monorepo completo para sistema de e-commerce com múltiplas aplicações frontend, API backend e infraestrutura containerizada. Desenvolvido com as melhores práticas de desenvolvimento moderno.

## 🏗️ Arquitetura

### 📦 Aplicações (`apps/`)
- **`web`** - Loja principal (Next.js)
- **`admin`** - Painel administrativo (Next.js)
- **`store`** - Interface da loja (Next.js)
- **`docs`** - Documentação (Next.js)
- **`api`** - API Backend (Java/Spring Boot)

### 🔧 Pacotes Compartilhados (`packages/`)
- **`@repo/ui`** - Biblioteca de componentes React
- **`@repo/eslint-config`** - Configurações ESLint
- **`@repo/typescript-config`** - Configurações TypeScript

### 🐳 Infraestrutura
- **PostgreSQL 15** - Banco de dados principal
- **Redis 7** - Cache e sessões
- **Adminer** - Interface web do banco

## 🛠️ Tecnologias

### Frontend
- **Next.js 15** - Framework React
- **TypeScript** - Tipagem estática
- **React 19** - Biblioteca de interface

### Backend
- **Java 17+** - Linguagem de programação
- **Spring Boot 3.2** - Framework Java
- **PostgreSQL** - Banco relacional
- **Redis** - Cache em memória

### DevOps
- **Docker & Docker Compose** - Containerização
- **Turborepo** - Gerenciamento monorepo
- **ESLint** - Linting de código

## 🚀 Quick Start

### Pré-requisitos
- Node.js 18+
- Java 17+
- Docker & Docker Compose
- npm 10.8.2+

### 1. Clone o repositório
```bash
git clone https://github.com/[seu-usuario]/ecommerce-turborepo.git
cd ecommerce-turborepo