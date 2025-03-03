package Animal;
import java.util.Random;


// 抽象類別 Animal，代表所有玩家可選擇的動物角色
public abstract class Animal {
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

    public Animal(String name) {
        this.name = name;
        this.skill1 = new Skill();
        this.skill2 = new Skill();
        this.skill3 = new Skill();
        this.skill4 = new Skill();
    }

    //public abstract int attack();

    public abstract int useSkill1();
    public abstract int useSkill2();
    public abstract int useSkill3();
    public abstract int useSkill4();


    public void judge(Animal user, Boss boss) {
        if (user.choice == 1 && user.skill1.mpConsume <= user.mp) {
            user.mp -= user.skill1.mpConsume;
            boss.hp -= user.useSkill1();
            user.hp -= boss.attack();
        } else if (user.choice == 2 && user.skill2.mpConsume <= user.mp) {
            user.mp -= user.skill2.mpConsume;
            boss.hp -= user.useSkill2();
            user.hp -= boss.attack();
        }else if (user.choice == 3 && user.skill3.mpConsume <= user.mp) {
            user.mp -= user.skill3.mpConsume;
            boss.hp -= user.useSkill3();
            user.hp -= boss.attack();
        }else if (user.choice == 4 && user.skill4.mpConsume <= user.mp) {
            user.mp -= user.skill4.mpConsume;
            boss.hp -= user.useSkill4();
            user.hp -= boss.attack();
        }else{
            System.out.println(ConsoleColor.RED + "技能施放失敗" + ConsoleColor.RESET);
        }




        showUserHpAndMp(user.hp,user.maxHp, user.mp,user.maxMP,user.name);
        System.out.println(ConsoleColor.PURPLE + "魔王 HP: " + boss.hp + "/200" + ConsoleColor.RESET);
    }



    public void showSkill(){
        System.out.println(ConsoleColor.YELLOW + "技能 1 : " + skill1.name + " 傷害 : " + skill1.damage + " MP : " + skill1.mpConsume);
        System.out.println("技能 2 : " + skill2.name + " 傷害 : " + skill2.damage + " MP : " + skill2.mpConsume);
        System.out.println("技能 3 : " + skill3.name + " 傷害 : " + skill3.damage + " MP : " + skill3.mpConsume);
        System.out.println("技能 4 : " + skill4.name + " 傷害 : " + skill4.damage + " MP : " + skill4.mpConsume + ConsoleColor.RESET);
    }

    public void showUserHpAndMp(int hp, int maxHp, int mp, int maxMp, String name){
        System.out.println(ConsoleColor.PURPLE + "玩家(" + name + ")" + ConsoleColor.RESET);
        System.out.println(ConsoleColor.RED + "HP: " + hp + "/" + maxHp + ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLUE + "MP: " + mp + "/" + maxMp + ConsoleColor.RESET);

    }

}


