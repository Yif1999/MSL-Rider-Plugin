package org.techotakus.rider.metal

import com.intellij.psi.tree.IElementType

class MetalTokenTypes {
    companion object {
        @JvmField val KEYWORD = IElementType("METAL_KEYWORD", MetalLanguage)
        @JvmField val IDENTIFIER = IElementType("METAL_IDENTIFIER", MetalLanguage)
        @JvmField val NUMBER = IElementType("METAL_NUMBER", MetalLanguage)
        @JvmField val STRING = IElementType("METAL_STRING", MetalLanguage)
        @JvmField val COMMENT = IElementType("METAL_COMMENT", MetalLanguage)
        @JvmField val WHITESPACE = IElementType("METAL_WHITESPACE", MetalLanguage)
    }
}
