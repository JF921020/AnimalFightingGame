package Animal;

public class Tiger extends Animal {
    public Tiger() {
        super("老虎");
    }
    @Override
    public int attack(int combo) {
        return combo > 2 ? 4 : 2;
    }
}
