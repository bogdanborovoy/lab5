package helpers;

import classes.*;
import commands.HelpCommand;
import commands.InfoCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NavigableSet;

import static org.junit.jupiter.api.Assertions.*;
class CollectionManagerTest {
    @Test
    public void testHelp(){
        CollectionManager collectionManager = new CollectionManager();
        Invoker invoker = new Invoker();

        invoker.setCommand("help", new HelpCommand(collectionManager, invoker));
        invoker.setCommand("info", new InfoCommand(collectionManager));

        String result = collectionManager.help(invoker);
        Assertions.assertTrue(result.contains("help"));
        Assertions.assertTrue(result.contains("вывести справку по доступным командам"));

        Assertions.assertTrue(result.contains("info"));
        Assertions.assertTrue(result.contains("вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)"));

    }
    @Test
    public void testInfo(){
        CollectionManager collectionManager = new CollectionManager();
        NavigableSet<SpaceMarine> spaceMarines = collectionManager.spaceMarines;
        spaceMarines.add(new SpaceMarine());
        String output = collectionManager.info();
        Assertions.assertTrue(output.contains("Тип коллекции: TreeSet"));
        Assertions.assertTrue(output.contains("Количество элементов: 1"));
    }
    @Test
    public void testShowEmptyCollection(){
        CollectionManager collectionManager = new CollectionManager();
        String output = collectionManager.show(collectionManager.spaceMarines);
        Assertions.assertEquals("Коллекция пустая", output);
    }
    @Test
    public void testShowNonEmptyCollection(){
        CollectionManager collectionManager = new CollectionManager();
        String name = "name";
        Coordinates coordinates = new Coordinates(1, 2);
        double health = 100;
        int heartCount = 3;
        AstartesCategory category = AstartesCategory.valueOf("HELIX");
        MeleeWeapon meleeWeapon = MeleeWeapon.valueOf("MANREAPER");
        Chapter chapter = new Chapter("name", 1);
        SpaceMarine spaceMarine = new SpaceMarine(name, coordinates, health, heartCount, category, meleeWeapon, chapter);
        collectionManager.spaceMarines.add(spaceMarine);
        String output = collectionManager.show(collectionManager.spaceMarines);
        Assertions.assertEquals(spaceMarine.toString()+"\n", output);

    }
    @Test
    public void testAdd() {
        CollectionManager collectionManager = new CollectionManager();
        collectionManager.spaceMarines.add(new SpaceMarine());
        Assertions.assertEquals(1, collectionManager.spaceMarines.size());
    }
    @Test
    public void testClear(){
        CollectionManager collectionManager = new CollectionManager();
        NavigableSet<SpaceMarine> spaceMarines = collectionManager.spaceMarines;
        for(int i=0; i<10; i++){
            spaceMarines.add(new SpaceMarine());
        }
        collectionManager.clear();
        Assertions.assertEquals(0, spaceMarines.size());
    }
    @Test
    public void testRemoveLower(){
        CollectionManager collectionManager = new CollectionManager();
        NavigableSet<SpaceMarine> spaceMarines = collectionManager.spaceMarines;
        SpaceMarine spaceMarine1 = new SpaceMarine();
        spaceMarines.add(spaceMarine1);
        SpaceMarine spaceMarine2 = new SpaceMarine();

        if(spaceMarine1.getId()<spaceMarine2.getId()){
            collectionManager.removeLower(spaceMarine2);
            Assertions.assertEquals(0, spaceMarines.size());
        }
        else {
            Assertions.assertEquals(1, collectionManager.spaceMarines.size());
            }
    }
    @Test
    public void testRemoveGreater(){
        CollectionManager collectionManager = new CollectionManager();
        NavigableSet<SpaceMarine> spaceMarines = collectionManager.spaceMarines;
        SpaceMarine spaceMarine1 = new SpaceMarine();
        spaceMarines.add(spaceMarine1);
        SpaceMarine spaceMarine2 = new SpaceMarine();

        if(spaceMarine2.getId()<spaceMarine1.getId()){
            collectionManager.removeGreater(spaceMarine1);
            Assertions.assertEquals(0, spaceMarines.size());
        }
        else {
            Assertions.assertEquals(1, collectionManager.spaceMarines.size());
        }
    }
    @Test
    public void testPrintFieldAscendingHealth(){
        CollectionManager collectionManager = new CollectionManager();
        NavigableSet<SpaceMarine> spaceMarines = collectionManager.spaceMarines;
        SpaceMarine spaceMarine1 = new SpaceMarine();
        SpaceMarine spaceMarine2 = new SpaceMarine();
        SpaceMarine spaceMarine3 = new SpaceMarine();

        spaceMarine1.setHealth(1);
        spaceMarine2.setHealth(2);
        spaceMarine3.setHealth(3);

        spaceMarines.add(spaceMarine1);
        spaceMarines.add(spaceMarine2);
        spaceMarines.add(spaceMarine3);
        List<Double> health_list = collectionManager.printFieldAscendingHealth(spaceMarines);
        int count = 1;
        for (int i=0; i<health_list.size()-1; i++){
            if (health_list.get(i + 1) >= health_list.get(i)){
                count++;
            }
        }
        Assertions.assertEquals(health_list.size(), count);
    }
    @Test
    public void testAddIfMax(){
        CollectionManager collectionManager = new CollectionManager();
        NavigableSet<SpaceMarine> spaceMarines = collectionManager.spaceMarines;
        SpaceMarine spaceMarine1 = new SpaceMarine();
        spaceMarines.add(spaceMarine1);
        spaceMarine1.setId();
        SpaceMarine spaceMarine2 = new SpaceMarine();
        collectionManager.addIfMax(spaceMarine2);
        if (spaceMarine2.getId()>spaceMarine1.getId()){
            Assertions.assertEquals(1, collectionManager.spaceMarines.size());
        }

    }

}