package org.techotakus.rider.metal

import com.intellij.lang.Language

object MetalLanguage : Language("Metal") {
    private fun readResolve(): Any = MetalLanguage
}
