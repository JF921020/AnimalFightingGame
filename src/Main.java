import Animal.*;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;
        

        do {
            System.out.print(ConsoleColor.GREEN + "請選擇遊戲模式(1.Random 2.Free 3.Continuous): " + ConsoleColor.RESET);
            int mode = scanner.nextInt();
            if (mode == 1) { //隨機敵人
                Animal player = playerChoose();
                Animal boss = bossChoose();
                Random random = new Random();


                if (player == null || boss == null) {
                    System.out.println(ConsoleColor.RED + "輸入不合法" + ConsoleColor.RESET);
                    scanner.close();
                    return;
                }

                System.out.println(ConsoleColor.PURPLE + "敵方 HP: " + boss.hp + "/200" + ConsoleColor.RESET);

                while (player.hp > 0 && boss.hp > 0) {
                    boss.choice = random.nextInt(3) + 1;
                    player.showSkill();
                    System.out.print(ConsoleColor.GREEN + "請輸入技能編號(1~4): " + ConsoleColor.RESET);
                    player.choice = scanner.nextInt();

                    player.judge(player, boss);

                    if (player.hp <= 0) {
                        System.out.println(ConsoleColor.RED + "你輸了" + ConsoleColor.RESET);
                    } else if (boss.hp <= 0) {
                        System.out.println(ConsoleColor.YELLOW + "你贏了" + ConsoleColor.RESET);
                    }

                }
            } else if (mode == 2) {//自選敵人
                Animal player = playerChoose();


            } else if (mode == 3) {//連續戰鬥
                Animal player = playerChoose();


            } else {
                System.out.println(ConsoleColor.YELLOW + "開發中~" + ConsoleColor.RESET);
            }

            while (true) {
                System.out.print(ConsoleColor.YELLOW + "是否再玩一次? (y/n): " + ConsoleColor.RESET);
                String input = scanner.next().toLowerCase();
                if (input.equals("y")) {
                    playAgain = true;
                    break;
                } else if (input.equals("n")) {
                    playAgain = false;
                    break;
                } else {
                    System.out.println(ConsoleColor.RED + "無效輸入，請輸入 'y' 或 'n'!" + ConsoleColor.RESET);
                }
            }

        } while (playAgain);


        System.out.println(ConsoleColor.YELLOW + "感謝遊玩!" + ConsoleColor.RESET);
        scanner.close();
    }

    public static Animal playerChoose() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ConsoleColor.GREEN + "請輸入想選的動物編號(1.狗 2.貓 3.熊 4.老虎): " + ConsoleColor.RESET);
        return getAnimal(scanner);
    }

    public static Animal bossChoose() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ConsoleColor.GREEN + "請輸入想選的敵方動物編號(1.狗 2.貓 3.熊 4.老虎): " + ConsoleColor.RESET);
        return getAnimal(scanner);
    }

    private static Animal getAnimal(Scanner scanner) {
        int choose = scanner.nextInt();

        if(choose == 1) {
            return new Dog();
        }else if(choose == 2) {
            return new Cat();
        }else if(choose == 3) {
            return new Bear();
        }else if(choose == 4) {
            return new Tiger();
        }else{
            return null;
        }
    }
}