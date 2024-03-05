package server_connection;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.*;

public class Api implements ConfigApi {
    private final OkHttpClient client = new OkHttpClient();

    public Api() {

    }

    public String getRanks() throws IOException {
        Request request = new Request.Builder().url(URL_PATH + RANKS).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
