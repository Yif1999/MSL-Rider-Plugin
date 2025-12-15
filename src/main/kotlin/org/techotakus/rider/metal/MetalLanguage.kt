package org.techotakus.rider.metal

import com.intellij.lang.Language

// 使用小写 ID "metal"，与 plugin.xml 与 LSP 映射保持一致
object MetalLanguage : Language("metal") {
    private fun readResolve(): Any = MetalLanguage
}
