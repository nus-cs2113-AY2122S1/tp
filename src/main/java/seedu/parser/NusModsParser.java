package seedu.parser;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import seedu.module.Mod;
import seedu.ui.TextUi;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class NusModsParser {

    public static void parseSearch(String searchTerm) {
        try {
            search(searchTerm);
        } catch (IOException | InterruptedException e) {
            TextUi.printErrorMessage();
        }
    }

    private static void search(String searchTerm) throws IOException, InterruptedException {
        // create a client
        var client = HttpClient.newHttpClient();

        // create a request
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.nusmods.com/v2/2021-2022/moduleList.json"))
                .header("accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        searchMods(response.body().getBytes(), searchTerm);
    }

    private static void searchMods(byte[] response, String searchTerm) {
        try (
                InputStream inputStream = new ByteArrayInputStream(response);
                JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
        ) {
            reader.beginArray();
            while (reader.hasNext()) {
                Mod mod = new Gson().fromJson(reader, Mod.class);
                printMatchingMod(mod, searchTerm);
            }
            reader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printMatchingMod(Mod mod, String searchTerm) {
        if (codeMatch(mod, searchTerm) || titleMatch(mod, searchTerm)) {
            System.out.println(mod);
        }
    }

    private static boolean codeMatch(Mod mod, String searchTerm) {
        return mod.getModuleCode().toLowerCase().contains(searchTerm);
    }

    private static boolean titleMatch(Mod mod, String searchTerm) {
        return mod.getTitle().toLowerCase().contains(searchTerm);
    }
}
