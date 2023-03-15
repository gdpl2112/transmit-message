package io.github.gdpl2112.transmitMsg

import net.mamoe.mirai.Bot
import net.mamoe.mirai.console.permission.AbstractPermitteeId
import net.mamoe.mirai.console.permission.PermissionService.Companion.hasPermission
import net.mamoe.mirai.event.EventHandler
import net.mamoe.mirai.event.SimpleListenerHost
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.PlainText

object M1ListenerHost : SimpleListenerHost() {
    private val data = HashMap<Long, SyncTransmit>()

    @EventHandler
    suspend fun onGroupMessageEvent(event: GroupMessageEvent) {
        var k = false;
        if (AbstractPermitteeId.ExactGroup(event.group.id).hasPermission(TransmitMessage.p0)) {
            k = true
        } else {
            if (AbstractPermitteeId.ExactMember(event.group.id, event.sender.id).hasPermission(TransmitMessage.p0)) {
                k = true
            }
        }
        if (!k) return
        val text = toText(event.message)
        val qid = event.sender.id
        if (text == TransmitMessage.config.comm) {
            data[qid] = SyncTransmit(qid, 0, null);
            event.subject.sendMessage(TransmitMessage.config.tips0)
        } else if (data.containsKey(qid)) {
            var st = data[qid]
            if (st?.data == null) {
                data[qid]?.data = event.message
                event.subject.sendMessage(TransmitMessage.config.tips1)
            } else if (st.target == 0L) {
                data[qid]?.target = text.toLong();
                event.subject.sendMessage(TransmitMessage.config.ask)
            } else {
                if (TransmitMessage.config.y == text) {
                    data[qid]?.let { send(it, event.bot) }
                    data.remove(qid)
                    event.subject.sendMessage("OK")
                } else if (TransmitMessage.config.n == text) {
                    data.remove(qid)
                    event.subject.sendMessage("OK")
                }
            }
        }
    }

    private suspend fun send(st: SyncTransmit, bot: Bot) {
        var f = bot.getFriend(st.target)
        if (f != null) {
            f.sendMessage(TransmitMessage.config.pre.replace("$1", st.qid.toString()))
            st.data?.let { f.sendMessage(it) }
        } else {
            for (group in bot.groups) {
                var m = group.get(st.target);
                if (m != null) {
                    m.sendMessage(TransmitMessage.config.pre.replace("$1", st.qid.toString()))
                    st.data?.let { m.sendMessage(it) }
                }
            }
        }
    }

    @EventHandler
    suspend fun onFriendMessageEvent(event: FriendMessageEvent) {
        if (!AbstractPermitteeId.ExactFriend(event.sender.id).hasPermission(TransmitMessage.p0)) {
            return
        }
        val text = toText(event.message)
        val qid = event.sender.id
        if (text == TransmitMessage.config.comm) {
            data[qid] = SyncTransmit(qid, 0, null);
            event.subject.sendMessage(TransmitMessage.config.tips0)
        } else if (data.containsKey(qid)) {
            var st = data[qid]
            if (st?.data == null) {
                data[qid]?.data = event.message
                event.subject.sendMessage(TransmitMessage.config.tips1)
            } else if (st.target == 0L) {
                data[qid]?.target = text.toLong();
                event.subject.sendMessage(TransmitMessage.config.ask)
            } else {
                if (TransmitMessage.config.y == text) {
                    data[qid]?.let { send(it, event.bot) }
                    data.remove(qid)
                    event.subject.sendMessage("OK")
                } else if (TransmitMessage.config.n == text) {
                    data.remove(qid)
                    event.subject.sendMessage("OK")
                }
            }
        }
    }

    fun toText(chain: MessageChain): String {
        var text = "";
        for (singleMessage in chain) {
            if (singleMessage is PlainText) {
                text = text + (singleMessage.content)
            } else if (singleMessage is At) {
                text = text + singleMessage.target
            }
        }
        return text.trim()
    }
}