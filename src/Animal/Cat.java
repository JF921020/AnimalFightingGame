package Animal;

public class Cat extends Animal {
    public Cat() {
        super("貓");
        hp = 80;
        mp = 100;
        maxHp = 80;
        rate = 0.3;
        super.skill1.name = "銳爪撕裂";
        super.skill2.name = "夜影突襲";
        super.skill3.name = "靈貓迴旋";
        super.skill4.name = "幻影連爪";

        super.skill1.damage = 7;
        super.skill2.damage = 10;
        super.skill3.damage = 15;
        super.skill4.damage = 20;

        super.skill1.mpConsume = 0;
        super.skill2.mpConsume = 5;
        super.skill3.mpConsume = 10;
        super.skill4.mpConsume = 20;

        showUserHpAndMp(hp, maxHp, mp, maxMP, name);

    }
}

