package Animal;

public class Dog extends Animal {
    public Dog() {
        super("狗");
        HP = 100;
    }

    @Override
    public int attack() {
        if (random.nextDouble() < 0.4) {
            System.out.println("玩家(" + name + ")使用了強力攻擊");
            return useSkill();
        } else {
            System.out.println("玩家(" + name + ")使用了普通攻擊");
            System.out.println("Boss受到了攻擊，生命值-10");
            return 10;
        }
    }

    @Override
    public int useSkill() {
        System.out.println("瘋狗亂撞! Boss HP -20");
        return 20;
    }
}
