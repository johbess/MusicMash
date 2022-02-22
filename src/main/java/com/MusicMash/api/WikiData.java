package com.MusicMash.api;

import com.fasterxml.jackson.core.type.TypeReference;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;

public class WikiData {
    private static final String URL = "https://www.wikidata.org/w/api.php";

    public static String queryWikiUrl(String wikiToken, RestTemplate restTemplate) throws MalformedURLException {
        String response = restTemplate.getForObject(URL+"?action=wbgetentities&ids="+wikiToken+"&format=json&props=sitelinks",String.class);
        return parseUrl(response,wikiToken);
    }

    private static String parseUrl(String response, String wikiToken) throws MalformedURLException {
        JSONObject tmp = new JSONObject(response);
        JSONObject entities = tmp.getJSONObject("entities");
        JSONObject token = entities.getJSONObject(wikiToken);
        JSONObject sitelinks = token.getJSONObject("sitelinks");
        JSONObject enwiki = sitelinks.getJSONObject("enwiki");
        String url = enwiki.getString("title");
        return url.replace(" ","_");
    }
}
