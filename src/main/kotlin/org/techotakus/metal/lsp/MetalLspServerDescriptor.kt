package org.techotakus.metal.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import org.techotakus.metal.MetalFileType
import java.nio.file.Files

/**
 * Metal LSP Server Descriptor using JetBrains Official LSP API.
 * This descriptor defines how to start and configure the Metal Language Server.
 */
class MetalLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "Metal LSP") {
    
    private val serverPath: String by lazy {
        extractLspBinary()
    }
    
    override fun isSupportedFile(file: VirtualFile): Boolean {
        return file.fileType == MetalFileType
    }
    
    override fun createCommandLine(): GeneralCommandLine {
        return GeneralCommandLine(serverPath)
    }
    
    /**
     * Extract the bundled metal-lsp binary to a temporary file.
     * The binary is macOS-only as it requires xcrun metal compiler.
     */
    private fun extractLspBinary(): String {
        val resourcePath = "/bin/metal-lsp"
        
        val resourceStream = MetalLspServerDescriptor::class.java.getResourceAsStream(resourcePath)
        if (resourceStream == null) {
            val errorMsg = "Metal LSP binary not found at $resourcePath. " +
                    "Please ensure the metal-lsp binary is placed in src/main/resources/bin/"
            LOG.error(errorMsg)
            throw IllegalStateException(errorMsg)
        }
        
        // Extract to temp file (no .exe suffix for macOS binary)
        val tempFile = Files.createTempFile("metal-lsp", "").toFile()
        tempFile.deleteOnExit()
        
        resourceStream.use { input ->
            tempFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        
        // Make executable (required on macOS)
        tempFile.setExecutable(true)
        
        LOG.info("Metal LSP binary extracted to: ${tempFile.absolutePath}")
        return tempFile.absolutePath
    }
    
    companion object {
        private val LOG = Logger.getInstance(MetalLspServerDescriptor::class.java)
    }
}
