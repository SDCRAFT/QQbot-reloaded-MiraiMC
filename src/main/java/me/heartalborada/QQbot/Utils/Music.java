package me.heartalborada.QQbot.Utils;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Music {
    public static String Get_MusicID(String name) {
        try {
            name = URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Internet.Url http = new Internet.Url();
        return http.doGet("http://music.163.com/api/search/pc?s=" + name + "&type=1");
    }

    public static String[] AllOfMusicInfo(String Name) {
        String jsonString = Music.Get_MusicID(Name);
        JSONObject song = JSONObject.parseObject(jsonString).getJSONObject("result").getJSONArray("songs").getJSONObject(0);
        String artistsName = song.getJSONArray("artists").getJSONObject(0).getString("name");
        String musicName = song.getString("name");
        String musicID = song.getString("id");
        String musicPic = song.getJSONObject("album").getString("blurPicUrl");
        return new String[]{artistsName, musicName, musicID, musicPic};
    }
    /*public String Get_Jump_Url(String id) {
        String location =null;
        try {
            String url = "https://music.163.com/song/media/outer/url?id=" + id + ".mp3";
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestMethod("GET");
            // 必须设置false，否则会自动redirect到Location的地址
            conn.setInstanceFollowRedirects(false);
            conn.addRequestProperty("Accept-Charset", "UTF-8;");
            conn.addRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
            conn.addRequestProperty("Referer", "https://music.163.com/song/media/outer/url");
            conn.connect();
            location = conn.getHeaderField("Location");
            serverUrl = new URL(location);
            conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.addRequestProperty("Accept-Charset", "UTF-8;");
            conn.addRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
            conn.addRequestProperty("Referer", "https://music.163.com/song/media/outer/url");
            conn.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }*/

}
