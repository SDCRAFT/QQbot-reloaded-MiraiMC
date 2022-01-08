package me.heartalborada.QQbot.Utils;

import com.alibaba.fastjson.JSONObject;

public class Version {
    public static String[] GET_new_version() {
        Internet.Url http = new Internet.Url();
        String data = http.doGet("http://launchermeta.mojang.com/mc/game/version_manifest.json");
        JSONObject snapshot = JSONObject.parseObject(data).getJSONArray("versions").getJSONObject(0);
        return new String[]{snapshot.getString("id"), snapshot.getString("type"), snapshot.getString("releaseTime")};
    }
}
