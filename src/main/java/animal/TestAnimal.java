package user.animal;


public class TestAnimal extends Animal {

    /**測試用角色**/
    public TestAnimal(String name,int mp, int hp,int ATK, double rate) {
        super(name);
        this.hp = hp;
        this.mp = mp;
        this.maxHp = 100;
        this.maxMP = 100;
        this.ATK = ATK;
        this.maxATK = ATK;
        this.rate = rate;
        this.maxRate = rate;
        super.skill1.name = "測試技能1";
        super.skill2.name = "測試技能2";
        super.skill3.name = "測試技能3";
        super.skill4.name = "測試技能4";

        super.skill1.mpConsume = 1;
        super.skill2.mpConsume = 2;
        super.skill3.mpConsume = 3;
        super.skill4.mpConsume = 4;

        super.skill1.useCount = 2;
        super.skill2.useCount = 2;
        super.skill3.useCount = 2;
        super.skill4.useCount = 2;

    }


}
