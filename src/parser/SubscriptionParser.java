package parser;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * Esta clase implementa el parser del  archivo de suscripcion (json)
 * Leer https://www.w3docs.com/snippets/java/how-to-parse-json-in-java.html
 * */

public abstract class SubscriptionParser extends GeneralParser {

    public JSONObject parseJson(String filePath) throws IOException {
        String jsonStr = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jsonObj = new JSONObject(jsonStr);
        return jsonObj;
    }


    private JSONArray getFilteredUrls(JSONObject json, String filterUrlType) {
        JSONArray filteredUrls = new JSONArray();

        for (int i = 0; i < json.length(); i++) {
            JSONObject subscription = json.getJSONObject("subscriptions");
            String urlType = subscription.getString("urlType");

            if (urlType.equals(filterUrlType)) {
                JSONArray urlParams = subscription.getJSONArray("urlParams");
                String url = subscription.getString("url");

                // Agregamos los endpoints a las urls base.
                for (int j = 0; j < urlParams.length(); j++) {
                    String param = urlParams.getString(j);
                    filteredUrls.put(url.replace("%s", param));
                }
            }
        }
        return filteredUrls;
    }

    @Override
    public JSONObject makeResponse(JSONObject jsonObj){
        // Accedemos a los valores de nuestro jsonObj
        JSONArray rssUrls = getFilteredUrls(jsonObj, "rss");
        JSONArray redditUrls = getFilteredUrls(jsonObj, "reddit");
    
        JSONObject json = new JSONObject();
        json.put("rss", rssUrls);
        json.put("reddit", redditUrls);
    
        System.out.println(json.toString());
        return json;
    }


    @Override
    public JSONObject parseFile(String filePath) throws IOException {
       // Obtenemos el path a nuestro json
       filePath = "../config/subscriptions.json";

       // Obtenemos el json
       JSONObject jsonObj = parseJson(filePath);

       // Obtenemos la response
       return makeResponse(jsonObj);
    }
}
