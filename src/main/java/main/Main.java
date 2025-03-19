package main;



import classes.*;
import com.opencsv.exceptions.CsvValidationException;
import commands.*;
import helpers.CollectionManager;
import helpers.Invoker;
import com.opencsv.*;

import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        //export FILE_NAME="/home/studs/s465267/lab5/files/values.csv"
        //export FILE_NAME="../src/main/java/files/values.csv"
        String filePath = System.getenv("FILE_NAME");
        //String filePath = "src/main/java/files/values.csv";
        CSVReader csvReader;
        CollectionManager receiver = new CollectionManager();
        Invoker invoker = new Invoker();


        invoker.setCommand("help", new HelpCommand(receiver, invoker));
        invoker.setCommand("info", new InfoCommand(receiver));
        invoker.setCommand("show", new ShowCommand(receiver));
        invoker.setCommand("add", new AddCommand(receiver));
        invoker.setCommand("update_id", new UpdateIDCommand(receiver));
        invoker.setCommand("remove_by_id", new RemoveByIDCommand(receiver));
        invoker.setCommand("clear", new ClearCommand(receiver));
        invoker.setCommand("save", new SaveCommand(receiver));
        invoker.setCommand("execute_script", new ExecuteSciptCommand(receiver, invoker));
        invoker.setCommand("exit", new ExitCommand(receiver));
        invoker.setCommand("add_if_max", new AddIfMaxCommand(receiver));
        invoker.setCommand("remove_greater", new RemoveGreaterCommand(receiver));
        invoker.setCommand("remove_lower", new RemoveLowerCommand(receiver));
        invoker.setCommand("count_greater_than_heart_count", new CountGreaterThanHeartCountCommand(receiver));
        invoker.setCommand("filter_starts_with_name", new FilterStartsWithNameCommand(receiver));
        invoker.setCommand("print_field_ascending_health", new PrintFieldAscendingHealthCommand(receiver));



        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
            try (InputStreamReader isr = new InputStreamReader(bis);) {
                csvReader = new CSVReader(isr);
                String[] nextRecord;
                while ((nextRecord = csvReader.readNext()) != null) {
                    for(int i = 0; i < nextRecord.length; i++) {
                        nextRecord[i] = nextRecord[i].trim();
                    }
                    String name = nextRecord[0];
                    Coordinates coordinates = new Coordinates(Integer.parseInt(nextRecord[1]), Integer.parseInt(nextRecord[2]));
                    double health = Double.parseDouble(nextRecord[3]);
                    int heartCount = Integer.parseInt(nextRecord[4]);
                    AstartesCategory category = AstartesCategory.valueOf(nextRecord[5]);
                    MeleeWeapon meleeWeapon = MeleeWeapon.valueOf(nextRecord[6]);
                    Chapter chapter = new Chapter(nextRecord[7], Integer.parseInt(nextRecord[8]));
                    receiver.spaceMarines.add(new SpaceMarine(name, coordinates, health, heartCount, category, meleeWeapon, chapter));
                }
            }
        }
        catch (IOException | CsvValidationException e) {
            System.out.println("Файл не найден");
            System.exit(1);

        }
        catch (NullPointerException e) {
            System.err.println("Файл не указан");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите команду (для получения списка всех команд введите \"help\"): ");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");
            Command command = invoker.getCommands().get(tokens[0]);
            invoker.runCommand(command);
            System.out.println("Введите команду (для получения списка всех команд введите \"help\"): ");
        }
    }
}