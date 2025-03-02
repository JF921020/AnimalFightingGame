package Animal;

public class Bear extends Animal {
    public Bear() {
        super("熊");
        HP = 150;
        rate = 0.3;
        super.skill1.name = "巨熊衝撞";
        super.skill2.name = "熊爪橫掃";
        super.skill3.name = "大地震擊";
        super.skill4.name = "熊王之怒";

        super.skill1.count = 25;
        super.skill2.count = 10;
        super.skill3.count = 18;
        super.skill4.count = 22;

        System.out.println("\u001B[35m玩家(" + name + ") HP: " +HP);
        System.out.println("\u001B[33m技能 1 : " + skill1.name + " 傷害 : " + skill1.count);
        System.out.println("\u001B[33m技能 2 : " + skill2.name + " 傷害 : " + skill2.count);
        System.out.println("\u001B[33m技能 3 : " + skill3.name + " 傷害 : " + skill3.count);
        System.out.println("\u001B[33m技能 4 : " + skill4.name + " 傷害 : " + skill4.count);
    }



    @Override
    public int useSkill1() {
        if (random.nextDouble() < 0.4) {
            System.out.println("\u001B[34m玩家(" + name + ")使用了巨熊衝撞! 暴擊! 魔王 HP -50");
            return 50;
        } else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了巨熊衝撞! 魔王 HP -25");
            return 25;
        }
    }

    public int useSkill2() {
        if (random.nextDouble() < 0.4) {
            System.out.println("\u001B[34m玩家(" + name + ")使用了熊爪橫掃! 暴擊! 魔王 HP -20");
            return 20;
        } else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了熊爪橫掃! 魔王 HP -10");
            return 10;
        }
    }

    public int useSkill3() {
        if (random.nextDouble() < 0.4) {
            System.out.println("\u001B[34m玩家(" + name + ")使用了大地震擊! 暴擊! 魔王 HP -35");
            return 35;
        } else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了大地震擊! 魔王 HP -18");
            return 18;
        }
    }

    public int useSkill4() {
        if (random.nextDouble() < 0.4) {
            System.out.println("\u001B[34m玩家(" + name + ")使用了熊王之怒! 暴擊! 魔王 HP -45");
            return 45;
        } else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了熊王之怒! 魔王 HP -22");
            return 22;
        }
    }
}
