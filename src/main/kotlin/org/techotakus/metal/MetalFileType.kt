package org.techotakus.metal

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object MetalFileType : LanguageFileType(MetalLanguage) {
    override fun getName(): String = "Metal"
    override fun getDescription(): String = "Metal shader language file"
    override fun getDefaultExtension(): String = "metal"
    override fun getIcon(): Icon = MetalIcons.FILE
}
