package location;

import Game.Player;
import Monster.Zombi;

public class Mağara extends Arena {
    
    public Mağara( Player player) {
        super(5, "Mağara", player, new Zombi(),"yemek");
    }
}