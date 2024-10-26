package location;

import Game.Player;
import inventory.*;
import Monster.Monster;
import java.util.Locale;
import java.util.Random;

public abstract class Arena extends Location{

    Random random=new Random();
    private Monster monster;
    private String award;
    private int numObs;
    public Arena(int id, String name, Player player, Monster monster, String award) {
        super(id, name, player);
        this.monster=monster;
        this.award=award;
    }

    @Override
    public boolean onLocation() {
        System.out.println("Şuan bulunduğunuz yer : "+this.getName());
        setNumObs(this.getId());
        System.out.println("Dikkatli olun burada "+this.getNumObs()+" adet " +this.getMonster().getName().toUpperCase()+ " yaşıyor!");
        System.out.print("Bu canavarla savaşmak için 'S' kaçmak için 'K' basınız : ");
        String sel=scanner.nextLine().toUpperCase();
        if(sel.equals("S")){
            if(!combat(getNumObs()) && this.getPlayer().getPlayerHealth()==0){
                return false;
            }
        }
        else if(sel.equals("K")){
            System.out.println("\u001B[31mCanavardan kaçtınız.\u001B[0m");

        }
        else {
            System.out.println("\u001B[33mYanlış girdiniz! Ana Ekrana dönülüyor\u001B[0m");
        }
        return true;
    }

    public boolean combat(int value){
        boolean isFirst=true;
        for (int i = 1; i <= value; i++) {

            System.out.println(this.getPlayer().toString());
            System.out.println(this.monster.toString());
            boolean finish=true;
            while (this.monster.getHealth()>0 && this.getPlayer().getPlayerHealth()>0){
                if(firstShot()==2 && isFirst) { 
                    System.out.println("Vuruş Hakkı Sizle Başlıyor.");
                    System.out.println("Vurmak için 'V' , kaçmak için 'K' basınız .");
                    String match = scanner.nextLine().toUpperCase(Locale.ROOT);
                    if (match.equals("V")) {
                        System.out.println(this.getPlayer().getİsim() + " " + this.monster.getName() + " canavarına "
                                + this.getPlayer().getPlayerDamage() + " puan hasar vurdunuz!");
                        int obsHealth = this.getMonster().getHealth() - this.getPlayer().getPlayerDamage();
                        this.getMonster().setHealth(obsHealth);
                        System.out.println(this.monster.getName() + " canavarının canı \u001B[31m" + this.getMonster().getHealth() + " kaldı.\u001B[0m");
                        if (this.getMonster().getHealth() > 0) { 
                            System.out.println("\u001B[32m"+i + ".  Canavar Size Vurdu !\u001B[0m");
                            int damage = this.getMonster().getDamage() - getPlayer().getPlayerDefence();
                            if (damage < 0) damage = 0;
                            this.getPlayer().getHero().setHealth(this.getPlayer().getPlayerHealth() - damage);
                            System.out.println("Canınız : \u001B[31m" + this.getPlayer().getPlayerHealth()+"\u001B[0m");
                            if (this.getPlayer().getPlayerHealth() == 0) {
                                return false;
                            }
                        }
                    }
                    else {
                        System.out.println("\u001B[31mSavaş alanından kaçtınız.\u001B[0m");
                        return true;
                    }
                }
                else {
                    finish = true;
                    isFirst = false;
                    System.out.println("\u001B[34mVuruş Hakkı Canavarla Başlıyor.\u001B[0m");
                    System.out.println("\u001B[32m"+i + ". Canavar Size Vurdu !\u001B[0m");
                    int damage = this.getMonster().getDamage() - getPlayer().getPlayerDefence();
                    if (damage < 0) damage = 0;
                    this.getPlayer().getHero().setHealth(this.getPlayer().getPlayerHealth() - damage);
                    System.out.println("Canınız : \u001B[31m" + this.getPlayer().getPlayerHealth()+"\u001B[0m");
                    if (this.getPlayer().getPlayerHealth() < 1) {
                        return false;
                    }
                    System.out.println("Vurmak için 'V' , kaçmak için 'K' basınız .");
                    String match = scanner.nextLine().toUpperCase(Locale.ROOT);
                    if (match.equals("V")) {
                        System.out.println(this.getPlayer().getİsim() + " " + this.monster.getName() + " canavarına  "+ this.getPlayer().getPlayerDamage() + " puan hasar vurdunuz!");
                        int obsHealth = this.getMonster().getHealth() - this.getPlayer().getPlayerDamage();
                        this.getMonster().setHealth(obsHealth);
                        System.out.println(this.monster.getName() + " canavarının canı \u001B[31m" + this.getMonster().getHealth() + " kaldı.\u001B[0m");
                    } else {
                        System.out.println("\u001B[30mSavaş alanından kaçtınız.\u001B[0m");

                        return true;
                    }
                }
            }
            if(this.getMonster().getHealth()<=0){
                isFirst=true;
                int x=value-i;
                System.out.println("\u001B[32mKazandınız.Kalan Canavar Sayısı :"+x+"\u001B[0m");

                    giveMoney(getMonster().getAwardMoney());
                    System.out.println("\u001B[33mÖdülünüz verildi . Bakiyeniz : " + this.getPlayer().getPlayerMoney()+"\u001B[0m");
                    if(x==0) {
                        giveItem(this.getAward());
                        System.out.println("\u001B[34m"+this.getAward() + " ödülünü kazandınız!"+"\u001B[0m");
                    }

                if(x!=0){
                    this.getMonster().setHealth(this.getMonster().getDefaultHealth());
                }
            }
        }
        return true;
    }
    
	private int firstShot(){
        Random r=new Random();
        int x= r.nextInt(1,3);
        return x;
    }

    private void giveMoney(int awardMoney) {
        this.getPlayer().getHero().setMoney(this.getPlayer().getPlayerMoney()+awardMoney);
    }
    private void giveItem(String award){
        if(award.equals("odun")){
            getPlayer().getInventory().setWood(true);
        }
        if(award.equals("su")){
            getPlayer().getInventory().setWater(true);
        }
        if(award.equals("yemek")){
            getPlayer().getInventory().setFood(true);
        }
    }

    public int getNumObs() {
        return numObs;
    }

    public void setNumObs(int id) {

            this.numObs = random.nextInt(1,4);
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }
}