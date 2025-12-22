package org.techotakus.metal.lsp

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider
import org.techotakus.metal.MetalFileType

/**
 * JetBrains Official LSP API integration for Metal Shading Language.
 * This provider is responsible for starting the Metal LSP server when a Metal file is opened.
 */
class MetalLspServerSupportProvider : LspServerSupportProvider {
    override fun fileOpened(
        project: Project,
        file: VirtualFile,
        serverStarter: LspServerSupportProvider.LspServerStarter
    ) {
        if (file.fileType == MetalFileType) {
            serverStarter.ensureServerStarted(MetalLspServerDescriptor(project))
        }
    }
}
