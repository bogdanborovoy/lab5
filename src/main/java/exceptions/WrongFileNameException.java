package exceptions;

/**
 * Исключение, выбрасываемое при неверном пути к файлу.
 * Наследуется от RuntimeException.
 * @author bogdanborovoy
 */
public class WrongFileNameException extends RuntimeException {
    /**
     * Конструктор без параметров. Выводит сообщение о неверном пути к файлу.
     */
    public WrongFileNameException() {
        System.out.println("Неверный путь к файлу");
    }

    /**
     * Конструктор с сообщением об ошибке.
     * @param message Сообщение об ошибке
     */
    public WrongFileNameException(String message) {
        super(message);
    }
}
