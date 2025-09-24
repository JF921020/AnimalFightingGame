package user;

import user.animal.*;
import user.function.*;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
public class Game{
    public static boolean battleCalled = false;

    /**
     * 遊戲主體
     */
    public static void start(Random rand)  {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;
        do {
            int mode = selectMode(scanner);
            handleGameMode(mode,scanner,rand);
            playAgain = Replay(scanner);
        } while (playAgain);
        scanner.close();
    }

    /**
     *依照模式選擇角色
     */
    public static void handleGameMode(int mode, Scanner scanner, Random random) {
        Animal player = null;
        Animal boss = null;

        switch (mode) {
            case 1 -> {
                System.out.print("以選擇隨機模式，進入選角畫面\n");
                player = playerChoose(scanner);
                boss = getEnemy(random.nextInt(4) + 1);
                battle(player, boss, scanner, random);
            }
            case 2 -> {
                System.out.print("以選擇自由模式，進入選角畫面\n");
                player = playerChoose(scanner);
                boss = bossChoose(scanner);
                battle(player, boss, scanner, random);
            }
            case 3 -> {
                System.out.print("以選擇完全隨機模式\n");
                player = getAnimal(random.nextInt(4) + 1);
                boss = getEnemy(random.nextInt(4) + 1);
                battle(player, boss, scanner, random);
            }
            default -> {
                System.out.println(ConsoleColor.YELLOW + "開發中~" + ConsoleColor.RESET);
            }
        }

    }

    /**
     *遊戲模式選擇
     */
    public static int selectMode(Scanner scanner) {
        System.out.print(ConsoleColor.GREEN + "請選擇遊戲模式(1.Random 2.Free 3.Full Random): " + ConsoleColor.RESET);
        return scanner.nextInt();
    }

    /**
     *重開遊戲
     */
    public static boolean Replay(Scanner scanner) {
        System.out.print(ConsoleColor.YELLOW + "是否再玩一次? (y/n): " + ConsoleColor.RESET);
        while (true) {
            try {
                String input = scanner.next().toLowerCase();
                if (input.equals("y")) {
                    return true;
                } else if (input.equals("n")) {
                    System.out.println(ConsoleColor.YELLOW + "感謝遊玩!" + ConsoleColor.RESET);
                    return false;
                } else {
                    throw new IOException("不是 y 或 n");
                }
            } catch (IOException e) {
                System.out.println(ConsoleColor.RED + "無效輸入，請輸入 'y' 或 'n'!" + ConsoleColor.RESET);
                scanner.nextLine();
                System.out.print(ConsoleColor.YELLOW + "是否再玩一次? (y/n): " + ConsoleColor.RESET);
            }
        }
    }

    /**
     *勝負判定
    **/
    public static void battle(Animal player, Animal boss, Scanner scanner, Random random) {
        battleCalled = true;
        System.out.println("進入戰鬥");
        System.out.println("-----------------------------------");
        System.out.println("-----------------------------------");
        while (player.hp > 0 && boss.hp > 0) {
            boss.choice = random.nextInt(4) + 1;
            System.out.println(ConsoleColor.BLUE + "玩家的回合" + ConsoleColor.RESET);
            System.out.println(player.showSkill());
            System.out.print(ConsoleColor.GREEN + "請輸入技能編號(1~4)，或使用道具(5): " + ConsoleColor.RESET);
            player.choice = scanner.nextInt();

            player.userAction(player, boss,scanner);

            if (player.hp <= 0) {
                System.out.println(ConsoleColor.RED + "你輸了" + ConsoleColor.RESET);
            }if (boss.hp <= 0) {
                System.out.println(ConsoleColor.YELLOW + "你贏了" + ConsoleColor.RESET);
            }
        }
    }

    /**
     *玩家角色選擇
     */
    public static Animal playerChoose(Scanner scanner) {
        String prompt = "請輸入想選的動物編號(1.狗 2.貓 3.熊 4.老虎): ";
        while (true) {
            System.out.print(ConsoleColor.GREEN + prompt + ConsoleColor.RESET);
            int choose = scanner.nextInt();
            if (choose >= 1 && choose <= 4) {
                return getAnimal(choose);
            } else if(choose == 99) {
                TestAnimal testanimal = new TestAnimal("test-player",10000,10,1000,0);
                return testanimal;
            } else {
                System.out.println(ConsoleColor.CYAN + "角色不存在，請重新選擇角色!" + ConsoleColor.RESET);
            }
        }
    }

    /**
     *敵方角色選擇
     */
    public static Animal bossChoose(Scanner scanner){
        String prompt = "請輸入想選的敵方動物編號(1.狗 2.貓 3.熊 4.老虎): ";
        while (true) {
            System.out.print(ConsoleColor.GREEN + prompt + ConsoleColor.RESET);
            int choose = scanner.nextInt();
            if (choose >= 1 && choose <= 4){
                return getEnemy(choose);
            } else if(choose == 99) {
                TestAnimal testanimal = new TestAnimal("test-boss",10000,10,10,0);
                return testanimal;
            } else {
                System.out.println(ConsoleColor.CYAN + "角色不存在，請重新選擇角色!" + ConsoleColor.RESET);
            }
        }
    }

    /**
     *玩家角色生成
     */
    static Animal getAnimal(int choose) {
        switch (choose) {
            case 1:
                System.out.println(ConsoleColor.PURPLE + "玩家(狗)" + ConsoleColor.RESET);
                return new Dog();
            case 2:
                System.out.println(ConsoleColor.PURPLE + "玩家(貓)" + ConsoleColor.RESET);
                return new Cat();
            case 3:
                System.out.println(ConsoleColor.PURPLE + "玩家(熊)" + ConsoleColor.RESET);
                return new Bear();
            case 4:
                System.out.println(ConsoleColor.PURPLE + "玩家(老虎)" + ConsoleColor.RESET);
                return new Tiger();
            default:
                return null;
        }
    }

    /**
     *敵方角色生成
     */
    static Animal getEnemy(int choose) {
        switch (choose) {
            case 1:
                System.out.println(ConsoleColor.PURPLE + "敵方(狗)" + ConsoleColor.RESET);
                return new Dog();
            case 2:
                System.out.println(ConsoleColor.PURPLE + "敵方(貓)" + ConsoleColor.RESET);
                return new Cat();
            case 3:
                System.out.println(ConsoleColor.PURPLE + "敵方(熊)" + ConsoleColor.RESET);
                return new Bear();
            case 4:
                System.out.println(ConsoleColor.PURPLE + "敵方(老虎)" + ConsoleColor.RESET);
                return new Tiger();
            default:
                return null;
        }
    }


}