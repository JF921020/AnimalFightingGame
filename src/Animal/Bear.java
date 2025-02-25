package Animal;

public class Bear extends Animal {
    public Bear() {
        super("熊");
        HP = 150;
    }
    @Override
    public int useSkill() {
        System.out.println("懶熊壓制! Boss HP -7");
        return 7;
    }
}
