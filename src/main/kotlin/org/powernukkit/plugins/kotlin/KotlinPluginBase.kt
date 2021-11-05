package org.powernukkit.plugins.kotlin

import cn.nukkit.Server
import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender
import cn.nukkit.command.PluginCommand
import cn.nukkit.command.PluginIdentifiableCommand
import cn.nukkit.plugin.PluginBase
import cn.nukkit.plugin.PluginLoader
import cn.nukkit.plugin.PluginLogger
import cn.nukkit.utils.Config
import com.github.michaelbull.logging.InlineLogger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import java.io.File
import java.io.InputStream

@Suppress("RedundantOverride")
public abstract class KotlinPluginBase: PluginBase() {
    @Suppress("LeakingThis")
    public val log: InlineLogger = InlineLogger(KotlinPluginLogger(this))

    @Suppress("LeakingThis")
    public val syncDispatcher: CoroutineDispatcher = SyncPluginExecutor(this).asCoroutineDispatcher()

    @Deprecated(message = "Use the log property instead.", replaceWith = ReplaceWith("log"))
    override fun getLogger(): PluginLogger {
        return super.getLogger()
    }

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>
    ): Boolean {
        return super.onCommand(sender, command, label, args)
    }

    override fun getResource(filename: String): InputStream? {
        return super.getResource(filename)
    }

    override fun saveResource(filename: String): Boolean {
        return super.saveResource(filename)
    }

    override fun saveResource(filename: String, replace: Boolean): Boolean {
        return super.saveResource(filename, replace)
    }

    override fun saveResource(filename: String, outputName: String, replace: Boolean): Boolean {
        return super.saveResource(filename, outputName, replace)
    }

    override fun getCommand(name: String): PluginIdentifiableCommand? {
        return super.getCommand(name)
    }

    override fun getPluginCommand(name: String): PluginCommand<*>? {
        return super.getPluginCommand(name)
    }

    override fun getConfig(): Config {
        return super.getConfig()
    }

    override fun getServer(): Server {
        return super.getServer()
    }

    override fun getName(): String {
        return super.getName()
    }

    override fun getPluginLoader(): PluginLoader {
        return super.getPluginLoader()
    }

    override fun getFile(): File {
        return super.getFile()
    }
}
