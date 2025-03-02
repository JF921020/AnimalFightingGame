package Animal;
import java.util.Random;



// 抽象類別 Animal，代表所有玩家可選擇的動物角色
public abstract class Animal {
    protected String name;
    public int HP = 20;
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
        if (user.choice == 1) {
            boss.HP -= user.useSkill1();
        } else if (user.choice == 2) {
            boss.HP -= user.useSkill2();
        }else if (user.choice == 3) {
            boss.HP -= user.useSkill3();
        }else if (user.choice == 4) {
            boss.HP -= user.useSkill4();
        }

        user.HP -= boss.attack();

        System.out.println("\u001B[35m玩家(" + user.name + ") HP: " + user.HP);
        System.out.println("\u001B[33m技能 1 : " + skill1.name + " 傷害 : " + skill1.count);
        System.out.println("\u001B[33m技能 2 : " + skill2.name + " 傷害 : " + skill2.count);
        System.out.println("\u001B[33m技能 3 : " + skill3.name + " 傷害 : " + skill3.count);
        System.out.println("\u001B[33m技能 4 : " + skill4.name + " 傷害 : " + skill4.count);
        System.out.println("\u001B[35m魔王 HP: " + boss.HP);
    }

    private int getMoveName(int choice) {
        if (choice == 1) {
            return useSkill1();
        } else if (choice == 2) {
            return useSkill2();
        } else if (choice == 3) {
            return useSkill3();
        } else if (choice == 4) {
            return useSkill4();
        }
        return 0;
    }
}


