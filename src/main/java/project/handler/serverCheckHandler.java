package project.handler;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class serverCheckHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        //헤더 수정
        //리스폰스 상태
        Headers responseHeaders = httpExchange.getResponseHeaders();

        String jsonMessage = """
            {
                "message" : "server check"
            }
            """;

        byte[] bytes = jsonMessage.getBytes();
        responseHeaders.set("Content-Type", "application/json");
        httpExchange.sendResponseHeaders(200, bytes.length);//상태 코드와 내용물 길이 보내기
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(bytes);
        outputStream.close();
    }

}
