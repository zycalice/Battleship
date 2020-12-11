
public class Crusier extends Ship{
	
	public Crusier() {
		this.length = 3;
		for (int i = 0; i<this.length;i++) {
			this.hit[i]=false;
		}
	}

	/*
	 * return the length of the ship
	 */
	@Override
	int getLength() {
		return this.length;
	}
	
	/*
	 * return the ship type
	 */
	@Override
	String getShipType() {
		return "crusier";
	}

}