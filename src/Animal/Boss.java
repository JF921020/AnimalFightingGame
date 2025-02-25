package Animal;

public class Boss {
    public double HP = 200;
    public int combo = 0;
    public int choice = 0;

    public int attack(int combo) {
        return combo > 2 ? 4 : 2;
    }
}




