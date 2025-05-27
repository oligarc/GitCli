import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class GitFetcher {

    public static ArrayList<String> getActivityFromUser(String username) {
        String url = "https://api.github.com/users/" + username + "/events";
        HttpResponse<String> response = null;
        ArrayList<String> show = new ArrayList<>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener la actividad del usuario: " + e.getMessage());
        }

        if(response == null || response.body().contains("\"message\":\"Not Found\"")){
            show.add("User not found");
        }else{
            show.addAll(parseGitHubResponse(response.body()));
        }

        return show;

    }

    public static ArrayList<String> parseGitHubResponse(String jsonStringList){
        JSONParser parser = new JSONParser();
        ArrayList<String> results = new ArrayList<>();
        try{

            JSONArray events = (JSONArray) parser.parse(jsonStringList);
            for(Object object : events){
                StringBuilder messages = new StringBuilder();
                JSONObject event = (JSONObject) object;
                String type = (String) event.get("type");
                type = "Pushed one commit to ";
                JSONObject repo = (JSONObject) event.get("repo");
                String repoName = (String) repo.get("name");
                String createdAt = (String) event.get("created_at");
                JSONObject payload = (JSONObject) event.get("payload");
                JSONArray commits = (JSONArray) payload.get("commits");

                if(commits != null){
                    for(Object c : commits){
                        JSONObject commit = (JSONObject) c;
                        String message = (String) commit.get("message");
                        messages.append("\"").append(message).append("\"");
                    }
                }else{
                    messages.append("No commits info");
                }

                results.add(type +repoName + " at " +createdAt + " with message " +messages.toString());

            }

        }catch (ParseException e){
            System.out.println("Error parseando JSON: " +e.getMessage());
        }

        return results;
    }


}
