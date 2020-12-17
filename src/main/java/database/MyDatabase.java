package database;

import objects.*;
import objects.forms.*;

import java.util.List;

public interface MyDatabase {
    void signUp(NewUserForm newUser) throws Exception;

    String signIn(User user) throws Exception;

    void addPainting(Painting newPainting) throws Exception;

    Painting getPainting(int id) throws Exception;

    List<Painting> getModeratedPaintings() throws Exception;

    //List<Painting> getTop(int count) throws Exception;

    //void like(LikeForm like) throws Exception;

    List<Painting> search(String query) throws Exception;

    void deletePainting(DeletePaintingForm deleteSong) throws Exception;

    void moderatePainting(DeletePaintingForm moderatePainting) throws Exception;

    void getUserRole(Token token) throws Exception;
}