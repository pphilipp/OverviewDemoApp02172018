package philippbugayevskiy.example.com.data.exception;

public class MissingMetaDataException extends Exception {
    private String message;

    public MissingMetaDataException() {
        super();
    }

    public MissingMetaDataException(final Throwable cause) {
        super(cause);
    }

    public MissingMetaDataException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Error: No program available at the moment";
    }
}
