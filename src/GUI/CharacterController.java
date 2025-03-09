package GUI;

import Animal.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Random;

public class CharacterController {
    @FXML
    private Label chosenLabel,user,enemyLabel,enemyl;
    @FXML
    private TextArea detail;
    @FXML
    private Label countdownLabel;
    @FXML
    private ToggleGroup selectionGroup;
    @FXML
    private RadioButton playerRadio, enemyRadio;


    @FXML
    private Button skillButton1, skillButton2, skillButton3, skillButton4;

    private Animal player;
    private Animal enemy;
    private String gameMode;
    protected Random random = new Random();
    private int countdownTime = 5;

    public void setGameMode(String mode) {
        this.gameMode = mode;
        System.out.println("角色選擇畫面，當前模式：" + gameMode);
        user.setText("HP: "+"\n"+ "MP: "+"\n" + "爆擊機率: " + "\n" + "攻擊力: \n");
        enemyl.setText("HP: "+"\n"+ "MP: "+"\n" + "爆擊機率: " + "\n" + "攻擊力: \n");
        if ("Random".equals(gameMode))
            enemyRadio.setDisable(true);

    }

    public void setEnemy() {
        if ((gameMode.equals("Random") || gameMode.equals("Full Random"))) {
            int choice = random.nextInt(4) + 1;
            if(choice == 1) {
                enemy = new Dog();
            }else if(choice == 2) {
                enemy = new Cat();
            }else if(choice == 3) {
                enemy = new Bear();
            }else if(choice == 4) {
                enemy = new Tiger();
            }
        }
        if (enemy != null) {
            enemyLabel.setText("敵人選擇：" + enemy.name);
            enemyl.setText("HP: " + enemy.hp +"\n"+ "MP: " + enemy.mp+"\n" + "爆擊機率: " + enemy.rate+"\n" + "攻擊力: " + enemy.maxATK);
        }
        // 更新「敵人選擇」的標籤

    }

    @FXML
    private void selectDog() {
        if (playerRadio.isSelected()) {
            // 選擇玩家
            player = new Dog();
            chosenLabel.setText("目前選擇（玩家）：" + player.name);
        } else if (enemyRadio.isSelected()) {
            // 選擇敵人
            enemy = new Dog();
            enemyLabel.setText("敵人選擇：" + enemy.name);
        }
        // 如果還有要顯示技能細節，可以在這裡處理
        updateCharacterInfo();
    }

    @FXML
    private void selectCat() {
        if (playerRadio.isSelected()) {
            // 選擇玩家
            player = new Cat();
            chosenLabel.setText("目前選擇（玩家）：" + player.name);
        } else if (enemyRadio.isSelected()) {
            // 選擇敵人
            enemy = new Cat();
            enemyLabel.setText("敵人選擇：" + enemy.name);
        }
        // 如果還有要顯示技能細節，可以在這裡處理
        updateCharacterInfo();
    }

    @FXML
    private void selectBear() {
        if (playerRadio.isSelected()) {
            // 選擇玩家
            player = new Bear();
            chosenLabel.setText("目前選擇（玩家）：" + player.name);
        } else if (enemyRadio.isSelected()) {
            // 選擇敵人
            enemy = new Bear();
            enemyLabel.setText("敵人選擇：" + enemy.name);
        }
        // 如果還有要顯示技能細節，可以在這裡處理
        updateCharacterInfo();
    }

    @FXML
    private void selectTiger() {
        if (playerRadio.isSelected()) {
            // 選擇玩家
            player = new Tiger();
            chosenLabel.setText("目前選擇（玩家）：" + player.name);
        } else if (enemyRadio.isSelected()) {
            // 選擇敵人
            enemy = new Tiger();
            enemyLabel.setText("敵人選擇：" + enemy.name);
        }
        // 如果還有要顯示技能細節，可以在這裡處理
        updateCharacterInfo();
    }


    @FXML
    private void startBattle() {
        if (player != null) {
            setEnemy();
            System.out.println("選擇的角色：" + player.name);
            openBattleScene();
        } else {
            chosenLabel.setText("請選擇角色！");
        }
        // 顯示倒數 Label，並初始化倒數時間
        countdownTime = 5;
        countdownLabel.setVisible(true);
        countdownLabel.setText(countdownTime + "秒後進入戰鬥");

        // 建立 Timeline 每秒更新一次倒數
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            countdownTime--;
            if (countdownTime > 0) {
                countdownLabel.setText(countdownTime + "秒後進入戰鬥");
            } else {
                timeline.stop();
                // 隱藏倒數 Label
                countdownLabel.setVisible(false);
                // 切換到 BattlePanel
                openBattleScene();
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }


    private void updateCharacterInfo() {
        if (player != null) {
            user.setText("HP: " + player.hp +"\n"+ "MP: " + player.mp+"\n" + "爆擊機率: " + player.rate+"\n" + "攻擊力: " + player.maxATK);
            chosenLabel.setText("目前選擇：" + player.name);
            skillButton1.setText(player.skill1.name);
            skillButton2.setText(player.skill2.name);
            skillButton3.setText(player.skill3.name);
            skillButton4.setText(player.skill4.name);
        }
        if (enemy != null) {
            enemyLabel.setText("敵人選擇：" + enemy.name);
            enemyl.setText("HP: " + enemy.hp +"\n"+ "MP: " + enemy.mp+"\n" + "爆擊機率: " + enemy.rate+"\n" + "攻擊力: " + enemy.maxATK);
        }
    }

    @FXML
    private void showSkill1() { showSkill(player.skill1); }
    @FXML
    private void showSkill2() { showSkill(player.skill2); }
    @FXML
    private void showSkill3() { showSkill(player.skill3); }
    @FXML
    private void showSkill4() { showSkill(player.skill4); }

    private void showSkill(Skill skill) {
        detail.setText(
                "技能名稱: " + skill.name + "\n" +
                        "傷害: " + player.ATK + "\n" +
                        "MP 消耗: " + skill.mpConsume + "\n" +
                        "施放次數: " + skill.useCount + "\n" +
                        skill.description
        );
    }

    private void openBattleScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BattleScene.fxml"));
            Parent battleRoot = loader.load();

            BattleController battleController = loader.getController();
            battleController.setPlayer(player, enemy); // 確保角色資訊正確傳遞

            Stage stage = (Stage) chosenLabel.getScene().getWindow();
            stage.setScene(new Scene(battleRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
