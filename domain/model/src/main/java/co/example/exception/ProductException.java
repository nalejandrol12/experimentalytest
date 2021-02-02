package co.example.exception;

public class ProductException extends RuntimeException{
    public ProductException(String error) {
        super(error);
    }
}
