import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import tailwindcss from '@tailwindcss/vite'; // 1. 이 줄을 추가하세요!
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

export default defineConfig({
  root: './', 
  plugins: [
    react(), 
    tailwindcss() // 2. 플러그인 리스트에 추가하세요!
  ],
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./"),
    },
    extensions: ['.mjs', '.js', '.mts', '.ts', '.jsx', '.tsx', '.json']
  },
  optimizeDeps: {
    include: [
      'react',
      'react-dom',
      'sonner',
      'next-themes',
      'lucide-react',
      'class-variance-authority',
      'clsx',
      'tailwind-merge',
      '@radix-ui/react-slot',
      '@radix-ui/react-tabs',
      '@radix-ui/react-dialog',
      '@radix-ui/react-avatar',
      '@radix-ui/react-dropdown-menu',
      '@radix-ui/react-alert-dialog',
      '@radix-ui/react-label',
      '@radix-ui/react-progress',
      '@radix-ui/react-checkbox',
      '@radix-ui/react-separator',
      '@radix-ui/react-select'
    ]
  },
  server: {
    port: 3000,
    open: true,
    watch: {
      usePolling: true,
    }
  }
});