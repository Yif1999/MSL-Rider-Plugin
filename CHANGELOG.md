<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# Metal Shader Language Support Changelog

## [1.2.0] - 2025-12-22

### Changed

- **重要：插件 ID 从 `org.techotakus.rider.metal` 更改为 `org.techotakus.metal`**
  - 符合 JetBrains 插件命名规范，避免使用产品名称 "Rider"
  - 包名从 `org.techotakus.rider.metal` 更改为 `org.techotakus.metal`
- 从 LSP4IJ 第三方库迁移到 JetBrains 官方 LSP API
- 移除 `com.redhat.devtools.lsp4ij` 依赖
- 使用 `platform.lsp.serverSupportProvider` 官方扩展点
- 插件图标改为 SVG 格式以获得更好的显示效果

### Fixed

- 修复插件图标不显示的问题（PNG → SVG）

## [1.1.0] - 2025-12-17

### Added

- 首个对外发布的版本，提供针对 Metal Shader Language (`.metal`) 的基础支持：
  - 语法高亮、代码补全、悬浮文档与基本结构解析。
  - 通过集成 `metal-lsp` 与 `LSP4IJ` 提供语言服务器能力。

### Changed

- 基于 IntelliJ Platform 2025.2.5 配置构建环境和依赖。

## [1.0.0] - 2025-12-14

### Added

- 项目初始骨架搭建，基于 IntelliJ 平台插件模板创建 Metal Shader Rider 插件工程。

