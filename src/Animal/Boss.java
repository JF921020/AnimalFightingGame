package Animal;

public class Boss implements AnimalCreate {
    public int hp = 30;
    public int combo = 0;
    public int choice = 0;

    @Override
    public int attack(int combo) {
        if (combo > 2) {
            System.out.println("Boss使用了暴擊");
            System.out.println("玩家受到了強力的攻擊，生命值-4");
            return 4;
        } else {
            System.out.println("Boss使用了普通攻擊");
            System.out.println("玩家受到了攻擊，生命值-2");
            return 2;
        }
    }


}
