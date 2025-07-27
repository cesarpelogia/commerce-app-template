# Build stage - usa o Dockerfile base do Node.js
FROM node:18-alpine AS base

# Instalar dependências apenas quando necessário
FROM base AS deps
WORKDIR /app

# Copiar arquivos de dependências da raiz do monorepo
COPY package.json yarn.lock* package-lock.json* pnpm-lock.yaml* ./
COPY turbo.json ./

# Copiar packages e dependências do workspace
COPY packages/ ./packages/
COPY apps/docs/package.json ./apps/docs/

# Instalar dependências
RUN \
  if [ -f yarn.lock ]; then yarn --frozen-lockfile; \
  elif [ -f package-lock.json ]; then npm ci; \
  elif [ -f pnpm-lock.yaml ]; then corepack enable pnpm && pnpm i --frozen-lockfile; \
  else echo "Lockfile not found." && exit 1; \
  fi

# Build stage
FROM base AS builder
WORKDIR /app

# Copiar dependências instaladas
COPY --from=deps /app/node_modules ./node_modules
COPY --from=deps /app/packages ./packages

# Copiar código da aplicação
COPY apps/docs ./apps/docs
COPY package.json turbo.json ./

# Construir a aplicação
ENV NEXT_TELEMETRY_DISABLED 1
RUN npm run build --filter=docs

# Runtime stage
FROM base AS runner
WORKDIR /app

ENV NODE_ENV production
ENV NEXT_TELEMETRY_DISABLED 1

# Criar usuário não-root
RUN addgroup --system --gid 1001 nodejs
RUN adduser --system --uid 1001 nextjs

# Copiar arquivos de build
COPY --from=builder /app/apps/docs/public ./apps/docs/public

# Definir permissões corretas antes de copiar
RUN mkdir -p ./apps/docs/.next
RUN chown nextjs:nodejs ./apps/docs/.next

# Copiar arquivos de build com permissões corretas
COPY --from=builder --chown=nextjs:nodejs /app/apps/docs/.next/standalone ./
COPY --from=builder --chown=nextjs:nodejs /app/apps/docs/.next/static ./apps/docs/.next/static

USER nextjs

EXPOSE 3001

ENV PORT 3001
ENV HOSTNAME "0.0.0.0"

CMD ["node", "apps/docs/server.js"]
