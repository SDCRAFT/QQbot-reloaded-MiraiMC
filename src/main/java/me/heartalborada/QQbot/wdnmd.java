package me.heartalborada.QQbot;

import java.util.Date;
import static me.heartalborada.QQbot.Utils.Player.GetPlayerTextures;
import static me.heartalborada.QQbot.Utils.Player.GetPlayerUsedName;
import static me.heartalborada.QQbot.Utils.Version.GET_new_version;

public class wdnmd {
    public static void main(String[] arg) {
        String[] result = GET_new_version();
        for(String a:result){
            System.out.println(a);
        }

        //System.out.println(GetPlayerTextures("7ba1a4aa4c824384947d75c4d03ce6f2"));
    }
}
