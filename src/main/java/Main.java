import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import objects.User;
import server.APIHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public final static int PORT = 9999;
    public final static String DOMAIN = /*"192.168.0.125"*/"localhost";
    public final static String PATH = "C:\\_MyProjects\\Idea\\PR-server\\assets";

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        HttpServer server = HttpServer.create(new InetSocketAddress(DOMAIN, PORT), 0);

        server.createContext("/", new APIHandler(PATH));

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        server.setExecutor(executor);

        server.start();
        System.out.println("Server started at http://" + DOMAIN + ":" + PORT);
    }
}
