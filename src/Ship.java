public abstract class Ship {
	
	private int bowRow;
	private int bowColumn;
	protected int length;
	private boolean horizontal;
	protected boolean[] hit = new boolean[4];
	
	/*
	 * constructor
	 */
	public Ship() {
	}
	
	/*
	 * get the row (0 to 9) which contains the bow (front) of the ship.
	 */
	int getBowRow() {
		return bowRow;
	}
	
	/*
	 * get the column (0 to 9) which contains the bow (front) of the ship
	 */
	int getBowColumn() {
		return bowColumn;
	}
	
	/* Returns the length of this particular ship. 
	 * This method exists only to be overridden, so it doesn't much matter what it returns; 
	 * an abstract "ship" doesn't have a fixed lengthxed length.xed length.xed length.
	 */
	boolean isHorizontal() {
		return horizontal;
	}
	
	/* 
	 * Sets the value of bowRow
	 */
	void setBowRow(int row) { 
		this.bowRow = row;
	}
	
	/*
	 * Sets the value of bowColumn
	 */
	void setBowColumn(int column) { 
		this.bowColumn = column;
	}
	
	/*
	 * Sets the value of the instance variable horizontal.
	 */
	void setHorizontal(boolean horizontal) { 
		this.horizontal = horizontal;
	}
	
	/*
	 * get the number of squares occupied by the ship. 
	 * An "empty sea" location has length 1
	 */
	abstract int getLength();
	
	/*
	 * get the type of the ship
	 */
	abstract String getShipType();
	
	/*
	 * Returns true if it is okay to put a ship of this length with its bow in this location,
	 * with the given orientation, and returns false otherwise.
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if (row>9 || column>9) return false;
		if (horizontal) {
			if (column+this.getLength()>9 || (column>0 && ocean.isOccupied(row,column-1) 
					|| (row+this.getLength()<9 && ocean.isOccupied(row,column+1)))) return false;
			for (int i=column; i<this.getLength();i++) {
				if(ocean.isOccupied(row,i) || (row>0 && ocean.isOccupied(row-1,i))
						||(row<9 && ocean.isOccupied(row+1,i))) return false;				
			}
			return true;
		} else {
			if (row+this.getLength()>9 || (row>0 && ocean.isOccupied(row-1,column) 
					|| (column+this.getLength()<9 && ocean.isOccupied(row+1,column)))) return false;
			for (int i=row; i<this.getLength();i++) {
				if(ocean.isOccupied(i,column) || (column>0 && ocean.isOccupied(i,column-1))
						||(column<9 && ocean.isOccupied(i,column+1))) return false;				
			}
			return true;
		}	
	}
	
	/*
	 * "Puts" the ship in the ocean.
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);
		Ship[][] ships = ocean.getShipArray();
		if (horizontal) {
		    for (int i = column; i < column + this.getLength(); i++) {
	            ships[row][i] = this;
	        }
		} else {
		    for (int i = row; i < row + this.getLength(); i++) {
		        ships[i][column] = this;
		    }
		}
	}
	
	/*
	 * if the space is occupied by the ship, mark that part of ship as hit and return true
	 */
	boolean shootAt(int row, int column) {
		// if already sunk return false
		if (this.isSunk()) return false;
		if (this.horizontal) {
			if (this.bowColumn == column) {
				for (int i = 0; i<this.getLength(); i++) {
					if (this.bowRow+i == row) {
						this.hit[i] = true;
						return true;
					}
				}
			} 
		} else {
			if (this.bowRow == row) {
				for (int i=0; i<this.getLength(); i++) {
					if (this.bowColumn+i==column) {
						this.hit[i] = true;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/*
	 * Return true if every part of the ship has been hit, false otherwise.
	 */
	boolean isSunk() {
		for (int i=0; i<this.getLength();i++) {
			if (!this.hit[i]) return false ;
		}
		return true;
	}
	
	/*
	 * Returns a single-character String to use in the Ocean's print method
	 */
	@Override
	public String toString() {
		if (this.isSunk()) return "x";
		return "S";
	}
}

