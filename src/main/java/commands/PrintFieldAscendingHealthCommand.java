package commands;

import helpers.CollectionManager;

/**
 * Класс PrintFieldAscendingHealthCommand
 * выводит значения поля health всех элементов в порядке возрастания
 * @author bogdanborovoy
 */
public class PrintFieldAscendingHealthCommand implements Command {
    CollectionManager cm;

    /**
     * Конструктор класса PrintFieldAscendingHealthCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для получения данных о health
     */
    public PrintFieldAscendingHealthCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды вывода значений поля health в порядке возрастания.
     */
    public void execute() {
        cm.printFieldAscendingHealth(cm.spaceMarines);
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "print_field_ascending_health : вывести значения поля health " +
                "всех элементов в порядке возрастания";
    }
}