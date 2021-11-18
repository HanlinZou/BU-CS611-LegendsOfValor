import java.io.*;
import java.util.*;

/**
 * Market class maintains information about the contents of all Items.
 * Market class has four lists, for armors, weapons, potions, and spells
 */
public class Market {
    final String filePath = System.getProperty("user.dir") + "/src/ConfigFiles/";

    ArrayList<Armor> armorArrayList = new ArrayList<>();
    ArrayList<Weapon> weaponArrayList = new ArrayList<>();
    ArrayList<Potion> potionArrayList = new ArrayList<>();
    ArrayList<Spell> spellArrayList = new ArrayList<>();

    /**
     * No-arg Constructor
     */
    Market() {
        initial();
    }

    /**
     * Scan each item category
     */
    public void initial() {
        initialArmor();
        initialWeapon();
        initialPotion();
        initialSpell();
    }

    /**
     * Scan armor file
     */
    public void initialArmor() {
        File file = new File(filePath + "Armory.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = br.readLine();
            while ((str = br.readLine()) != null) {
                if (str.equals(""))
                    break;
                String[] stat = str.split("\\s+");
                armorArrayList.add(new Armor(stat[0], Integer.parseInt(stat[1]),
                        Integer.parseInt(stat[2]), Integer.parseInt(stat[3])));
            }
            br.close();
        } catch (IOException e) {
            System.out.println(Color.RED + "Please enter the correct filepath");
            e.printStackTrace();
        }
    }

    /**
     * Scan weapon file
     */
    public void initialWeapon() {
        File file = new File(filePath + "Weaponry.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = br.readLine();
            while ((str = br.readLine()) != null) {
                if (str.equals(""))
                    break;
                String[] stat = str.split("\\s+");
                weaponArrayList.add(new Weapon(stat[0], Integer.parseInt(stat[1]), Integer.parseInt(stat[2]),
                        Integer.parseInt(stat[3]), Integer.parseInt(stat[4])));
            }
            br.close();
        } catch (IOException e) {
            System.out.println(Color.RED + "Please enter the correct filepath");
            e.printStackTrace();
        }
    }

    /**
     * Scan potion file
     */
    public void initialPotion() {
        File file = new File(filePath + "Potions.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = br.readLine();
            while ((str = br.readLine()) != null) {
                if (str.equals(""))
                    break;
                String[] stat = str.split("\\s+");
                potionArrayList.add(new Potion(stat[0], Integer.parseInt(stat[1]), Integer.parseInt(stat[2]),
                        Integer.parseInt(stat[3]), stat[4]));
            }
            br.close();
        } catch (IOException e) {
            System.out.println(Color.RED + "Please enter the correct filepath");
            e.printStackTrace();
        }
    }

    /**
     * Scan spell file
     */
    public void initialSpell() {
        File file = new File(filePath + "FireSpells.txt");
        File file2 = new File(filePath + "IceSpells.txt");
        File file3 = new File(filePath + "LightningSpells.txt");
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
                spellArrayList.add(new FireSpell(stat[0], Integer.parseInt(stat[1]), Integer.parseInt(stat[2]),
                        Integer.parseInt(stat[3]), Integer.parseInt(stat[4])));
            }
            while ((str2 = br2.readLine()) != null) {
                if (str2.equals(""))
                    break;
                String[] stat = str2.split("\\s+");
                spellArrayList.add(new IceSpell(stat[0], Integer.parseInt(stat[1]), Integer.parseInt(stat[2]),
                        Integer.parseInt(stat[3]), Integer.parseInt(stat[4])));
            }
            while ((str3 = br3.readLine()) != null) {
                if (str3.equals(""))
                    break;
                String[] stat = str3.split("\\s+");
                spellArrayList.add(new LightningSpell(stat[0], Integer.parseInt(stat[1]), Integer.parseInt(stat[2]),
                        Integer.parseInt(stat[3]), Integer.parseInt(stat[4])));
            }
            br.close();
            br2.close();
            br3.close();
        } catch (IOException e) {
            System.out.println(Color.RED + "Please enter the correct filepath");
            e.printStackTrace();
        }
    }

    /**
     * Display all armor info to player during purchase
     */
    public void displayArmor() {
        String border = getBorder();

        String title = "Name                    Price    Required Level    Damage Reduction";

        System.out.println(Color.CYAN + "Armors:" + Color.RESET);

        System.out.print(border);
        System.out.println(title);
        System.out.print(border);

        for (int i = 0; i < armorArrayList.size(); i++) {
            Armor a = armorArrayList.get(i);

            String name = (i + 1) + ". " + a.getName();
            int cost = a.getCost();
            int level = a.getMinLv();
            int damageReduction = a.getDefense();

            System.out.printf("%-23s %-10d %-15d %-14d", name, cost, level, damageReduction);
            System.out.println();
        }
        System.out.println(border);
    }

    /**
     * Display all weapons info to player during purchase
     */
    public void displayWeapon() {
        String border = getBorder();
        String title =
            "Name                  Price    Required Level    Base Damage    Required Hands";

        System.out.println(Color.CYAN + "Weapons:" + Color.RESET);

        System.out.print(border);
        System.out.println(title);
        System.out.print(border);

        for (int i = 0; i < weaponArrayList.size(); i++) {
            Weapon w = weaponArrayList.get(i);

            // Print all the stats of all the weapons
            String name = (i + 1) + ". " + w.getName();
            int cost = w.getCost();
            int level = w.getMinLv();
            int damage = w.getDamage();
            int hands = w.getHands();

            System.out.printf("%-21s %-10d %-15d %-15d %-5d", name, cost, level, damage, hands);
            System.out.println();
        }
        System.out.println(border);
    }

    /**
     * Display all potions' info to player during purchase
     */
    public void displayPotion() {
        String border = getBorder();

        String title =
            "Name                    Price    Required Level    Increased Amount    Affected Attr";

        System.out.println(Color.CYAN + "Potions:" + Color.RESET);

        System.out.print(border);
        System.out.println(title);
        System.out.print(border);

        for (int i = 0; i < potionArrayList.size(); i++) {
            Potion p = potionArrayList.get(i);

            String name = (i + 1) + ". " + p.getName();
            int cost = p.getCost();
            int level = p.getMinLv();
            int increase = p.getStatInc();
            String affected = p.getStatCategory();

            System.out.printf("%-23s %-10d %-15d %-19d %-9s", name, cost, level, increase, affected);
            System.out.println();
        }
        System.out.println(border);
    }

    /**
     * Display all spells info to player during purchase
     */
    public void displaySpell() {
        String border = getBorder();

        String title =
            "Name                    Price    Required Level    Base Damage    Mana Cost    Spell Type";

        System.out.println(Color.CYAN + "Spells:" + Color.RESET);

        System.out.print(border);
        System.out.println(title);
        System.out.print(border);

        for (int i = 0; i < spellArrayList.size(); i++) {
            Spell s = spellArrayList.get(i);

            String name = (i + 1) + ". " + s.getName();
            int cost = s.getCost();
            int level = s.getMinLv();
            int damage = s.getDamage();
            int manaCost = s.getManaCost();
            String type = s.getType();

            System.out.printf("%-24s %-14d %-13d %-14d %-9d %-16s", name, cost, level, damage, manaCost, type);
            System.out.println();
        }
        System.out.println(border);
    }

    private String getBorder() {
        return Color.CYAN +
            "------------------------------------------------------------------------------------------" +
            Color.RESET + "\n";
    }

    /**
     * This method performs shopping process
     */
    public void shopping(Hero hero) {
        Scanner sc = new Scanner(System.in);
        boolean leave = false;

        while (!leave) {
            System.out.println(Color.YELLOW + "Welcome to PROFITEER's Store. ");
            System.out.print(Color.YELLOW + "Do you want to buy or sell? 'B' to buy, 'S' to sell: ");
            String decision = sc.next();

            // if input is invalid, leave the store
            if (decision.equalsIgnoreCase("B"))
                purchase(hero);
            else if (decision.equalsIgnoreCase("S"))
                sell(hero);

            // make sure player wants to leave
            System.out.print(Color.YELLOW + "Are you done for this hero? 'N' = stay, any other keys = leave ");
            decision = sc.next();
            if (!decision.equalsIgnoreCase("N"))
                leave = true;
        }
    }

    /**
     * The method performs purchase process
     *
     * @param hero hero that wants to purchase
     */
    public void purchase(Hero hero) {
        Scanner sc = new Scanner(System.in);

        System.out.print(Color.GREEN + "1. Weapon\n2. Armor\n3. Potion\n4. Spell\n" +
                "Any other keys: I want to leave\nWhat do you want: ");
        String decision = sc.next();
        int itemNo;

        switch (decision) {
            case "1":
                displayWeapon();

                System.out.print(Color.YELLOW + "Which one you want to buy: ");
                decision = sc.next();
                // validate input is valid

                while(!decision.matches("[0-9]*") || Integer.parseInt(decision) < 1 ||
                        Integer.parseInt(decision) > weaponArrayList.size()) {
                    System.out.print(Color.RED + "Your selection is invalid, try again: " + Color.RESET);
                    decision = sc.next();
                }
                itemNo = Integer.parseInt(decision) - 1;

                // validate hero is qualified
                if (hero.getLevel() >= weaponArrayList.get(itemNo).getMinLv() &&
                        hero.getMoney() >= weaponArrayList.get(itemNo).getCost()) {
                    hero.getStorage().add(weaponArrayList.get(itemNo));
                    hero.setMoney(hero.getMoney() - weaponArrayList.get(itemNo).getCost());
                    System.out.println(Color.GREEN + "You successfully bought a weapon.");
                } else
                    System.out.println(Color.RED + "You failed buying a weapon.");
                break;
            case "2":
                displayArmor();

                System.out.print(Color.YELLOW + "Which one you want to buy: ");
                decision = sc.next();

                // validate input is valid
                while(!decision.matches("[0-9]*") || Integer.parseInt(decision) < 1 ||
                        Integer.parseInt(decision) > armorArrayList.size()) {
                    System.out.print(Color.RED + "Your selection is invalid, try again: " + Color.RESET);
                    decision = sc.next();
                }
                itemNo = Integer.parseInt(decision) - 1;

                // validate hero is qualified
                if (hero.getLevel() >= armorArrayList.get(itemNo).getMinLv()
                        && hero.getMoney() >= armorArrayList.get(itemNo).getCost()) {
                    hero.getStorage().add(armorArrayList.get(itemNo));
                    hero.setMoney(hero.getMoney() - armorArrayList.get(itemNo).getCost());
                    System.out.println(Color.GREEN + "You successfully bought an armor.");
                } else
                    System.out.println(Color.RED + "You failed buying an armor.");

                break;
            case "3":
                displayPotion();

                System.out.print(Color.YELLOW + "Which one you want to buy: ");
                decision = sc.next();

                // validate input is valid
                while(!decision.matches("[0-9]*") || Integer.parseInt(decision) < 1 ||
                        Integer.parseInt(decision) > potionArrayList.size()) {
                    System.out.print(Color.RED + "Your selection is invalid, try again: " + Color.RESET);
                    decision = sc.next();
                }
                itemNo = Integer.parseInt(decision) - 1;

                // validate hero is qualified
                if (hero.getLevel() >= potionArrayList.get(itemNo).getMinLv()
                        && hero.getMoney() >= potionArrayList.get(itemNo).getCost()) {
                    hero.getStorage().add(potionArrayList.get(itemNo));
                    hero.setMoney(hero.getMoney() - potionArrayList.get(itemNo).getCost());
                    System.out.println(Color.GREEN + "You successfully bought a potion.");
                } else
                    System.out.println(Color.RED + "You failed buying a potion.");
                break;
            case "4":
                displaySpell();

                System.out.print(Color.YELLOW + "Which one you want to buy: ");
                decision = sc.next();

                // validate input is valid
                while(!decision.matches("[0-9]*") || Integer.parseInt(decision) < 1 ||
                        Integer.parseInt(decision) > spellArrayList.size()) {
                    System.out.print(Color.RED + "Your selection is invalid, try again: " + Color.RESET);
                    decision = sc.next();
                }
                itemNo = Integer.parseInt(decision) - 1;

                // validate hero is qualified
                if (hero.getLevel() >= spellArrayList.get(itemNo).getMinLv()
                        && hero.getMoney() >= spellArrayList.get(itemNo).getCost()) {
                    hero.getStorage().add(spellArrayList.get(itemNo));
                    hero.setMoney(hero.getMoney() - spellArrayList.get(itemNo).getCost());
                    System.out.println(Color.GREEN + "You successfully bought a spell.");
                } else
                    System.out.println(Color.RED + "You failed buying a spell.");
                break;
            default:
                System.out.println(Color.YELLOW + "Hmm, liar! You don't want to buy anything, do ya?");
            }
    }

    /**
     * The method performs sell process
     *
     * @param hero hero wants to sell
     */
    public void sell(Hero hero) {
        Scanner sc = new Scanner(System.in);

        // display everything in this hero's inventory
        for (int i = 0; i < hero.getStorage().size(); i++)
            System.out.println(Color.GREEN + (i + 1) + ". " + hero.getStorage().get(i));

        // if there's anything can sell
        if (hero.getStorage().size() != 0) {
            System.out.print(Color.YELLOW + "Which item you want to sell?");
            String decision = sc.next();

            while (!decision.matches("[0-9]*")) {
                System.out.print(Color.RED + "Your selection is invalid, try again: " + Color.RESET);
                decision = sc.next();
            }
            int itemNo = Integer.parseInt(decision) - 1;

            // after validate, sell the item
            if (itemNo >= 0 && itemNo < hero.getStorage().size()) {
                hero.setMoney(hero.getMoney() + hero.getStorage().get(itemNo).getCost() / 2);
                hero.getStorage().remove(itemNo);
                System.out.println(Color.GREEN + "You successfully sold an item.");
            } else
                System.out.println(Color.RED + "You failed to sell an item. Verify your item No.");
        } else
            System.out.println(Color.YELLOW + "Dude you have nothing to sell!");
    }
}
