/**
* This is a class for a cruiser (ship of length 3) in the game Battleship
*
* @author  Xinyi Li & Yuchen Zhang
* @version 1.0
* @since   2020-12-15
*/
public class Cruiser extends Ship{

	/**
	 * constructor
	 */
	public Cruiser() {
		this.length = 3;
		for (int i = 0; i<this.length;i++) {
			this.hit[i]=false;
		}
	}

	/**
	 * get the length of the ship
	 * @return length of the ship
	 */
	@Override
	public int getLength() {
		return this.length;
	}
	
	/**
	 * get the type of the ship
	 * @return ship type
	 */
	@Override
	public String getShipType() {
		return "cruiser";
	}

}