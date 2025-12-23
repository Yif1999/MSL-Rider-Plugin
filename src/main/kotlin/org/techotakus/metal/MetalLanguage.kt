package org.techotakus.metal

import com.intellij.lang.Language

object MetalLanguage : Language("metal") {
    private fun readResolve(): Any = MetalLanguage
}
