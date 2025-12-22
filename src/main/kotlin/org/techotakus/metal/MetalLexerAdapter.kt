package org.techotakus.metal

import com.intellij.lexer.LexerBase
import com.intellij.psi.tree.IElementType

class MetalLexerAdapter : LexerBase() {
    private var buffer: CharSequence = ""
    private var startOffset = 0
    private var endOffset = 0
    private var currentOffset = 0
    private var currentTokenType: IElementType? = null
    
    // Metal keywords
    private val keywords = setOf(
        "kernel", "vertex", "fragment", "constant", "device", "threadgroup",
        "struct", "typedef", "enum", "union",
        "if", "else", "for", "while", "do", "switch", "case", "default", "break", "continue", "return",
        "true", "false",
        "float", "float2", "float3", "float4", "float2x2", "float3x3", "float4x4",
        "half", "half2", "half3", "half4",
        "int", "int2", "int3", "int4",
        "uint", "uint2", "uint3", "uint4",
        "bool", "bool2", "bool3", "bool4",
        "void", "const", "static", "inline",
        "texture1d", "texture2d", "texture3d", "texture_cube",
        "sampler", "buffer"
    )

    override fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
        this.buffer = buffer
        this.startOffset = startOffset
        this.endOffset = endOffset
        this.currentOffset = startOffset
        advance()
    }

    override fun getState(): Int = 0

    override fun getTokenType(): IElementType? = currentTokenType

    override fun getTokenStart(): Int = startOffset

    override fun getTokenEnd(): Int = currentOffset

    override fun advance() {
        startOffset = currentOffset
        
        if (currentOffset >= endOffset) {
            currentTokenType = null
            return
        }

        val char = buffer[currentOffset]
        
        when {
            // Whitespace
            char.isWhitespace() -> {
                currentTokenType = MetalTokenTypes.WHITESPACE
                while (currentOffset < endOffset && buffer[currentOffset].isWhitespace()) {
                    currentOffset++
                }
            }
            // Line comment
            char == '/' && currentOffset + 1 < endOffset && buffer[currentOffset + 1] == '/' -> {
                currentTokenType = MetalTokenTypes.COMMENT
                while (currentOffset < endOffset && buffer[currentOffset] != '\n') {
                    currentOffset++
                }
            }
            // Block comment
            char == '/' && currentOffset + 1 < endOffset && buffer[currentOffset + 1] == '*' -> {
                currentTokenType = MetalTokenTypes.COMMENT
                currentOffset += 2
                while (currentOffset + 1 < endOffset) {
                    if (buffer[currentOffset] == '*' && buffer[currentOffset + 1] == '/') {
                        currentOffset += 2
                        break
                    }
                    currentOffset++
                }
            }
            // String
            char == '"' -> {
                currentTokenType = MetalTokenTypes.STRING
                currentOffset++
                while (currentOffset < endOffset && buffer[currentOffset] != '"') {
                    if (buffer[currentOffset] == '\\') currentOffset++
                    currentOffset++
                }
                if (currentOffset < endOffset) currentOffset++
            }
            // Number
            char.isDigit() -> {
                currentTokenType = MetalTokenTypes.NUMBER
                while (currentOffset < endOffset && (buffer[currentOffset].isLetterOrDigit() || buffer[currentOffset] == '.')) {
                    currentOffset++
                }
            }
            // Identifier or Keyword
            char.isLetter() || char == '_' -> {
                val identStart = currentOffset
                while (currentOffset < endOffset && (buffer[currentOffset].isLetterOrDigit() || buffer[currentOffset] == '_')) {
                    currentOffset++
                }
                val word = buffer.substring(identStart, currentOffset)
                currentTokenType = if (keywords.contains(word)) {
                    MetalTokenTypes.KEYWORD
                } else {
                    MetalTokenTypes.IDENTIFIER
                }
            }
            // Other characters
            else -> {
                currentTokenType = MetalTokenTypes.IDENTIFIER
                currentOffset++
            }
        }
    }

    override fun getBufferSequence(): CharSequence = buffer
    override fun getBufferEnd(): Int = endOffset
}
