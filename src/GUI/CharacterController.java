package GUI;

import Animal.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CharacterController {
    @FXML
    private Label chosenLabel,user;
    @FXML
    private TextArea detail;

    @FXML
    private Button skillButton1, skillButton2, skillButton3, skillButton4;

    private Animal player;
    private String gameMode;

    public void setGameMode(String mode) {
        this.gameMode = mode;
        System.out.println("角色選擇畫面，當前模式：" + gameMode);
    }

    @FXML
    private void selectDog() {
        player = new Dog();
        chosenLabel.setText("目前選擇：狗");
        updateCharacterInfo();
    }

    @FXML
    private void selectCat() {
        player = new Cat();
        chosenLabel.setText("目前選擇：貓");
        updateCharacterInfo();
    }

    @FXML
    private void selectBear() {
        player = new Bear();
        chosenLabel.setText("目前選擇：熊");
        updateCharacterInfo();
    }

    @FXML
    private void selectTiger() {
        player = new Tiger();
        chosenLabel.setText("目前選擇：老虎");
        updateCharacterInfo();
    }


    @FXML
    private void startBattle() {
        if (player != null) {
            System.out.println("選擇的角色：" + player.name);
            openBattleScene();
        } else {
            chosenLabel.setText("請選擇角色！");
        }
    }

    private void updateCharacterInfo() {
        if (player != null) {
            user.setText("HP: " + player.hp + " MP: " + player.mp + " 爆擊機率: " + player.rate);
            chosenLabel.setText("目前選擇：" + player.name);
            skillButton1.setText(player.skill1.name);
            skillButton2.setText(player.skill2.name);
            skillButton3.setText(player.skill3.name);
            skillButton4.setText(player.skill4.name);
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
            battleController.setPlayer(player, gameMode); // 確保角色資訊正確傳遞

            Stage stage = (Stage) chosenLabel.getScene().getWindow();
            stage.setScene(new Scene(battleRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
