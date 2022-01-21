package me.heartalborada.QQbot.bungee.register;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.dreamvoid.miraimc.api.MiraiBot;
import me.dreamvoid.miraimc.api.bot.MiraiGroup;
import me.dreamvoid.miraimc.bungee.event.MiraiGroupMessageEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


import java.util.Objects;

import static me.heartalborada.QQbot.Utils.Player.GetPlayerTextures;
import static me.heartalborada.QQbot.Utils.Player.GetPlayerUsedName;
import static me.heartalborada.QQbot.Utils.Version.GET_new_version;
import static me.heartalborada.QQbot.Config.*;

public class tencentQQEvent implements Listener {
    public tencentQQEvent() {
        logger.info("Tencent QQ events are listening now");
    }

    //Untested code
    @EventHandler
    public void onQQMessages(MiraiGroupMessageEvent g) {
        MiraiGroup group = MiraiBot.getBot(g.getBotID()).getGroup(g.getGroupID());
        boolean flag=false;
        for(long groupID:EnableGroup){
            if(groupID==g.getGroupID()){
                flag=true;
            }
        }
        if(!flag)
            return;
        if (g.getBotID() == EnableBotAccount) {
            if (g.getMessage().equals(onlineCommand)) {
                //Only result online player numbers
                if (isOnlyResponsePlayerNumber) {
                    group.sendMessage(playerNumbersMessage + Bukkit.getOnlinePlayers().toArray(new Player[0]).length);
                }
                //result online player names
                else {
                    StringBuilder msg = new StringBuilder();
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("PlayerCount");
                    out.writeUTF("ALL");

                    if (msg.length()!=0) {
                        group.sendMessage(playerNameMessage.replace("%p%",msg));
                    } else {
                        group.sendMessage(nonePlayerOnServer);
                    }
                }
            } else if (g.getMessage().equals(getLRMCommand)) {
                String[] result = GET_new_version();
                String msg = LRMFormat.replace("%ver%", result[0]).replace("%type%", result[1]).replace("%rt%", result[2]);
                group.sendMessage(msg);
            } else if (Objects.equals(StringUtils.substring(g.getMessage(), 0, getSkinCommand.length()), getSkinCommand)) {
                String id = g.getMessage().substring(getSkinCommand.length());
                String uuid = me.heartalborada.QQbot.Utils.Player.GetPlayerUUID(id);
                if (uuid.equals("Error")) {
                    group.sendMessage(skinOnError);
                } else {
                    String msg = skinFormat
                            .replaceAll("%pn%", id)
                            .replaceAll("%uuid%", uuid)
                            .replaceAll("%hn%", GetPlayerUsedName(uuid))
                            .replaceAll("%sk%",GetPlayerTextures(uuid));
                    group.sendMessage(msg);
                }
            } else if(Objects.equals(g.getMessage(), helpCommand)){
                group.sendMessage(helpFormat
                        .replaceAll("%oc%",onlineCommand)
                        .replaceAll("%lrmc%",getLRMCommand)
                        .replaceAll("%sc%",getSkinCommand)
                        .replaceAll("%hc%",helpCommand));
            } else {
                TextComponent message=new TextComponent(QQMessageFormat
                                                            .replaceAll("%gn%",g.getGroupName())
                                                            .replaceAll("%gi%", String.valueOf(g.getGroupID()))
                                                            .replaceAll("%si%",String.valueOf(g.getSenderID()))
                                                            .replaceAll("%sn%",g.getSenderNameCard())
                                                            .replaceAll("%m%",g.getMessage()));
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("QQbot");
                out.writeUTF("Argument");
                Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
                player.sendPluginMessage(this,"BungeeCord", out.toByteArray());
            }
        }
    }
}
