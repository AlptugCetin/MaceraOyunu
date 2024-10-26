package location;

import Game.Player;

public class GüvenliEv extends Normal{
    public GüvenliEv(Player player) {
        super(1,"Güvenli ev" ,player);
    }

    @Override
    public boolean onLocation() {
        System.out.println("Güvenli Evdesiniz.");
        if(getPlayer().win()){
            System.out.println("Tebrikler, tüm itemleri topladınız ve krallığınızı kurtardınız.");
            System.out.println("Krallığa geri dönüyorsunuz...");
            System.exit(0);
        }
        System.out.println("Mevcut Sağlığınız :"+getPlayer().getHero().getHealth());
        System.out.println("Canınız yenilendi.");
        getPlayer().getHero().setHealth(getPlayer().getHero().getDefaultHealth());
        System.out.println("Sağlığınız :"+getPlayer().getHero().getHealth());
        return true;
    }
}