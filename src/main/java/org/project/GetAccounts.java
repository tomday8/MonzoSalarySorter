package org.project;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetAccounts {
    public static void logError(String message, int response, Exception e) {
        System.err.println(
                "ERROR: "
                        + message +
                        " Status code: "
                        + response
        );
        e.printStackTrace(System.err);
    }

    public static void getAccounts(
            String monzoApiUrl,
            String accessToken
    ) {
        String url = monzoApiUrl + "/accounts";
        Map<String, String> headers = new HashMap<>();
        headers.put(
                "Authorization", "Bearer " + accessToken
        );
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .build();

        int responseCode = -1;

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                responseCode = response.code();
            }
            assert response.body() != null;
            String jsonString = response.body().string();
            System.out.println(jsonString);
        } catch (IOException e) {
            logError("Failed to get account: Status code ", responseCode, e);
        }
    }
}
