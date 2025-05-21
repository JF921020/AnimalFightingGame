package Animal;

import Function.ConsoleColor;

public class Tiger extends Animal {
    public Tiger() {
        super("老虎");
        hp = 300;
        mp = 100;
        maxHp = 300;
        int ATK = 20;
        int maxATK = 20;
        rate = 0.1;
        maxRate = 0.1;
        super.skill1.name = "猛虎撲殺";
        super.skill2.name = "虎爪連擊";
        super.skill3.name = "猛虎怒吼";
        super.skill4.name = "疾風猛虎爪";

        super.skill1.description = "對敵人造成重擊";
        super.skill2.description = "快速連擊，高機率造成4次傷害";
        super.skill3.description = "釋放震撼怒吼，降低敵人攻擊力30%，持續2回合";
        super.skill4.description = "疾風般撕裂敵人防禦";





        showHpAndMp(hp, maxHp, mp, maxMP);

    }

    @Override
    public int useSkill2(Animal user,Animal target) {
        if(user.mp >= user.skill2.mpConsume && user.skill2.useCount > 0){
            user.mp -= user.skill2.mpConsume;
            user.skill2.useCount--;
            int damage;
            if (random.nextDouble() < 0.6) {
                damage = user.ATK * 4;
                System.out.println(ConsoleColor.BLUE + name +"使用了" + user.skill2.name + "! 造成4次攻擊! 傷害" + damage);
            } else {
                damage = user.ATK;
                System.out.println(ConsoleColor.BLUE + name +"使用了" + user.skill2.name + "! 傷害" + damage);
            }
            return damage;
        }else{
            System.out.println(ConsoleColor.RED + "技能施放失敗" + ConsoleColor.RESET);
            return 0;
        }
    }

    @Override
    public int useSkill3(Animal user,Animal target) {
        if(user.mp >= user.skill3.mpConsume && user.skill3.useCount > 0){
            user.mp -= user.skill3.mpConsume;
            user.skill3.useCount--;
            System.out.println(ConsoleColor.BLUE + name +"使用了" + user.skill3.name + "! 敵方傷害降低30%，持續2回合!");
            if(target.debuff.state == 0){
                target.debuff.state = 2;
                target.ATK =target.ATK / 10 * 7;
            }
            target.debuff.duration = 1;


        }else{
            System.out.println(ConsoleColor.RED + "技能施放失敗" + ConsoleColor.RESET);
        }
        return 0;
    }


}
