package abstraction.assigment_problems;

public abstract class GameCharacter {

    private static int counter = 0;

    private final String characterId;

    protected GameCharacter() {
        counter++;
        characterId = "CHR-" + counter;
    }

    public abstract String getSpecialMove();

    public String getCharacterId() {
        return characterId;
    }
}
