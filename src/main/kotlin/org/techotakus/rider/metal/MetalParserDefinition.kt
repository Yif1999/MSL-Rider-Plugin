package org.techotakus.rider.metal

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.techotakus.rider.metal.psi.MetalFile
import org.techotakus.rider.metal.psi.MetalTypes

class MetalParserDefinition : ParserDefinition {
    override fun createLexer(project: com.intellij.openapi.project.Project?): Lexer = MetalLexerAdapter()

    override fun createParser(project: com.intellij.openapi.project.Project?): PsiParser = object : PsiParser {
        override fun parse(root: IElementType, builder: com.intellij.lang.PsiBuilder): ASTNode {
            builder.setDebugMode(true) // Helper for debugging
            val mark = builder.mark()
            while (!builder.eof()) {
                builder.advanceLexer()
            }
            mark.done(root)
            return builder.treeBuilt
        }
    }

    override fun getFileNodeType(): IFileElementType = FILE
    override fun getCommentTokens(): TokenSet = TokenSet.create(MetalTokenTypes.COMMENT)
    override fun getStringLiteralElements(): TokenSet = TokenSet.create(MetalTokenTypes.STRING)
    override fun createElement(node: ASTNode?): PsiElement = com.intellij.psi.impl.source.tree.LeafPsiElement(node!!.elementType, node.text) // Simple leaf element creation
    override fun createFile(viewProvider: FileViewProvider): PsiFile = MetalFile(viewProvider)

    companion object {
        val FILE = IFileElementType(MetalLanguage)
    }
}
