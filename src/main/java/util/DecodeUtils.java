package util;

import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class DecodeUtils {

    public static void writeFile(HttpExchange exchange, File file) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        exchange.sendResponseHeaders(200, file.length());
        //System.out.println(new String(new FileInputStream(file).readAllBytes()));
        outputStream.write(new FileInputStream(file).readAllBytes());
        outputStream.flush();
        outputStream.close();
    }

    public static void writeJson(HttpExchange exchange, String param, String msg) throws IOException {
        writeJson(exchange, "{\"" + param + "\":\"" + msg + "\"}");
    }

    public static void writeJson(HttpExchange exchange, String json) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        exchange.sendResponseHeaders(200, json.length());
        outputStream.write(json.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    public static Map<String, String> paramsToMap(String params) {
        HashMap<String, String> res = new HashMap<>();
        for (String s : params.split("&")) {
            String[] splitted = s.split("=");
            for (int j = 0; j < splitted.length; j++)
                splitted[j] = URLDecoder.decode(splitted[j]);
            res.put(splitted[0], splitted[1]);
        }
        return res;
    }
}
