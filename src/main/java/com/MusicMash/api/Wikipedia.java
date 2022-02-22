package com.MusicMash.api;

import java.net.*;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Wikipedia {
    private static final String URL = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&%20exintro=true&redirects=true&titles=";

    public static String queryDescription(String url) {
        URI uri;
        String response = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            uri = new URI(URL+url);
            response = restTemplate.getForObject(uri, String.class);
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return parseDescription(response);
    }

    private static String parseDescription(String response) {
        JSONObject curr = new JSONObject(response);
        JSONObject query = curr.getJSONObject("query");
        JSONObject pages = query.getJSONObject("pages");
        Iterator<String> keys = pages.keys();
        String key = keys.next();
        JSONObject description = pages.getJSONObject(key);
        String result = description.getString("extract");
        Pattern pattern = Pattern.compile("<p\\sclass=\"mw-empty-elt\">.*\n*</p>",Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) {
            result = result.replace(matcher.group(0),"");
        }
        result = result.replace("\\","").replace("\\n","");
        return result;
    }
}
