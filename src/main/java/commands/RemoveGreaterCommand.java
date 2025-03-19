package commands;

import classes.SpaceMarine;
import helpers.CollectionManager;

/**
 * Класс RemoveGreaterCommand
 * удаляет из коллекции все элементы, превышающие заданный
 * @author bogdanborovoy
 */
public class RemoveGreaterCommand implements Command {
    CollectionManager cm;

    /**
     * Конструктор класса RemoveGreaterCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для удаления элементов
     */
    public RemoveGreaterCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды удаления элементов, превышающих заданный.
     */
    public void execute() {
        System.out.println("Создание элемента");
        cm.removeGreater(cm.add());
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "remove_greater : удалить из коллекции все элементы, превышающие заданный";
    }
}