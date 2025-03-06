package Animal;

public class Tiger extends Animal {
    public Tiger() {
        super("老虎");
        hp = 300;
        mp = 100;
        maxHp = 120;
        rate = 0.1;
        super.skill1.name = "猛虎撲殺";
        super.skill2.name = "虎爪連擊";
        super.skill3.name = "猛虎怒吼";
        super.skill4.name = "疾風猛虎爪";

        super.skill1.damage = 15;
        super.skill2.damage = 18;
        super.skill3.damage = 0;
        super.skill4.damage = 40;

        super.skill1.mpConsume = 0;
        super.skill2.mpConsume = 5;
        super.skill3.mpConsume = 25;
        super.skill4.mpConsume = 30;

        super.skill1.useCount = 100;
        super.skill2.useCount = 10;
        super.skill3.useCount = 4;
        super.skill4.useCount = 2;

        showHpAndMp(hp, maxHp, mp, maxMP);

    }
    private int endSkill3 = 0;

    @Override
    public int useSkill1() {
        if(endSkill3 == 0) {
            rate = 0.1;
        }
        skill1.useCount--;
        endSkill3--;
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m" + name + "使用了" + skill1.name + "! 暴擊! 傷害" + skill1.damage * 2);
            return skill1.damage * 2;
        } else {
            System.out.println("\u001B[34m" + name + "使用了" + skill1.name + "! 傷害" + skill1.damage);
            return skill1.damage;
        }


    }

    public int useSkill2() {
        skill2.useCount--;
        if(endSkill3 == 0) {
            rate = 0.1;
        }
        endSkill3--;
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m" + name + "使用了" + skill2.name + "! 暴擊! 傷害" + skill2.damage * 2);
            return skill2.damage * 2;
        } else {
            System.out.println("\u001B[34m" + name + "使用了" + skill2.name + "! 傷害" + skill2.damage);
            return skill2.damage;
        }

    }

    public int useSkill3() {
        if(endSkill3 == 0) {
            rate = 0.1;
        }
        skill3.useCount--;
        System.out.println("\u001B[34m" + name + "使用了" + skill3.name + "! 爆擊率持續提升2回合");
        rate = 0.4;
        endSkill3 = 2;
        return skill3.damage;

    }

    public int useSkill4() {
        if(endSkill3 == 0) {
            rate = 0.1;
        }
        skill4.useCount--;
        endSkill3--;
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m" + name + "使用了" + skill4.name + "! 暴擊! 傷害" + skill4.damage * 2);
            return skill4.damage * 2;
        } else {
            System.out.println("\u001B[34m" + name + "使用了" + skill4.name + "! 傷害" + skill4.damage);
            return skill4.damage;
        }

    }



}
