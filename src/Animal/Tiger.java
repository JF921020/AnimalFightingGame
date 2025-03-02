package Animal;

public class Tiger extends Animal {
    public Tiger() {
        super("老虎");
        HP = 120;
        rate = 0.1;
        super.skill1.name = "猛虎撲殺";
        super.skill2.name = "虎爪連擊";
        super.skill3.name = "猛虎怒吼";
        super.skill4.name = "疾風猛虎爪";

        super.skill1.count = 22;
        super.skill2.count = 15;
        super.skill3.count = 18;
        super.skill4.count = 25;

        System.out.println("\u001B[35m玩家(" + name + ") HP: " +HP);
        System.out.println("\u001B[33m技能 1 : " + skill1.name + " 傷害 : " + skill1.count);
        System.out.println("\u001B[33m技能 2 : " + skill2.name + " 傷害 : " + skill2.count);
        System.out.println("\u001B[33m技能 3 : " + skill3.name + " 傷害 : " + skill3.count);
        System.out.println("\u001B[33m技能 4 : " + skill4.name + " 傷害 : " + skill4.count);
    }



    @Override
    public int useSkill1() {
        if (random.nextDouble() < 0.4) {
            System.out.println("\u001B[34m玩家(" + name + ")使用了猛虎撲殺! 暴擊! 魔王 HP -45");
            return 45;
        } else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了猛虎撲殺! 魔王 HP -22");
            return 22;
        }
    }

    public int useSkill2() {
        if (random.nextDouble() < 0.4) {
            System.out.println("\u001B[34m玩家(" + name + ")使用了虎爪連擊! 暴擊! 魔王 HP -30");
            return 30;
        } else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了虎爪連擊! 魔王 HP -15");
            return 15;
        }
    }

    public int useSkill3() {
        if (random.nextDouble() < 0.4) {
            System.out.println("\u001B[34m玩家(" + name + ")使用了猛虎怒吼! 暴擊! 魔王 HP -35");
            return 35;
        } else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了猛虎怒吼! 魔王 HP -18");
            return 18;
        }
    }

    public int useSkill4() {
        if (random.nextDouble() < 0.4) {
            System.out.println("\u001B[34m玩家(" + name + ")使用了疾風猛虎爪! 暴擊! 魔王 HP -50");
            return 50;
        } else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了疾風猛虎爪! 魔王 HP -25");
            return 25;
        }
    }
}
