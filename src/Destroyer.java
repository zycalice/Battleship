
public class Destroyer extends Ship{
	
	public Destroyer() {
		this.length = 2;
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
		return "destroyer";
	}

}
