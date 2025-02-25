package Animal;

public class Dog extends Animal {
    public Dog() {
        super("狗");
        HP = 100;
    }
    @Override
    public int useSkill() {
        System.out.println("瘋狗亂撞! Boss HP -6");
        return 6;
    }
}
