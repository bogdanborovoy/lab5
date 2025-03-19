package exceptions;

/**
 * Исключение, выбрасываемое при неправильном имени команды.
 * Наследуется от RuntimeException.
 * @author bogdanborovoy
 */
public class WrongCommandNameException extends RuntimeException {
    /**
     * Конструктор без параметров.
     */
    public WrongCommandNameException() {}

    /**
     * Конструктор с сообщением об ошибке.
     * @param message Сообщение об ошибке
     */
    public WrongCommandNameException(String message) {
        super(message);
    }
}
