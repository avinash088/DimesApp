package yelp;

/*
 Example code based on code from Nicholas Smith at http://imnes.blogspot.com/2011/01/how-to-use-yelp-v2-from-java-including.html
 For a more complete example (how to integrate with GSON, etc) see the blog post above.
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 * Example for accessing the Yelp API.
 */
public class Yelp {

    OAuthService service;
    Token accessToken;

    /**
     * Setup the Yelp API OAuth credentials.
     *
     * OAuth credentials are available from the developer site, under Manage API
     * access (version 2 API).
     *
     * @param consumerKey Consumer key
     * @param consumerSecret Consumer secret
     * @param token Token
     * @param tokenSecret Token secret
     */
    public Yelp(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        this.service = new ServiceBuilder().provider(YelpApi2.class).apiKey(consumerKey).apiSecret(consumerSecret).build();
        this.accessToken = new Token(token, tokenSecret);
    }

    /**
     * Search with term and location.
     *
     * @param term Search term
     * @param latitude Latitude
     * @param longitude Longitude
     * @return JSON string response
     */
//  public String search(String term, double latitude, double longitude) {
//    OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
//    request.addQuerystringParameter("term", term);
//    request.addQuerystringParameter("ll", latitude + "," + longitude);
//    this.service.signRequest(this.accessToken, request);
//    Response response = request.send();
//    return response.getBody();
//  }
    public String search(String term, String limit, double latitude, double longitude) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("limit", limit);
        request.addQuerystringParameter("ll", latitude + "," + longitude);
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

    public String searchBar(String term, Number sort, double latitude, double longitude) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("sort", sort.toString());
        request.addQuerystringParameter("ll", latitude + "," + longitude);
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

    public String place(String term, String location, String limit) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("location", location);
        request.addQuerystringParameter("limit", limit);
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

    public String place(String location, String limit) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
        request.addQuerystringParameter("location", location);
        request.addQuerystringParameter("limit", limit);
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

    public String location(String location) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
        request.addQuerystringParameter("location", location);
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

    public String tempId(String id) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
        request.addQuerystringParameter("id", id.toString());
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

    public String loc(String id) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/business/" + id);
        request.addQuerystringParameter("id", id);
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

    public String near(double latitude, double longitude, String distance, String id) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/business/");
        request.addQuerystringParameter("ll", latitude + "," + longitude);
        request.addQuerystringParameter("id", id);
        request.addQuerystringParameter("distance", distance);
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }
}
