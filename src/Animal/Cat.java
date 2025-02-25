package Animal;

public class Cat extends Animal {
    public Cat() {
        super("貓");
        HP = 80;
    }

    @Override
    public int attack() {
        if (random.nextDouble() < 0.3) {
            System.out.println("玩家(" + name + ")使用了強力攻擊");
            return useSkill();
        } else {
            System.out.println("玩家(" + name + ")使用了普通攻擊");
            System.out.println("Boss受到了攻擊，生命值-15");
            return 15;
        }
    }

    @Override
    public int useSkill() {
        System.out.println("貓爪連擊! Boss HP -25");
        return 25;
    }
}

