package server_connection;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Models.Ranking;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Api implements ConfigApi {
    private final OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();

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
            return gson.fromJson(response.body().string(), listType);
        }
    }
}
