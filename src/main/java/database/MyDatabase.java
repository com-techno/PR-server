package database;

import objects.*;
import objects.forms.*;

import java.util.List;

public interface MyDatabase {
    void signUp(NewUserForm newUser) throws Exception;

    String signIn(User user) throws Exception;

    void addPainting(Painting newPainting) throws Exception;

    void moderatePainting(ModeratePaintingForm moderatePainting) throws Exception;

    void editPainting(EditPaintingForm editPainting) throws Exception;

    Painting getPainting(int id) throws Exception;

    List<Painting> getPaintings() throws Exception;

    List<Painting> getModeratedPaintings() throws Exception;

    //List<Painting> getTop(int count) throws Exception;

    //void like(LikeForm like) throws Exception;

    List<Painting> getNotModeratedPaintings() throws Exception;

    List<Painting> search(String query) throws Exception;

    void deletePainting(DeletePaintingForm deleteSong) throws Exception;

    void addAuthor(NewAuthorForm newAuthor) throws Exception;

    void editAuthor(EditAuthorForm editAuthor) throws Exception;

    Author getAuthor(int id) throws Exception;

    void deleteAuthor(DeleteAuthorForm deleteAuthor) throws Exception;
}