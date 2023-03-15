package io.github.gdpl2112.transmitMsg

import net.mamoe.mirai.message.data.MessageChain

data class SyncTransmit(
    val qid: Long,
    var target: Long,
    var data: MessageChain?
)
