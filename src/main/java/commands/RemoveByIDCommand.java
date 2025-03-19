package commands;

import helpers.CollectionManager;

/**
 * Класс RemoveByIDCommand
 * удаляет элемент из коллекции по его id
 * @author bogdanborovoy
 */
public class RemoveByIDCommand implements Command {
    CollectionManager cm;

    /**
     * Конструктор класса RemoveByIDCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для удаления элемента по id
     */
    public RemoveByIDCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды удаления элемента по id.
     */
    public void execute() {
        cm.removeById();
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "remove_by_id : удалить элемент из коллекции по его id";
    }
}