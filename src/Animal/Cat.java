package Animal;

public class Cat extends Animal {
    public Cat() {
        super("貓");
        HP = 80;
        rate = 0.3;
    }



    @Override
    public int useSkill1() {
        if (random.nextDouble() < 0.4) { // 40% 機率觸發技能
            System.out.println("\u001B[34m玩家(" + name + ")使用了銳爪撕裂! 暴擊! 魔王 HP -40");
            return 40;
        } else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了銳爪撕裂! 魔王 HP -20");
            return 20;
        }
    }

    public int useSkill2() {
        if (random.nextDouble() < 0.4) {
            System.out.println("\u001B[34m玩家(" + name + ")使用了夜影突襲! 暴擊! 魔王 HP -15");
            return 15;
        } else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了夜影突襲! 魔王 HP -7");
            return 7;
        }
    }

    public int useSkill3() {
        if (random.nextDouble() < 0.4) {
            System.out.println("\u001B[34m玩家(" + name + ")使用了靈貓迴旋! 暴擊! 魔王 HP -20");
            return 20;
        } else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了靈貓迴旋! 魔王 HP -10");
            return 10;
        }
    }

    public int useSkill4() {
        if (random.nextDouble() < 0.4) {
            System.out.println("\u001B[34m玩家(" + name + ")使用了幻影連爪! 暴擊! 魔王 HP -30");
            return 30;
        } else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了幻影連爪! 魔王 HP -15");
            return 15;
        }
    }
}

