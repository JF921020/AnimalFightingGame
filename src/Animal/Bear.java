package Animal;

public class Bear extends Animal {
    public Bear() {
        super("熊");
        hp = 150;
        mp = 100;
        maxHp = 150;
        rate = 0.3;
        super.skill1.name = "巨熊衝撞";
        super.skill2.name = "熊爪橫掃";
        super.skill3.name = "大地震擊";
        super.skill4.name = "熊王之怒";

        super.skill1.damage = 10;
        super.skill2.damage = 18;
        super.skill3.damage = 22;
        super.skill4.damage = 25;

        super.skill1.mpConsume = 0;
        super.skill2.mpConsume = 5;
        super.skill3.mpConsume = 10;
        super.skill4.mpConsume = 20;

        showUserHpAndMp( hp, maxHp, mp, maxMP, name);

    }
}
