package inventory;

	public class Inventory {
	    private boolean wood = false;
	    private boolean water = false;
	    private boolean food = false;
	    private Weapon weapon;
	    private Armor armor;


	    public Inventory(boolean wood, boolean water, boolean food,Weapon weapon, Armor armor) {
	        this.wood = wood;
	        this.water = water;
	        this.food = food;
	        this.weapon = weapon;
	        this.armor=armor;
	    }

	    public boolean isWood() {
	        return wood;
	    }

	    public void setWood(boolean wood) {
	        this.wood = wood;
	    }
	    public boolean isWater() {
	        return water;
	    }

	    public void setWater(boolean water) {
	        this.water = water;
	    }

	    public boolean isFood() {
	        return food;
	    }

	    public void setFood(boolean food) {
	        this.food = food;
	    }

	    public Weapon getWeapon() {
	        return weapon;
	    }

	    public void setWeapon(Weapon weapon) {
	        this.weapon = weapon;
	    }

	    public Armor getArmor() {
	        return armor;
	    }

		public void setArmor(Armor armor) {
			this.armor=armor;
			
		}
	}


