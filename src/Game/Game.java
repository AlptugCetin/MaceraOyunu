package Game;

import characters.Savaşçı;
import characters.Hero;
import characters.Okçu;
import characters.Şövalye;
import inventory.Armor;
import inventory.Inventory;
import inventory.Weapon;
import location.*;

import java.util.Scanner;

public class Game {

    Scanner scanner=new Scanner(System.in);

    private Player player;
    private Location location;
    private Weapon weapon=new Weapon(0,"Yok",0,0) ;
    private Armor armor=new Armor(0,"Yok",0,0);
    private Inventory inventory=new Inventory(false,false,false,weapon,armor);

    public void start(){
        System.out.println("OYUNA HOŞGELDİNİZ.");
        System.out.println("---------------------------");
        System.out.println("Sen, cesur bir maceracısın. Ünlü bir krallık, esrarengiz bir\r\n"
        		+ "şekilde ortadan kayboldu. Halk, korku içinde, kaybolan krallıklarını geri\r\n"
        		+ "alacak bir kahramanın ortaya çıkmasını bekliyor. Sen de bu göreve talip olan\r\n"
        		+ "cesur bir macerasın. Yola çıkmadan önce birçok tehlike, canavar ve sürprizle\r\n"
        		+ "karşılaşacaksın.\n");
        System.out.print("İsim Giriniz : ");
        String nick=scanner.next();
        int heroNumber;
        Hero[] charList={new Savaşçı(),new Okçu(),new Şövalye()};
        while (true) {
            System.out.println("Karakterler ");
            for (Hero chr : charList){
                System.out.print(chr.getId()+"-"+ chr.getName() + " : Hasar="+chr.getDamage() +" Sağlık="+chr.getHealth()+" Para="+chr.getMoney()+" Zırh="+chr.getArmor()+"\n");
            }
            System.out.print("\nSeçmek istediğiniz karakter numarasını giriniz : ");

            heroNumber = scanner.nextInt();
            if(heroNumber>3 || heroNumber<1){
                System.out.println("Yanlış numara girdiniz lütfen tekrar deneyin.");
            }else{
                break;
            }
        }
        player = new Player(heroNumber,nick,inventory);
        player.selectChar();
        while (true){
            System.out.println(player.toString());
            System.out.println("Lokasyon Listesi :");
            System.out.println("-------------------------------------------------------");
            Location[] locations={new GüvenliEv(player),new Mağaza(player),new Orman(player),new Nehir(player),new Mağara(player)};
            for(Location loc: locations){
                System.out.println(loc.getId()+"-"+loc.getName());
            }
            System.out.println("6-Envanter");
            System.out.println("7-Oyunu Sonlandır.");
            System.out.print("Gitmek istediğiniz lokasyonun numarasını yazınız : ");
            int locationId=scanner.nextInt();

            if(locationId==7){
                System.out.println("Oyundan çıkış yapıldı.");
                System.exit(0);
            }
            if(!player.selectLocation(locationId)){
                System.out.println("Oyun bitti! Kaybettiniz!");
                break;
            }
        }
    }

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}