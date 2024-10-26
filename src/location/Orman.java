package location;

import Game.Player;
import Monster.Vampir;

public class Orman extends Arena{
    public Orman(Player player) {
        super(3, "Orman", player, new Vampir(),"odun");
    }
}