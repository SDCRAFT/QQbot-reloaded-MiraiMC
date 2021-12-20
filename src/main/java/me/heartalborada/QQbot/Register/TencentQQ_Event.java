package me.heartalborada.QQbot.Register;

import me.dreamvoid.miraimc.api.MiraiBot;
import me.dreamvoid.miraimc.api.bot.MiraiGroup;
import me.dreamvoid.miraimc.bukkit.event.MiraiGroupMessageEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;

import static me.heartalborada.QQbot.Main.plugin_name;

import static me.heartalborada.QQbot.Config.*;
import static me.heartalborada.QQbot.Utils.Player.*;
import static me.heartalborada.QQbot.Utils.Version.GET_new_version;
import static org.bukkit.Bukkit.*;

public class TencentQQ_Event implements Listener {
    public TencentQQ_Event() {
        getLogger().info(plugin_name + "Tencent QQ events are listening now");
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
        System.out.println("\""+onlineCommand+"\""+g.getMessage()+"\"");
        if(!flag)
            return;
        if (g.getBotID() == EnableBotAccount) {
            if (g.getMessage().equals(onlineCommand)) {
                //Only result online player numbers
                if (isOnlyResponsePlayerNumber) {
                    group.sendMessage(playerNumbersMessage.replaceAll("%n%", String.valueOf(Bukkit.getOnlinePlayers().size())));
                }
                //result online player names
                else {
                    StringBuilder msg = new StringBuilder();
                    Player[] players = getOnlinePlayers().toArray(new Player[0]);
                    for (int i = 0; i < players.length; i++) {
                        System.out.println(players[i].getDisplayName());
                        if (i == players.length - 1) {
                            msg.append(players[i].getDisplayName());
                            break;
                        }
                        msg.append(players[i].getDisplayName()).append(",");
                    }
                    if (msg.length()!=0) {
                        group.sendMessage(playerNameMessage.replace("%p%",msg));
                    } else {
                        group.sendMessage(nonePlayerOnServer);
                    }
                }
            } else if (g.getMessage().equals(getLRMCommand)) {
                String[] result = GET_new_version();
                String msg = LRMFormat
                        .replace("%ver%", result[0])
                        .replace("%type%", result[1])
                        .replace("%rt%", result[2]);
                group.sendMessage(msg);
            } else if (Objects.equals(StringUtils.substring(g.getMessage(), 0, getSkinCommand.length()), getSkinCommand)) {
                String id = g.getMessage().substring(getSkinCommand.length());
                String uuid = GetPlayerUUID(id);
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
                Bukkit.broadcastMessage(QQMessageFormat
                        .replaceAll("%gn%",g.getGroupName())
                        .replaceAll("%gi%", String.valueOf(g.getGroupID()))
                        .replaceAll("%si%",String.valueOf(g.getSenderID()))
                        .replaceAll("%sn%",g.getSenderNameCard())
                        .replaceAll("%m%",g.getMessage()));
            }
        }
    }
}
