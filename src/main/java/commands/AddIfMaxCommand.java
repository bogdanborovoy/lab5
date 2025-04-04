package commands;

import helpers.CollectionManager;

import java.util.Scanner;

/**
 * Класс AddIfMaxCommand
 * добавляет новый элемент в коллекцию,
 * если его значение превышает значение наибольшего элемента этой коллекции
 * @author bogdanborovoy
 */
public class AddIfMaxCommand implements Command {
    CollectionManager cm;
    private boolean interactive;
    String[] value;
    public boolean isInteractive() {
        return interactive;
    }
    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    }

    @Override
    public void passValue(String[] value) {

    }

    /**
     * Конструктор класса AddIfMaxCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для добавления элемента
     */
    public AddIfMaxCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды добавления элемента в коллекцию,
     * если его значение превышает значение наибольшего элемента этой коллекции.
     */
    public void execute() {
        Scanner sc = new Scanner(System.in);
        String[] args = sc.nextLine().split(", ");
        if (interactive){
            System.out.println("Создание элемента");
            cm.addIfMax(cm.add(sc));
        }
        else{
            cm.addIfMax(cm.add(args));
        }
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "add_if_max : добавить новый элемент в коллекцию, " +
                "если его значение превышает значение наибольшего элемента этой коллекции";
    }
}