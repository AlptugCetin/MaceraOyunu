package location;

import Game.Player;

public abstract class Normal extends Location{

    public Normal(int id,String name, Player player) {
        super(id,name, player);
    }

    @Override
    public boolean onLocation() {
        return true;
    }
}