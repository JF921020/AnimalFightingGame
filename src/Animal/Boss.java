package Animal;

import java.util.Random;

public class Boss {
        public int HP = 200;
        public int choice = 0;
        protected Random random = new Random();

        public int attack() {
            if (random.nextDouble() < 0.3) { // 30% 機率施放技能攻擊
                System.out.println("Boss使用了暴擊攻擊!");
                System.out.println("玩家受到了強力的攻擊，生命值-15");
                return 15;
            } else {
                System.out.println("Boss使用了普通攻擊");
                System.out.println("玩家受到了攻擊，生命值-5");
                return 5;
            }
        }
}





