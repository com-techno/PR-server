package util;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import database.MyDatabase;
import objects.Painting;
import objects.forms.DeletePaintingForm;
import objects.forms.EditPaintingForm;
import objects.forms.NewPaintingForm;

import static util.DecodeUtils.writeJson;

public class AdminUtils {

    public static void moderatePainting(HttpExchange exchange, Gson gson, MyDatabase database, String json) throws Exception {
        NewPaintingForm newPainting = gson.fromJson(json, NewPaintingForm.class);
        database.addPainting(new Painting(newPainting));
        writeJson(exchange, "message", "Moderated");
    }

    public static void editPainting(HttpExchange exchange, Gson gson, MyDatabase database, String json) throws Exception {
        EditPaintingForm editPainting = gson.fromJson(json, EditPaintingForm.class);
        database.editPainting(editPainting);
        writeJson(exchange, "message", "Added");
    }

    public static void editAuthor(HttpExchange exchange, Gson gson, MyDatabase database, String json) throws Exception {
        EditPaintingForm editPainting = gson.fromJson(json, EditPaintingForm.class);
        database.editPainting(editPainting);
        writeJson(exchange, "message", "Added");
    }
    // TODO: 13.12.2020 getNotModeratedPaintings
    // TODO: 13.12.2020 getAllPaintings
}
