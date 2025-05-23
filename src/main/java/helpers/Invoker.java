package helpers;

import commands.Command;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс Invoker отвечает за управление и выполнение команд.
 * Он хранит команды в словаре и позволяет их выполнять по имени.
 * @author bogdanborovoy
 */
public class Invoker implements Serializable {
    private boolean interactive;
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
     */
    public void runCommand(String name) {
        Command command = commands.get(name);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Команда не найдена, повторите попытку");
        }
    }
    public void runCommand(String[] value) {
        String name = value[0];
        String [] args = Arrays.copyOfRange(value, 1, value.length);
        Command command = commands.get(value[0]);
        if (command != null) {
            command.passValue(args);
            command.execute();
        } else {
            System.out.println("Команда не найдена, повторите попытку");
        }
    }
}