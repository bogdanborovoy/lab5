package commands;

import classes.SpaceMarine;
import helpers.CollectionManager;

/**
 * Класс AddCommand
 * добавляет новый элемент в коллекцию
 * @author bogdanborovoy
 */
public class AddCommand implements Command {
    CollectionManager cm;

    /**
     * Конструктор класса AddCommand.
     *
     * @param receiver Менеджер коллекции, который будет использоваться для добавления элемента
     */
    public AddCommand(CollectionManager receiver) {
        this.cm = receiver;
    }

    /**
     * Метод для выполнения команды добавления элемента в коллекцию.
     */
    public void execute() {
        cm.add();
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "add : добавить новый элемент в коллекцию";
    }
}