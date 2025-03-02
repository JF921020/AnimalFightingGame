package Animal;

public class Dog extends Animal {
    public Dog() {
        super("狗");
        HP = 100;
        rate = 0.4;
    }



    @Override
    public int useSkill1() {
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m玩家(" + name + ")使用了咬住! 暴擊! 魔王 HP -40");
            return 40;
        }else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了咬住! 魔王 HP -20");
            return 20;
        }

    }

    @Override
    public int useSkill2() {
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m玩家(" + name + ")使用了狂犬突襲! 暴擊! 魔王 HP -15");
            return 15;
        }else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了狂犬突襲! 魔王 HP -7");
            return 7;
        }
    }

    @Override
    public int useSkill3() {
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m玩家(" + name + ")使用了撕裂! 暴擊! 魔王 HP -20");
            return 20;
        }else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了撕裂! 魔王 HP -10");
            return 10;
        }
    }

    @Override
    public int useSkill4() {
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m玩家(" + name + ")使用了瘋狗亂撞! 暴擊! 魔王 HP -30");
            return 30;
        }else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了瘋狗亂撞! 魔王 HP -15");
            return 15;
        }
    }
}
