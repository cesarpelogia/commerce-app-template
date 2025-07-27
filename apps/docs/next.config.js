/** @type {import('next').NextConfig} */
const nextConfig = {
  output: 'standalone',
  experimental: {
    // Necessário para monorepos
    outputFileTracingRoot: '../../',
  },
};

export default nextConfig;
