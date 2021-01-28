package co.example.enums;

public enum ImageTypeEnum {
    FRONT("FRONT"),
    BACK("BACK"),
    OTHER("OTHER");

    private final String name;

    ImageTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
