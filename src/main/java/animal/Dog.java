package user.animal;

import user.function.*;

import java.util.Random;

public class Dog extends Animal {
    public Dog() {
        super("狗");
        hp = 350;
        mp = 100;
        maxHp = 350;
        ATK = 15;
        maxATK = 15;
        rate = 0.4;
        maxRate = 0.4;
        super.skill1.name = "血牙狩獵";
        super.skill2.name = "狂野突襲";
        super.skill3.name = "野獸咆哮";
        super.skill4.name = "裂喉撕咬";

        super.skill1.damage = ATK;
        super.skill2.damage = ATK * 2;
        super.skill3.damage = 0;
        super.skill4.damage = ATK * 3;


        super.skill1.description = "猛然撲向敵人，銜住要害並深深刺入，造成傷害。";
        super.skill2.description = "快速向前突進，以高速撞擊敵人，造成傷害。";
        super.skill3.description = "發出震耳欲聾的狂野怒吼，降低敵人40%攻擊力，持續2回合";
        super.skill4.description = "用尖銳的獠牙撕裂敵人的喉嚨，造成流血效果，使敵人持續失血。";

        showHpAndMp(hp, maxHp, mp, maxMP);
    }

    /**Dog特殊技能，無傷害，可造成目標攻擊力降低一回合**/
    @Override
    public int useSkill3(Animal user,Animal target, Random random) {
        if(user.mp >= user.skill3.mpConsume && user.skill3.useCount > 0){
            user.mp -= user.skill3.mpConsume;
            user.skill3.useCount--;
            System.out.println(ConsoleColor.BLUE + name +"使用了" + user.skill3.name + "! 敵方傷害降低40%，持續2回合!");
            if(target.debuff.state == 0){
                target.debuff.state = 2;
                target.ATK =target.ATK / 10 * 6;
            }
            target.debuff.duration = 1;
        }else{
            System.out.println(ConsoleColor.RED + "技能施放失敗" + ConsoleColor.RESET);
        }
        return 0;
    }

    @Override
    public int useSkill4(Animal user,Animal target, Random random) {
        if(user.mp >= user.skill4.mpConsume && user.skill4.useCount > 0){
            user.mp -= user.skill4.mpConsume;
            user.skill4.useCount--;
            System.out.println(ConsoleColor.BLUE + name +"使用了" + user.skill4.name + "! 造成流血效果，使敵人持續失血2回合!");
            if(target.debuff.state == 0){
                target.debuff.state = 1;
            }
            target.debuff.duration = 1;
            return user.ATK * 3;
        }else{
            System.out.println(ConsoleColor.RED + "技能施放失敗" + ConsoleColor.RESET);
            return 0;
        }
    }
}
