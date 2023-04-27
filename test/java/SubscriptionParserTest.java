

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import parser.SubscriptionParser;

public class SubscriptionParserTest {
    SubscriptionParser subscriptionParser = new SubscriptionParser();

    @Test
    public void testParseJson() throws IOException {
        String filePath = "test/resources/subscriptions.json";
        JSONObject expectedJsonObj = new JSONObject("{\"subscriptions\":{\"rss\":{\"url\":\"https://example.com/rss/%s\",\"urlParams\":[\"news\",\"sports\"]},\"reddit\":{\"url\":\"https://www.reddit.com/r/%s.json\",\"urlParams\":[\"programming\",\"science\"]}}}");
        JSONObject actualJsonObj = subscriptionParser.parseJson(filePath);
        assertEquals(expectedJsonObj.toString(), actualJsonObj.toString());
    }

    @Test
    public void testGetFilteredUrls() {
        JSONObject jsonObj = new JSONObject("{\"subscriptions\":{\"rss\":{\"url\":\"https://example.com/rss/%s\",\"urlParams\":[\"news\",\"sports\"]},\"reddit\":{\"url\":\"https://www.reddit.com/r/%s.json\",\"urlParams\":[\"programming\",\"science\"]}}}");
        JSONArray expectedRssUrls = new JSONArray();
        expectedRssUrls.put("https://example.com/rss/news");
        expectedRssUrls.put("https://example.com/rss/sports");
        JSONArray actualRssUrls = subscriptionParser.getFilteredUrls(jsonObj, "rss");
        assertEquals(expectedRssUrls.toString(), actualRssUrls.toString());
        JSONArray expectedRedditUrls = new JSONArray();
        expectedRedditUrls.put("https://www.reddit.com/r/programming.json");
        expectedRedditUrls.put("https://www.reddit.com/r/science.json");
        JSONArray actualRedditUrls = subscriptionParser.getFilteredUrls(jsonObj, "reddit");
        assertEquals(expectedRedditUrls.toString(), actualRedditUrls.toString());
    }

    @Test
    public void testParseFile() throws IOException {
        String filePath = "test/resources/subscriptions.json";
        JSONObject expectedJsonObj = new JSONObject();
        expectedJsonObj.put("rss", subscriptionParser.getFilteredUrls(subscriptionParser.parseJson(filePath), "rss"));
        expectedJsonObj.put("reddit", subscriptionParser.getFilteredUrls(subscriptionParser.parseJson(filePath), "reddit"));
        JSONObject actualJsonObj = subscriptionParser.parseFile(filePath);
        assertEquals(expectedJsonObj.toString(), actualJsonObj.toString());
    }
    public static void main(String[] args){
        SubscriptionParserTest test = new SubscriptionParserTest();
        try {
            test.testParseJson();
            test.testGetFilteredUrls();
            test.testParseFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (AssertionError e){
            e.printStackTrace();
        }
    }
}