package chursov.dto;

public class Tag {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tag() {
    }
}
