package database;

import objects.Painting;
import objects.TimeStamp;
import objects.User;
import objects.forms.DeletePaintingForm;
import objects.forms.NewUserForm;
import util.DatabaseUtils;
import util.HashUtils;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static util.HashUtils.encode;

public class SQLDatabase implements MyDatabase {

    DatabaseUtils db;

    public SQLDatabase() throws SQLException, ClassNotFoundException {
        db = new DatabaseUtils();
    }

    @Override
    public void signUp(NewUserForm newUser) throws Exception {
            String query = "SELECT * FROM user WHERE login='" + newUser.getLogin() + "'";
            System.out.println(query);
            try {
            ResultSet resSet = db.execSqlQuery(query);
            if (resSet.isClosed()) {
                query = "INSERT INTO user (login, passhash" + (newUser.getEmail() == null ? "" : ", email") + ") " +
                        "VALUES (\"" + newUser.getLogin() + "\", " +
                        "\"" + new String(encode(newUser.getPassword()), StandardCharsets.UTF_16) + "\"" + (newUser.getEmail() == null ? "" : ", " +
                        "\"" + newUser.getEmail() + "\"") + ");";
                System.out.println(query);
                db.execSqlUpdate(query);
            } else throw new Exception("Account with same login already exists");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("SQLException");
        }

    }

    @Override
    public String signIn(User user) throws Exception {
        String pass = new String(encode(user.passHash), StandardCharsets.UTF_16);
        String query = "SELECT * FROM user WHERE login='" + user.login + "'";
        System.out.println(query);
        try {
            ResultSet resSet = db.execSqlQuery(query);
            if (resSet.isClosed()) throw new Exception("Account doesn't exists");
            String passhash = resSet.getString("passhash");
            resSet.close();
            if (pass.equals(passhash))
                return HashUtils.getToken(user);
            else throw new Exception("Password is incorrect");
        } catch (SQLException e){
            e.printStackTrace();
            throw new Exception("SQLException");
        }
    }

    @Override
    public void addPainting(Painting newPainting) throws Exception {
        try {
            String query = "INSERT INTO painting (name, date, published, src) " +
                    "VALUES (" +
                    "\"" + newPainting.getName() + "\", " +
                    "\"" + newPainting.getDate() + "\", " +
                    "\"" + newPainting.getPublished().toString() + "\", " +
                    "\"" + newPainting.getSrc() + "\");";
            System.out.println(query);
            db.execSqlUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("SQLException");
        }
    }

    @Override
    public Painting getPainting(int id) throws Exception {
        String query = "SELECT * FROM painting WHERE id=" + id + ";";
        System.out.println(query);
        try {
            ResultSet rs = db.execSqlQuery(query);
            if (rs.isClosed())
                throw new Exception("Painting with same id doesn't exists");
            return new Painting(rs.getString("name"),
                    rs.getString("date"),
                    new TimeStamp(rs.getString("published")),
                    new TimeStamp(rs.getString("edited")),
                    new TimeStamp(rs.getString("moderated")),
                    rs.getString("src"),
                    rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("SQLException");
        }
    }

    /**
     * Rating system //todo доделать систему рейтинга в БД
     *
     * @Override public List<Painting> getTop(int count) throws Exception {
     * List<Painting> top = new LinkedList<>();
     * ResultSet rs = db.execSqlQuery("SELECT * INTO painting ORDER BY likes, dislikes");
     * if (rs.isClosed()) throw new Exception("No data found");
     * for (int i = 0; i < count && !rs.isClosed(); i++) {
     * Painting topPainting = new Painting();//todo дописать список всех ячеек
     * top.add(topPainting);
     * rs.next();
     * }
     * return top;
     * }
     * @Override public void like(LikeForm like) throws Exception {
     * String query = "UPDATE song SET ";
     * switch (like.getLike()) {
     * case -2:
     * query += "dislikes=dislikes-1 WHERE dislikes != 0 AND id= ";
     * case -1:
     * query += "dislikes=dislikes+1 WHERE id= ";
     * break;
     * case 1:
     * query += "likes=likes+1 WHERE id= ";
     * break;
     * case 2:
     * query += "likes=likes-1 WHERE likes != 0 AND id= ";
     * break;
     * }
     * query += like.getArticleId();
     * db.execSqlUpdate(query);
     * }
     */

    @Override
    public List<Painting> search(String query) throws Exception {
        query = query.toLowerCase()
                .replace(" ", "")
                .replace(",", "")
                .replace(".", "")
                .replace(":", "");
        List<Painting> paintings = new LinkedList<>();
        try {
            ResultSet rs = db.execSqlQuery("SELECT * INTO painting");
            if (rs.isClosed()) throw new Exception("No data found");
            do {
                String author = rs.getString("author")
                        .toLowerCase()
                        .replace(" ", "")
                        .replace(",", "")
                        .replace(".", "")
                        .replace(":", "");
                if (author.contains(query)) {
                    Painting painting = new Painting(rs.getString("name"),
                            rs.getString("date"),
                            new TimeStamp(rs.getString("published")),
                            new TimeStamp(rs.getString("edited")),
                            new TimeStamp(rs.getString("moderated")),
                            rs.getString("src"),
                            rs.getInt("id"));
                    paintings.add(painting);
                    rs.next();
                }

            } while (!rs.isClosed());
            if (paintings.isEmpty()) throw new Exception("No results found");
            return paintings;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("SQLException");
        }
    }

    @Override
    public void deletePainting(DeletePaintingForm deletePainting) throws Exception {
        try {
            db.execSqlUpdate("DELETE FROM painting WHERE id=" + deletePainting.getPaintingId());
        } catch (SQLException e){
            e.printStackTrace();
            throw new Exception("SQLException");
        }
    }
}
