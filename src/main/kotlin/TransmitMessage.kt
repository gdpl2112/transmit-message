package io.github.gdpl2112.transmitMsg

import io.github.kloping.initialize.FileInitializeValue
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.extension.PluginComponentStorage
import net.mamoe.mirai.console.permission.PermissionId
import net.mamoe.mirai.console.permission.PermissionService
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.utils.info

object TransmitMessage : KotlinPlugin(JvmPluginDescription(
    id = "io.github.gdpl2112.transmitMsg.TransmitMessage",
    name = "transmit-message",
    version = "1.0",
) {
    author("kloping")
}) {
    var config: Config = Config()
    val p0 = PermissionId("io.github.gdpl2112.transmitMsg.TransmitMessage", "op")

    override fun onEnable() {
        config = FileInitializeValue.getValue("./conf/transmit-message/config.json", config, true)
        GlobalEventChannel.registerListenerHost(M1ListenerHost)
        CommandManager.registerCommand(CommandLine.INSTANCE, true)
        PermissionService.INSTANCE.register(p0, "传话权限", TransmitMessage.parentPermission)
        logger.info { "Plugin loaded" }
    }

    override fun PluginComponentStorage.onLoad() {

    }
}