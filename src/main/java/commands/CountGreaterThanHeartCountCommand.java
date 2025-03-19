package commands;

import helpers.CollectionManager;

/**
 * Класс CountGreaterThanHeartCountCommand
 * выводит количество элементов,
 * значение поля heartCount которых больше заданного
 * @author bogdanborovoy
 */
public class CountGreaterThanHeartCountCommand implements Command {
    CollectionManager cm;

    /**
     * Конструктор класса CountGreaterThanHeartCountCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для выполнения команды
     */
    public CountGreaterThanHeartCountCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды вывода количества элементов,
     * значение поля heartCount которых больше заданного.
     */
    public void execute() {
        cm.countGreaterThanHeartCount();
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "count_greater_than_heart_count : вывести количество элементов, " +
                "значение поля heartCount которых больше заданного";
    }
}