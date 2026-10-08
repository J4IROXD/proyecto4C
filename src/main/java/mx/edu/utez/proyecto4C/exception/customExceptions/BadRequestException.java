package mx.edu.utez.proyecto4C.exception.customExceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message){
        super(message);
    }
}
