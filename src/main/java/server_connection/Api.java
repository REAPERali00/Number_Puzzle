package server_connection;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Api implements ConfigApi {
    private URL url;
    private HttpURLConnection connection;
    private BufferedInputStream in;

    public Api() {

    }

    public boolean connectUrl() {
        try {
            url = new URL(URL_PATH);
            connection = (HttpURLConnection) url.openConnection();
            return true;
        } catch (IOException e) {
            System.out.println("Couldn't connect to the url");
            e.printStackTrace();
            return false;
        }
    }
}
