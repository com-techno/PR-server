package objects.forms;

import objects.TimeStamp;

public class EditPaintingForm {

    int id;
    String name;
    String description;
    String date;
    TimeStamp published;
    String src;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public TimeStamp getPublished() {
        return published;
    }

    public String getSrc() {
        return src;
    }
}
