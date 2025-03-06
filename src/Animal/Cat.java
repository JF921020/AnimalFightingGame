package Animal;

public class Cat extends Animal {
    public Cat() {
        super("貓");
        hp = 250;
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
        super.skill4.mpConsume = 30;

        super.skill1.useCount = 100;
        super.skill2.useCount = 10;
        super.skill3.useCount = 4;
        super.skill4.useCount = 2;

        showHpAndMp(hp, maxHp, mp, maxMP);

    }

    @Override
    public int useSkill1() {
        skill1.useCount --;
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m" + name + "使用了" + skill1.name + "! 暴擊! 傷害" + skill1.damage * 2);
            return skill1.damage * 2;
        } else {
            System.out.println("\u001B[34m" + name + "使用了" + skill1.name + "! 傷害" + skill1.damage);
            return skill1.damage;
        }

    }

    public int useSkill2() {
        skill2.useCount --;
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m" + name + "使用了" + skill2.name + "! 暴擊! 傷害" + skill2.damage * 2);
            return skill2.damage * 2;
        } else {
            System.out.println("\u001B[34m" + name + "使用了" + skill2.name + "! 傷害" + skill2.damage);
            return skill2.damage;
        }

    }

    public int useSkill3() {
        skill3.useCount --;
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m" + name + "使用了" + skill3.name + "! 暴擊! 傷害" + skill3.damage * 2);
            return skill3.damage * 2;
        } else {
            System.out.println("\u001B[34m" + name + "使用了" + skill3.name + "! 傷害" + skill3.damage);
            return skill3.damage;
        }

    }

    public int useSkill4() {
        skill4.useCount --;
        if (random.nextDouble() < rate) {// 40% 機率觸發技能
            System.out.println("\u001B[34m" + name + "使用了" + skill4.name + "! 暴擊! 傷害" + skill4.damage * 2);
            return skill4.damage * 2;
        } else {
            System.out.println("\u001B[34m" + name + "使用了" + skill4.name + "! 傷害" + skill4.damage);
            return skill4.damage;
        }

    }

}

