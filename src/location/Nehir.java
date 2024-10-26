package location;

import Game.Player;
import Monster.Ayı;

public class Nehir extends Arena {
    
    public Nehir(Player player) {
        super(4, "Nehir", player, new Ayı(),"su");
    }

}