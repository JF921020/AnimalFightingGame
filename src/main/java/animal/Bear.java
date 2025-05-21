package Animal;

import Function.ConsoleColor;

public class Bear extends Animal {
    public Bear() {
        super("熊");
        hp = 450;
        mp = 100;
        maxHp = 450;
        int ATK = 12;
        int maxATK = 12;
        rate = 0.3;
        maxRate = 0.3;
        super.skill1.name = "巨熊衝撞";
        super.skill2.name = "熊爪橫掃";
        super.skill3.name = "大地震擊";
        super.skill4.name = "熊王之怒";

        super.skill1.description = "以猛烈的撞擊攻擊敵人，造成物理傷害";
        super.skill2.description = "揮舞熊爪進行橫掃，造成大量傷害";
        super.skill3.description = "猛烈震擊大地，對敵人造成傷害，同時降低敵人攻擊力20%，持續2回合";
        super.skill4.description = "發動熊王之怒，造成巨量傷害，回復部分HP持續2回合";


        showHpAndMp(hp, maxHp, mp, maxMP);

    }

    @Override
    public int useSkill3(Animal user,Animal target) {
        if(user.mp >= user.skill3.mpConsume && user.skill3.useCount > 0){
            user.mp -= user.skill3.mpConsume;
            user.skill3.useCount--;
            System.out.println(ConsoleColor.BLUE + name +"使用了" + user.skill3.name + "! 敵方傷害降低20%，持續2回合!");
            if(target.debuff.state == 0){
                target.debuff.state = 2;
                target.ATK =target.ATK / 10 * 8;
            }
            target.debuff.duration = 1;
            return user.ATK * 2;
        }else{
            System.out.println(ConsoleColor.RED + "技能施放失敗" + ConsoleColor.RESET);
            return 0;
        }
    }

    @Override
    public int useSkill4(Animal user,Animal target) {
        if(user.mp >= user.skill4.mpConsume && user.skill4.useCount > 0){
            user.mp -= user.skill4.mpConsume;
            user.skill4.useCount--;
            System.out.println(ConsoleColor.BLUE + name +"使用了" + user.skill4.name + "! 持續回復部分HP2回合!");
            if(user.buff.state == 0){
                user.buff.state = 1;
            }
            user.buff.duration = 2;
            return user.ATK * 5;
        }else{
            System.out.println(ConsoleColor.RED + "技能施放失敗" + ConsoleColor.RESET);
            return 0;
        }
    }

}
