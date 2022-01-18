package me.heartalborada.QQbot.bungee;

import me.heartalborada.QQbot.bungeeMain;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import static jdk.jfr.internal.SecuritySupport.getResourceAsStream;
import static me.heartalborada.QQbot.Config.*;

public class yaml {
    private final bungeeMain plugin;

    public yaml(bungeeMain plugin){
        this.plugin =plugin;
        PluginDir=plugin.getDataFolder();
    }

    public void loadConfig(){
        Configuration configuration = null;
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(plugin.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            if (!plugin.getDataFolder().exists())
                plugin.getDataFolder().mkdir();
            File file = new File(plugin.getDataFolder(), "config.yml");
            if (!file.exists()) {
                try (InputStream in = getResourceAsStream("config.yml")) {
                    Files.copy(in, file.toPath());
                } catch (IOException ex) {
                    e.printStackTrace();
                }
            }
        }
        EnableBotAccount=configuration.getLong("bot.account",0);
        EnableGroup = configuration.getLongList("bot.enable-group").toArray(new Long[0]);
        onlineCommand=configuration.getString("commands.online",".online");
        getLRMCommand=configuration.getString("commands.latest-minecraft-version",".LMRV");
        getSkinCommand=configuration.getString("commands.online-player-skin",".skin");
        helpCommand=configuration.getString("commands.help",".help");
        isOnlyResponsePlayerNumber=configuration.getBoolean("settings.is-only-response-player-numbers",false);
        onJoinMessage=configuration.getString("messages.server.join","[Server] %p% join the game");
        onQuitMessage=configuration.getString("messages.server.quit","[Server] %p% quit the game");
        onChatMessage=configuration.getString("messages.server.chat","[Chat] %p%:%m%");
        QQMessageFormat=configuration.getString("messages.QQMessageToServer","[group:%gn%][sender:%sn%][message:%m%]");
        nonePlayerOnServer=configuration.getString("messages.online.no-player","No one player");
        playerNumbersMessage=configuration.getString("messages.online.only-response-player-number","Online player numbers: %n%");
        playerNameMessage=configuration.getString("messages.online.response-player-name","Online players: %p%");
        LRMFormat=configuration.getString("messages.latest-minecraft-version","[The Latest Release Version] Version:%ver%,type:%type%,release time:%rt%");
        skinFormat=configuration.getString("messages.skin.format","[Skin] Player Name:%pn%,skin:%sk%,history name:%hn%");
        skinOnError=configuration.getString("messages.skin.error","error");
        historyNameFormat=configuration.getString("messages.skin.history-name-format","Player name:%n%,usage Time:%t%\n");
        skinResponseFormat=configuration.getString("messages.skin.skin-response-format","skin type:%st%\nskin url:%s%\ncape url:%cp%");
        helpFormat=configuration.getString("messages.help","[Help] %oc%:get server online player\n%lrmc%:get minecraft latest version\n%sc%:get online player skin and cape\n%hc%:get commands help");
    }
}
