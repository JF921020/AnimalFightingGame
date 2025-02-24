package Animal;

public class Dog extends Animal {
    public Dog() {
        super("狗");
    }
    @Override
    public int attack(int combo) {
        return combo > 2 ? 4 : 2;
    }
//    @Override
//    public int attack(int combo) {
//        if (combo > 2) {
//            System.out.println("玩家(狗)使用了瘋狗亂撞");
//            System.out.println("Boss受到了強力的攻擊，生命值-4");
//            return 4;
//        } else {
//            System.out.println("玩家(狗)使用了普通攻擊");
//            System.out.println("Boss受到了攻擊，生命值-2");
//            return 2;
//        }
//    }

}
