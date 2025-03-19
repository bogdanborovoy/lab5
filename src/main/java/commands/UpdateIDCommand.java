package commands;

import helpers.CollectionManager;

/**
 * Класс UpdateIDCommand
 * обновляет значение элемента коллекции, id которого равен заданному
 * @author bogdanborovoy
 */
public class UpdateIDCommand implements Command {
    CollectionManager cm;
    long id;

    /**
     * Конструктор класса UpdateIDCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для обновления элемента
     */
    public UpdateIDCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды обновления элемента коллекции по id.
     */
    public void execute() {
        cm.updateID();
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "update_id : обновить значение элемента коллекции, id которого равен заданному";
    }
}