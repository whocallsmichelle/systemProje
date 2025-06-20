package Model.Author;

public class Author {
    private int authorId;
    private String name;
    private String surname;
    private String website;

    public Author(int authorId, String name, String surname, String website) {
        this.authorId = authorId;
        this.name = name;
        this.surname = surname;
        this.website = website;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
