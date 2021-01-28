package co.example.enums;

public enum NameParamsFindByNameEnum {
    NAME("NAME"),
    SIZE("SIZE"),
    PAGE("PAGE");

    private final String param;

    NameParamsFindByNameEnum(String param) {
        this.param = param;
    }

    public String getParam() {
        return this.param;
    }
}
