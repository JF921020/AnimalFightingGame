package user.animal;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AnimalTest {

    @Nested
    @DisplayName("角色行動模組")
    class damageTest {
        @Test
        @DisplayName("敵人行動模組")
        void enemyAction(){
            //檢查敵人有沒有照選擇執行對應技能
            Animal player = new TestAnimal("TestPlayer",10,10000,10,0);
            Animal boss = new TestAnimal("Testboss",1000,100,10,0);
            boss.choice = 1;
            assertEquals(10,boss.enemyAction(boss,player));
            boss.choice = 2;
            assertEquals(20,boss.enemyAction(boss,player));
            boss.choice = 3;
            assertEquals(30,boss.enemyAction(boss,player));
            boss.choice = 4;
            assertEquals(40,boss.enemyAction(boss,player));
            boss.choice = 5;
            assertEquals(0,boss.enemyAction(boss,player));
        }

        @Test
        @DisplayName("玩家行動模組")
        void userAction()throws Exception{
            //檢查玩家角色有沒有照選擇執行對應技能
            //用技能傷害去檢測施放的是哪個技能
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(out));
            out.reset();
            Animal player = new TestAnimal("TestPlayer",100,100,5,0);
            Animal boss = new TestAnimal("TestBoss",100,100,5,0);
            Scanner scanner = new Scanner(System.in);
            player.choice = 1;
            player.userAction(player,boss,scanner);
            assertEquals(95,boss.hp);
            player.choice = 2;
            player.userAction(player,boss,scanner);
            assertEquals(85,boss.hp);
            player.choice = 3;
            player.userAction(player,boss,scanner);
            assertEquals(70,boss.hp);
            player.choice = 4;
            player.userAction(player,boss,scanner);
            assertEquals(50,boss.hp);
            //測試damage大於目標血量時，最後輸出的boss.hp是否會為零
            boss.hp = 5;
            player.choice = 4;
            player.userAction(player,boss,scanner);
            assertEquals(0,boss.hp);
            player.choice = 6;
            player.userAction(player,boss,scanner);
            System.setOut(originalOut);
            String output = out.toString("UTF-8");
            assertTrue(output.contains("技能施放失敗"));
        }

        @Nested
        @DisplayName("道具使用測試")
        class useProp{
            @Test
            @DisplayName("回血道具使用機制")
            void useHpprops(){
                String simulatedInput ="1\n";
                Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
                Animal player = new TestAnimal("TestPlayer",10,10,10,0);
                Animal boss = new TestAnimal("TestBoss",100,100,0,0);
                player.maxHp = 100;
                player.choice = 5;
                player.userAction(player,boss,scanner);
                assertEquals(90,player.hp);
                assertEquals(2,player.HPprops.propsCount);
                scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
                player.userAction(player,boss,scanner);
                assertEquals(100,player.hp);//道具回復量超過最大生命值
                assertEquals(1,player.HPprops.propsCount);
            }
            @Test
            @DisplayName("回魔道具使用機制")
            void useMpprops(){
                String simulatedInput ="2\n";
                Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
                Animal player = new TestAnimal("TestPlayer",10,10,10,0);
                Animal boss = new TestAnimal("TestBoss",100,100,0,0);
                player.maxMP = 100;
                player.choice = 5;
                player.userAction(player,boss,scanner);
                assertEquals(90,player.mp);
                assertEquals(2,player.MPprops.propsCount);
                scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
                player.userAction(player,boss,scanner);
                assertEquals(100,player.mp);//道具回復量超過最大生命值
                assertEquals(1,player.MPprops.propsCount);
            }

            @Test
            @DisplayName("道具使用失敗")
            void usePropsFail() throws Exception {
                String simulatedInput ="3\n";
                Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new TestAnimal("TestPlayer",10,10,10,0);
                Animal boss = new TestAnimal("TestBoss",100,100,0,0);
                out.reset();
                player.choice = 5;
                player.userAction(player,boss,scanner);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertTrue(output.contains("沒有此道具"));

                player.HPprops.propsCount = 0;
                player.MPprops.propsCount = 0;
                simulatedInput ="1\n";
                scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
                out = new ByteArrayOutputStream();
                originalOut = System.out;
                System.setOut(new PrintStream(out));
                out.reset();
                player.choice = 5;
                player.userAction(player,boss,scanner);
                System.setOut(originalOut);
                output = out.toString("UTF-8");
                assertTrue(output.contains("道具不足!!"));

                simulatedInput ="2\n";
                scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
                out = new ByteArrayOutputStream();
                originalOut = System.out;
                System.setOut(new PrintStream(out));
                out.reset();
                player.choice = 5;
                player.userAction(player,boss,scanner);
                System.setOut(originalOut);
                output = out.toString("UTF-8");
                assertTrue(output.contains("道具不足!!"));

            }
        }

        @Nested
        @DisplayName("增減益效果")
        class buffAndDebuff {
            @Test
            @DisplayName("Buff效果檢查")
            void buffTest(){
                Animal player = new TestAnimal("TestPlayer",100,100,10,0.7);
                player.buff.state = 2;//增加攻擊力，但運算邏輯會放在對應的技能模組裡
                player.buff.duration = 2;
                player.BuffCheck(player);
                assertEquals(1,player.buff.duration);
                player.BuffCheck(player);
                assertEquals(0,player.buff.duration);

                player.maxHp = 200;
                player.buff.state = 1;//每回合回復hp
                player.buff.duration = 1;
                player.BuffCheck(player);
                assertEquals(0,player.buff.duration);
                assertEquals(120,player.hp);

                player.hp = 190;
                player.buff.state = 1;
                player.buff.duration = 1;
                player.BuffCheck(player);
                assertEquals(0,player.buff.duration);
                assertEquals(200,player.hp);

                player.buff.state = 0;//無buff，攻擊力和暴擊率回復正常
                player.ATK = 5;
                player.rate = 0.8;
                player.BuffCheck(player);
                assertEquals(0,player.buff.duration);
                assertEquals(10,player.ATK);
                assertEquals(0.7,player.rate);

                player.buff.state = 2;//duration為0時，回復到無buff狀態
                player.buff.duration = 0;
                player.BuffCheck(player);
                assertEquals(0,player.buff.state);
            }

            @Test
            @DisplayName("DeBuff效果檢查")
            void deBuffTest(){
                Animal player = new TestAnimal("TestPlayer",100,100,10,0.7);
                player.debuff.state = 2;//減少攻擊力，但運算邏輯會放在對應的技能模組裡
                player.debuff.duration = 2;
                player.deBuffCheck(player);
                assertEquals(1,player.debuff.duration);
                player.deBuffCheck(player);
                assertEquals(0,player.debuff.duration);

                player.maxHp = 200;
                player.debuff.state = 1;//每回合回復hp
                player.debuff.duration = 1;
                player.deBuffCheck(player);
                assertEquals(0,player.debuff.duration);
                assertEquals(80,player.hp);

                player.hp = 10;
                player.debuff.state = 1;
                player.debuff.duration = 1;
                player.deBuffCheck(player);
                assertEquals(0,player.debuff.duration);
                assertEquals(0,player.hp);

                player.debuff.state = 0;//無buff，攻擊力和暴擊率回復正常
                player.ATK = 5;
                player.rate = 0.8;
                player.deBuffCheck(player);
                assertEquals(0,player.debuff.duration);
                assertEquals(10,player.ATK);
                assertEquals(0.7,player.rate);

                player.debuff.state = 2;
                player.debuff.duration = 0;
                player.deBuffCheck(player);
                assertEquals(0,player.debuff.state);
            }
        }

    }

    @Nested
    @DisplayName("技能機制測試")
    class skillTest {
        @Nested
        @DisplayName("技能組(Dog)")
        class DogSkill{
            @ParameterizedTest
            @CsvSource({
                    "1, 15",
                    "0.8, 15",
                    "0.41, 15",
                    "0.39, 30",
                    "0.28, 30",
                    "0, 30"
            })//15是常態傷害，30是暴擊傷害
            @DisplayName("技能1")
            void Skill1(double testRate,int expectDamage) {
                Animal player = new Dog();
                Animal boss = new Dog();
                //固定隨機值，低於 rate = 0.4，會暴擊
                Random fakeRandom = new Random() {
                    @Override
                    public double nextDouble() {
                        return testRate;
                    }
                };
                assertEquals(expectDamage, player.useSkill1(player,boss,fakeRandom));
            }

            @Test
            @DisplayName("技能1-施放失敗(次數不足)")//技能1不消耗mp
            //檢查次數不足的情況下，使用技能是否會回傳提示
            void Skill1FailByNoCount() throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Dog();
                Animal boss = new Dog();
                out.reset();
                player.skill1.useCount = 0;
                assertEquals(0,player.useSkill1(player,boss,player.random));
                assertEquals(0,player.skill1.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertTrue(output.contains("技能施放失敗"));
            }


            @ParameterizedTest
            @CsvSource({
                    "1, 30",
                    "0.75, 30",
                    "0.401, 30",
                    "0.399, 60",
                    "0.28, 60",
                    "0, 60"
            })
            @DisplayName("技能2")
            void Skill2(double testRate,int expectDamage) {
                Animal player = new Dog();
                Animal boss = new Dog();
                //固定隨機值，低於 rate = 0.4，會暴擊
                Random fakeRandom = new Random() {
                    @Override
                    public double nextDouble() {
                        return testRate;
                    }
                };

                assertEquals(expectDamage, player.useSkill2(player,boss,fakeRandom));
            }

            @DisplayName("技能2-施放失敗(mp不足)")
            @ParameterizedTest
            @CsvSource({
                    "1, 1, True",
                    "2, 1, True",
                    "3, 1, True",
                    "4, 1, True",
                    "5, 0, False",
                    "6, 0, False"
            })
                //檢查mp不足的情況下，使用技能是否會回傳提示
            void Skill2FailByNoMp(int mp, int expectCount, boolean expect) throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Dog();
                Animal boss = new Dog();
                player.mp = mp;
                player.skill2.useCount = 1;
                player.useSkill2(player,boss,player.random);
                assertEquals(expectCount,player.skill2.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertEquals(expect,output.contains("技能施放失敗"));
            }

            @Test
            @DisplayName("技能2-施放失敗(次數不足)")
            void Skill2FailByNoCount() throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Dog();
                Animal boss = new Dog();
                out.reset();
                player.skill2.useCount = 0;
                assertEquals(0,player.useSkill2(player,boss,player.random));
                assertEquals(0,player.skill2.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertTrue(output.contains("技能施放失敗"));
            }

            @Test
            @DisplayName("技能3")//該技能會降低敵人攻擊力
            void Skill3(){
                Animal player = new Dog();
                Animal boss = new Dog();
                boss.debuff.state = 0;
                assertEquals(0,player.useSkill3(player,boss,player.random));
                assertEquals(4,player.skill3.useCount);
                assertEquals(95,player.mp);
                assertEquals(6,boss.ATK);
                //檢查敵人是否有被降低攻擊力
                assertEquals(1,boss.debuff.duration);
                boss.debuff.state = 1;
                //敵人身上已存在debuff的情況下，不會附加新的debuff
                assertEquals(0,player.useSkill3(player,boss,player.random));
                assertEquals(3,player.skill3.useCount);
                assertEquals(90,player.mp);
                assertEquals(6,boss.ATK);
                assertEquals(1,boss.debuff.duration);
            }

            @Test
            @DisplayName("技能3-施放失敗(次數不足)")
            void Skill3FailByNoCount() throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Dog();
                Animal boss = new Dog();
                out.reset();
                player.skill3.useCount = 0;
                assertEquals(0,player.useSkill3(player,boss,player.random));
                assertEquals(0,player.skill3.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertTrue(output.contains("技能施放失敗"));
            }

            @DisplayName("技能3-施放失敗(mp不足)")
            @ParameterizedTest
            @CsvSource({
                    "1, 1, True",
                    "4, 1, True",
                    "5, 0, False",
                    "6, 0, False"
            })
            void Skill3FailByNoMp(int mp, int expectCount, boolean expect) throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Dog();
                Animal boss = new Dog();
                player.mp = mp;
                player.skill3.useCount = 1;
                player.useSkill3(player,boss,player.random);
                assertEquals(expectCount,player.skill3.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertEquals(expect,output.contains("技能施放失敗"));
            }

            @Test
            @DisplayName("技能4")
            void Skill4Success() {
                Animal player = new Dog();
                Animal boss = new Dog();

                boss.debuff.state = 0;
                assertEquals(45, player.useSkill4(player, boss,player.random));
                assertEquals(4, player.skill4.useCount);
                assertEquals(90, player.mp);
                assertEquals(1, boss.debuff.state);
                assertEquals(1, boss.debuff.duration);

                boss.debuff.state = 1;
                //敵人身上已存在debuff的情況下，不會附加新的debuff
                assertEquals(45, player.useSkill4(player, boss,player.random));
                assertEquals(80, player.mp);
                assertEquals(1, boss.debuff.state);
                assertEquals(1, boss.debuff.duration);
            }

            @Test
            @DisplayName("技能4-施放失敗(次數不足)")
            void Skill4FailByNoCount() throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Dog();
                Animal boss = new Dog();
                out.reset();
                player.skill4.useCount = 0;
                assertEquals(0,player.useSkill4(player,boss,player.random));
                assertEquals(0,player.skill4.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertTrue(output.contains("技能施放失敗"));
            }

            @DisplayName("技能4-施放失敗(mp不足)")
            @ParameterizedTest
            @CsvSource({
                    "1, 1, True",
                    "2, 1, True",
                    "3, 1, True",
                    "4, 1, True",
                    "10, 0, False",
                    "11, 0, False"
            })
            void Skill4FailByNoMp(int mp, int expectCount, boolean expect) throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Dog();
                Animal boss = new Dog();
                player.mp = mp;
                player.skill4.useCount = 1;
                player.useSkill4(player,boss,player.random);
                assertEquals(expectCount,player.skill4.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertEquals(expect,output.contains("技能施放失敗"));
            }

        }

        @Nested
        @DisplayName("技能組(Cat)")
        class CatSkill{
            @ParameterizedTest
            @CsvSource({
                    "1, 15",
                    "0.8, 15",
                    "0.51, 15",
                    "0.49, 30",
                    "0.28, 30",
                    "0, 30"
            })
            @DisplayName("技能1")
            void Skill1(double testRate,int expectDamage) {
                Animal player = new Cat();
                Animal boss = new Cat();
                //固定隨機值，低於 rate = 0.5，會暴擊
                Random fakeRandom = new Random() {
                    @Override
                    public double nextDouble() {
                        return testRate;
                    }
                };

                assertEquals(expectDamage, player.useSkill1(player,boss,fakeRandom));
            }

            @Test
            @DisplayName("技能1-施放失敗(次數不足)")//技能1不消耗mp
            void Skill1FailByNoCount() throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Cat();
                Animal boss = new Cat();
                out.reset();
                player.skill1.useCount = 0;
                assertEquals(0,player.useSkill1(player,boss,player.random));
                assertEquals(0,player.skill1.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertTrue(output.contains("技能施放失敗"));
            }


            @ParameterizedTest
            @CsvSource({
                    "1, 30",
                    "0.8, 30",
                    "0.51, 30",
                    "0.49, 60",
                    "0.28, 60",
                    "0, 60"
            })
            @DisplayName("技能2")
            void Skill2(double testRate,int expectDamage) {
                Animal player = new Cat();
                Animal boss = new Cat();
                //固定隨機值，低於 rate = 0.5，會暴擊
                Random fakeRandom = new Random() {
                    @Override
                    public double nextDouble() {
                        return testRate;
                    }
                };

                assertEquals(expectDamage, player.useSkill2(player,boss,fakeRandom));
            }

            @DisplayName("技能2-施放失敗(mp不足)")
            @ParameterizedTest
            @CsvSource({
                    "1, 1, True",
                    "2, 1, True",
                    "3, 1, True",
                    "4, 1, True",
                    "5, 0, False",
                    "6, 0, False"
            })
            void Skill2FailByNoMp(int mp, int expectCount, boolean expect) throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Cat();
                Animal boss = new Cat();
                player.mp = mp;
                player.skill2.useCount = 1;
                player.useSkill2(player,boss,player.random);
                assertEquals(expectCount,player.skill2.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertEquals(expect,output.contains("技能施放失敗"));
            }

            @Test
            @DisplayName("技能2-施放失敗(次數不足)")
            void Skill2FailByNoCount() throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Cat();
                Animal boss = new Cat();
                out.reset();
                player.skill2.useCount = 0;
                assertEquals(0,player.useSkill2(player,boss,player.random));
                assertEquals(0,player.skill2.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertTrue(output.contains("技能施放失敗"));
            }

            @ParameterizedTest
            @CsvSource({
                    "1, 45",
                    "0.8, 45",
                    "0.51, 45",
                    "0.49, 90",
                    "0.28, 90",
                    "0, 90"
            })
            @DisplayName("技能3")
            void Skill3(double testRate,int expectDamage) {
                Animal player = new Cat();
                Animal boss = new Cat();
                //固定隨機值，低於 rate = 0.5，會暴擊
                Random fakeRandom = new Random() {
                    @Override
                    public double nextDouble() {
                        return testRate;
                    }
                };

                assertEquals(expectDamage, player.useSkill3(player,boss,fakeRandom));
            }

            @Test
            @DisplayName("技能3-施放失敗(次數不足)")
            void Skill3FailByNoCount() throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Cat();
                Animal boss = new Cat();
                out.reset();
                player.skill3.useCount = 0;
                assertEquals(0,player.useSkill3(player,boss,player.random));
                assertEquals(0,player.skill3.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertTrue(output.contains("技能施放失敗"));
            }

            @DisplayName("技能3-施放失敗(mp不足)")
            @ParameterizedTest
            @CsvSource({
                    "1, 1, True",
                    "2, 1, True",
                    "3, 1, True",
                    "4, 1, True",
                    "5, 0, False",
                    "6, 0, False"
            })
            void Skill3FailByNoMp(int mp, int expectCount, boolean expect) throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Cat();
                Animal boss = new Cat();
                player.mp = mp;
                player.skill3.useCount = 1;
                player.useSkill3(player,boss,player.random);
                assertEquals(expectCount,player.skill3.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertEquals(expect,output.contains("技能施放失敗"));
            }

            @ParameterizedTest
            @CsvSource({
                    "1, 60",
                    "0.8, 60",
                    "0.51, 60",
                    "0.49, 120",
                    "0.28, 120",
                    "0, 120"
            })
            @DisplayName("技能4")
            void Skill4(double testRate,int expectDamage) {
                Animal player = new Cat();
                Animal boss = new Cat();
                //固定隨機值，低於 rate = 0.5，會暴擊
                Random fakeRandom = new Random() {
                    @Override
                    public double nextDouble() {
                        return testRate;
                    }
                };

                assertEquals(expectDamage, player.useSkill4(player,boss,fakeRandom));
            }

            @Test
            @DisplayName("技能4-施放失敗(次數不足)")
            void Skill4FailByNoCount() throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Cat();
                Animal boss = new Cat();
                out.reset();
                player.skill4.useCount = 0;
                assertEquals(0,player.useSkill4(player,boss,player.random));
                assertEquals(0,player.skill4.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertTrue(output.contains("技能施放失敗"));
            }

            @DisplayName("技能4-施放失敗(mp不足)")
            @ParameterizedTest
            @CsvSource({
                    "1, 1, True",
                    "2, 1, True",
                    "3, 1, True",
                    "4, 1, True",
                    "10, 0, False",
                    "11, 0, False"
            })
            void Skill4FailByNoMp(int mp, int expectCount, boolean expect) throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Cat();
                Animal boss = new Cat();
                player.mp = mp;
                player.skill4.useCount = 1;
                player.useSkill4(player,boss,player.random);
                assertEquals(expectCount,player.skill4.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertEquals(expect,output.contains("技能施放失敗"));
            }

        }

        @Nested
        @DisplayName("技能組(Bear)")
        class BearSkill {

            @ParameterizedTest
            @CsvSource({
                    "1, 12",
                    "0.8, 12",
                    "0.31, 12",
                    "0.29, 24",
                    "0.15, 24",
                    "0, 24"
            })
            @DisplayName("技能1")
            void Skill1(double testRate,int expectDamage) {
                Animal player = new Bear();
                Animal boss = new Bear();
                //固定隨機值，低於 rate = 0.3，會暴擊
                Random fakeRandom = new Random() {
                    @Override
                    public double nextDouble() {
                        return testRate;
                    }
                };

                assertEquals(expectDamage, player.useSkill1(player,boss,fakeRandom));
            }

            @Test
            @DisplayName("技能1-施放失敗(次數不足)")//技能1不消耗mp
            void Skill1FailByNoCount() throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Bear();
                Animal boss = new Bear();
                out.reset();
                player.skill1.useCount = 0;
                assertEquals(0,player.useSkill1(player,boss,player.random));
                assertEquals(0,player.skill1.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertTrue(output.contains("技能施放失敗"));
            }


            @ParameterizedTest
            @CsvSource({
                    "1, 24",
                    "0.8, 24",
                    "0.31, 24",
                    "0.29, 48",
                    "0.15, 48",
                    "0, 48"
            })
            @DisplayName("技能2")
            void Skill2(double testRate,int expectDamage) {
                Animal player = new Bear();
                Animal boss = new Bear();
                //固定隨機值，低於 rate = 0.3，會暴擊
                Random fakeRandom = new Random() {
                    @Override
                    public double nextDouble() {
                        return testRate;
                    }
                };

                assertEquals(expectDamage, player.useSkill2(player,boss,fakeRandom));
            }

            @DisplayName("技能2-施放失敗(mp不足)")
            @ParameterizedTest
            @CsvSource({
                    "1, 1, True",
                    "2, 1, True",
                    "3, 1, True",
                    "4, 1, True",
                    "5, 0, False",
                    "6, 0, False"
            })
            void Skill2FailByNoMp(int mp, int expectCount, boolean expect) throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Bear();
                Animal boss = new Bear();
                player.mp = mp;
                player.skill2.useCount = 1;
                player.useSkill2(player,boss,player.random);
                assertEquals(expectCount,player.skill2.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertEquals(expect,output.contains("技能施放失敗"));
            }

            @Test
            @DisplayName("技能2-施放失敗(次數不足)")
            void Skill2FailByNoCount() throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Bear();
                Animal boss = new Bear();
                out.reset();
                player.skill2.useCount = 0;
                assertEquals(0,player.useSkill2(player,boss,player.random));
                assertEquals(0,player.skill2.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertTrue(output.contains("技能施放失敗"));
            }

            @Test
            @DisplayName("技能3 成功施放")
            void bearSkill3() {
                Animal player = new Bear();
                Animal boss = new Bear();
                boss.debuff.state = 0;
                assertEquals(24, player.useSkill3(player, boss,player.random));
                assertEquals(95, player.mp);
                assertEquals(4, player.skill3.useCount);
                assertEquals(8, boss.ATK);
                assertEquals(2, boss.debuff.duration);
                assertEquals(2, boss.debuff.state);
                boss.debuff.state = 1;
                //敵人身上已存在debuff的情況下，不會附加新的debuff
                assertEquals(24,player.useSkill3(player,boss,player.random));
                assertEquals(3,player.skill3.useCount);
                assertEquals(90,player.mp);
                assertEquals(8,boss.ATK);
            }

            @DisplayName("技能3-施放失敗(mp不足)")
            @ParameterizedTest
            @CsvSource({
                    "1, 1, True",
                    "2, 1, True",
                    "3, 1, True",
                    "4, 1, True",
                    "5, 0, False",
                    "6, 0, False"
            })
            void Skill3FailByNoMp(int mp, int expectCount, boolean expect) throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Bear();
                Animal boss = new Bear();
                player.mp = mp;
                player.skill3.useCount = 1;
                player.useSkill3(player,boss,player.random);
                assertEquals(expectCount,player.skill3.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertEquals(expect,output.contains("技能施放失敗"));
            }

            @Test
            @DisplayName("技能3-施放失敗(次數不足)")
            void Skill3FailByNoCount() throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Bear();
                Animal boss = new Bear();
                out.reset();
                player.skill3.useCount = 0;
                assertEquals(0,player.useSkill3(player,boss,player.random));
                assertEquals(0,player.skill3.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertTrue(output.contains("技能施放失敗"));
            }




            @Test
            @DisplayName("技能4")//該技能會回復角色血量
            void bearSkill4() {
                Animal player = new Bear();
                Animal boss = new Bear();
                player.hp = 200;
                assertEquals(60, player.useSkill4(player, boss,player.random));
                assertEquals(90, player.mp);
                assertEquals(4, player.skill4.useCount);
                assertEquals(245, player.hp);
                assertEquals(1, player.buff.duration);
                assertEquals(1, player.buff.state);
            }

            @DisplayName("技能4-施放失敗(mp不足)")
            @ParameterizedTest
            @CsvSource({
                    "1, 1, True",
                    "2, 1, True",
                    "3, 1, True",
                    "4, 1, True",
                    "10, 0, False",
                    "11, 0, False"
            })
            void Skill4FailByNoMp(int mp, int expectCount, boolean expect) throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Bear();
                Animal boss = new Bear();
                player.mp = mp;
                player.skill4.useCount = 1;
                player.useSkill4(player,boss,player.random);
                assertEquals(expectCount,player.skill4.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertEquals(expect,output.contains("技能施放失敗"));
            }

            @Test
            @DisplayName("技能4-施放失敗(次數不足)")
            void Skill4FailByNoCount() throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Bear();
                Animal boss = new Bear();
                out.reset();
                player.skill4.useCount = 0;
                assertEquals(0,player.useSkill4(player,boss,player.random));
                assertEquals(0,player.skill4.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertTrue(output.contains("技能施放失敗"));
            }


        }

        @Nested
        @DisplayName("技能組(Tiger)")
        class TigerSkill{
            @ParameterizedTest
            @CsvSource({
                    "1, 20",
                    "0.8, 20",
                    "0.11, 20",
                    "0.09, 40",
                    "0.03, 40",
                    "0, 40"
            })
            @DisplayName("技能1")
            void Skill1(double testRate,int expectDamage) {
                Animal player = new Tiger();
                Animal boss = new Tiger();
                //固定隨機值，低於 rate = 0.1，會暴擊
                Random fakeRandom = new Random() {
                    @Override
                    public double nextDouble() {
                        return testRate;
                    }
                };

                assertEquals(expectDamage, player.useSkill1(player,boss,fakeRandom));
            }

            @Test
            @DisplayName("技能1-施放失敗(次數不足)")//技能1不消耗mp
            void Skill1FailByNoCount() throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Tiger();
                Animal boss = new Tiger();
                out.reset();
                player.skill1.useCount = 0;
                assertEquals(0,player.useSkill1(player,boss,player.random));
                assertEquals(0,player.skill1.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertTrue(output.contains("技能施放失敗"));
            }


            @Test
            @DisplayName("技能2")
            void Skill2() {
                Animal player = new Tiger();
                Animal boss = new Tiger();
                assertEquals(80, player.useSkill2(player, boss,player.random));
                assertEquals(95, player.mp);
                assertEquals(9, player.skill2.useCount);
                assertEquals(80,player.useSkill2(player,boss,player.random));
                assertEquals(8,player.skill2.useCount);
                assertEquals(90,player.mp);
            }

            @DisplayName("技能2-施放失敗(mp不足)")
            @ParameterizedTest
            @CsvSource({
                    "1, 1, True",
                    "2, 1, True",
                    "3, 1, True",
                    "4, 1, True",
                    "5, 0, False",
                    "6, 0, False"
            })
            void Skill2FailByNoMp(int mp, int expectCount, boolean expect) throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Tiger();
                Animal boss = new Tiger();
                player.mp = mp;
                player.skill2.useCount = 1;
                player.useSkill2(player,boss,player.random);
                assertEquals(expectCount,player.skill2.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertEquals(expect,output.contains("技能施放失敗"));
            }

            @Test
            @DisplayName("技能2-施放失敗(次數不足)")
            void Skill2FailByNoCount() throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Tiger();
                Animal boss = new Tiger();
                out.reset();
                player.skill2.useCount = 0;
                assertEquals(0,player.useSkill2(player,boss,player.random));
                assertEquals(0,player.skill2.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertTrue(output.contains("技能施放失敗"));
            }

            @Test
            @DisplayName("技能3")//該技能會降低敵人攻擊力
            void Skill3() {
                Animal player = new Tiger();
                Animal boss = new Tiger();
                player.hp = 200;
                boss.debuff.state = 0;
                assertEquals(0, player.useSkill3(player, boss,player.random));
                assertEquals(95, player.mp);
                assertEquals(4, player.skill3.useCount);
                assertEquals(14, boss.ATK);//檢查敵人是否有被降低攻擊力
                assertEquals(1, boss.debuff.duration);
                assertEquals(2, boss.debuff.state);
                boss.debuff.state =2;
                //敵人身上已存在debuff的情況下，不會附加新的debuff
                assertEquals(0,player.useSkill3(player,boss,player.random));
                assertEquals(3,player.skill3.useCount);
                assertEquals(90,player.mp);
                assertEquals(14, boss.ATK);
            }

            @Test
            @DisplayName("技能3-施放失敗(次數不足)")
            void Skill3FailByNoCount() throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Tiger();
                Animal boss = new Tiger();
                out.reset();
                player.skill3.useCount = 0;
                assertEquals(0,player.useSkill3(player,boss,player.random));
                assertEquals(0,player.skill3.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertTrue(output.contains("技能施放失敗"));
            }

            @DisplayName("技能3-施放失敗(mp不足)")
            @ParameterizedTest
            @CsvSource({
                    "1, 1, True",
                    "2, 1, True",
                    "3, 1, True",
                    "4, 1, True",
                    "5, 0, False",
                    "6, 0, False"
            })
            void Skill3FailByNoMp(int mp, int expectCount, boolean expect) throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Tiger();
                Animal boss = new Tiger();
                player.mp = mp;
                player.skill3.useCount = 1;
                player.useSkill3(player,boss,player.random);
                assertEquals(expectCount,player.skill3.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertEquals(expect,output.contains("技能施放失敗"));
            }

            @ParameterizedTest
            @CsvSource({
                    "1, 80",
                    "0.8, 80",
                    "0.11, 80",
                    "0.09, 160",
                    "0.03, 160",
                    "0, 160"
            })
            @DisplayName("技能4")
            void Skill4(double testRate,int expectDamage) {
                Animal player = new Tiger();
                Animal boss = new Tiger();
                //固定隨機值，低於 rate = 0.1，會暴擊
                Random fakeRandom = new Random() {
                    @Override
                    public double nextDouble() {
                        return testRate;
                    }
                };

                assertEquals(expectDamage, player.useSkill4(player,boss,fakeRandom));
            }

            @Test
            @DisplayName("技能4-施放失敗(次數不足)")
            void Skill4FailByNoCount() throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Tiger();
                Animal boss = new Tiger();
                out.reset();
                player.skill4.useCount = 0;
                assertEquals(0,player.useSkill4(player,boss,player.random));
                assertEquals(0,player.skill4.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertTrue(output.contains("技能施放失敗"));
            }

            @DisplayName("技能4-施放失敗(mp不足)")
            @ParameterizedTest
            @CsvSource({
                    "1, 1, True",
                    "2, 1, True",
                    "3, 1, True",
                    "4, 1, True",
                    "10, 0, False",
                    "11, 0, False"
            })
            void Skill4FailByNoMp(int mp, int expectCount, boolean expect) throws Exception{
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(out));
                Animal player = new Tiger();
                Animal boss = new Tiger();
                player.mp = mp;
                player.skill4.useCount = 1;
                player.useSkill4(player,boss,player.random);
                assertEquals(expectCount,player.skill4.useCount);
                System.setOut(originalOut);
                String output = out.toString("UTF-8");
                assertEquals(expect,output.contains("技能施放失敗"));
            }

        }

    }

}