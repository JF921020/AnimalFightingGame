package Animal;

public class Dog extends Animal {
    public Dog() {
        super("狗");
        hp = 100;
        mp = 100;
        maxHp = 100;
        rate = 0.4;

        super.skill1.name = "咬住";
        super.skill2.name = "狂犬突襲";
        super.skill3.name = "利牙撕裂";
        super.skill4.name = "瘋狗亂撞";

        super.skill1.damage = 7;
        super.skill2.damage = 10;
        super.skill3.damage = 15;
        super.skill4.damage = 20;

        super.skill1.mpConsume = 0;
        super.skill2.mpConsume = 5;
        super.skill3.mpConsume = 10;
        super.skill4.mpConsume = 20;


        showUserHpAndMp( hp, maxHp, mp, maxMP, name);

    }
}
