package commands;

import helpers.CollectionManager;

/**
 * Класс SaveCommand
 * сохраняет коллекцию в файл
 * @author bogdanborovoy
 */
public class SaveCommand implements Command {
    CollectionManager cm;

    /**
     * Конструктор класса SaveCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для сохранения коллекции в файл
     */
    public SaveCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды сохранения коллекции в файл.
     */
    public void execute() {
        cm.save(cm.spaceMarines);
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "save : сохранить коллекцию в файл";
    }
}