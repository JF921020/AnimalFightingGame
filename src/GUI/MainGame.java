package GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class MainGame extends Application {

    private static Stage primaryStage; // 讓 primaryStage 可在全域使用

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage; // 設定全域變數
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("動物大亂鬥");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private ToggleGroup modeChoose; // 確保有 @FXML

    private String selectedMode = "";

    @FXML
    private void handleModeSelection() {
        if (modeChoose == null) {
            System.out.println("modeGroup 為 null，FXML 可能未正確加載！");
            return;
        }

        RadioButton selectedRadio = (RadioButton) modeChoose.getSelectedToggle();
        if (selectedRadio != null) {
            selectedMode = selectedRadio.getText();
            System.out.println("選擇的模式：" + selectedMode);
        } else {
            System.out.println("沒有選擇模式！");
        }
    }

    @FXML
    private void startGame() {
        if (!selectedMode.isEmpty()) {
            System.out.println("進入角色選擇畫面，模式：" + selectedMode);
            openCharacterSelection();
        } else {
            System.out.println("請先選擇一個遊戲模式！");
        }
    }

    private void openCharacterSelection() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CharacterSelection.fxml"));
            Parent characterRoot = loader.load();

            CharacterController characterController = loader.getController();
            characterController.setGameMode(selectedMode);

            primaryStage.setScene(new Scene(characterRoot)); // 使用已初始化的 primaryStage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
