package GUI;

import Animal.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.util.ResourceBundle;

public class BattleController implements Initializable {

    // FXML 注入，請確認 BattleScene.fxml 中有對應 fx:id 設定
    @FXML
    private Label playerNameLabel;
    @FXML
    private Label enemyNameLabel;
    @FXML
    private ProgressBar playerHP;
    @FXML
    private ProgressBar playerMP;
    @FXML
    private ProgressBar enemyHP;
    @FXML
    private ProgressBar enemyMP;
    @FXML
    private Button skill1,skill2,skill3,skill4;

    // 玩家與敵人角色
    private Animal player;
    private Animal enemy;

    /**
     * 透過此方法由上個場景傳入角色物件，
     * 並更新 UI 顯示
     */
    public void setPlayer(Animal player, Animal enemy) {
        this.player = player;
        this.enemy = enemy;
        updateUI();

    }

    /**
     * 在注入完成後初始化（FXML Loader 呼叫）
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * 更新玩家與敵人的 UI，例如名稱與進度條
     */
    private void updateUI() {
        if (player != null) {
            playerNameLabel.setText("玩家: "+player.name);
            double playerHpRatio = (double) player.hp / player.maxHp;
            double playerMpRatio = (double) player.mp / player.maxMP;
            playerHP.setProgress(playerHpRatio);
            playerMP.setProgress(playerMpRatio);
        }
        if (enemy != null) {
            enemyNameLabel.setText("敵方: "+enemy.name);
            double enemyHpRatio = (double) enemy.hp / enemy.maxHp;
            double enemyMpRatio = (double) enemy.mp / enemy.maxMP;
            enemyHP.setProgress(enemyHpRatio);
            enemyMP.setProgress(enemyMpRatio);
        }
        skill1.setText(player.skill1.name+"\n"+"MP: "+player.skill1.mpConsume+"\n"+"剩餘施放次數: "+player.skill1.useCount);
        skill2.setText(player.skill2.name+"\n"+"MP: "+player.skill2.mpConsume+"\n"+"剩餘施放次數: "+player.skill2.useCount);
        skill3.setText(player.skill3.name+"\n"+"MP: "+player.skill3.mpConsume+"\n"+"剩餘施放次數: "+player.skill3.useCount);
        skill4.setText(player.skill4.name+"\n"+"MP: "+player.skill4.mpConsume+"\n"+"剩餘施放次數: "+player.skill4.useCount);
        Tooltip tooltip1 = new Tooltip(player.skill1.description);
        skill1.setTooltip(tooltip1);
        Tooltip tooltip2 = new Tooltip(player.skill2.description);
        skill2.setTooltip(tooltip2);
        Tooltip tooltip3 = new Tooltip(player.skill3.description);
        skill3.setTooltip(tooltip3);
        Tooltip tooltip4 = new Tooltip(player.skill4.description);
        skill4.setTooltip(tooltip4);

    }

    // 以下示範一個技能按鈕事件 (其他技能可依此模式撰寫)
    @FXML
    private void useSkill1() {
        // 玩家先攻擊
        int damage = player.useSkill1(player, enemy);
        enemy.hp -= damage;
        // 若敵人仍存活，進行反擊
        if (enemy.hp > 0) {
            int enemyDamage = enemy.enemyAttack(enemy, player);
            player.hp -= enemyDamage;
        }
        updateUI();
        checkBattleEnd();
    }

    /**
     * 檢查戰鬥是否結束，依據雙方血量決定勝負
     */
    private void checkBattleEnd() {
        if (player.hp <= 0) {
            System.out.println("玩家死亡，遊戲結束！");
        } else if (enemy.hp <= 0) {
            System.out.println("敵人死亡，玩家勝利！");
        }
        // 根據需要可進一步切換場景或顯示對話框
    }
}
