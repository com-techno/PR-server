package objects;

public class Author {

    int id;
    String name;
    String bornDate;
    String bornPlace;
    String portrait;

    public Author(int id, String name, String bornDate, String bornPlace, String portrait) {
        this.id = id;
        this.name = name;
        this.bornDate = bornDate;
        this.bornPlace = bornPlace;
        this.portrait = portrait;
    }
}
