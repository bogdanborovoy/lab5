package commands;

import helpers.CollectionManager;
import helpers.Invoker;

/**
 * Класс ExecuteScriptCommand
 * считывает и исполняет скрипт из указанного файла
 * (в скрипте содержатся команды в таком же виде,
 * в котором их вводит пользователь в интерактивном режиме)
 * @author bogdanborovoy
 */
public class ExecuteSciptCommand implements Command {
    CollectionManager cm;
    Invoker invoker;

    /**
     * Конструктор класса ExecuteSciptCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для выполнения скрипта
     * @param invoker Инвокер, который будет использоваться для выполнения команд из скрипта
     */
    public ExecuteSciptCommand(CollectionManager cm, Invoker invoker) {
        this.cm = cm;
        this.invoker = invoker;
    }

    /**
     * Метод для выполнения команды выполнения скрипта.
     */
    public void execute() {
        cm.executeScript(invoker);
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "execute_script : считать и исполнить скрипт из указанного файла. " +
                "В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в " +
                "интерактивном режиме";
    }
}