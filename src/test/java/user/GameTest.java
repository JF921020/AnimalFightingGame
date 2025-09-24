package user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import user.animal.Animal;
import user.animal.TestAnimal;

import java.io.*;
import java.time.Duration;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @BeforeAll
    static void setUp(){
        Game game = new Game();
    }

    @Nested
    @DisplayName("角色相關機制")
    class character{
        // 測試角色選擇機制（輸入角色數字後返回正確角色）
        @DisplayName("角色選擇")
        @ParameterizedTest
        @CsvSource({
                "1, Dog, 狗, 350, 100, 15, 0.4",
                "2, Cat, 貓, 250, 100, 15, 0.5",
                "3, Bear, 熊, 450, 100, 12, 0.3",
                "4, Tiger, 老虎, 300, 100, 20, 0.1",
                "99, TestAnimal, test-player, 10, 10000, 1000, 0"
        })
        // 使用參數化測試，驗證 user.Game.playerChoose() 是否回傳正確角色
        void playerChoose(int input, String  expectedType, String expectedName, int expectedHp, int expectedMp, int expectedAtk, double expectedRate) {
            String simulatedInput = input + "\n";
            Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
            Animal result = Game.playerChoose(scanner);
            assertEquals(expectedType, result.getClass().getSimpleName());//檢查建構的角色類型
            assertEquals(expectedName, result.name);//檢查建構的角色名
            assertEquals(expectedHp, result.hp);//檢查建構的角色hp
            assertEquals(expectedMp, result.mp);//檢查建構的角色mp
            assertEquals(expectedAtk, result.ATK);//檢查建構的角色攻擊力
            assertEquals(expectedRate, result.rate); // double 有誤差容忍值
        }

        // 測試敵方角色選擇機制（輸入角色數字後返回正確角色）
        @DisplayName("敵方角色選擇")
        @ParameterizedTest
        @CsvSource({
                "1, Dog, 狗, 350, 100, 15, 0.4",
                "2, Cat, 貓, 250, 100, 15, 0.5",
                "3, Bear, 熊, 450, 100, 12, 0.3",
                "4, Tiger, 老虎, 300, 100, 20, 0.1",
                "99, TestAnimal, test-boss, 10, 10000, 10, 0"
        })
        // 使用參數化測試，驗證 user.Game.playerChoose() 是否回傳正確角色
        void bossChoose(int input, String  expectedType, String expectedName, int expectedHp, int expectedMp, int expectedAtk, double expectedRate) {
            String simulatedInput = input + "\n";
            Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
            Animal result = Game.bossChoose(scanner);

            assertEquals(expectedType, result.getClass().getSimpleName());
            assertEquals(expectedName, result.name);
            assertEquals(expectedHp, result.hp);
            assertEquals(expectedMp, result.mp);
            assertEquals(expectedAtk, result.ATK);
            assertEquals(expectedRate, result.rate); // double 有誤差容忍值
        }

        @Test
        @DisplayName("角色不存在-玩家")
        // 測試輸入錯誤角色數字（如 5）是否能提示並重新選擇
        void userNull() throws UnsupportedEncodingException {
            String input = "5\n";
            Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            assertEquals(null, Game.getAnimal(5));
            assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
                try {
                    Game.playerChoose(scanner);
                } catch (Exception ignored) {
                    // 不做任何事，只是為了觸發流程
                }
            });
            input = "0" + "\n";
            Scanner scanner2 = new Scanner(new ByteArrayInputStream(input.getBytes()));
            assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
                try {
                    Game.playerChoose(scanner2);

                } catch (Exception ignored) {
                    // 不做任何事，只是為了觸發流程
                }
            });
        }
        @Test
        @DisplayName("角色不存在-敵方")
        // 測試輸入錯誤角色數字（如 5）是否能提示並重新選擇
        void enemyNull(){
            String input = "5" + "\n";
            Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
            assertEquals(null, Game.getEnemy(5));
            assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
                try {
                    Game.bossChoose(scanner);
                } catch (Exception ignored) {
                    // 不做任何事，只是為了觸發流程
                }
            });
            input = "0" + "\n";
            Scanner scanner2 = new Scanner(new ByteArrayInputStream(input.getBytes()));
            assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
                try {
                    Game.bossChoose(scanner2);
                } catch (Exception ignored) {
                    // 不做任何事，只是為了觸發流程
                }
            });
        }
    }

    @Nested
    @DisplayName("戰鬥機制")
    class battle{
        @Test
        @DisplayName("戰鬥測試-玩家勝利")
        void battle1() throws Exception {
            Animal player = new TestAnimal("玩家",100, 100,1000,0);
            Animal boss = new TestAnimal("敵人",10, 10,0,0);
            //player攻擊力極高，boss.hp極低，使用一次技能即可結束遊戲

            String input = "1\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            Scanner scanner = new Scanner(System.in);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            Game.battle(player, boss, scanner, new Random());

            String output = outputStream.toString("UTF-8");
            assertTrue(output.contains("你贏了"));
        }

        @Test
        @DisplayName("戰鬥測試-敵方勝利")
        void battle2() throws Exception {
            Animal player = new TestAnimal("玩家",100, 10,0,0);
            Animal boss = new TestAnimal("敵人",100, 100,1000,0);
            //player.hp極低，boss攻擊力極高，經過一回合即可結束遊戲
            System.setIn(new ByteArrayInputStream("1\n".getBytes()));
            Scanner scanner = new Scanner(System.in);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));
            Game.battle(player, boss, scanner, new Random());
            player.hp = 0;
            System.setOut(originalOut);

            String output = outputStream.toString("UTF-8");
            assertTrue(output.contains("你輸了"));
        }

        @Test
        @DisplayName("輸入y（繼續遊戲）")
        void replayYes() {
            Scanner scanner = new Scanner(new ByteArrayInputStream("y\n".getBytes()));
            assertTrue(Game.Replay(scanner));
        }
        @Test
        @DisplayName("輸入n（結束遊戲）")
        void replayNo() {
            Scanner scanner = new Scanner(new ByteArrayInputStream("n\n".getBytes()));
            assertFalse(Game.Replay(scanner));
        }
        @Test
        @DisplayName("錯誤輸入")
        //檢查例外輸入是否有回傳提示
        void replayInvalid() throws Exception {
            Scanner scanner = new Scanner(new ByteArrayInputStream("abc\ny\n".getBytes()));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(out));
            boolean result = Game.Replay(scanner);
            System.setOut(originalOut);
            String output = out.toString("UTF-8");
            assertTrue(output.contains("無效輸入"));
            assertTrue(result);
        }
    }

    @Nested
    @DisplayName("遊戲模式相關機制")
    class mode {
        @Test
        @DisplayName("選擇遊戲模式")
        void selectModeTest() {
            String input = "1\n";
            Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
            assertEquals(1,Game.selectMode(scanner));
        }

        @Test
        @DisplayName("隨機模式")// 測試 Random 模式的完整流程
        void testCase1() {
            //腳本:模式1+選擇TestAnimal+使用技能1+再次遊玩+模式1+選擇TestAnimal+使用技能1+不再遊玩
            String input = "1\n99\n1\ny\n1\n99\n1\nn\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            Game.battleCalled = false;//檢查是否有進入戰鬥並完整結束
            Game.start(new Random());
            assertTrue(Game.battleCalled);
        }

        @Test
        @DisplayName("自由模式")// 測試 Free 模式的完整流程
        void testCase2() {
            //腳本:模式2+選擇TestAnimal+選擇敵方TestAnimal+使用技能1+不再遊玩
            String input = "2\n99\n99\n1\nn\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            Game.battleCalled = false;//檢查是否有進入戰鬥並完整結束
            Game.start(new Random());
            assertTrue(Game.battleCalled);
        }

        @Test
        @DisplayName("全隨機模式")// 測試Full Random 模式的完整流程
        void testCase3() {
            //腳本:模式3+使用1技能+不再遊玩
            StringBuilder inputBuilder = new StringBuilder();
            inputBuilder.append("3\n");
            for (int i = 0; i < 38; i++) {
                //因採用隨機角色，所以無法確定要使用幾次技能才能結束遊戲
                //假設敵方為hp多的角色，己方為攻擊力最低的角色，在不考慮暴擊的情況，需使用38次1技能才能結束遊戲
                inputBuilder.append("1\n");
                //如果遊戲提前結束，多餘的輸入1會被重開遊戲的功能視為輸入錯誤，並重新要求輸入，直到輸入次數達38次
            }
            inputBuilder.append("n\n");
            String input = inputBuilder.toString();
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            Game.battleCalled = false;//檢查是否有進入戰鬥並完整結束
            Game.start(new Random());
            assertTrue(Game.battleCalled);
        }

        @Test
        @DisplayName("所選模式不存在")
        void testCaseDefault() {
            //腳本:模式99(不存在)+不再遊玩
            String input = "99\nn\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            Game.battleCalled = false;//檢查是否有進入戰鬥並完整結束
            Game.start(new Random());
            assertFalse(Game.battleCalled);
        }
    }



}


