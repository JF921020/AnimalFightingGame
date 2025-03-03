package Animal;

import java.util.Random;

public class Boss {
        public int hp = 200;
        public int choice = 0;
        protected Random random = new Random();

        public int attack() {
            if (random.nextDouble() < 0.3) { // 30% 機率施放技能攻擊
                System.out.println("\u001B[31m魔王使用了暴擊攻擊! 玩家 HP -15");
                return 15;
            } else {
                System.out.println("\u001B[31m魔王使用了普通攻擊! 玩家 HP -5");
                return 5;
            }
        }
}





