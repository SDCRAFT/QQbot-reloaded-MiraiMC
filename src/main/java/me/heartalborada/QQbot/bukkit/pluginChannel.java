package me.heartalborada.QQbot.bukkit;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import static me.heartalborada.QQbot.Config.nonePlayerOnServer;
import static me.heartalborada.QQbot.Config.playerNameMessage;
import static org.bukkit.Bukkit.getOnlinePlayers;

public class pluginChannel implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if(!channel.equals("BungeeCord")){
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if(subchannel.equals("QQBot")){
            String a=in.readUTF();
            if(a.equals("PlayerCount")){
                boolean b=in.readBoolean();
                if(b){
                    Player[] players = getOnlinePlayers().toArray(new Player[0]);
                    player.sendPluginMessage();
                }
            }
        }
    }
}
