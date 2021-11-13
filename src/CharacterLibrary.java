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
    public static String ANSI_RESET = "\u001b[0m";
    public static String ANSI_INFO = "\u001b[38;5;136m";
    public static String ANSI_ERROR = "\u001b[38;5;196m";
    final String filePath = System.getProperty("user.dir") + "/src/ConfigFiles/";

    ArrayList<Warrior> warriorArrayList = new ArrayList<>();
    ArrayList<Sorcerer> sorcererArrayList = new ArrayList<>();
    ArrayList<Paladin> paladinArrayList = new ArrayList<>();
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
                warriorArrayList.add(new Warrior(stat[0], Integer.parseInt(stat[1]),
                        Integer.parseInt(stat[2]), Integer.parseInt(stat[3]),
                        Integer.parseInt(stat[4]), Integer.parseInt(stat[5]),
                        Integer.parseInt(stat[6])));
            }
            br.close();
        } catch (IOException e) {
            System.out.println(ANSI_ERROR + "Please enter the correct filepath");
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
            System.out.println(ANSI_ERROR + "Please enter the correct filepath");
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
            System.out.println(ANSI_ERROR + "Please enter the correct filepath");
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
            System.out.println(ANSI_ERROR + "Please enter the correct filepath");
            e.printStackTrace();
        }
    }

    /**
     * display all warriors in hero selection process
     */
    public void displayWarrior() {
        for (int i = 0; i < warriorArrayList.size(); i++) {
            System.out.println(ANSI_INFO + (i + 1) + ". " + warriorArrayList.get(i) + ANSI_RESET);
        }
    }

    /**
     * display all sorcerers in hero selection process
     */
    public void displaySorcerer() {
        for (int i = 0; i < sorcererArrayList.size(); i++) {
            System.out.println(ANSI_INFO + (i + 1) + ". " + sorcererArrayList.get(i) + ANSI_RESET);
        }
    }

    /**
     * display all paladins in hero selection process
     */
    public void displayPaladin() {
        for (int i = 0; i < paladinArrayList.size(); i++) {
            System.out.println(ANSI_INFO + (i + 1) + ". " + paladinArrayList.get(i) + ANSI_RESET);
        }
    }
}
