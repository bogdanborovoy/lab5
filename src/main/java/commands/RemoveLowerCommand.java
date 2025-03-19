package commands;

import classes.SpaceMarine;
import helpers.CollectionManager;

/**
 * Класс RemoveLowerCommand
 * удаляет из коллекции все элементы, меньшие, чем заданный
 * @author bogdanborovoy
 */
public class RemoveLowerCommand implements Command {
    CollectionManager cm;

    /**
     * Конструктор класса RemoveLowerCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для удаления элементов
     */
    public RemoveLowerCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды удаления элементов, меньших, чем заданный.
     */
    public void execute() {
        System.out.println("Создание элемента");
        cm.removeLower(cm.add());
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "remove_lower : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}