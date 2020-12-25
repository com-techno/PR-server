package objects;

import objects.forms.NewPaintingForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class Painting {

    int id;
    String name;
    String description;
    String date;
    TimeStamp published;
    TimeStamp edited = null;
    TimeStamp moderated = null;
    String src;
    public Painting(int id, String name, String description, String date, TimeStamp published, TimeStamp edited, TimeStamp moderated, String src) {
        this.name = name;
        this.date = date;
        this.published = published;
        this.edited = edited;
        this.moderated = moderated;
        this.src = src;
        this.id = id;
        this.description = description;
    }

    public Painting(ResultSet rs) throws SQLException {
        String editTime = rs.getString("edited");
        boolean edited = editTime != null;
        String moderateTime = rs.getString("moderated");
        boolean moderated = moderateTime != null;
        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.description = rs.getString("description");
        this.date = rs.getString("date");
        this.published = new TimeStamp(rs.getString("published"));
        this.edited = edited ? new TimeStamp(editTime) : null;
        this.moderated = moderated ? new TimeStamp(moderateTime) : null;
        this.src = rs.getString("src");
    }

    public Painting(NewPaintingForm newPainting) throws Exception {
        checkCompletion(newPainting);
        this.name = newPainting.getName();
        this.date = newPainting.getDate();
        this.src = newPainting.getSrc();
        this.published = new TimeStamp(Calendar.getInstance());
        this.description = newPainting.getDescription();
    }

    public String getDescription() {
        return description;
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
