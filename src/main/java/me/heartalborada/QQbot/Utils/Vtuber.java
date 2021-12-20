package me.heartalborada.QQbot.Utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static me.heartalborada.QQbot.Utils.Vtuber.tags.HasVtbTag;

public class Vtuber {
    public static void main(String[] arg) {
        System.out.println(HasVtbTag("https://www.bilibili.com/video/BV1q64y1k7VM?from=search&seid=16303840743111705974"));
    }

    public static class tags {
        private static String Get302Url(String url) throws IOException {
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);
            conn.addRequestProperty("Accept-Charset", "UTF-8;");
            conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36 Edg/92.0.902.55");
            conn.connect();
            String[] tmp = conn.getHeaderField("Location").split("\\?")[0].split("/");
            return tmp[tmp.length - 1];
        }

        private static String GetAidOrBid(String Url) {
            String[] tmp = Url.split("/");
            String ABid = tmp[tmp.length - 1];
            if (ABid.startsWith("av")) {
                return "aid=" + ABid.split("v")[1];
            } else {
                return "bvid=" + ABid.subSequence(2, ABid.length());
            }
        }

        public static boolean HasVtbTag(String Url) {
            Url = Url.split("\\?")[0];
            String tmp = Url;
            if (Url.startsWith("https://b23.tv/") && Url.split("https://b23.tv/")[0] == "") {
                try {
                    tmp = Get302Url(Url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String VideoTags = new Internet().doGet("https://api.bilibili.com/x/tag/archive/tags?" + GetAidOrBid(tmp)).toLowerCase();
            String[] PassTags = {"vtb", "vup", "vtuber", "虚拟偶像", "虚拟主播", "虚拟UP主"};
            for (int i = 0; i < PassTags.length; i++) {
                if (VideoTags.contains(PassTags[i])) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class url {
        public static List<String> extractUrls(String text) {
            List<String> containedUrls = new ArrayList<String>();
            String urlRegex = "((https?|http?):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
            Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
            Matcher urlMatcher = pattern.matcher(text);

            while (urlMatcher.find()) {
                containedUrls.add(text.substring(urlMatcher.start(0),
                        urlMatcher.end(0)));
            }

            return containedUrls;
        }
    }
}
