package bbangkeMonster.entity;

import java.util.List;

public class Superior {
    PokemonType type;
    private List<PokemonType> strong;
    private List<PokemonType> weak;

    public Superior(PokemonType type, List strong, List weak) {
        this.type = type;
        this.strong = strong;
        this.weak = weak;
    }

    public PokemonType getType() {
        return type;
    }

    public List<PokemonType> getStrong() {
        return strong;
    }

    public List<PokemonType> getWeak() {
        return weak;
    }
}
