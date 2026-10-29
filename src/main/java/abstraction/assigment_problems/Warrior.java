package abstraction.assigment_problems;

public class Warrior extends GameCharacter implements Attackable, Defendable {

    private final String name;

    public Warrior(String name) {
        super();
        this.name = name;
    }

    @Override
    public String attack() {
        return name + " strikes with a blade";
    }

    @Override
    public String attack(String weaponName) {
        String article = "AEIOUaeiou".indexOf(weaponName.charAt(0)) >= 0 ? "an " : "a ";
        return name + " strikes with " + article + weaponName;
    }

    @Override
    public String defend() {
        return name + " raises a shield";
    }

    @Override
    public String getSpecialMove() {
        return name + " unleashes Whirlwind Slash";
    }
}
