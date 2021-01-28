package co.example.enums;

public enum StatusCodeEnum {
    CREATE(201),
    BAT_REQUEST(400),
    INTERNAL_ERROR(500);

    private final int code;

    StatusCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
