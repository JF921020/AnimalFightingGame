package Animal;

public class Tiger extends Animal {
    public Tiger() {
        super("老虎");
        HP = 120;
    }

    @Override
    public int attack() {
        if (random.nextDouble() < 0.1) {
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
        System.out.println("虎虎生風! Boss HP -20! 玩家(\" + name + \")回復20點生命值!");
        HP+=20;
        return 20;
    }
}
