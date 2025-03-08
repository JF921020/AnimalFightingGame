package GUI;

import Animal.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BattleController {
    @FXML
    private Label playerLabel;
    @FXML
    private Label hpLabel;
    @FXML
    private Label mpLabel;
    @FXML
    private Label bossHpLabel; // 顯示敵人 HP

    private Animal player;
    private Animal boss;

    public void setPlayer(Animal player, String gameMode) {
        this.player = player;

        // 根據模式選擇敵人
        if (gameMode.equals("Random")) {
            boss = new Bear();
        } else {
            boss = new Dog();
        }

        playerLabel.setText("角色：" + player.name);
        hpLabel.setText("HP: " + player.hp);
        mpLabel.setText("MP: " + player.mp);
        bossHpLabel.setText("敵人 HP: " + boss.hp);
    }

    @FXML
    private void attack() {
        boss.hp -= 10;
        bossHpLabel.setText("敵人 HP: " + boss.hp); // 更新 UI
        System.out.println(player.name + " 攻擊了敵人，敵人剩餘 HP：" + boss.hp);
    }
}
