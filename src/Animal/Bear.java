package Animal;

public class Bear extends Animal {
    public Bear() {
        super("熊");
    }
    @Override
    public int attack(int combo) {
        return combo > 2 ? 4 : 2;
    }
}
