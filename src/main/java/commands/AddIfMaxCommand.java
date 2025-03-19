package commands;

import classes.SpaceMarine;
import helpers.CollectionManager;

/**
 * Класс AddIfMaxCommand
 * добавляет новый элемент в коллекцию,
 * если его значение превышает значение наибольшего элемента этой коллекции
 * @author bogdanborovoy
 */
public class AddIfMaxCommand implements Command {
    CollectionManager cm;

    /**
     * Конструктор класса AddIfMaxCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для добавления элемента
     */
    public AddIfMaxCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды добавления элемента в коллекцию,
     * если его значение превышает значение наибольшего элемента этой коллекции.
     */
    public void execute() {
        System.out.println("Создание элемента");
        cm.addIfMax(cm.add());
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "add_if_max : добавить новый элемент в коллекцию, " +
                "если его значение превышает значение наибольшего элемента этой коллекции";
    }
}