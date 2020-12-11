
public class EmptySea extends Ship{
	
	public EmptySea() {
		this.length = 1;
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
		return "empty";
	}
	
	/*
	 * always returns false to indicate that nothing was hit
	 */
	@Override
	boolean shootAt(int row, int column) {
		return false;
	}
	
	/*
	 * always returns false to indicate that nothing was sunk
	 */
	@Override
	boolean isSunk() {
		return false;
	}
	
	/*
	 * return "empty"
	 */
	@Override
	public String toString() {
		return this.getShipType();
	}
	
}




