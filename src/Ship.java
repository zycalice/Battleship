/**
* This is a class that manages the ships (all types)
*
* @author  Xinyi Li & Yuchen Zhang
* @version 1.0
* @since   2020-12-15
*/
public abstract class Ship {
	
	private int bowRow;
	private int bowColumn;
	protected int length;
	private boolean horizontal;
	protected boolean[] hit = new boolean[4];

	/**
	 * get the row (0 to 9) which contains the bow (front) of the ship.
	 * @return the row position of bow
	 */
	int getBowRow() {
		return bowRow;
	}

	/**
	 * get the column position of bow
	 * @return the column position of bow
	 */
	int getBowColumn() {
		return bowColumn;
	}
	

	/**
	 * check if the ship is horizontal
	 * @return true if the ship is horizontal, otherwise false
	 */
	boolean isHorizontal() {
		return horizontal;
	}


	/**
	 * set the row position of bow
	 * @param row row position of bow
	 */
	void setBowRow(int row) { 
		this.bowRow = row;
	}
	

	/**
	 * set the column position of bow
	 * @param column column position of bow
	 */
	void setBowColumn(int column) { 
		this.bowColumn = column;
	}


	/**
	 * set the orientation of this ship
	 * @param horizontal set the ship to be horizontal or not
	 */
	void setHorizontal(boolean horizontal) { 
		this.horizontal = horizontal;
	}


	/**
	 * abstract method, will be overridden
	 */
	abstract int getLength();
	

	/**
	 * abstract method, will be overridden
	 */
	abstract String getShipType();

	/**
	 * check if it is okay to put ship at a location
	 * @param row row position of the bow of the ship
	 * @param column column position of the bow of the ship
	 * @param horizontal a boolean variable determine if the ship is horizontal or not
	 * @param ocean the ocean for this game
	 * @return true is the ship bow can be placed at this position; false otherwise
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean){
		int shipLen = this.getLength();
		int rowMin, rowMax, colMin, colMax;

		//check for illegal arguments
		if (row<0) throw new IllegalArgumentException("Row position of bow cannot be negative");
		if (column<0) throw new IllegalArgumentException("Column position of bow cannot be negative");

		//get a range of squares to check for emptiness
		if (horizontal){//horizontal version
			//check if the space can even fit the ship length
			if (column + shipLen-1>9) return false;
			else{
				rowMin = Math.max(0,row-1);
				rowMax = Math.min(9,row+1);
				colMin = Math.max(0,column-1);
				colMax = Math.min(9,column+shipLen);
			}
		}else{//Vertical version
			if (row + shipLen-1>9)return false;
			else{
				rowMin = Math.max(0,row-1);
				rowMax = Math.min(9,row+shipLen);
				colMin = Math.max(0,column-1);
				colMax = Math.min(9,column+1);
			}
		}

		//for loop to check all relevant spaces
		//rowMin and rowMax inclusive
		for (int i=rowMin; i<=rowMax; i++){
			for (int j=colMin; j<=colMax; j++){
				if (ocean.isOccupied(i,j)) return false;
			}
		}
		return true;

	}

	/**
	 * puts the sea in the ocean
	 * @param row row position of the ship bow
	 * @param column column position of the ship bow
	 * @param horizontal horizontal or not
	 * @param ocean ocean of the game
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		//update ship
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);

		//update ocean
		if (horizontal) {
		    for (int i = column; i < column + this.getLength(); i++) {
	            ocean.ships[row][i] = this;
	        }
		} else {
		    for (int i = row; i < row + this.getLength(); i++) {
		        ocean.ships[i][column] = this;
		    }
		}
	}

	/**
	 * if the space is occupied by the ship, mark that part of ship as hit and return true
	 * @param row row position of the hit
	 * @param column column position of the hit
	 * @return true or false if the ship is being hit
	 */
	boolean shootAt(int row, int column) {
		// if already sunk return false
		if (this.isSunk()) return false;

		if (this.horizontal) { //horizontal version
			int diffCol = column - this.bowColumn;
			if (this.bowRow == row && diffCol >= 0 && diffCol<this.getLength()) {
				this.hit[diffCol] = true;
				return true;
			} 
		} else { //vertical version
			int diffRow = row - this.bowRow;
			if (this.bowColumn == column && diffRow >= 0 && diffRow<this.getLength()) {
				this.hit[diffRow] = true;
				return true;
			}
		}
		return false;
	}

	/**
	 * if all parts of the ship has been hit
	 * @return true if all parts of the ship has been hit; false otherwise;
	 */
	boolean isSunk() {
		for (int i=0; i<this.getLength();i++) {
			if (!this.hit[i]) return false ;
		}
		return true;
	}

	/**
	 * string value to indicate if this ship has been sunk or not
	 * can be only used to print location that the user has shot at
	 * @return "x" if the ship has been sunk,"S" if it has not been sunk.
	 */
	@Override
	public String toString() {
		if (this.isSunk()) return "x";
		return "S";
	}
}

