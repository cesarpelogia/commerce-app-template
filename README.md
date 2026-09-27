# E-commerce Turborepo

> Estrutura inicial de um sistema de e-commerce com Turborepo, Next.js, Spring Boot e Docker.

[![TypeScript](https://img.shields.io/badge/TypeScript-5.8.3-blue)](https://www.typescriptlang.org/)
[![Next.js](https://img.shields.io/badge/Next.js-15.4.2-black)](https://nextjs.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-green)](https://spring.io/projects/spring-boot)
[![Turborepo](https://img.shields.io/badge/Turborepo-2.5.5-red)](https://turbo.build/)
[![Docker](https://img.shields.io/badge/Docker-Compose-blue)](https://docs.docker.com/compose/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)](https://www.postgresql.org/)

## Sobre o Projeto

Monorepo para um sistema de e-commerce. A estrutura de frontend e infraestrutura está criada, mas as funcionalidades de negócio ainda estão em desenvolvimento.

## 🏗️ Arquitetura

### Aplicações (`apps/`)
- **`web`** - Aplicação principal em Next.js.
- **`docs`** - Aplicação de documentação em Next.js.
- **`template`** - Backend em Java com Spring Boot. O nome atual do módulo ainda é `template`.
- **`admin`** e **`store`** - Diretórios reservados, ainda sem implementação.

### Pacotes Compartilhados (`packages/`)
- **`@repo/ui`** - Componentes React compartilhados.
- **`@repo/eslint-config`** - Configurações de ESLint.
- **`@repo/typescript-config`** - Configurações de TypeScript.

### Infraestrutura
- **PostgreSQL 15** - Banco configurado para a aplicação.
- **Redis 7** - Serviço preparado para cache e sessões; ainda não integrado ao backend.
- **Adminer** - Interface web para administração do banco em desenvolvimento.

## 🛠️ Tecnologias

### Frontend
- **Next.js 15.4.2** - Framework React.
- **TypeScript 5.8** - Tipagem estática.
- **React 19.1** - Biblioteca de interface.

### Backend
- **Java 21** - Linguagem de programação.
- **Spring Boot 4.1.1** - Framework Java.
- **Spring Data JPA** - Persistência.
- **Flyway** - Migrações de banco, ainda sem migrações criadas.
- **Spring Security** - Dependência preparada para segurança.
- **PostgreSQL** - Banco relacional.

### DevOps
- **Docker & Docker Compose** - Containerização
- **Turborepo** - Gerenciamento monorepo
- **ESLint** - Linting de código

## Quick Start

### Pré-requisitos
- Node.js 18+
- Java 21+
- Docker & Docker Compose
- npm 10.8.2+

### 1. Clone o repositório
```bash
git clone https://github.com/[seu-usuario]/ecommerce-turborepo.git
cd ecommerce-turborepo
