import Animal.*;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\u001B[32m請輸入想選的動物編號(1.狗 2.貓 3.熊 4.老虎): ");
        int choose = scanner.nextInt();

        Animal player = switch (choose) {
            case 1 -> new Dog();
            case 2 -> new Cat();
            case 3 -> new Bear();
            case 4 -> new Tiger();
            default -> null;
        };

        if (player == null) {
            System.out.println("\u001B[31m輸入不合法");
            scanner.close();
            return;
        }

        Boss boss = new Boss();
        Random random = new Random();
        int round = 1;

        while (player.HP > 0 && boss.HP > 0) {
            boss.choice = random.nextInt(3) + 1;
            System.out.print("\u001B[32m請輸入技能編號(1~4): ");
            player.choice = scanner.nextInt();

            player.judge(player, boss);

            if (player.HP <= 0) {
                System.out.println("\u001B[31m你輸了");
            } else if (boss.HP <= 0) {
                System.out.println("\u001B[33m你贏了");
            }

            round++;
        }

        scanner.close();
    }
}