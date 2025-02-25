package Animal;

public class Cat extends Animal {
    public Cat() {
        super("貓");
        HP = 80;
    }
    @Override
    public int useSkill() {
        System.out.println("貓爪連擊! Boss HP -5");
        return 5;
    }
}

