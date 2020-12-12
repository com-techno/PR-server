package objects;

import objects.forms.NewPaintingForm;

import java.util.Calendar;

public class Painting {

    String name;
    String date;
    TimeStamp published;
    TimeStamp edited = null;
    TimeStamp moderated = null;
    String src;
    int id;


    public Painting(String name, String date, TimeStamp published, TimeStamp edited, TimeStamp moderated, String src, int id) {
        this.name = name;
        this.date = date;
        this.published = published;
        this.edited = edited;
        this.moderated = moderated;
        this.src = src;
        this.id = id;
    }

    public Painting(NewPaintingForm newPainting) throws Exception {
        checkCompletion(newPainting);
        this.name = newPainting.getName();
        this.date = newPainting.getDate();
        this.src = newPainting.getSrc();
        this.published = new TimeStamp(Calendar.getInstance());
    }

    public void checkCompletion(NewPaintingForm newArticle) throws Exception {
        if (newArticle.getName() == null || newArticle.getSrc() == null)
            throw new Exception("Form is incomplete");
    }

    public void setAuthor(String name) {
        this.name = name;
    }

    public void giveId(int id) {
        this.id = id;
    }

    /*
    public void changeLikes(LikeForm like) {
        switch (like.getLike()) {
            case -2:
                if (dislikes != 0) dislikes--;
                break;
            case -1:
                dislikes++;
                break;
            case 1:
                likes++;
                break;
            case 2:
                if (likes != 0) likes--;
                break;
        }
    }
    */
    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public TimeStamp getPublished() {
        return published;
    }

    public void setPublished(TimeStamp time) {
        this.published = time;
    }

    public TimeStamp getEdited() {
        return edited;
    }

    public TimeStamp getModerated() {
        return moderated;
    }

    public String getSrc() {
        return src;
    }

    public int getId() {
        return id;
    }

}
