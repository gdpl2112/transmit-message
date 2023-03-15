package io.github.gdpl2112.transmitMsg

import io.github.kloping.initialize.FileInitializeValue
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.java.JCompositeCommand

class CommandLine private constructor() : JCompositeCommand(TransmitMessage, "TransmitMessage") {
    companion object {
        @JvmField
        val INSTANCE = CommandLine()
    }

    init {
        description = "TransmitMessage 命令"
    }


    @Description("重新加载配置")
    @SubCommand("reload")
    suspend fun CommandSender.tmReload() {
        TransmitMessage.config = FileInitializeValue.getValue("./conf/transmit-message/config.json",
            TransmitMessage.config, true)
        sendMessage(" Reloading the complete ")
    }

}