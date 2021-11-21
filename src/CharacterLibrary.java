import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * CharacterLibrary class maintains information about the contents of all Characters.
 * Character class has three lists for three different kinds of heroes and one list
 * for all monsters that heroes might encounter.
 */
public class CharacterLibrary {
    final String filePath = System.getProperty("user.dir") + "/src/ConfigFiles/";

    // Heroes
    ArrayList<Hero> warriorArrayList = new ArrayList<>();
    ArrayList<Hero> sorcererArrayList = new ArrayList<>();
    ArrayList<Hero> paladinArrayList = new ArrayList<>();

    // Monsters
    ArrayList<Dragon> dragonArrayList = new ArrayList<>();
    ArrayList<Exoskeleton> exoArrayList = new ArrayList<>();
    ArrayList<Spirit> spiritArrayList = new ArrayList<>();

    /**
     * No-arg constructor
     * Scan all heroes and monsters files.
     */
    CharacterLibrary() {
        scan();
    }

    /**
     * Scan all heroes and monsters files.
     */
    public void scan() {
        scanWarrior();
        scanSorcerer();
        scanPaladin();
        scanMonster();
    }

    /**
     * Scan Warrior file
     */
    public void scanWarrior() {
        File file = new File(filePath + "Warriors.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = br.readLine();
            while ((str = br.readLine()) != null) {
                if (str.equals(""))
                    break;
                String[] stat = str.split("\\s+");
                warriorArrayList.add(
                    new Warrior(
                        stat[0], Integer.parseInt(stat[1]),
                        Integer.parseInt(stat[2]), Integer.parseInt(stat[3]),
                        Integer.parseInt(stat[4]), Integer.parseInt(stat[5]),
                        Integer.parseInt(stat[6])
                    )
                );
            }
            br.close();
        } catch (IOException e) {
            System.out.println(Color.getColor().RED + "Please enter the correct filepath");
            e.printStackTrace();
        }
    }

    /**
     * Scan Sorcerer file
     */
    public void scanSorcerer() {
        File file = new File(filePath + "Sorcerers.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = br.readLine();
            while ((str = br.readLine()) != null) {
                if (str.equals(""))
                    break;
                String[] stat = str.split("\\s+");
                sorcererArrayList.add(new Sorcerer(stat[0], Integer.parseInt(stat[1]),
                        Integer.parseInt(stat[2]), Integer.parseInt(stat[3]),
                        Integer.parseInt(stat[4]), Integer.parseInt(stat[5]),
                        Integer.parseInt(stat[6])));
            }
            br.close();
        } catch (IOException e) {
            System.out.println(Color.getColor().RED + "Please enter the correct filepath");
            e.printStackTrace();
        }
    }

    /**
     * Scan Paladin file
     */
    public void scanPaladin() {
        File file = new File(filePath + "Paladins.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = br.readLine();
            while ((str = br.readLine()) != null) {
                if (str.equals(""))
                    break;
                String[] stat = str.split("\\s+");
                paladinArrayList.add(new Paladin(stat[0], Integer.parseInt(stat[1]),
                        Integer.parseInt(stat[2]), Integer.parseInt(stat[3]),
                        Integer.parseInt(stat[4]), Integer.parseInt(stat[5]),
                        Integer.parseInt(stat[6])));
            }
            br.close();
        } catch (IOException e) {
            System.out.println(Color.getColor().RED + "Please enter the correct filepath");
            e.printStackTrace();
        }
    }

    /**
     * Scan Monster files
     */
    public void scanMonster() {
        File file = new File(filePath + "Dragons.txt");
        File file2 = new File(filePath + "Exoskeletons.txt");
        File file3 = new File(filePath + "Spirits.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedReader br2 = new BufferedReader(new FileReader(file2));
            BufferedReader br3 = new BufferedReader(new FileReader(file3));
            String str = br.readLine();
            String str2 = br2.readLine();
            String str3 = br3.readLine();
            while ((str = br.readLine()) != null) {
                if (str.equals(""))
                    break;
                String[] stat = str.split("\\s+");
                dragonArrayList.add(new Dragon(stat[0], Integer.parseInt(stat[1]), 100 * Integer.parseInt(stat[1]),
                        Integer.parseInt(stat[2]), Integer.parseInt(stat[3]), Integer.parseInt(stat[4])));
            }
            while ((str2 = br2.readLine()) != null) {
                if (str2.equals(""))
                    break;
                String[] stat = str2.split("\\s+");
                exoArrayList.add(new Exoskeleton(stat[0], Integer.parseInt(stat[1]), 100 * Integer.parseInt(stat[1]),
                        Integer.parseInt(stat[2]), Integer.parseInt(stat[3]), Integer.parseInt(stat[4])));
            }
            while ((str3 = br3.readLine()) != null) {
                if (str3.equals(""))
                    break;
                String[] stat = str3.split("\\s+");
                spiritArrayList.add(new Spirit(stat[0], Integer.parseInt(stat[1]), 100 * Integer.parseInt(stat[1]),
                        Integer.parseInt(stat[2]), Integer.parseInt(stat[3]), Integer.parseInt(stat[4])));
            }
            br.close();
            br2.close();
            br3.close();
        } catch (IOException e) {
            System.out.println(Color.getColor().RED + "Please enter the correct filepath");
            e.printStackTrace();
        }
    }

    /**
     * display heroes in the given list.
     *
     * @param list A list of heroes.
     */
    private void displayHero(ArrayList<Hero> list) {
        String border =
            Color.getColor().PURPLE +
            "-------------------------------------------------------------------------------------------" +
            Color.getColor().RESET + "\n";

        String title =
            "Name                       HP    Level    Mana    Strength    Dexterity    Agility    Money";

        System.out.print(border);
        System.out.println(title);
        System.out.print(border);

        for (int i = 0; i < list.size(); i++) {
            Hero hero = list.get(i);

            String name = (i + 1) + ". " + hero.getName();
            int level = hero.getLevel();
            int money = hero.getMoney();

            int hp = hero.getHP();
            int mana = hero.getMP();

            int strength = hero.getStrength();
            int dexterity = hero.getDexterity();
            int agility = hero.getAgility();

            System.out.printf(
                "%-25s %-8d %-6d %-9d %-12d %-11d %-8d %-8d",
                name, hp, level, mana, strength, dexterity, agility, money
            );
            System.out.println();
        }

        System.out.println(border);
    }

    /**
     * display all warriors in hero selection process
     */
    public void displayWarrior() {
        displayHero(warriorArrayList);
    }

    /**
     * display all sorcerers in hero selection process
     */
    public void displaySorcerer() {
        displayHero(sorcererArrayList);
    }

    /**
     * display all paladins in hero selection process
     */
    public void displayPaladin() {
        displayHero(paladinArrayList);
    }
}
