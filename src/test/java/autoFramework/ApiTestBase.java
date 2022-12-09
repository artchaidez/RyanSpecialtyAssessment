package autoFramework;

import com.google.gson.Gson;

import com.google.gson.Gson;
import org.apache.http.HttpStatus;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiTestBase extends AutoLogger {

    Gson gson = new Gson();

    // TODO: handle different Status Codes
    /** Returns the class of a response body*/
    public HttpResponse<String> Post(Object parms, String resource, String apiKey) throws Exception {

        HttpResponse<String> response = null;

        String jsonRequest = gson.toJson(parms);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(resource))
                .header("x-api-key", apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        try
        {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch (Exception e)
        {
            // TODO: should be using Info or something else?
            Info("Something went wrong with the POST.");
            throw new Exception("Caught Exception", e);
        }

        apiLog(response, jsonRequest);

        return response;
    }

    /** Returns the int of a status code*/
    public int PostInt(String parms, String resource, String apiKey) throws Exception {

        int response;

        String jsonRequest = gson.toJson(parms);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(resource))
                .header("x-api-key", apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        try
        {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).statusCode();
        }
        catch (Exception e)
        {
            // TODO: should be using Info or something else?
            Info("Something went wrong with the POST.");
            throw new Exception("Caught Exception", e);
        }

        return response;
    }
}
