package Animal;

public interface AnimalCreate {
    public String name = "";
    public int hp = 20;
    public int combo = 0;
    public int choice = 0;
    public boolean win = false;

    int attack(int combo);

}
