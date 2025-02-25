package Animal;

public abstract class Animal {
    protected String name;
    public int HP = 20;
    public int combo = 0;
    public int choice = 0;

    public Animal(String name) {
        this.name = name;
    }

    public int attack(int combo) {
        if (combo > 2) {
            System.out.println("玩家(" + name + ")使用了強力攻擊");
            System.out.println("Boss受到了強力的攻擊，生命值-4");
            return 4;
        } else {
            System.out.println("玩家(" + name + ")使用了普通攻擊");
            System.out.println("Boss受到了攻擊，生命值-2");
            return 2;
        }
    }

    public abstract int useSkill();

    public void judge(Animal user, Boss boss) {
        System.out.println("玩家" + user.name + "出招: " + getMoveName(user.choice));
        System.out.println("Boss出招: " + getMoveName(boss.choice));

        if (user.choice == boss.choice) {
            System.out.println("無事發生");
        } else if ((user.choice == 1 && boss.choice == 3) ||
                (user.choice == 2 && boss.choice == 1) ||
                (user.choice == 3 && boss.choice == 2)) {
            user.combo++;
            boss.HP -= user.attack(user.combo);
        } else {
            boss.combo++;
            user.HP -= boss.attack(boss.combo);
        }

        System.out.println("玩家 HP: " + user.HP);
        System.out.println("Boss HP: " + boss.HP);
    }

    private String getMoveName(int choice) {
        switch (choice) {
            case 1: return "剪刀";
            case 2: return "石頭";
            case 3: return "布";
            default: return "未知";
        }
    }
}
