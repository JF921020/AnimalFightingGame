# 動物對戰遊戲 (Java Console Game)

## 📖 專案介紹
這是一個以 **Java** 撰寫的文字介面 RPG 遊戲。玩家可以選擇不同的動物角色（Dog、Cat、Bear、Tiger）進行對戰，每個角色擁有獨特的技能與能力，並可進行回合制戰鬥。

本專案的主要目標是：
- 展示 **物件導向程式設計 (OOP)** 概念（繼承、多型、封裝）。
- 練習 **控制流程設計** 與 **例外處理**。
- 體驗 **回合制遊戲流程** 與 **技能系統設計**。

---

## 📂 專案架構
```
src/main/java/
├── animal/
│   ├── Animal.java       # 動物基底類別 (抽象類別)
│   ├── Bear.java         # 熊角色
│   ├── Cat.java          # 貓角色
│   ├── Dog.java          # 狗角色
│   ├── Tiger.java        # 老虎角色
│   └── TestAnimal.java   # 測試用類別
│
├── function/
│   ├── Buff.java         # Buff 系統（狀態加成 / 減益）
│   ├── ConsoleColor.java # 控制台文字顏色 (美化輸出)
│   ├── Props.java        # 道具系統
│   ├── Skill.java        # 技能系統
│
├── Game.java             # 遊戲主流程 (模式選擇、對戰邏輯)
└── Launcher.java         # 程式進入點 (main 方法)
```

---

## ⚔️ 功能特色
- **角色系統**：Dog、Cat、Bear、Tiger，每個角色都有專屬技能。
- **技能系統**：透過 `Skill` 類別，提供角色多樣化技能。
- **Buff 系統**：利用 `Buff` 影響角色屬性（攻擊力、防禦力、狀態效果）。
- **道具系統**：`Props` 可提供恢復、強化等效果。
- **遊戲模式**：
  - Random：隨機角色與敵人。
  - Free：自由選角。
  - Full Random：完全隨機。
- **回合制戰鬥**：玩家與敵人依序行動，直到一方血量歸零。
- **再次遊玩機制**：遊戲結束後可選擇是否重玩。

---

## ▶️ 執行方式
### 1.透過IntelliJ IDEA 開啟專案

### 2.執行Launcher.java
---

## 🧪 測試建議
- **角色技能測試**：Dog、Cat、Bear、Tiger 的技能是否正確。
- **Buff/Props 測試**：狀態加成、道具效果是否正確。
- **Game 模式測試**：Random / Free / Full Random 是否運作正常。
- **例外處理測試**：非法輸入（非數字、超範圍數字）是否能正確處理。
- **再次遊玩測試**：輸入 y/n 是否能正確控制流程。

### ✅ JUnit 測試範例
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BuffTest {
    @Test
    void testAttackBuffIncreasesDamage() {
        Dog dog = new Dog("TestDog", 100, 20);
        Buff buff = new Buff("AttackUp", 5, "attack");
        dog.applyBuff(buff);
        assertEquals(25, dog.getAttackPower());
    }
}
```

---

## 📌 未來可擴充功能
- 加入更多角色與技能。
- 擴展道具系統（如稀有裝備）。
- 提供 GUI 版本（Swing/JavaFX）。
- 增加 **存檔/讀檔** 功能。
- 增加網路連線對戰模式。
- 增加遊戲劇情以及地圖探索機制(GUI完成後)。

---

## 👨‍💻 作者
此專案為學習與練習 **Java 物件導向設計**、**遊戲開發** 與 **軟體測試** 所製作。
