import java.io.*;
import java.util.*;
/**
 * Market class maintains information about the contents of all Items.
 * Market class has four lists, for armors, weapons, potions, and spells
 */
public class Market {
    public static String ANSI_RESET = "\u001b[0m";
    public static String ANSI_ITEM = "\u001b[38;5;11m";
    public static String ANSI_ERROR = "\u001b[38;5;196m";
    public static String ANSI_PROMPT = "\u001b[38;5;230m";
    public static String ANSI_SELECT = "\u001b[38;5;106m";
    public static String ANSI_GOODNEWS = "\u001b[38;5;31m";
    public static String ANSI_BADNEWS = "\u001b[38;5;124m";

    final String filePath = System.getProperty("user.dir") + "/src/ConfigFiles/";

    ArrayList<Armor> armorArrayList = new ArrayList<>();
    ArrayList<Weapon> weaponArrayList = new ArrayList<>();
    ArrayList<Potion> potionArrayList = new ArrayList<>();
    ArrayList<Spell> spellArrayList = new ArrayList<>();

    /**
     * No-arg Constructor
     */
    Market(){
        initial();
    }

    /**
     * Scan each item category
     */
    public void initial(){
        initialArmor();
        initialWeapon();
        initialPotion();
        initialSpell();
    }

    /**
     * Scan armor file
     */
    public void initialArmor(){
        File file = new File(filePath + "Armory.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = br.readLine();
            while((str = br.readLine()) != null){
                if(str.equals(""))
                    break;
                String[] stat = str.split("\\s+");
                armorArrayList.add(new Armor(stat[0], Integer.parseInt(stat[1]),
                        Integer.parseInt(stat[2]), Integer.parseInt(stat[3])));
            }
            br.close();
        }catch (IOException e){
            System.out.println(ANSI_ERROR + "Please enter the correct filepath");
            e.printStackTrace();
        }
    }

    /**
     * Scan weapon file
     */
    public void initialWeapon(){
        File file = new File(filePath + "Weaponry.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = br.readLine();
            while((str = br.readLine()) != null){
                if(str.equals(""))
                    break;
                String[] stat = str.split("\\s+");
                weaponArrayList.add(new Weapon(stat[0], Integer.parseInt(stat[1]), Integer.parseInt(stat[2]),
                        Integer.parseInt(stat[3]), Integer.parseInt(stat[4])));
            }
            br.close();
        }catch (IOException e){
            System.out.println(ANSI_ERROR + "Please enter the correct filepath");
            e.printStackTrace();
        }
    }

    /**
     * Scan potion file
     */
    public void initialPotion(){
        File file = new File(filePath + "Potions.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = br.readLine();
            while((str = br.readLine()) != null){
                if(str.equals(""))
                    break;
                String[] stat = str.split("\\s+");
                potionArrayList.add(new Potion(stat[0], Integer.parseInt(stat[1]), Integer.parseInt(stat[2]),
                        Integer.parseInt(stat[3]), stat[4]));
            }
            br.close();
        }catch (IOException e){
            System.out.println(ANSI_ERROR + "Please enter the correct filepath");
            e.printStackTrace();
        }
    }

    /**
     * Scan spell file
     */
    public void initialSpell(){
        File file = new File(filePath + "FireSpells.txt");
        File file2 = new File(filePath + "IceSpells.txt");
        File file3 = new File(filePath + "LightningSpells.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedReader br2 = new BufferedReader(new FileReader(file2));
            BufferedReader br3 = new BufferedReader(new FileReader(file3));
            String str = br.readLine();
            String str2 = br2.readLine();
            String str3 = br3.readLine();
            while((str = br.readLine()) != null){
                if(str.equals(""))
                    break;
                String[] stat = str.split("\\s+");
                spellArrayList.add(new FireSpell(stat[0], Integer.parseInt(stat[1]), Integer.parseInt(stat[2]),
                        Integer.parseInt(stat[3]), Integer.parseInt(stat[4])));
            }
            while((str2 = br2.readLine()) != null){
                if(str2.equals(""))
                    break;
                String[] stat = str2.split("\\s+");
                spellArrayList.add(new IceSpell(stat[0], Integer.parseInt(stat[1]), Integer.parseInt(stat[2]),
                        Integer.parseInt(stat[3]), Integer.parseInt(stat[4])));
            }
            while((str3 = br3.readLine()) != null){
                if(str3.equals(""))
                    break;
                String[] stat = str3.split("\\s+");
                spellArrayList.add(new LightningSpell(stat[0], Integer.parseInt(stat[1]), Integer.parseInt(stat[2]),
                        Integer.parseInt(stat[3]), Integer.parseInt(stat[4])));
            }
            br.close();
            br2.close();
            br3.close();
        }catch (IOException e){
            System.out.println(ANSI_ERROR + "Please enter the correct filepath");
            e.printStackTrace();
        }
    }

    /**
     * Display all armor info to player during purchase
     */
    public void displayArmor(){
        System.out.println(ANSI_ITEM + "Armors:");
        for (int i = 0; i < armorArrayList.size(); i++) {
            System.out.println(ANSI_ITEM + (i+1) + ". " + armorArrayList.get(i) + ANSI_RESET);
        }
    }

    /**
     * Display all weapons info to player during purchase
     */
    public void displayWeapon(){
        System.out.println(ANSI_ITEM + "Weapons:");
        for (int i = 0; i < weaponArrayList.size(); i++) {
            System.out.println(ANSI_ITEM + (i+1) + ". " + weaponArrayList.get(i) + ANSI_RESET);
        }
    }

    /**
     * Display all potions' info to player during purchase
     */
    public void displayPotion(){
        System.out.println(ANSI_ITEM + "Potions:");
        for (int i = 0; i < potionArrayList.size(); i++) {
            System.out.println(ANSI_ITEM + (i+1) + ". " + potionArrayList.get(i) + ANSI_RESET);
        }
    }

    /**
     * Display all spells info to player during purchase
     */
    public void displaySpell(){
        System.out.println(ANSI_ITEM + "Spells:");
        for (int i = 0; i < spellArrayList.size(); i++) {
            System.out.println(ANSI_ITEM + (i+1) + ". " + spellArrayList.get(i) + ANSI_RESET);
        }
    }

    /**
     * This method performs shopping process
     */
    public void shopping(Hero hero){
        Scanner sc = new Scanner(System.in);
        boolean leave = false;

        while(!leave){
            System.out.println(ANSI_PROMPT + "Welcome to PROFITEER's Store. ");
            System.out.print(ANSI_PROMPT + "Do you want to buy or sell? 'B' to buy, 'S' to sell: ");
            String decision = sc.next();

            // if input is invalid, leave the store
            if(decision.equalsIgnoreCase("B"))
                purchase(hero);
            else if(decision.equalsIgnoreCase("S"))
                sell(hero);

            // make sure player wants to leave
            System.out.print(ANSI_PROMPT + "Are you done for this hero? 'N' = stay, any other keys = leave ");
            decision = sc.next();
            if(!decision.equalsIgnoreCase("N"))
                leave = true;
        }
    }

    /**
     * The method performs purchase process
     * @param hero hero that wants to purchase
     */
    public void purchase(Hero hero){
        Scanner sc = new Scanner(System.in);

        System.out.print(ANSI_SELECT + "1. Weapon\n2. Armor\n3. Potion\n4. Spell\n" +
                "Any other keys: I want to leave\nWhat do you want: ");
        String decision = sc.next();
        int itemNo;

        switch (decision) {
            case "1":
                displayWeapon();

                System.out.print(ANSI_PROMPT + "Which one you want to buy: ");
                decision = sc.next();
                // validate input is valid

                while(!decision.matches("[0-9]*") || Integer.parseInt(decision) < 1 ||
                        Integer.parseInt(decision) > weaponArrayList.size()){
                    System.out.print(ANSI_ERROR + "Your selection is invalid, try again: " + ANSI_RESET);
                    decision = sc.next();
                }
                itemNo = Integer.parseInt(decision) - 1;

                //validate hero is qualified
                if (hero.getLevel() >= weaponArrayList.get(itemNo).getMinLv() &&
                        hero.getMoney() >= weaponArrayList.get(itemNo).getCost()) {
                    hero.getStorage().add(weaponArrayList.get(itemNo));
                    hero.setMoney(hero.getMoney() - weaponArrayList.get(itemNo).getCost());
                    System.out.println(ANSI_GOODNEWS + "You successfully bought a weapon.");
                }
                else
                    System.out.println(ANSI_BADNEWS + "You failed buying a weapon.");
                break;
            case "2":
                displayArmor();

                System.out.print(ANSI_PROMPT + "Which one you want to buy: ");
                decision = sc.next();

                // validate input is valid
                while(!decision.matches("[0-9]*") || Integer.parseInt(decision) < 1 ||
                        Integer.parseInt(decision) > armorArrayList.size()){
                    System.out.print(ANSI_ERROR + "Your selection is invalid, try again: " + ANSI_RESET);
                    decision = sc.next();
                }
                itemNo = Integer.parseInt(decision) - 1;

                //validate hero is qualified
                if (hero.getLevel() >= armorArrayList.get(itemNo).getMinLv()
                        && hero.getMoney() >= armorArrayList.get(itemNo).getCost()) {
                    hero.getStorage().add(armorArrayList.get(itemNo));
                    hero.setMoney(hero.getMoney() - armorArrayList.get(itemNo).getCost());
                    System.out.println(ANSI_GOODNEWS + "You successfully bought an armor.");
                }
                else
                    System.out.println(ANSI_BADNEWS + "You failed buying an armor.");

                break;
            case "3":
                displayPotion();

                System.out.print(ANSI_PROMPT + "Which one you want to buy: ");
                decision = sc.next();

                // validate input is valid
                while(!decision.matches("[0-9]*") || Integer.parseInt(decision) < 1 ||
                        Integer.parseInt(decision) > potionArrayList.size()){
                    System.out.print(ANSI_ERROR + "Your selection is invalid, try again: " + ANSI_RESET);
                    decision = sc.next();
                }
                itemNo = Integer.parseInt(decision) - 1;

                //validate hero is qualified
                if (hero.getLevel() >= potionArrayList.get(itemNo).getMinLv()
                        && hero.getMoney() >= potionArrayList.get(itemNo).getCost()) {
                    hero.getStorage().add(potionArrayList.get(itemNo));
                    hero.setMoney(hero.getMoney() - potionArrayList.get(itemNo).getCost());
                    System.out.println(ANSI_GOODNEWS + "You successfully bought a potion.");
                }
                else
                    System.out.println(ANSI_BADNEWS + "You failed buying a potion.");
                break;
            case "4":
                displaySpell();

                System.out.print(ANSI_PROMPT + "Which one you want to buy: ");
                decision = sc.next();

                // validate input is valid
                while(!decision.matches("[0-9]*") || Integer.parseInt(decision) < 1 ||
                        Integer.parseInt(decision) > spellArrayList.size()){
                    System.out.print(ANSI_ERROR + "Your selection is invalid, try again: " + ANSI_RESET);
                    decision = sc.next();
                }
                itemNo = Integer.parseInt(decision) - 1;

                //validate hero is qualified
                if (hero.getLevel() >= spellArrayList.get(itemNo).getMinLv()
                        && hero.getMoney() >= spellArrayList.get(itemNo).getCost()) {
                    hero.getStorage().add(spellArrayList.get(itemNo));
                    hero.setMoney(hero.getMoney() - spellArrayList.get(itemNo).getCost());
                    System.out.println(ANSI_GOODNEWS + "You successfully bought a spell.");
                }
                else
                    System.out.println(ANSI_BADNEWS + "You failed buying a spell.");
                break;
            default:
                System.out.println(ANSI_PROMPT + "Hmm, liar! You don't want to buy anything, do ya?");
        }
    }

    /**
     * The method performs sell process
     * @param hero hero wants to sell
     */
    public void sell(Hero hero){
        Scanner sc = new Scanner(System.in);

        //display everything in this hero's inventory
        for(int i = 0; i < hero.getStorage().size(); i++)
            System.out.println(ANSI_SELECT + (i+1) + ". " + hero.getStorage().get(i));

        //if there's anything can sell
        if(hero.getStorage().size() != 0) {
            System.out.print(ANSI_PROMPT + "Which item you want to sell?");
            String decision = sc.next();

            while(!decision.matches("[0-9]*")){
                System.out.print(ANSI_ERROR + "Your selection is invalid, try again: " + ANSI_RESET);
                decision = sc.next();
            }
            int itemNo = Integer.parseInt(decision) - 1;

            //after validate, sell the item
            if(itemNo >= 0 && itemNo < hero.getStorage().size()){
                hero.setMoney(hero.getMoney() + hero.getStorage().get(itemNo).getCost() / 2);
                hero.getStorage().remove(itemNo);
                System.out.println(ANSI_GOODNEWS + "You successfully sold an item.");
            }
            else
                System.out.println(ANSI_BADNEWS + "You failed to sell an item. Verify your item No.");
        }
        else
            System.out.println(ANSI_PROMPT + "Dude you have nothing to sell!");
    }
}
