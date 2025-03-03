import Animal.*;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;
        do{
            System.out.print(ConsoleColor.GREEN + "請輸入想選的動物編號(1.狗 2.貓 3.熊 4.老虎): " + ConsoleColor.RESET);
            int choose = scanner.nextInt();

            Animal player = switch (choose) {
                case 1 -> new Dog();
                case 2 -> new Cat();
                case 3 -> new Bear();
                case 4 -> new Tiger();
                default -> null;
            };



            if (player == null) {
                System.out.println(ConsoleColor.RED + "輸入不合法" + ConsoleColor.RESET);
                scanner.close();
                return;
            }

            Boss boss = new Boss();
            Random random = new Random();
            int round = 1;


            System.out.println(ConsoleColor.PURPLE + "魔王 HP: " + boss.hp + "/200" + ConsoleColor.RESET);

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

                round++;
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

        }while (playAgain);



        System.out.println(ConsoleColor.YELLOW + "感謝遊玩!" + ConsoleColor.RESET);
        scanner.close();
    }
}