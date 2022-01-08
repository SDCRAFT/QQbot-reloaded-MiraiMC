package me.heartalborada.QQbot.Utils;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import static me.heartalborada.QQbot.Config.historyNameFormat;
import static me.heartalborada.QQbot.Config.skinResponseFormat;

public class Player {
    public static String GetPlayerUUID(String name) {
        Internet.Url http = new Internet.Url();
        String data = http.doGet("https://api.mojang.com/users/profiles/minecraft/" + name);
        if (data.contains("error") || data.equals("")) {
            return "Error";
        }
        return JSONObject.parseObject(data).getString("id");
    }

    public static String GetPlayerTextures(String Player_UUID){
        JSONObject json = JSONObject.parseObject(GetPlayerTexturesJson(Player_UUID)).getJSONObject("textures");
        String skinUrl= json.getJSONObject("SKIN").getString("url");
        String skinType=json.getJSONObject("SKIN").getJSONObject("metadata").getString("model");
        String capeUrl="None";
        if(json.containsKey("CAPE")){
            capeUrl=json.getJSONObject("CAPE").getString("url");
        }
        return skinResponseFormat.replaceAll("%s%",skinUrl).replaceAll("%st%",skinType).replaceAll("%cp%",capeUrl);
    }
    public static String GetPlayerUsedName(String Player_UUID) {
        Internet.Url http = new Internet.Url();
        String data= "{\"names\":"+http.doGet("https://api.mojang.com/user/profiles/"+Player_UUID+"/names")+"}";
        Object[] arr= JSONObject.parseObject(data).getJSONArray("names").toArray();
        System.out.println(data);
        StringBuilder allOfUsedName = new StringBuilder();
        for(int i=arr.length-1;i>=0;i--){
            if(!JSONObject.parseObject(String.valueOf(arr[i])).containsKey("changedToAt")&&arr.length==1){
                allOfUsedName.append(
                        historyNameFormat
                                .replace("%n%",JSONObject.parseObject(String.valueOf(arr[i])).getString("name"))
                                .replace("%t%",buildUsageName(0,0))
                );
                continue;
            }
            else if(i==arr.length-1){
                allOfUsedName.append(
                        //int tmp =JSONObject.parseObject(String.valueOf(arr[i])).containsKey("") != null ? allOfUsedName.toString() : "None";
                        historyNameFormat
                                .replace("%n%",JSONObject.parseObject(String.valueOf(arr[i])).getString("name"))
                                .replace("%t%",buildUsageName(0,JSONObject.parseObject(String.valueOf(arr[i])).getLong("changedToAt")))
                );
                continue;
            } else  if(i==0){
                allOfUsedName.append(
                        historyNameFormat
                                .replace("%n%",JSONObject.parseObject(String.valueOf(arr[i])).getString("name"))
                                .replace("%t%",buildUsageName(JSONObject.parseObject(String.valueOf(arr[i+1])).getLong("changedToAt"),0))
                );
                continue;
            }
            allOfUsedName.append(
                    historyNameFormat
                            .replace("%n%",JSONObject.parseObject(String.valueOf(arr[i])).getString("name"))
                            .replace("%t%",buildUsageName(JSONObject.parseObject(String.valueOf(arr[i])).getLong("changedToAt"),JSONObject.parseObject(String.valueOf(arr[i+1])).getLong("changedToAt")))
            );
        }
        return allOfUsedName != null ? allOfUsedName.toString() : "None";
    }

    private static String buildUsageName(long ct1,long ct2){
        if(ct1==ct2&&ct1==0) {
            return "Now - Registration";
        }else if(ct1==0){
            return "Now - "+Time.timestampToDate(ct2/1000);
        } else if(ct2==0){
            return Time.timestampToDate(ct1/1000) +" - Registration";
        }
        return Time.timestampToDate(ct1/1000)+"-"+Time.timestampToDate(ct2/1000);
    }

    private static String GetPlayerTexturesJson(String Player_UUID) {
        Internet.Url http = new Internet.Url();
        String json="Error";
        try {
            json=new me.heartalborada.QQbot.Utils.Internet().Eget("https://sessionserver.mojang.com/session/minecraft/profile/"+Player_UUID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String baseCode = JSONObject.parseObject(json)
                .getJSONArray("properties")
                .getJSONObject(0)
                .getString("value");
        String s = null;
        try {
            s = new String(Base64.getDecoder().decode(baseCode), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }
}
