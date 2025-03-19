package commands;

import helpers.CollectionManager;

/**
 * Класс InfoCommand
 * выводит в стандартный поток вывода информацию о коллекции
 * (тип, дата инициализации, количество элементов и т.д.)
 * @author bogdanborovoy
 */
public class InfoCommand implements Command {
    CollectionManager cm;

    /**
     * Конструктор класса InfoCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для получения информации о коллекции
     */
    public InfoCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды вывода информации о коллекции.
     */
    public void execute() {
        cm.info();
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "info : вывести в стандартный поток вывода информацию о коллекции " +
                "(тип, дата инициализации, количество элементов и т.д.)";
    }
}