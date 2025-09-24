# 動物對戰遊戲 (Java Console Game)

## 📖 專案介紹
這是一個以 **Java** 撰寫的簡易文字介面對戰遊戲。玩家可以選擇不同的動物角色（Dog、Cat、Bear、Tiger）進行對戰，每個角色擁有獨特的技能與能力，並可進行回合制戰鬥。

本專案的主要目標是：
- 展示 **物件導向程式設計 (OOP)** 概念（繼承、多型、封裝）。
- 練習 **控制流程設計** 與 **例外處理**。
- 體驗 **回合制遊戲流程** 與 **技能系統設計**。

---

## 📂 專案架構
```
src/
├── Animal.java      # 動物基底類別 (抽象類別)
├── Dog.java         # Dog 類別，繼承 Animal
├── Cat.java         # Cat 類別，繼承 Animal
├── Bear.java        # Bear 類別，繼承 Animal
├── Tiger.java       # Tiger 類別，繼承 Animal
├── Game.java        # 遊戲主流程 (玩家與敵人戰鬥、遊戲模式)
└── Launcher.java    # 程式進入點 (main 方法)
```

---

## ⚔️ 功能特色
- **角色系統**：玩家可選擇 Dog、Cat、Bear、Tiger，每個角色都有專屬技能。
- **技能系統**：不同角色技能具有不同的攻擊/防禦/特殊效果。
- **遊戲模式**：
  - Random：隨機選擇角色與敵人。
  - Free：玩家自由選擇角色。
  - Full Random：角色與敵人均隨機選擇。
- **回合制戰鬥**：玩家與電腦輪流攻擊，直到一方血量歸零。
- **再次遊玩機制**：每場遊戲結束後可選擇是否重玩。

---

## ▶️ 執行方式
### 1. 編譯
在專案目錄下執行：
```bash
javac *.java
```

### 2. 執行
使用 `Launcher` 作為程式入口：
```bash
java Launcher
```

---

## 🧪 測試建議
專案可以搭配 **JUnit 5** 撰寫測試，建議測試範圍：
1. **Animal 子類別技能**：確認不同動物技能效果正確。
2. **Game 模式**：
   - 測試 Random / Free / Full Random 模式是否正確執行。
   - 測試回合制戰鬥是否能正確判斷勝負。
3. **例外處理**：
   - 玩家輸入非法選項（字母、超出範圍數字）。
   - 再次遊玩時的輸入控制（y/n）。

### ✅ 測試範例 (DogSkillTest.java)
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DogSkillTest {
    @Test
    void testDogSkill1Damage() {
        Dog dog = new Dog("TestDog", 100, 20);
        int beforeHp = 100;
        int damage = dog.useSkill1();
        assertTrue(damage > 0, "技能應造成傷害");
        assertEquals(beforeHp - damage, 100 - damage);
    }
}
```

---

## 📌 未來可擴充功能
- 加入 **道具系統**（例如回血藥水、防禦加成）。
- 加入 **Buff/Debuff 系統**（中毒、暈眩、攻擊力上升）。
- 製作 **簡易 GUI 版本**（例如使用 JavaFX 或 Swing）。
- 加入 **存檔與讀檔機制**。

---

## 👨‍💻 作者
此專案為學習與練習 **Java 物件導向程式設計** 與 **軟體測試** 所開發。
