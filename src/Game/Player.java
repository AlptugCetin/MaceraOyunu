package Game;


import characters.*;
import inventory.*;
import location.*;

public class Player {
    private int heroNumber;
    private Hero hero;
    private Inventory inventory;
    private Armor armor;
    private Location location;
    private String isim;
    
	

    public Player(int heroNumber,String isim,Inventory inventory){
        this.heroNumber=heroNumber;
        if(isim.equals(""))
            isim="Player1";
        this.isim=isim;
        this.inventory=inventory;
    }

    public void selectChar(){
        switch (this.heroNumber){
            case 1:
            default:
                this.hero=new Savaşçı();
                break;
            case 2:
                this.hero=new Okçu();
                break;
            case 3:
                this.hero=new Şövalye();
                break;
        }
    }

    public boolean selectLocation(int locId){ 
        switch (locId){
            case 1:
                this.location=new GüvenliEv(this);
                break;
            case 2:
                this.location=new Mağaza(this);
                break;
            case 3:
                if(isWinLoc(new Orman(this))){
                    this.location=new GüvenliEv(this);
                    break;
                }else {
                    this.location = new Orman(this);
                    break;
                }
            case 4:
                if(isWinLoc(new Nehir(this))){
                    this.location=new GüvenliEv(this);
                    break;
                }else {
                    this.location = new Nehir(this);
                    break;
                }
            case 5:
                if(isWinLoc(new Mağara(this))){
                    this.location=new GüvenliEv(this);
                    break;
                }else {
                    this.location=new Mağara(this);
                    break;
                }
            case 6:
                getItemList();
                return true;
        }
        return location.onLocation();
    }


    private boolean isWinLoc(Arena arena){
        if(arena.getId()==3 && this.getInventory().isWood()){
            System.out.println("Bu alanda savası kazandınız. Tekrar Giremezsiniz.");
            return true;
        }
        if(arena.getId()==4 && this.getInventory().isWater()){
            System.out.println("Bu alanda savası kazandınız. Tekrar Giremezsiniz.");
            return true;
        }
        if(arena.getId()==5 && this.getInventory().isFood()){
            System.out.println("Bu alanda savası kazandınız. Tekrar Giremezsiniz.");
            return true;
        }
        return false;

    }

    private void getItemList() {
        System.out.println("Silah : "+this.getInventory().getWeapon().getName());
        System.out.println("Zırh : "+this.getInventory().getArmor().getName());
        System.out.println("Odun : "+this.getInventory().isWood());
        System.out.println("Su : "+this.getInventory().isWater());
        System.out.println("Yemek : "+this.getInventory().isFood());
    }

    public boolean win(){
        return this.getInventory().isWood() && this.getInventory().isWater() && this.getInventory().isFood();
    }

    public String getİsim() {
        return isim;
    }

    public void setİsim(String isim) {
        this.isim = isim;
    }

    public int getHeroNumber() {
        return heroNumber;
    }

    public void setHeroNumber(int heroNumber) {
        this.heroNumber = heroNumber;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    public int getPlayerHealth(){
        return this.getHero().getHealth();
    }
    public int getPlayerDamage(){
        return this.getHero().getDamage();
    }
    public int getPlayerMoney(){
        return this.getHero().getMoney();
    }
    public int getPlayerDefence(){
        return this.getInventory().getArmor().getDefence();
        		
    }

    public Inventory getInventory() {
        return inventory;
    }
   

    @Override
    public String toString() {
        return  "\u001B[31mOyuncunun Özellikleri :\n" +
                "İsim = " + this.isim +
                ", Seçilen karakter= " + this.hero.getName() +
                ", Karakter canı = " + this.hero.getHealth() +
                ", Karakterin başlangıçtaki hasarı = " + this.hero.getDefaultDamage() +
                ", Karakterin toplam hasarı = "+this.getHero().getDamage()+
                ", Paranız = " + this.getHero().getMoney() +
                ", Karakterin Başlangıçtaki Zırh savunması ="+ this.hero.getDefaultArmor()+
                ", Extra Zırh Savunması ="+this.getInventory().getArmor().getDefence()+
                ", Silah hasarı ="+this.getInventory().getWeapon().getDamage()+"\u001B[0m";
    }

}