package helpers;

import classes.*;
import com.opencsv.exceptions.CsvValidationException;
import commands.Command;
import com.opencsv.*;
import exceptions.WrongFileNameException;

import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Класс управления коллекцией космических кораблей
 * @author bogdanborovoy
 */
public class CollectionManager {

    /**
     * Компаратор для сравнения элементов коллекции по ID.
     */
    static class IDComparator implements Comparator<SpaceMarine> {
        /**
         * Сравнивает два объекта SpaceMarine по их ID.
         *
         * @param o1 Первый объект SpaceMarine
         * @param o2 Второй объект SpaceMarine
         * @return Результат сравнения ID
         */
        public int compare(SpaceMarine o1, SpaceMarine o2) {
            return Long.compare(o1.getId(), o2.getId());
        }
    }

    /**
     * Коллекция космических десантников.
     */
    public TreeSet<SpaceMarine> spaceMarines;

    /**
     * Дата создания коллекции.
     */
    private ZonedDateTime creationDate;

    /**
     * Конструктор по умолчанию.
     * Инициализирует коллекцию и устанавливает дату создания.
     */
    public CollectionManager() {
        spaceMarines = new TreeSet<>(new IDComparator());
        setCreationDate();
    }

    /**
     * Возвращает дату создания коллекции.
     *
     * @return Дата создания коллекции
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Устанавливает дату создания коллекции.
     */
    public void setCreationDate() {
        this.creationDate = ZonedDateTime.now();
    }

    /**
     * Выводит справку по доступным командам.
     *
     * @param invoker Инвокер, содержащий команды
     * @return Строка с описанием всех команд
     */
    public String help(Invoker invoker) {
        StringBuilder output = new StringBuilder();
        for (Command c : invoker.getCommands().values()) {
            output.append(c.descr()).append("\n");
        }
        System.out.println(output);
        return output.toString();
    }

    /**
     * Выводит информацию о коллекции.
     *
     * @return Строка с информацией о коллекции
     */
    public String info() {
        StringBuilder output = new StringBuilder();

        String type = spaceMarines.getClass().getName().split("\\.")[2];
        output.append("Тип коллекции: ").append(type).append("\n");

        String date = DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(this.getCreationDate());
        output.append("Время инициализации: ").append(date).append("\n");

        String size = String.valueOf(spaceMarines.size());
        output.append("Количество элементов: ").append(size).append("\n");
        System.out.println(output);
        return output.toString();
    }

    /**
     * Выводит все элементы коллекции в строковом представлении.
     *
     * @param spaceMarines Коллекция космических десантников
     * @return Строка с элементами коллекции
     */
    public String show(NavigableSet<SpaceMarine> spaceMarines) {
        StringBuilder output = new StringBuilder();
        for (SpaceMarine spaceMarine : spaceMarines) {
            output.append(spaceMarine.toString()).append("\n");
        }
        if (spaceMarines.isEmpty()) {
            output.append("Коллекция пустая");
        }
        System.out.println(output);
        return output.toString();
    }

    /**
     * Добавляет новый элемент в коллекцию.
     *
     * @return Добавленный элемент
     */
    public SpaceMarine add() {
        SpaceMarine spaceMarine = new SpaceMarine();
        Scanner sc = new Scanner(System.in);
        spaceMarine.setId();
        spaceMarine.setCreationDate();
        while (spaceMarine.getName() == null) {
            try {
                System.out.println("Введите имя: ");
                spaceMarine.setName(sc.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Coordinates coordinates = new Coordinates();

        while (coordinates.getX() == null) {
            try {
                System.out.println("Введите координаты по x: ");
                Integer x = Integer.parseInt(sc.nextLine());
                coordinates.setX(x);
            } catch (NumberFormatException e) {
                System.out.println("Координаты должны быть целым числом");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        while (coordinates.getY() == 0) {
            try {
                System.out.println("Введите координаты по y: ");
                int y = Integer.parseInt(sc.nextLine());
                coordinates.setY(y);
                if (y == 0) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Координаты должны быть целым числом");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        spaceMarine.setCoordinates(coordinates);

        while (spaceMarine.getHealth() == 0) {
            try {
                System.out.println("Введите показатель здоровья: ");
                double health = Double.parseDouble(sc.nextLine());
                spaceMarine.setHealth(health);
            } catch (NumberFormatException e) {
                System.out.println("Показатель здоровья должен быть числом");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        while (spaceMarine.getHeartCount() == 0) {
            try {
                System.out.println("Введите количество сердец: ");
                int heartCount = Integer.parseInt(sc.nextLine());
                spaceMarine.setHeartCount(heartCount);
            } catch (NumberFormatException e) {
                System.out.println("Количество сердец должно быть целым числом");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.print("Выберите тип десантника: ");
        while (spaceMarine.getCategory() == null) {
            try {
                AstartesCategory.printValues();
                String line = sc.nextLine().toUpperCase();
                spaceMarine.setCategory(AstartesCategory.valueOf(line));
            } catch (IllegalArgumentException e) {
                System.out.println("Выберите тип из предложенных");
            }
        }

        System.out.print("Выберите тип оружия: ");
        while (spaceMarine.getMeleeWeapon() == null) {
            try {
                MeleeWeapon.printValues();
                String line = sc.nextLine().toUpperCase();
                spaceMarine.setMeleeWeapon(MeleeWeapon.valueOf(line));
            } catch (IllegalArgumentException e) {
                System.out.println("Выберите тип из предложенных");
            }
        }

        Chapter chapter = new Chapter();
        while (chapter.getName() == null) {
            try {
                System.out.println("Введите название ордена: ");
                chapter.setName(sc.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (chapter.getMarinesCount() == null) {
            try {
                System.out.println("Введите количество десантников: ");
                Integer marinesCount = Integer.parseInt(sc.nextLine());
                chapter.setMarinesCount(marinesCount);
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        spaceMarine.setChapter(chapter);
        spaceMarines.add(spaceMarine);
        System.out.println("Космический десантник создан");
        return spaceMarine;
    }

    /**
     * Обновляет значение элемента коллекции по его ID.
     */
    public void updateID() {
        Scanner sc = new Scanner(System.in);
        Long id = null;
        while (id == null) {
            try {
                System.out.println("Введите id: ");
                id = Long.parseLong(sc.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("ID должен быть в формате long");
            }
        }
        int count = 0;
        for (SpaceMarine spaceMarine : spaceMarines) {
            if (spaceMarine.getId() == id) {
                spaceMarines.remove(spaceMarine);
                add();
            }
        }
        System.out.println("Обновлено " + count + " элемент(а)");
    }

    /**
     * Удаляет элемент из коллекции по его ID.
     */
    public void removeById() {
        Scanner sc = new Scanner(System.in);
        Long id1 = null;
        while (id1 == null) {
            try {
                System.out.println("Введите id: ");
                id1 = Long.parseLong(sc.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("ID должен быть в формате long");
            }
        }
        long id = id1;

        for (SpaceMarine spaceMarine : this.spaceMarines) {
            if (spaceMarine.getId() == id) {
                System.out.println("Элемент " + id + " удален");
                spaceMarines.remove(spaceMarine);
                break;
            }
        }
    }

    /**
     * Очищает коллекцию.
     */
    public void clear() {
        spaceMarines.clear();
    }

    /**
     * Сохраняет коллекцию в файл.
     *
     * @param spaceMarines Коллекция космических десантников
     */
    public void save(NavigableSet<SpaceMarine> spaceMarines) {
        Scanner sc = new Scanner(System.in);
        String fileName = null;
        while (fileName == null) {
            System.out.println("Введите путь к файлу: ");
            fileName = sc.nextLine();
            File file = new File(fileName);
            try {
                if (!file.exists()) {
                    fileName = null;
                    throw new WrongFileNameException();
                }
            } catch (WrongFileNameException e) {
                continue;
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                for (SpaceMarine spaceMarine : spaceMarines) {
                    bw.write(spaceMarine.toString());
                    bw.newLine();
                }
                System.out.println("Коллекция сохранена в файл " + fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Считывает и исполняет скрипт из указанного файла.
     *
     * @param invoker Инвокер, содержащий команды
     */
    public void executeScript(Invoker invoker) {
        Scanner sc = new Scanner(System.in);
        String script = null;
        while (script == null) {
            System.out.println("Введите путь к файлу: ");
            script = sc.nextLine();
            File file = new File(script);
            try {
                if (!file.exists()) {
                    script = null;
                    throw new WrongFileNameException();
                }
            } catch (WrongFileNameException e) {
                continue;
            }
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(script))) {
                InputStreamReader isr = new InputStreamReader(bis);
                CSVReader csvReader = new CSVReader(isr);
                String[] nextRecord;
                while ((nextRecord = csvReader.readNext()) != null) {
                    for (int i = 0; i < nextRecord.length; i++) {
                        System.out.println("Выполняется команда " + nextRecord[i].trim());
                        invoker.runCommand(nextRecord[i].trim());
                    }
                }
                isr.close();
            } catch (IOException | CsvValidationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Завершает программу (без сохранения в файл).
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Добавляет новый элемент в коллекцию, если его значение превышает
     * значение наибольшего элемента этой коллекции.
     *
     * @param spaceMarine Элемент для добавления
     */
    public void addIfMax(SpaceMarine spaceMarine) {
        if (spaceMarines.isEmpty()) {
            System.out.println("Коллекция пустая");
        } else {
            Iterator<SpaceMarine> iterator = spaceMarines.iterator();
            SpaceMarine lastElement = iterator.next();
            while (iterator.hasNext()) {
                lastElement = iterator.next();
            }
            if (lastElement.getId() > spaceMarine.getId()) {
                spaceMarines.add(spaceMarine);
                System.out.println("Элемент добавлен");
            } else {
                System.out.println("ID элемента не превышает значение наибольшего элемента этой коллекции");
                System.out.println("Элемент не был добавлен");
            }
        }
    }

    /**
     * Удаляет из коллекции все элементы, превышающие заданный.
     *
     * @param spaceMarine Элемент для сравнения
     */
    public void removeGreater(SpaceMarine spaceMarine) {
        NavigableSet<SpaceMarine> lowerSpaceMarines = new TreeSet<>(spaceMarines.tailSet(spaceMarine, false));
        int count = 0;
        for (SpaceMarine lowerSpaceMarine : lowerSpaceMarines) {
            spaceMarines.remove(lowerSpaceMarine);
            count++;
        }
        spaceMarines.remove(spaceMarine);
        System.out.println(count + " элемент(а) удалено");
    }

    /**
     * Удаляет из коллекции все элементы, меньшие, чем заданный.
     *
     * @param spaceMarine Элемент для сравнения
     */
    public void removeLower(SpaceMarine spaceMarine) {
        System.out.println("Создание элемента");
        SortedSet<SpaceMarine> lowerSpaceMarines = new TreeSet<>(spaceMarines.headSet(spaceMarine));
        int count = 0;
        for (SpaceMarine lowerSpaceMarine : lowerSpaceMarines) {
            spaceMarines.remove(lowerSpaceMarine);
            count++;
        }
        spaceMarines.remove(spaceMarine);
        System.out.println(count + " элемент(а) удалено");
    }

    /**
     * Выводит количество элементов, значение поля heartCount которых больше заданного.
     */
    public void countGreaterThanHeartCount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите количество сердец: ");
        int heartCount = 0;
        while (heartCount == 0) {
            try {
                heartCount = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Количество сердец должно быть целым числом");
            }
        }
        int count = 0;
        for (SpaceMarine spaceMarine : spaceMarines) {
            if (spaceMarine.getHeartCount() > heartCount) {
                count++;
            }
        }
        System.out.println("В коллекции " + count + " элементов, значение поля heartCount которых больше заданного");
    }

    /**
     * Выводит элементы, значение поля name которых начинается с заданной подстроки.
     */
    public void filterStartsWithName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите подстроку: ");
        String name = sc.nextLine();
        for (SpaceMarine spaceMarine : spaceMarines) {
            if (spaceMarine.getName().toLowerCase().startsWith(name.toLowerCase())) {
                System.out.println(spaceMarine);
            }
        }
    }

    /**
     * Выводит значения поля health всех элементов в порядке возрастания.
     *
     * @param spaceMarines Коллекция космических десантников
     * @return Список значений health
     */
    public List<Double> printFieldAscendingHealth(NavigableSet<SpaceMarine> spaceMarines) {
        List<Double> health_list = new ArrayList<>();
        for (SpaceMarine spaceMarine : spaceMarines) {
            health_list.add(spaceMarine.getHealth());
            Collections.sort(health_list);
        }
        health_list.forEach(health -> System.out.print(health + " "));
        System.out.println();
        return health_list;
    }
}