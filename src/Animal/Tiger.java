package Animal;

public class Tiger extends Animal {
    public Tiger() {
        super("老虎");
        hp = 120;
        mp = 100;
        maxHp = 120;
        rate = 0.1;
        super.skill1.name = "猛虎撲殺";
        super.skill2.name = "虎爪連擊";
        super.skill3.name = "猛虎怒吼";
        super.skill4.name = "疾風猛虎爪";

        super.skill1.damage = 15;
        super.skill2.damage = 18;
        super.skill3.damage = 25;
        super.skill4.damage = 40;

        super.skill1.mpConsume = 0;
        super.skill2.mpConsume = 5;
        super.skill3.mpConsume = 10;
        super.skill4.mpConsume = 30;

        showHpAndMp(hp, maxHp, mp, maxMP);

    }


}
