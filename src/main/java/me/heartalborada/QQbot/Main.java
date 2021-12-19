package me.heartalborada.QQbot;

import me.dreamvoid.miraimc.api.MiraiBot;
import me.heartalborada.QQbot.Register.Bukkit_Event;
import me.heartalborada.QQbot.Register.TencentQQ_Event;
import org.bukkit.plugin.java.JavaPlugin;
import me.heartalborada.QQbot.Utils.yaml;

import java.util.logging.Logger;

import static me.heartalborada.QQbot.Config.EnableBotAccount;

public class Main extends JavaPlugin {
    public static String plugin_name = "[QQBot] ";
    public Logger logger = this.getLogger();

    @Override // load plugin
    public void onLoad() {

    }

    @Override // enable plugin
    public void onEnable() {
        new yaml(this).loadConfig();
        if (!MiraiBot.getBot(EnableBotAccount).isOnline()) {
            logger.warning(plugin_name + "The bot account is offline,please check it.");
            this.getServer().getPluginManager().disablePlugin(this);
        }
        logger.info(plugin_name + "Register Bukkit Events");
        this.getServer().getPluginManager().registerEvents(new Bukkit_Event(), this);
        logger.info(plugin_name + "Register Tencent QQ Events");
        this.getServer().getPluginManager().registerEvents(new TencentQQ_Event(), this);
    }

    @Override // disable plugin
    public void onDisable() {
        logger.info(plugin_name + "Disabling.");
        logger.info(plugin_name + "Thanks for your use this plugin.");
    }
}
