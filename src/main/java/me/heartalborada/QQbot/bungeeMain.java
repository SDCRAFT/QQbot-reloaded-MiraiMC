package me.heartalborada.QQbot;

import me.dreamvoid.miraimc.api.MiraiBot;
import me.heartalborada.QQbot.bungee.register.bungeeEvent;
import me.heartalborada.QQbot.bungee.register.tencentQQEvent;
import net.md_5.bungee.api.plugin.Plugin;
import me.heartalborada.QQbot.bungee.yaml;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static me.heartalborada.QQbot.Config.EnableBotAccount;

public class bungeeMain extends Plugin {
    public Logger logger = this.getLogger();

    private void disable() {
        try {
            this.onDisable();
            for (Handler handler : this.getLogger().getHandlers()) {
                handler.close();
            }
        } catch (Throwable t) {
            getLogger().log(Level.SEVERE, "Exception disabling plugin " + this.getDescription().getName(), t);
        }
        this.getProxy().getScheduler().cancel(this);
        this.getExecutorService().shutdownNow();
        this.getProxy().getPluginManager().unregisterCommands(this);
        this.getProxy().getPluginManager().unregisterCommands(this);
    }

    @Override // load plugin
    public void onLoad() {
        new yaml(this).loadConfig();
        if(EnableBotAccount==null){
            disable();
        }
        if (!(MiraiBot.getBot(EnableBotAccount).isOnline()||MiraiBot.getBot(EnableBotAccount).isExist())) {
            logger.warning("The bot account is offline or doesn't exist,please check it.");
            disable();
        }
    }

    @Override // enable plugin
    public void onEnable() {
        logger.info("Register Bukkit Events");
        this.getProxy().getPluginManager().registerListener(this,new bungeeEvent());
        logger.info("Register Tencent QQ Events");
        this.getProxy().getPluginManager().registerListener(this,new tencentQQEvent());
    }

    @Override // disable plugin
    public void onDisable() {
        logger.info("Disabling.");
        logger.info("Thanks for your use this plugin.");
    }
}
