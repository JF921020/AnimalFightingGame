package Animal;

public class Bear extends Animal {
    public Bear() {
        super("熊");
        HP = 150;
    }

    @Override
    public int attack() {
        if (random.nextDouble() < 0.3) {
            System.out.println("玩家(" + name + ")使用了強力攻擊");
            System.out.println("玩家(" + name + ")回復15點生命值");
            HP += 15;
            return useSkill();
        } else {
            System.out.println("玩家(" + name + ")使用了普通攻擊");
            System.out.println("Boss受到了攻擊，生命值-5");
            System.out.println("玩家(" + name + ")回復5點生命值");
            HP += 5;
            return 5;
        }
    }

    @Override
    public int useSkill() {
        System.out.println("懶熊壓制! Boss HP -15");
        return 15;
    }
}
