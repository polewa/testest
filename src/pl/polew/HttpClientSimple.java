package pl.polew;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HttpClientSimple {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(URI.create("https://pluralsight.com"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.headers().map());

        CompletableFuture<HttpResponse<String>> resFuture =
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        resFuture.thenAccept(res -> System.out.println(response.version()));
        resFuture.join();
    }
}
