import { defaults } from "jest-config"

export default {
  transform: {
    "^.+\\.ts$": "ts-jest",
  },
  testRegex: "\\.test\\.ts$",
  moduleFileExtensions: [...defaults.moduleFileExtensions, "ts", "js"],
  globals: {
    "ts-jest": {
      diagnostics: true,
    },
  },
}
