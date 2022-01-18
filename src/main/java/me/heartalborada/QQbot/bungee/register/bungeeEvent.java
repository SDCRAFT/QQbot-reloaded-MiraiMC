package me.heartalborada.QQbot.bungee.register;

import me.dreamvoid.miraimc.api.MiraiBot;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PlayerHandshakeEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import static me.heartalborada.QQbot.Config.*;
import static org.bukkit.Bukkit.getLogger;

public class bungeeEvent implements Listener {
    public bungeeEvent() {
        getLogger().info("Bukkit events are listening now");
    }

    @EventHandler
    public void onPlayerJoin(PlayerHandshakeEvent j) {
        for (long group : EnableGroup) {
            MiraiBot.getBot(EnableBotAccount).getGroup(group).sendMessage(onJoinMessage.replaceAll("%p%",j.getConnection().getName()));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerDisconnectEvent q) {
        for (long group : EnableGroup) {
            MiraiBot.getBot(EnableBotAccount).getGroup(group).sendMessage(onQuitMessage.replaceAll("%p%",q.getPlayer().getDisplayName()));
        }
    }

    /*@EventHandler
    public void onPlayerChat(ChatEvent c) {
        for (long group : EnableGroup) {
            MiraiBot.getBot(EnableBotAccount).getGroup(group).sendMessage(onChatMessage.replaceAll("%p%",c.getReceiver()..getDisplayName()).replaceAll("%m%",c.getMessage()));
        }
    }*/
}
