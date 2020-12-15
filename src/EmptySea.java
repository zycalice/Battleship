/**
* This is a class for a empty sea (empty area with length 1) in the game Battleship
*
* @author  Xinyi Li & Yuchen Zhang
* @version 1.0
* @since   2020-12-15
*/
public class EmptySea extends Ship{
	
	public EmptySea() {
		this.length = 1;
		for (int i = 0; i<this.length;i++) {
			this.hit[i]=false;
		}
	}

	/**
	 * get the length of the empty sea
	 * @return length of the empty sea
	 */
	@Override
	int getLength() {
		return this.length;
	}
	
	 /**
	 * get the type of empty sea
	 * @return empty
	 */
	@Override
	String getShipType() {
		return "empty";
	}
	
	/**
	 * always returns false to indicate that nothing was hit
	 * @return false
	 */
	@Override
	boolean shootAt(int row, int column) {
		return false;
	}
	
	/**
	 * always returns false to indicate that nothing was sunk
	 * @return false
	 */
	@Override
	boolean isSunk() {
		return false;
	}
	
	/**
	 * @return empty type
	 */
	@Override
	public String toString() {
		return this.getShipType();
	}
	
}




