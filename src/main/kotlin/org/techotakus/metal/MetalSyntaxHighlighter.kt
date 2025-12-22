package org.techotakus.metal

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

class MetalSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer = MetalLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            MetalTokenTypes.KEYWORD -> KEYWORD_KEYS
            MetalTokenTypes.NUMBER -> NUMBER_KEYS
            MetalTokenTypes.STRING -> STRING_KEYS
            MetalTokenTypes.COMMENT -> COMMENT_KEYS
            MetalTokenTypes.IDENTIFIER -> IDENTIFIER_KEYS
            else -> EMPTY_KEYS
        }
    }

    companion object {
        private val KEYWORD_KEYS = arrayOf(DefaultLanguageHighlighterColors.KEYWORD)
        private val NUMBER_KEYS = arrayOf(DefaultLanguageHighlighterColors.NUMBER)
        private val STRING_KEYS = arrayOf(DefaultLanguageHighlighterColors.STRING)
        private val COMMENT_KEYS = arrayOf(DefaultLanguageHighlighterColors.LINE_COMMENT)

        // 自定义一�?Metal 标识符颜�?key，默认参考局部变量颜色，比纯白更醒目
        val METAL_IDENTIFIER: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey(
                "METAL_IDENTIFIER",
                DefaultLanguageHighlighterColors.LOCAL_VARIABLE
            )

        private val IDENTIFIER_KEYS = arrayOf(METAL_IDENTIFIER)
        private val EMPTY_KEYS = emptyArray<TextAttributesKey>()
    }
}
