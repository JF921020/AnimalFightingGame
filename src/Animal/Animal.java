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
            System.out.println("\u001B[34m" + name + "使用了" + skill1.name + "! 暴擊! 傷害" + skill1.damage * 2);
            return skill1.damage * 2;
        } else {
            System.out.println("\u001B[34m" + name + "使用了" + skill1.name + "! 傷害" + skill1.damage);
            return skill1.damage;
        }

    }

    public int useSkill2() {
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m" + name + "使用了" + skill2.name + "! 暴擊! 傷害" + skill2.damage * 2);
            return skill2.damage * 2;
        } else {
            System.out.println("\u001B[34m" + name + "使用了" + skill2.name + "! 傷害" + skill2.damage);
            return skill2.damage;
        }

    }

    public int useSkill3() {
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m" + name + "使用了" + skill3.name + "! 暴擊! 傷害" + skill3.damage * 2);
            return skill3.damage * 2;
        } else {
            System.out.println("\u001B[34m" + name + "使用了" + skill3.name + "! 傷害" + skill3.damage);
            return skill3.damage;
        }

    }

    public int useSkill4() {
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m" + name + "使用了" + skill4.name + "! 暴擊! 傷害" + skill4.damage * 2);
            return skill4.damage * 2;
        } else {
            System.out.println("\u001B[34m" + name + "使用了" + skill4.name + "! 傷害" + skill4.damage);
            return skill4.damage;
        }

    }

    //使用回血道具
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

    //使用回魔道具
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


    //角色行動模組，透過user.choice決定行動方式
    public void judge(Animal user, Animal boss) {
        if (user.choice == 1 && user.skill1.mpConsume <= user.mp) {
            user.mp -= user.skill1.mpConsume;
            System.out.print(ConsoleColor.PURPLE + "(玩家)" + ConsoleColor.RESET);
            boss.hp -= user.useSkill1();
            showAll(user, boss);
            System.out.println(ConsoleColor.BLUE + "敵方的回合" + ConsoleColor.RESET);
            user.hp -= bossAttack(boss);
        } else if (user.choice == 2 && user.skill2.mpConsume <= user.mp) {
            user.mp -= user.skill2.mpConsume;
            System.out.print(ConsoleColor.PURPLE + "(玩家)" + ConsoleColor.RESET);
            boss.hp -= user.useSkill2();
            showAll(user, boss);
            System.out.println(ConsoleColor.BLUE + "敵方的回合" + ConsoleColor.RESET);
            user.hp -= bossAttack(boss);
        } else if (user.choice == 3 && user.skill3.mpConsume <= user.mp) {
            user.mp -= user.skill3.mpConsume;
            System.out.print(ConsoleColor.PURPLE + "(玩家)" + ConsoleColor.RESET);
            boss.hp -= user.useSkill3();
            showAll(user, boss);
            System.out.println(ConsoleColor.BLUE + "敵方的回合" + ConsoleColor.RESET);
            user.hp -= bossAttack(boss);
        } else if (user.choice == 4 && user.skill4.mpConsume <= user.mp) {
            user.mp -= user.skill4.mpConsume;
            System.out.print(ConsoleColor.PURPLE + "(玩家)" + ConsoleColor.RESET);
            boss.hp -= user.useSkill4();
            showAll(user, boss);
            System.out.println(ConsoleColor.BLUE + "敵方的回合" + ConsoleColor.RESET);
            user.hp -= bossAttack(boss);
        } else if (user.choice == 5) {
            System.out.print(ConsoleColor.GREEN + "請輸入道具編號(I.回血道具 數量:" + HPprops.propsCount + " II.回魔道具 數量:" + MPprops.propsCount + "):" + ConsoleColor.RESET);
            int chooseProps = scanner.nextInt();
            if (chooseProps == 1) {
                if (HPprops.propsCount > 0) {
                    user.hp += user.useHPprops();
                    showAll(user, boss);
                    System.out.println(ConsoleColor.BLUE + "敵方的回合" + ConsoleColor.RESET);
                    user.hp -= bossAttack(boss);
                } else if (HPprops.propsCount == 0) {
                    System.out.println(ConsoleColor.RED + "道具不足!!" + ConsoleColor.RESET);
                }

            } else if (chooseProps == 2) {
                if (MPprops.propsCount > 0) {
                    user.mp += user.useMPprops();
                    showAll(user, boss);
                    System.out.println(ConsoleColor.BLUE + "敵方的回合" + ConsoleColor.RESET);
                    user.hp -= bossAttack(boss);
                } else if (MPprops.propsCount == 0) {
                    System.out.print(ConsoleColor.RED + "道具不足!!" + ConsoleColor.RESET);
                }
            } else {
                System.out.println(ConsoleColor.RED + "沒有此道具!!" + ConsoleColor.RESET);
            }

        } else {
            System.out.println(ConsoleColor.RED + "技能施放失敗" + ConsoleColor.RESET);
        }

        showAll(user, boss);
    }


    //每回合玩家或敵人行動結束顯示雙方HP及MP
    public void showAll(Animal user, Animal boss) {
        System.out.println(ConsoleColor.PURPLE + "玩家(" + user.name + ")" + ConsoleColor.RESET);
        if (user.hp < 0) user.hp = 0;
        showHpAndMp(user.hp, user.maxHp, user.mp, user.maxMP);
        System.out.println(ConsoleColor.PURPLE + "敵方(" + boss.name + ")" + ConsoleColor.RESET);
        if (boss.hp < 0) boss.hp = 0;
        showHpAndMp(boss.hp, boss.maxHp, boss.mp, boss.maxMP);
        System.out.println("-----------------------------------");
        System.out.println("-----------------------------------");
    }

    //顯示玩家角色技能及數值
    public void showSkill() {
        System.out.println(ConsoleColor.YELLOW + "技能 1 : " + skill1.name + " 傷害 : " + skill1.damage + " MP : " + skill1.mpConsume);
        System.out.println("技能 2 : " + skill2.name + " 傷害 : " + skill2.damage + " MP : " + skill2.mpConsume);
        System.out.println("技能 3 : " + skill3.name + " 傷害 : " + skill3.damage + " MP : " + skill3.mpConsume);
        System.out.println("技能 4 : " + skill4.name + " 傷害 : " + skill4.damage + " MP : " + skill4.mpConsume + ConsoleColor.RESET);
    }

    //顯示角色HP及MP
    public void showHpAndMp(int hp, int maxHp, int mp, int maxMp) {
        System.out.println(ConsoleColor.RED + "HP: " + hp + "/" + maxHp + "  " + ConsoleColor.BLUE + "MP: " + mp + "/" + maxMp + ConsoleColor.RESET);
    }

    //敵方角色攻擊模組
    public int bossAttack(Animal boss) {
        boss.choice = random.nextInt(4) + 1;
        if (boss.choice == 1 && boss.skill1.mpConsume <= boss.mp) {
            boss.mp -= boss.skill1.mpConsume;
            System.out.print(ConsoleColor.PURPLE + "(敵方)" + ConsoleColor.RESET);
            return boss.useSkill1();
        } else if (boss.choice == 2 && boss.skill2.mpConsume <= boss.mp) {
            boss.mp -= boss.skill2.mpConsume;
            System.out.print(ConsoleColor.PURPLE + "(敵方)" + ConsoleColor.RESET);
            return boss.useSkill2();
        } else if (boss.choice == 3 && boss.skill3.mpConsume <= boss.mp) {
            boss.mp -= boss.skill3.mpConsume;
            System.out.print(ConsoleColor.PURPLE + "(敵方)" + ConsoleColor.RESET);
            return boss.useSkill3();
        } else if (boss.choice == 4 && boss.skill4.mpConsume <= boss.mp) {
            boss.mp -= boss.skill4.mpConsume;
            System.out.print(ConsoleColor.PURPLE + "(敵方)" + ConsoleColor.RESET);
            return boss.useSkill4();
        } else {
            return bossAttack(boss);
        }
    }

    //在角色清單顯示所有角色相關資訊
    public abstract void showDetail();

}

//道具類別
class Props {
    public String name;
    public int propsCount = 3;
    public int recovery = 50;
}

//技能類別
class Skill {
    public String name;
    public int damage;
    public int mpConsume;
}



