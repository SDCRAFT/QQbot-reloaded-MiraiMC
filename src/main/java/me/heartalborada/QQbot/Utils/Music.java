package me.heartalborada.QQbot.Utils;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Music {
    public static String[] AllOfMusicInfo(String Name) {
        String jsonString = Music.Get_MusicID(Name);
        JSONObject song = JSONObject.parseObject(jsonString).getJSONObject("result").getJSONArray("songs").getJSONObject(0);
        String artistsName = song.getJSONArray("artists").getJSONObject(0).getString("name");
        String musicName = song.getString("name");
        String musicID = song.getString("id");
        String musicPic = song.getJSONObject("album").getString("blurPicUrl");
        return new String[]{artistsName, musicName, musicID, musicPic};
    }

    private static String Get_MusicID(String name) {
        try {
            name = URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new Internet().doGet("http://music.163.com/api/search/pc?s=" + name + "&type=1");
    }
}
