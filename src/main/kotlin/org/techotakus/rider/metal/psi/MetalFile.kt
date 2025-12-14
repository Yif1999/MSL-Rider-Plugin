package org.techotakus.rider.metal.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import org.techotakus.rider.metal.MetalFileType
import org.techotakus.rider.metal.MetalLanguage

class MetalFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, MetalLanguage) {
    override fun getFileType() = MetalFileType
    override fun toString() = "Metal File"
}
