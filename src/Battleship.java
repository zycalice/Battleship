/**
* This is a class for a battleship (ship of length 4) in the game Battleship
*
* @author  Xinyi Li & Yuchen Zhang
* @version 1.0
* @since   2020-12-15
*/

public class Battleship extends Ship{
	
	public Battleship() {
		this.length = 4;
		for (int i = 0; i<this.length;i++) {
			this.hit[i]=false;
		}
	}

	/**
	 * get the length of the ship
	 * @return length of the ship
	 */
	@Override
	int getLength() {
		return this.length;
	}
	
	/**
	 * get the type of the ship
	 * @return ship type
	 */
	@Override
	String getShipType() {
		return "battleship";
	}

}
