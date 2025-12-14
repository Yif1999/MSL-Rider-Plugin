# Metal Shader Language Support

![Build Status](https://github.com/techotakus/metal-shader-support/workflows/Build/badge.svg)

<!-- Plugin description -->
Provides comprehensive Metal Shader Language (`.metal`) support for JetBrains Rider and other IntelliJ-based IDEs. This plugin integrates the Metal Language Server to provide a rich editing experience.
<!-- Plugin description end -->

## ✨ Features

- **Syntax Highlighting**: Beautiful colorization for keywords, types, attributes, strings, comments, and numbers.
- **Code Completion**: Intelligent completion for Metal keywords, built-in functions, and vector types.
- **Real-time Diagnostics**: Instant error checking and validation leveraging the native Metal compiler.
- **Hover Documentation**: Access built-in function documentation by hovering over code.
- **Code Structure**: Basic parsing support for improved navigation.

## 💻 Requirements

- **Operating System**: **macOS** is required.
  - The plugin relies on `xcrun` and the Metal compiler toolchain which are exclusive to macOS.
- **IDE**: JetBrains Rider or IntelliJ IDEA (2025.2+).

## 🚀 Installation

1. Open **Settings/Preferences** > **Plugins** > **Marketplace**.
2. Search for "Metal Shader Language Support".
3. Click **Install** and restart the IDE.

*Alternatively, you can install the plugin from disk using the release zip file.*

## 🔧 Under the Hood

This plugin bundles and integrates [metal-lsp](https://github.com/cmyr/metal-lsp) to provide advanced language features via the Language Server Protocol (LSP). It utilizes [LSP4IJ](https://github.com/redhat-developer/lsp4ij) for seamless LSP client integration within the IntelliJ Platform.

---
*Developed with ❤️ by Yif*
