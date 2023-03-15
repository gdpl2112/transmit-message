## 传话插件

用于群内/私聊 内通过bot的传话

配置文件
```json
{
	"ask":"确定发送该传话内容吗?\n确定/取消",
	"comm":"传话",
	"err":"传话失败",
	"n":"取消",
	"ok":"传话成功",
	"pre":"来自$1的传话",
	"tips0":"请发送传话内容",
	"tips1":"请发送传话目标",
	"y":"确定"
}
```

更改保存配置文件后使用命令: 可立即生效

    /TransmitMessage reload

传话需要权限

    io.github.gdpl2112.transmitMsg.TransmitMessage:op

赋予该群123456权限

    /perm permit g123456 io.github.gdpl2112.transmitMsg.TransmitMessage:op

赋予该用户123456权限

    /perm permit u123456 io.github.gdpl2112.transmitMsg.TransmitMessage:op
