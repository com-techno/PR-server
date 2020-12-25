package util;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import database.MyDatabase;
import objects.Painting;
import objects.forms.DeleteAuthorForm;
import objects.forms.DeletePaintingForm;
import objects.forms.NewPaintingForm;

import java.util.Map;

import static util.DecodeUtils.writeJson;

public class AuthorUtils {


    public static void addAuthor(HttpExchange exchange, Gson gson, MyDatabase database, String json) throws Exception {
        NewPaintingForm newAuthor = gson.fromJson(json, NewPaintingForm.class);
        database.addAuthor(newAuthor);
        writeJson(exchange, "message", "Added");
    }

    public static void getAuthor(HttpExchange exchange, Gson gson, MyDatabase database) throws Exception {
        String s = exchange.getRequestURI().getQuery();
        if (s == null) throw new Exception("Params required");
        Map<String, String> params = DecodeUtils.paramsToMap(s);
        writeJson(exchange, gson.toJson(database.getAuthor(Integer.parseInt(params.get("id")))));
    }

    public static void deleteAuthor(HttpExchange exchange, Gson gson, MyDatabase database, String json) throws Exception {
        DeleteAuthorForm deleteAuthor = gson.fromJson(json, DeleteAuthorForm.class);
        database.deleteAuthor(deleteAuthor);
        writeJson(exchange, "message", "Song deleted");
    }
}
