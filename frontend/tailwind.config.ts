import type { Config } from "tailwindcss";

export default {
  content: [
    "./pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./components/**/*.{js,ts,jsx,tsx,mdx}",
    "./app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      colors: {
        background: "var(--background)",
        foreground: "var(--foreground)",
        'hcmut-light': "#0381FF", // Add quotes and use kebab-case
        'hcmut-dark': "#02067A", // Add quotes and use kebab-case
      },
    },
  },
  plugins: [],
} satisfies Config;
