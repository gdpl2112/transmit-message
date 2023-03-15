package io.github.gdpl2112.transmitMsg

data class Config(
    var comm: String = "传话",
    var tips0: String = "请发送传话内容",
    var tips1: String = "请发送传话目标",
    var ask: String = "确定发送该传话内容吗?\n确定/取消",
    var y: String = "确定",
    var n: String = "取消",
    var ok: String = "传话成功",
    var err: String = "传话失败",
    var pre: String = "来自$1的传话",
)
