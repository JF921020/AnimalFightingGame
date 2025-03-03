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
        super.skill4.mpConsume = 20;

        showUserHpAndMp( hp, maxHp, mp, maxMP, name);

    }



    @Override
    public int useSkill1() {
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m玩家(" + name + ")使用了" + skill1.name + "! 暴擊! 魔王 HP -" + skill1.damage * 2);
            return skill1.damage * 2;
        }else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了" + skill1.name + "! 魔王 HP -" + skill1.damage );
            return skill1.damage;
        }

    }

    @Override
    public int useSkill2() {
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m玩家(" + name + ")使用了" + skill2.name + "! 暴擊! 魔王 HP -" + skill2.damage * 2);
            return skill2.damage * 2;
        }else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了" + skill2.name + "! 魔王 HP -" + skill2.damage );
            return skill2.damage;
        }

    }

    @Override
    public int useSkill3() {
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m玩家(" + name + ")使用了" + skill3.name + "! 暴擊! 魔王 HP -" + skill3.damage * 2);
            return skill3.damage * 2;
        }else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了" + skill3.name + "! 魔王 HP -" + skill3.damage );
            return skill3.damage;
        }

    }

    @Override
    public int useSkill4() {
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m玩家(" + name + ")使用了" + skill4.name + "! 暴擊! 魔王 HP -" + skill4.damage * 2);
            return skill4.damage * 2;
        }else {
            System.out.println("\u001B[34m玩家(" + name + ")使用了" + skill4.name + "! 魔王 HP -" + skill4.damage );
            return skill4.damage;
        }

    }
}
