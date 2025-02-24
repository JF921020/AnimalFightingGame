package Animal;

public class Cat {
    public String name = "(Cat)";
    public int hp = 20;
    public int combo = 0;
    public int choice = 0;

    public int attack(int combo) {
        if (combo > 2) {
            System.out.println("玩家(貓)使用了貓貓爪擊");
            System.out.println("Boss受到了強力的攻擊，生命值-4");
            return 4;
        } else {
            System.out.println("玩家(貓)使用了普通攻擊");
            System.out.println("Boss受到了攻擊，生命值-2");
            return 2;
        }
    }

    public void Catjudge(Cat user, Boss boss) {
        String[] choices = {"", "剪刀", "石頭", "布"};

        if (user.choice < 1 || user.choice > 3 || boss.choice < 1 || boss.choice > 3) {
            System.out.println("輸入不合法");
            return;
        }

        System.out.println("玩家" + user.name + "出" + choices[user.choice]);
        System.out.println("Boss出" + choices[boss.choice]);

        if (user.choice == boss.choice) {
            System.out.println("無事發生");
        } else {
            processBattle(user, boss);
        }

        System.out.println("玩家" + user.name + " HP:" + user.hp);
        System.out.println("Boss HP:" + boss.hp);
    }

    private void processBattle(Cat user, Boss boss) {
        if ((user.choice == 1 && boss.choice == 3) ||
                (user.choice == 2 && boss.choice == 1) ||
                (user.choice == 3 && boss.choice == 2)) {
            user.combo++;
            boss.hp -= user.attack(user.combo);
        } else {
            boss.combo++;
            user.hp -= boss.attack(boss.combo);
        }
    }
}
