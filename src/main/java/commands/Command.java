package commands;

/**
 * Шаблон команд
 * @author bogdanborovoy
 */

public interface Command {

    /**
     * Шаблон реализации команды
     */
    void execute();

    /**
     * Описание команды
     * @return Описание команды в виде строки
     */
    String descr();

}
