package me.heartalborada.QQbot;

import me.dreamvoid.miraimc.api.MiraiBot;
import me.heartalborada.QQbot.bukkit.register.bukkitEvent;
import me.heartalborada.QQbot.bukkit.register.tencentQQEvent;
import org.bukkit.plugin.java.JavaPlugin;
import me.heartalborada.QQbot.bukkit.yaml;
import me.heartalborada.QQbot.bukkit.pluginChannel;

import java.util.logging.Logger;

import static me.heartalborada.QQbot.Config.EnableBotAccount;
import static me.heartalborada.QQbot.Config.bungeeCord_support;

public class bukkitMain extends JavaPlugin {
    public Logger logger = this.getLogger();

    @Override // load plugin
    public void onLoad() {
        new yaml(this).loadConfig();
        if(!bungeeCord_support) {
            if (EnableBotAccount == null) {
                this.getServer().getPluginManager().disablePlugin(this);
            }
            if (!(MiraiBot.getBot(EnableBotAccount).isOnline() || MiraiBot.getBot(EnableBotAccount).isExist())) {
                logger.warning("The bot account is offline or doesn't exist,please check it.");
                this.getServer().getPluginManager().disablePlugin(this);
            }
        } else {
            this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
            this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord",new pluginChannel());
        }
    }

    @Override // enable plugin
    public void onEnable() {
        logger.info("Register Bukkit Events");
        this.getServer().getPluginManager().registerEvents(new bukkitEvent(), this);
        if(!bungeeCord_support) {
            logger.info("Register Tencent QQ Events");
            this.getServer().getPluginManager().registerEvents(new tencentQQEvent(), this);
        }
    }

    @Override // disable plugin
    public void onDisable() {
        logger.info("Disabling.");
        logger.info("Thanks for your use this plugin.");
    }
}
