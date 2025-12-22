package org.techotakus.metal

import com.intellij.lang.Language

// 使用小写 ID "metal"，与 plugin.xml �?LSP 映射保持一�?
object MetalLanguage : Language("metal") {
    private fun readResolve(): Any = MetalLanguage
}
