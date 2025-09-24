package user.animal;
import user.function.*;

public class Cat extends Animal {
    public Cat() {
        super("貓");
        hp = 250;
        mp = 100;
        maxHp = 250;
        ATK = 15;
        maxATK = 15;
        rate = 0.5;
        maxRate = 0.5;
        super.skill1.name = "銳爪撕裂";
        super.skill1.description = "以銳利爪子撕裂敵人，造成高傷害，並有機率使敵人受到額外傷害。";

        super.skill2.name = "夜影突襲";
        super.skill2.description = "從黑暗中迅速襲擊敵人，造成中等傷害，並降低敵人的爆擊率。";

        super.skill3.name = "靈貓迴旋";
        super.skill3.description = "快速旋轉身體進行反擊，造成傷害。";

        super.skill4.name = "幻影連爪";
        super.skill4.description = "連續發動幻影攻擊，對敵人造成多次傷害。";



        showHpAndMp(hp, maxHp, mp, maxMP);

    }


}

