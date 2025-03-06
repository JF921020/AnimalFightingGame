import Animal.*;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;


        do {
            System.out.print(ConsoleColor.GREEN + "請選擇遊戲模式(1.Random 2.Free 3.Full Random 4.Continuous): " + ConsoleColor.RESET);
            int mode = scanner.nextInt();
            if (mode == 1) { //隨機敵人
                Random random = new Random();
                Animal player = playerChoose();
                Animal boss = getBoss(random.nextInt(4) + 1);
                System.out.println("-----------------------------------");
                System.out.println("-----------------------------------");


                if (player == null || boss == null) {
                    System.out.println(ConsoleColor.RED + "輸入不合法" + ConsoleColor.RESET);
                    scanner.close();
                    return;
                }


                while (player.hp > 0 && boss.hp > 0) {
                    boss.choice = random.nextInt(4) + 1;
                    System.out.println(ConsoleColor.BLUE + "玩家的回合" + ConsoleColor.RESET);
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
            }
            else if (mode == 2) {//自選敵人
                Random random = new Random();
                Animal player = playerChoose();
                Animal boss = bossChoose();
                System.out.println("-----------------------------------");
                System.out.println("-----------------------------------");


                if (player == null || boss == null) {
                    System.out.println(ConsoleColor.RED + "輸入不合法" + ConsoleColor.RESET);
                    scanner.close();
                    return;
                }

                while (player.hp > 0 && boss.hp > 0) {
                    boss.choice = random.nextInt(4) + 1;
                    System.out.println(ConsoleColor.BLUE + "玩家的回合" + ConsoleColor.RESET);
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


            }
            else if (mode == 3) {//完全隨機
                Random random = new Random();
                Animal player = getAnimal(random.nextInt(4) + 1);
                Animal boss = getBoss(random.nextInt(4) + 1);
                System.out.println("-----------------------------------");
                System.out.println("-----------------------------------");


                if (player == null || boss == null) {
                    System.out.println(ConsoleColor.RED + "輸入不合法" + ConsoleColor.RESET);
                    scanner.close();
                    return;
                }


                while (player.hp > 0 && boss.hp > 0) {
                    boss.choice = random.nextInt(4) + 1;
                    System.out.println(ConsoleColor.BLUE + "玩家的回合" + ConsoleColor.RESET);
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

            }
            else if(mode == 4) {//連續戰鬥
                Random random = new Random();
                boolean nextDoor = true;
                int DoorNum = 1;
                Animal player = playerChoose();
                if (player == null) {
                    System.out.println(ConsoleColor.RED + "輸入不合法" + ConsoleColor.RESET);
                    scanner.close();
                    return;
                }
                player.HPprops.propsCount = 10;
                player.MPprops.propsCount = 10;
                while (nextDoor) {
                    Animal boss = getBoss(DoorNum);
                    System.out.println("-----------------------------------");
                    System.out.println("-----------------------------------");
                    while (player.hp > 0 && boss.hp > 0) {
                        boss.choice = random.nextInt(4) + 1;
                        System.out.println(ConsoleColor.BLUE + "玩家的回合" + ConsoleColor.RESET);
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

                    if(player.hp == 0){
                        System.out.println(ConsoleColor.RED + "連續戰鬥失敗" + ConsoleColor.RESET);
                        break;
                    }

                    if(DoorNum != 4) {
                        System.out.println(ConsoleColor.YELLOW + "進入下一關" + ConsoleColor.RESET);
                        System.out.println(ConsoleColor.PURPLE + "玩家" + ConsoleColor.RESET);
                        if (player.hp < 0) player.hp = 0;
                        player.showHpAndMp(player.hp, player.maxHp, player.mp, player.maxMP);
                    }
                    if(DoorNum == 4) {
                        nextDoor = false;
                    }
                    DoorNum++;
                }
            }else {
                System.out.println(ConsoleColor.YELLOW + "開發中~" + ConsoleColor.RESET);
            }

            //每次遊戲結束詢問
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


    //玩家角色選擇
    public static Animal playerChoose() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(ConsoleColor.GREEN + "請輸入想選的動物編號(1.狗 2.貓 3.熊 4.老虎 輸入5可以查看角色資訊): " + ConsoleColor.RESET);
            int choose = scanner.nextInt();

            if (choose == 5) {
                // 顯示所有角色資訊
                System.out.println(ConsoleColor.YELLOW + "角色資訊：" + ConsoleColor.RESET);
                System.out.println(ConsoleColor.YELLOW + "狗:" + ConsoleColor.RESET);
                Dog dog = new Dog();
                dog.showSkill();
                System.out.println("-----------------------------------");
                System.out.println(ConsoleColor.YELLOW + "貓:" + ConsoleColor.RESET);
                Cat cat = new Cat();
                cat.showSkill();
                System.out.println("-----------------------------------");
                System.out.println(ConsoleColor.YELLOW + "熊:" + ConsoleColor.RESET);
                Bear bear = new Bear();
                bear.showSkill();
                System.out.println("-----------------------------------");
                System.out.println(ConsoleColor.YELLOW + "老虎:" + ConsoleColor.RESET);
                Tiger tiger = new Tiger();
                tiger.showSkill();
                System.out.println("-----------------------------------");
                System.out.println(ConsoleColor.CYAN + "請重新選擇角色!" + ConsoleColor.RESET);
            } else if (choose >= 1 && choose <= 4) {
                return getAnimal(choose);
            } else {
                System.out.println(ConsoleColor.RED + "沒有這個角色!!!!" + ConsoleColor.RESET);
                System.out.println(ConsoleColor.CYAN + "請重新選擇角色!" + ConsoleColor.RESET);
            }
        }
    }

    //敵方角色選擇
    public static Animal bossChoose() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ConsoleColor.GREEN + "請輸入想選的敵方動物編號(1.狗 2.貓 3.熊 4.老虎): " + ConsoleColor.RESET);
        int choose = scanner.nextInt();

        return getBoss(choose);
    }

    //玩家角色生成
    private static Animal getAnimal(int choose) {

        if (choose == 1) {
            System.out.println(ConsoleColor.PURPLE + "玩家(狗)" + ConsoleColor.RESET);
            return new Dog();
        } else if (choose == 2) {
            System.out.println(ConsoleColor.PURPLE + "玩家(貓)" + ConsoleColor.RESET);
            return new Cat();
        } else if (choose == 3) {
            System.out.println(ConsoleColor.PURPLE + "玩家(熊)" + ConsoleColor.RESET);
            return new Bear();
        } else if (choose == 4) {
            System.out.println(ConsoleColor.PURPLE + "玩家(老虎)" + ConsoleColor.RESET);
            return new Tiger();
        } else {
            return null;
        }
    }

    //敵方角色生成
    private static Animal getBoss(int choose) {

        if (choose == 1) {
            System.out.println(ConsoleColor.PURPLE + "敵方(狗)" + ConsoleColor.RESET);
            return new Dog();
        } else if (choose == 2) {
            System.out.println(ConsoleColor.PURPLE + "敵方(貓)" + ConsoleColor.RESET);
            return new Cat();
        } else if (choose == 3) {
            System.out.println(ConsoleColor.PURPLE + "敵方(熊)" + ConsoleColor.RESET);
            return new Bear();
        } else if (choose == 4) {
            System.out.println(ConsoleColor.PURPLE + "敵方(老虎)" + ConsoleColor.RESET);
            return new Tiger();
        } else {
            return null;
        }
    }
}