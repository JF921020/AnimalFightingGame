package Animal;

import java.util.Random;
import java.util.Scanner;


// 抽象類別 Animal，代表所有玩家可選擇的動物角色
public abstract class Animal {
    Scanner scanner = new Scanner(System.in);
    public String name;
    public int hp = 20;
    public int mp = 10;
    public int maxHp = 100;
    public int maxMP = 100;
    public int ATK = 10;
    public int maxATK = 10;
    public Buff debuff;
    public Buff buff;
    protected Random random = new Random();
    public int choice = 0;
    public double rate = 0;
    public double maxRate = 0;
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
        this.debuff = new Buff();
        this.buff = new Buff();

        skill1.mpConsume = 0;
        skill2.mpConsume = 5;
        skill3.mpConsume = 5;
        skill4.mpConsume = 10;

        skill1.useCount = 100;
        skill2.useCount = 10;
        skill3.useCount = 5;
        skill4.useCount = 5;
    }

    // 修改後的預設技能使用方法，接收目標 (可能是敵人或自己)
    public int useSkill1(Animal user,Animal target) {
        if(user.mp >= user.skill1.mpConsume && user.skill1.useCount > 0){
            user.mp -= user.skill1.mpConsume;
            return performSkill(skill1, user.rate,1);
        }else{
            System.out.println(ConsoleColor.RED + "技能施放失敗" + ConsoleColor.RESET);
            return 0;
        }

    }

    public int useSkill2(Animal user,Animal target) {
        if(user.mp >= user.skill2.mpConsume && user.skill2.useCount > 0){
            user.mp -= user.skill2.mpConsume;
            return performSkill(skill2, user.rate,1);
        }else{
            System.out.println(ConsoleColor.RED + "技能施放失敗" + ConsoleColor.RESET);
            return 0;
        }
    }

    public int useSkill3(Animal user,Animal target) {
        if(user.mp >= user.skill3.mpConsume && user.skill3.useCount > 0){
            user.mp -= user.skill3.mpConsume;
            return performSkill(skill3, user.rate,2);
        }else{
            System.out.println(ConsoleColor.RED + "技能施放失敗" + ConsoleColor.RESET);
            return 0;
        }
    }

    public int useSkill4(Animal user,Animal target) {
        if(user.mp >= user.skill4.mpConsume && user.skill4.useCount > 0){
            user.mp -= user.skill4.mpConsume;
            return performSkill(skill4, user.rate,3);
        }else{
            System.out.println(ConsoleColor.RED + "技能施放失敗" + ConsoleColor.RESET);
            return 0;
        }
    }

    // 統一處理技能傷害及 buff 施加的邏輯
    protected int performSkill(Skill skill, double rate, int power) {
        skill.useCount--;
        int damage;
        if (random.nextDouble() < rate) {
            damage = ATK * 2 * power;
            System.out.println(ConsoleColor.BLUE + name +"使用了" + skill.name + "! 暴擊! 傷害" + damage);
        } else {
            damage = ATK * power;
            System.out.println(ConsoleColor.BLUE + name +"使用了" + skill.name + "! 傷害" + damage);
        }
        return damage;
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

    public int enemyAttack(Animal boss, Animal user){
        if(boss.choice==1) return boss.useSkill1(boss,user);
        else if(boss.choice==2) return boss.useSkill2(boss,user);
        else if(boss.choice==3) return boss.useSkill3(boss,user);
        else if(boss.choice==4) return boss.useSkill4(boss,user);
        return 0;
    }

    //角色行動模組，透過user.choice決定行動方式
    public void judge(Animal user, Animal boss) {
        boss.choice = random.nextInt(4) + 1;
        if (user.choice == 1) {
            System.out.print(ConsoleColor.PURPLE + "(玩家)" + ConsoleColor.RESET);
            boss.hp -= user.useSkill1(user,boss);
            showAll(user, boss);
            System.out.println(ConsoleColor.BLUE + "敵方的回合" + ConsoleColor.RESET);
            System.out.print(ConsoleColor.PURPLE + "(敵方)" + ConsoleColor.RESET);
            user.hp -= enemyAttack(boss,user);
        } else if (user.choice == 2) {
            user.mp -= user.skill2.mpConsume;
            System.out.print(ConsoleColor.PURPLE + "(玩家)" + ConsoleColor.RESET);
            boss.hp -= user.useSkill2(user,boss);
            showAll(user, boss);
            System.out.println(ConsoleColor.BLUE + "敵方的回合" + ConsoleColor.RESET);
            System.out.print(ConsoleColor.PURPLE + "(敵方)" + ConsoleColor.RESET);
            user.hp -= enemyAttack(boss,user);
        } else if (user.choice == 3) {
            user.mp -= user.skill3.mpConsume;
            System.out.print(ConsoleColor.PURPLE + "(玩家)" + ConsoleColor.RESET);
            boss.hp -= user.useSkill3(user,boss);
            showAll(user, boss);
            System.out.println(ConsoleColor.BLUE + "敵方的回合" + ConsoleColor.RESET);
            System.out.print(ConsoleColor.PURPLE + "(敵方)" + ConsoleColor.RESET);
            user.hp -= enemyAttack(boss,user);
        } else if (user.choice == 4) {
            user.mp -= user.skill4.mpConsume;
            System.out.print(ConsoleColor.PURPLE + "(玩家)" + ConsoleColor.RESET);
            boss.hp -= user.useSkill4(user,boss);
            showAll(user, boss);
            System.out.println(ConsoleColor.BLUE + "敵方的回合" + ConsoleColor.RESET);
            System.out.print(ConsoleColor.PURPLE + "(敵方)" + ConsoleColor.RESET);
            user.hp -= enemyAttack(boss,user);
        } else if (user.choice == 5) {
            System.out.print(ConsoleColor.GREEN + "請輸入道具編號(I.回血道具 數量:" + HPprops.propsCount + " II.回魔道具 數量:" + MPprops.propsCount + "):" + ConsoleColor.RESET);
            int chooseProps = scanner.nextInt();
            if (chooseProps == 1) {
                if (HPprops.propsCount > 0) {
                    user.hp += user.useHPprops();
                    showAll(user, boss);
                    System.out.println(ConsoleColor.BLUE + "敵方的回合" + ConsoleColor.RESET);
                    System.out.print(ConsoleColor.PURPLE + "(敵方)" + ConsoleColor.RESET);
                    user.hp -= enemyAttack(boss,user);
                } else if (HPprops.propsCount == 0) {
                    System.out.println(ConsoleColor.RED + "道具不足!!" + ConsoleColor.RESET);
                }

            }
            else if (chooseProps == 2) {
                if (MPprops.propsCount > 0) {
                    user.mp += user.useMPprops();
                    showAll(user, boss);
                    System.out.println(ConsoleColor.BLUE + "敵方的回合" + ConsoleColor.RESET);
                    System.out.print(ConsoleColor.PURPLE + "(敵方)" + ConsoleColor.RESET);
                    user.hp -= enemyAttack(boss,user);
                } else if (MPprops.propsCount == 0) {
                    System.out.print(ConsoleColor.RED + "道具不足!!" + ConsoleColor.RESET);
                }
            }
            else {
                System.out.println(ConsoleColor.RED + "沒有此道具!!" + ConsoleColor.RESET);
            }
        }else{
            System.out.println(ConsoleColor.RED + "技能施放失敗" + ConsoleColor.RESET);
        }
        BuffCheck(user);
        BuffCheck(boss);
        showAll(user, boss);
        deBuffCheck(user);
        deBuffCheck(boss);

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
    public String showSkill() { // ✅ 改成 String 回傳
        return "技能 1 : " + skill1.name + "\n" +
                "傷害 : " + ATK + " MP : " + skill1.mpConsume + " 施放次數 : " + skill1.useCount + "\n" +
                "技能 2 : " + skill2.name + "\n" +
                "傷害 : " + ATK + " MP : " + skill2.mpConsume + " 施放次數 : " + skill2.useCount + "\n" +
                "技能 3 : " + skill3.name + "\n" +
                "傷害 : " + ATK * 2 + " MP : " + skill3.mpConsume + " 施放次數 : " + skill3.useCount + "\n" +
                "技能 4 : " + skill4.name + "\n" +
                "傷害 : " + ATK * 3 + " MP : " + skill4.mpConsume + " 施放次數 : " + skill4.useCount;
    }


    public void deBuffCheck(Animal animal) {
        if(animal.debuff.duration == 0){
            animal.debuff.state = 0;
        }else{
            animal.debuff.duration--;
        }
        if(animal.debuff.state == 0){//正常
            animal.ATK = animal.maxATK;
            animal.rate = animal.maxRate ;
        }
        else if(animal.debuff.state == 1){//流血
            if(animal.hp < (animal.maxHp/10))
                animal.hp = 0;
            else
                animal.hp -= animal.maxHp/10;
        }
    }
    public void BuffCheck(Animal animal) {
        if(animal.buff.duration == 0){
            animal.buff.state = 0;
        }else{
            animal.buff.duration--;
        }
        if(animal.buff.state == 0){//正常
            animal.ATK = animal.maxATK;
            animal.rate = animal.maxRate ;
        }
        else if(animal.buff.state == 1){//回復
            int maxRecovery = animal.maxHp-animal.hp;
            if(maxRecovery <= (animal.maxHp/10)) {
                animal.hp = animal.maxHp;
                System.out.println(animal.name+"回復了"+maxRecovery+"點生命值");
            }
            else{
                animal.hp += animal.maxHp/10;
                System.out.println(animal.name+"回復了"+animal.maxHp/10+"點生命值");
            }
        }
    }
    
    //顯示角色HP及MP
    public void showHpAndMp(int hp, int maxHp, int mp, int maxMp) {
        System.out.println(ConsoleColor.RED + "HP: " + hp + "/" + maxHp + "  " + ConsoleColor.BLUE + "MP: " + mp + "/" + maxMp + ConsoleColor.RESET);
    }

}



