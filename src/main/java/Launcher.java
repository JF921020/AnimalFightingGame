package user;

import user.function.ConsoleColor;

import java.util.Random;
import java.util.logging.Logger;

public class Launcher {
    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());
    /**
    *啟動程式
    **/
    public static void main(String[] args) {
        System.out.println(ConsoleColor.YELLOW+"回合制對戰遊戲"+ConsoleColor.RESET);
        LOGGER.info("遊戲初始化.....");
        Game.start(new Random());
    }
}
