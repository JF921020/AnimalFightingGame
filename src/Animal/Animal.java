package Animal;

import java.util.Random;
import java.util.Scanner;


// 抽象類別 Animal，代表所有玩家可選擇的動物角色
public abstract class Animal {
    Scanner scanner = new Scanner(System.in);
    protected String name;
    public int hp = 20;
    public int mp = 10;
    public int maxHp = 100;
    public int maxMP = 100;
    protected Random random = new Random();
    public int choice = 0;
    public double rate = 0;
    public Skill skill1;
    public Skill skill2;
    public Skill skill3;
    public Skill skill4;
    public Props HPprops;
    public Props MPprops;

    public Animal(String name) {
        this.name = name;
        this.skill1 = new Skill();
        this.skill2 = new Skill();
        this.skill3 = new Skill();
        this.skill4 = new Skill();
        this.HPprops = new Props();
        this.MPprops = new Props();
    }


    public int useSkill1() {
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m" + name + "使用了" + skill1.name + "! 暴擊! 敵方 HP -" + skill1.damage * 2);
            return skill1.damage * 2;
        } else {
            System.out.println("\u001B[34m" + name + "使用了" + skill1.name + "! 敵方 HP -" + skill1.damage);
            return skill1.damage;
        }

    }

    public int useSkill2() {
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m" + name + "使用了" + skill2.name + "! 暴擊! 敵方 HP -" + skill2.damage * 2);
            return skill2.damage * 2;
        } else {
            System.out.println("\u001B[34m" + name + "使用了" + skill2.name + "! 敵方 HP -" + skill2.damage);
            return skill2.damage;
        }

    }

    public int useSkill3() {
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m" + name + "使用了" + skill3.name + "! 暴擊! 敵方 HP -" + skill3.damage * 2);
            return skill3.damage * 2;
        } else {
            System.out.println("\u001B[34m" + name + "使用了" + skill3.name + "! 敵方 HP -" + skill3.damage);
            return skill3.damage;
        }

    }

    public int useSkill4() {
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m" + name + "使用了" + skill4.name + "! 暴擊! 敵方 HP -" + skill4.damage * 2);
            return skill4.damage * 2;
        } else {
            System.out.println("\u001B[34m" + name + "使用了" + skill4.name + "! 敵方 HP -" + skill4.damage);
            return skill4.damage;
        }

    }

    public int useHPprops() {
        int actualRecovery = (maxHp - hp);
        HPprops.propsCount -= 1;
        if (actualRecovery >= HPprops.recovery) {
            System.out.println("\u001B[34m玩家使用了" + "回血道具" + "!  HP +" + HPprops.recovery);
            return HPprops.recovery;
        } else {
            System.out.println("\u001B[34m玩家使用了" + "回血道具" + "!  HP +" + actualRecovery);
            return actualRecovery;
        }

    }

    public int useMPprops() {
        int actualRecovery = (maxMP - mp);
        MPprops.propsCount -= 1;
        if (actualRecovery >= MPprops.recovery) {
            System.out.println("\u001B[34m玩家使用了" + "回魔道具" + "!  MP +" + MPprops.recovery);
            return MPprops.recovery;
        } else {
            System.out.println("\u001B[34m玩家使用了" + "回魔道具" + "!  MP +" + actualRecovery);
            return actualRecovery;
        }

    }


    public void judge(Animal user, Animal boss) {

        if (user.choice == 1 && user.skill1.mpConsume <= user.mp) {
            user.mp -= user.skill1.mpConsume;
            boss.hp -= user.useSkill1();
            //user.hp -= boss.attack();
        } else if (user.choice == 2 && user.skill2.mpConsume <= user.mp) {
            user.mp -= user.skill2.mpConsume;
            boss.hp -= user.useSkill2();
            //user.hp -= boss.attack();
        } else if (user.choice == 3 && user.skill3.mpConsume <= user.mp) {
            user.mp -= user.skill3.mpConsume;
            boss.hp -= user.useSkill3();
            //user.hp -= boss.attack();
        } else if (user.choice == 4 && user.skill4.mpConsume <= user.mp) {
            user.mp -= user.skill4.mpConsume;
            boss.hp -= user.useSkill4();
            //user.hp -= boss.attack();
        } else if (user.choice == 5) {
            System.out.print(ConsoleColor.GREEN + "請輸入道具編號(I.回血道具 數量:" + HPprops.propsCount + " II.回魔道具 數量:" + MPprops.propsCount + "):" + ConsoleColor.RESET);
            int chooseProps = scanner.nextInt();
            if (chooseProps == 1) {
                if (HPprops.propsCount > 0) {
                    user.hp += user.useHPprops();
                    //user.hp -= boss.attack();
                } else if (HPprops.propsCount == 0) {
                    System.out.println(ConsoleColor.RED + "道具不足!!" + ConsoleColor.RESET);
                }

            }
            if (chooseProps == 2) {
                if (MPprops.propsCount > 0) {
                    user.mp += user.useMPprops();
                    //user.hp -= boss.attack();
                } else if (MPprops.propsCount == 0) {
                    System.out.print(ConsoleColor.RED + "道具不足!!" + ConsoleColor.RESET);
                }
            }

        } else {
            System.out.println(ConsoleColor.RED + "技能施放失敗" + ConsoleColor.RESET);
        }


        showUserHpAndMp(user.hp, user.maxHp, user.mp, user.maxMP, user.name);
        System.out.println(ConsoleColor.PURPLE + "敵方 HP: " + boss.hp + "/200" + ConsoleColor.RESET);
    }


    public void showSkill() {
        System.out.println(ConsoleColor.YELLOW + "技能 1 : " + skill1.name + " 傷害 : " + skill1.damage + " MP : " + skill1.mpConsume);
        System.out.println("技能 2 : " + skill2.name + " 傷害 : " + skill2.damage + " MP : " + skill2.mpConsume);
        System.out.println("技能 3 : " + skill3.name + " 傷害 : " + skill3.damage + " MP : " + skill3.mpConsume);
        System.out.println("技能 4 : " + skill4.name + " 傷害 : " + skill4.damage + " MP : " + skill4.mpConsume + ConsoleColor.RESET);
    }

    public void showUserHpAndMp(int hp, int maxHp, int mp, int maxMp, String name) {
        System.out.println(ConsoleColor.PURPLE + "玩家(" + name + ")" + ConsoleColor.RESET);
        System.out.println(ConsoleColor.RED + "HP: " + hp + "/" + maxHp + ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLUE + "MP: " + mp + "/" + maxMp + ConsoleColor.RESET);

    }

}

class Props {
    public String name;
    public int propsCount = 3;
    public int recovery = 50;
}

class Skill {
    public String name;
    public int damage;
    public int mpConsume;
}



