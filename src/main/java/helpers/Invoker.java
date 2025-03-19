package helpers;

import commands.Command;
import commands.HelpCommand;
import exceptions.WrongCommandNameException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс Invoker отвечает за управление и выполнение команд.
 * Он хранит команды в словаре и позволяет их выполнять по имени.
 * @author bogdanborovoy
 */
public class Invoker {
    /**
     * Словарь команд, где ключ — название команды, а значение — сама команда.
     */
    Map<String, Command> commands = new HashMap<>();

    /**
     * Добавляет команду в словарь.
     *
     * @param name Название команды
     * @param command Команда, которая будет выполнена
     */
    public void setCommand(String name, Command command) {
        commands.put(name, command);
    }

    /**
     * Возвращает словарь команд.
     *
     * @return Словарь команд, где ключ — название команды, а значение — сама команда
     */
    public Map<String, Command> getCommands() {
        return commands;
    }

    /**
     * Выполняет команду, переданную в качестве параметра.
     *
     * @param command Команда для выполнения
     */
    public void runCommand(Command command) {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Команда не найдена, повторите попытку");
        }
    }

    /**
     * Выполняет команду по её названию.
     *
     * @param name Название команды
     * @throws WrongCommandNameException Если команда с указанным названием не найдена
     */
    public void runCommand(String name) {
        Command command = commands.get(name);
        if (command != null) {
            command.execute();
        } else {
            throw new WrongCommandNameException("Команда не найдена, повторите попытку");
        }
    }
}