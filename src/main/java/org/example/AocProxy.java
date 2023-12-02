package org.example;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AocProxy {
    private static final String SESSION = "53616c7465645f5fcd298a25ec1c3689eaa51d57458f3444d8b85f86e80a1ab77f2564525eb938d6517b11592cd324b58bc6fe23c29e3b32d65139447cb71d6b";
    private static final String INPUTS_DIRECTORY = Main.inputsDirectory;
    private static final String YEAR = "2023";
    private static final String BASE_URL = "https://adventofcode.com";
    private static final HttpClient client = HttpClient.newBuilder().build();
    
    private static String zDay(int day) {
        return (day < 10 ? "0" : "") + day;
    }
    
    public static File getInput(int day) {
        String path = INPUTS_DIRECTORY+"day"+zDay(day)+".txt";
        File dayFile = new File(path);
        if(!dayFile.isFile()) {
            System.out.println("File for day "+ zDay(day)+"missing");
            try {
                HttpRequest request = HttpRequest.newBuilder(new URI(BASE_URL+"/"+YEAR+"/day/"+day+"/input"))
                        .setHeader("Cookie","session="+SESSION)
                        .GET()
                        .build();
                HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get(path)));
            } catch (URISyntaxException | InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        
        return dayFile;
    }
    
}
