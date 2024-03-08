package server_connection;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Models.Ranking;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Api implements ConfigApi {
    private final OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private MediaType MEDIA_TYPE_JSON = MediaType.get("application/json; charset=utf-8");

    public Api() {

    }

    public List<Ranking> getRanks() throws IOException {
        Request request = new Request.Builder().url(URL_PATH + RANKS).build();
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            System.out.println(responseBody);
            if (response.body() == null)
                throw new IOException("Response body is null");
            Type listType = new TypeToken<List<Ranking>>() {
            }.getType();

            return gson.fromJson(responseBody, listType);
        }

    }

    public void postRanks(Ranking ranking) {
        if (ranking == null) {
            return;
        }
        String json = gson.toJson(ranking);
        RequestBody body = RequestBody.create(json, MEDIA_TYPE_JSON);

        // Build the request
        Request request = new Request.Builder()
                .url(URL_PATH + POST_RANKS) // Replace with your actual URL
                .post(body)
                .build();

        // Execute the request
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);

            // Handle response
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
