package commands;

import helpers.CollectionManager;

/**
 * Класс FilterStartsWithNameCommand
 * выводит элементы, значение поля name которых начинается с заданной подстроки
 * @author bogdanborovoy
 */
public class FilterStartsWithNameCommand implements Command {
    CollectionManager cm;
    String name;

    /**
     * Конструктор класса FilterStartsWithNameCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для фильтрации элементов
     */
    public FilterStartsWithNameCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Конструктор класса FilterStartsWithNameCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для фильтрации элементов
     * @param name Подстрока, с которой должно начинаться значение поля name
     */
    public FilterStartsWithNameCommand(CollectionManager cm, String name) {
        this.cm = cm;
        this.name = name;
    }

    /**
     * Метод для выполнения команды фильтрации элементов по началу строки в поле name.
     */
    public void execute() {
        cm.filterStartsWithName();
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "filter_starts_with_name name : вывести элементы, " +
                "значение поля name которых начинается с заданной подстроки";
    }
}