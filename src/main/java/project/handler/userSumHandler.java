package project.handler;

import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import project.entity.user;
public class userSumHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Headers headers = httpExchange.getResponseHeaders();
        FileReader fileReader = new FileReader("C:\\Users\\82103\\IdeaProjects\\Programmers-API-SERVER\\data\\input\\user.json");
        ObjectMapper objectMapper = new ObjectMapper();
        user[] users = objectMapper.readValue(fileReader, user[].class);
        int sum = 0;
        for (user user : users) {
            sum += user.post_count;
        }
        String message =  """
            {
                "sum" : %d
            }
            """.formatted(sum);
        headers.set("Content-Type", "application/json");
        byte[] bytes = message.getBytes();
        OutputStream outputStream = httpExchange.getResponseBody();
        httpExchange.sendResponseHeaders(200, bytes.length);
        outputStream.write(bytes);
        outputStream.close();
    }

}
