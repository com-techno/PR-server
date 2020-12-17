package util;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.util.List;
import java.util.Map;

import database.MyDatabase;
import objects.Painting;
import objects.forms.*;

import static util.DecodeUtils.writeJson;

public class PaintingUtils {

    public static void addPainting(HttpExchange exchange, Gson gson, MyDatabase database, String json) throws Exception {
        NewPaintingForm newPainting = gson.fromJson(json, NewPaintingForm.class);
        database.addPainting(new Painting(newPainting));
        writeJson(exchange, "message", "Added");
    }

    public static void getPainting(HttpExchange exchange, Gson gson, MyDatabase database) throws Exception {
        String s = exchange.getRequestURI().getQuery();
        if (s == null) throw new Exception("Params required");
        Map<String, String> params = DecodeUtils.paramsToMap(s);
        writeJson(exchange, gson.toJson(database.getPainting(Integer.parseInt(params.get("id")))));
    }

    public static void getModeratedPaintings(HttpExchange exchange, Gson gson, MyDatabase database) throws Exception {
        writeJson(exchange, gson.toJson(database.getModeratedPaintings()));
    }

    public static void deletePainting(HttpExchange exchange, Gson gson, MyDatabase database, String json) throws Exception {
        DeletePaintingForm deletePainting = gson.fromJson(json, DeletePaintingForm.class);
        database.deletePainting(deletePainting);
        writeJson(exchange, "message", "Song deleted");
    }
/*

    public static void like(HttpExchange exchange, Gson gson, MyDatabase database, String json) throws Exception {
        LikeForm like = gson.fromJson(json, LikeForm.class);
        database.like(like);
        writeJson(exchange, "message", "Liked");
    }

    public static void top(HttpExchange exchange, Gson gson, MyDatabase database) throws Exception {
        String s = exchange.getRequestURI().getQuery();
        if (s == null) throw new Exception("Params required");
        Map<String, String> params = DecodeUtils.paramsToMap(s);
        List<Painting> result = database.getTop(Integer.parseInt(params.get("count")));
        writeJson(exchange, gson.toJson(result));
    }
*/

    public static void search(HttpExchange exchange, Gson gson, MyDatabase database) throws Exception {
        String s = exchange.getRequestURI().getQuery();
        if (s == null) throw new Exception("Params required");
        Map<String, String> params = DecodeUtils.paramsToMap(s);

    }

}
