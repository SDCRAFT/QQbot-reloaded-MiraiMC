package me.heartalborada.QQbot.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Json {
    public static void main(String[] args) {
        String jsonString = Music.Get_MusicID("Moon Halo");
        JSONObject song = JSONObject.parseObject(jsonString).getJSONObject("result").getJSONArray("songs").getJSONObject(0);
        String artistsName = song.getJSONArray("artists").getJSONObject(0).getString("name");
        String musicName = song.getString("name");
        String musicID = song.getString("id");

        String pretty = JSON.toJSONString(song, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        //String pretty = JSON.toJSONString(songs.getJSONObject(1), SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(pretty);
    }
}
