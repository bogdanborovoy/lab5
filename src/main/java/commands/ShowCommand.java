package commands;

import helpers.CollectionManager;

/**
 * Класс ShowCommand
 * выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 * @author bogdanborovoy
 */
public class ShowCommand implements Command {
    CollectionManager cm;

    /**
     * Конструктор класса ShowCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для вывода элементов
     */
    public ShowCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды вывода всех элементов коллекции.
     */
    public void execute() {
        cm.show(cm.spaceMarines);
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}