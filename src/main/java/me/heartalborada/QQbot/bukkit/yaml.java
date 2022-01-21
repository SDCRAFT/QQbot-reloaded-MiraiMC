package me.heartalborada.QQbot.bukkit;

import me.heartalborada.QQbot.bukkitMain;

import static me.heartalborada.QQbot.Config.*;
public class yaml {
    private final bukkitMain plugin;
     
    public yaml(bukkitMain plugin){
        this.plugin =plugin;
        PluginDir=plugin.getDataFolder();
    }

    public void loadConfig(){
        this.plugin.saveDefaultConfig();
        bungeecord=this.plugin.getConfig().getBoolean("bunggcord-support");
        EnableBotAccount=this.plugin.getConfig().getLong("bot.account",0);
        EnableGroup = this.plugin.getConfig().getLongList("bot.enable-group").toArray(new Long[0]);
        onlineCommand=this.plugin.getConfig().getString("commands.online",".online");
        getLRMCommand=this.plugin.getConfig().getString("commands.latest-minecraft-version",".LMRV");
        getSkinCommand=this.plugin.getConfig().getString("commands.online-player-skin",".skin");
        helpCommand=this.plugin.getConfig().getString("commands.help",".help");
        isOnlyResponsePlayerNumber=this.plugin.getConfig().getBoolean("settings.is-only-response-player-numbers",false);
        onJoinMessage=this.plugin.getConfig().getString("messages.server.join","[Server] %p% join the game");
        onQuitMessage=this.plugin.getConfig().getString("messages.server.quit","[Server] %p% quit the game");
        onChatMessage=this.plugin.getConfig().getString("messages.server.chat","[Chat] %p%:%m%");
        QQMessageFormat=this.plugin.getConfig().getString("messages.QQMessageToServer","[group:%gn%][sender:%sn%][message:%m%]");
        nonePlayerOnServer=this.plugin.getConfig().getString("messages.online.no-player","No one player");
        playerNumbersMessage=this.plugin.getConfig().getString("messages.online.only-response-player-number","Online player numbers: %n%");
        playerNameMessage=this.plugin.getConfig().getString("messages.online.response-player-name","Online players: %p%");
        LRMFormat=this.plugin.getConfig().getString("messages.latest-minecraft-version","[The Latest Release Version] Version:%ver%,type:%type%,release time:%rt%");
        skinFormat=this.plugin.getConfig().getString("messages.skin.format","[Skin] Player Name:%pn%,skin:%sk%,history name:%hn%");
        skinOnError=this.plugin.getConfig().getString("messages.skin.error","error");
        historyNameFormat=this.plugin.getConfig().getString("messages.skin.history-name-format","Player name:%n%,usage Time:%t%\n");
        skinResponseFormat=this.plugin.getConfig().getString("messages.skin.skin-response-format","skin type:%st%\nskin url:%s%\ncape url:%cp%");
        helpFormat=this.plugin.getConfig().getString("messages.help","[Help] %oc%:get server online player\n%lrmc%:get minecraft latest version\n%sc%:get online player skin and cape\n%hc%:get commands help");
    }
}
