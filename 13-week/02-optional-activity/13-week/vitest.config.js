import { defineConfig } from "vitest/config";

export default defineConfig({
  esbuild: { jsx: "automatic" },
  test: {
    // Backend usa "node"; las pruebas de frontend declaran jsdom en su propio archivo
    environment: "node",
    globals: true,
  },
});
