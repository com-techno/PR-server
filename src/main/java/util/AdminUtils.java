package util;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import database.MyDatabase;
import objects.Painting;
import objects.forms.DeletePaintingForm;
import objects.forms.NewPaintingForm;

import static util.DecodeUtils.writeJson;

public class AdminUtils {

    public static void moderatePainting(HttpExchange exchange, Gson gson, MyDatabase database, String json) throws Exception {
        DeletePaintingForm moderatePainting = gson.fromJson(json, DeletePaintingForm.class);
        database.moderatePainting(moderatePainting);
        writeJson(exchange, "message", "Added");
    }

    // TODO: 13.12.2020 getNotModeratedPaintings
    // TODO: 13.12.2020 getAllPaintings
}
