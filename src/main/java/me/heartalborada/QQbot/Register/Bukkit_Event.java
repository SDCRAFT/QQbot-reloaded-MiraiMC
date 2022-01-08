package me.heartalborada.QQbot.Register;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.dreamvoid.miraimc.api.MiraiBot;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.heartalborada.QQbot.Config.*;
import static org.bukkit.Bukkit.getLogger;

public class Bukkit_Event implements Listener {
    public Bukkit_Event() {
        getLogger().info("Bukkit events are listening now");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent j) {
        for (long group : EnableGroup) {
            MiraiBot.getBot(EnableBotAccount).getGroup(group).sendMessage(onJoinMessage.replaceAll("%p%",j.getPlayer().getDisplayName()));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent q) {
        for (long group : EnableGroup) {
            MiraiBot.getBot(EnableBotAccount).getGroup(group).sendMessage(onQuitMessage.replaceAll("%p%",q.getPlayer().getDisplayName()));
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent c) {
        for (long group : EnableGroup) {
            MiraiBot.getBot(EnableBotAccount).getGroup(group).sendMessage(onChatMessage.replaceAll("%p%",c.getPlayer().getDisplayName()).replaceAll("%m%",c.getMessage()));
        }
    }
}
