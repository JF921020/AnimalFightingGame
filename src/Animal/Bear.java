package Animal;

public class Bear extends Animal {
    public Bear() {
        super("熊");
        HP = 150;
        rate = 0.3;
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
