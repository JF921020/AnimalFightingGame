package Animal;

public class Tiger extends Animal {
    public Tiger() {
        super("老虎");
        HP = 120;
    }
    @Override
    public int useSkill() {
        System.out.println("虎虎生風! Boss HP -8");
        return 8;
    }
}
