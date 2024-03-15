package project;


import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import project.handler.serverCheckHandler;
import project.handler.userSumHandler;

public class App {
    private final static int address = 5678;
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        App app = new App();
        app.run();
    }
    public void run() throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(address);
        HttpServer httpServer = HttpServer.create(socketAddress, 0);
        httpServer.createContext("/", new serverCheckHandler());
        httpServer.createContext("/sum", new userSumHandler());
        httpServer.start();
    }
}
