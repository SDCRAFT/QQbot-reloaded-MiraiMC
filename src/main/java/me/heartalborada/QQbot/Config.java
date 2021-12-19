package me.heartalborada.QQbot;

import java.io.File;

public class Config {
    public static File PluginDir;
    //Commands
    public static String onlineCommand;
    public static String getLRMCommand; //LatestReleaseMinecraft
    public static String getSkinCommand; //Skin
    public static String helpCommand;
    //QQ Message to minecraft server
    public static String QQMessageFormat;
        //[group:%gn%][sender:%sn%][message:%m%]
        //%gn%:groupName %gi%:groupID
        //%sn%:senderName %si%:senderID
        //%m%:message
    //Command help
    public static String helpFormat;
        //Example [Help] %oc%:get server online player\n
        //%lrmc%:get minecraft latest version\n
        //%sc%:get online player skin and cape\n
        //%hc%:get commands help
    //Latest Minecraft Version
    public static String LRMFormat; //Example [The Latest Release Version] Version:%ver%,type:%type%,release time%rt%;
    //skin
    public static String skinFormat; //Example [Skin] Player Name:%pn%,skin:%sk%,history name:%hn%
    public static String skinOnError;
    public static String historyNameFormat; //Example Player name:%n%,usage Time:%t%
    public static String skinResponseFormat;  //Example skin type:%st%\nskin url:%s%\ncape url:%cp%
    //Enable Account&Group
    public static Long EnableBotAccount;
    public static Long[] EnableGroup;
    //Online
    public static boolean isOnlyResponsePlayerNumber;
    public static String playerNumbersMessage;
    public static String nonePlayerOnServer;
    public static String playerNameMessage;
    //Bukkit Event Messages
    public static String onJoinMessage;
    public static String onQuitMessage;
    public static String onChatMessage;
}
