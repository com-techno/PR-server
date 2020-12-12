package util;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import database.MyDatabase;
import objects.User;
import objects.forms.NewUserForm;

import static util.DecodeUtils.writeJson;


public class UserUtils {

    public static void signUp(HttpExchange exchange, Gson gson, MyDatabase database, String json) throws Exception {
        NewUserForm newUser = gson.fromJson(json, NewUserForm.class);
        database.signUp(newUser);
        writeJson(exchange, "message", "User successfully registered");
    }

    public static void signIn(HttpExchange exchange, Gson gson, MyDatabase database, String json) throws Exception {
        writeJson(exchange, "token", database.signIn(gson.fromJson(json, User.class)));
    }
}
