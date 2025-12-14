package org.techotakus.rider.metal.lsp

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.redhat.devtools.lsp4ij.LanguageServerFactory
import com.redhat.devtools.lsp4ij.server.ProcessStreamConnectionProvider
import com.redhat.devtools.lsp4ij.server.StreamConnectionProvider
import java.nio.file.Files

class MetalLanguageServerFactory : LanguageServerFactory {
    override fun createConnectionProvider(project: Project): StreamConnectionProvider {
        return MetalLspConnectionProvider(project)
    }
}

private class MetalLspConnectionProvider(val project: Project) : ProcessStreamConnectionProvider() {
    init {
        // Metal LSP is macOS-only (requires xcrun metal compiler)
        val resourcePath = "/bin/metal-lsp"
        
        val resourceStream = MetalLanguageServerFactory::class.java.getResourceAsStream(resourcePath)
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
        
        val commands = listOf(tempFile.absolutePath)
        super.setCommands(commands)
    }
    
    companion object {
        private val LOG = Logger.getInstance(MetalLspConnectionProvider::class.java)
    }
}
