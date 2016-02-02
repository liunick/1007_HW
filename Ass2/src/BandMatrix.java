/**
 * BandMatrix is a special type of matrix that contains a band down the 
 * top-left to bottom-right diagonal. As per what is said in the instructions,
 * any matrix is a band matrix as long it has 2 equal sized triangles on both
 * sides. This includes a matrix with equal triangles of size 0.
 * 
 * I chose to hold the compressed matrix in a 2D array, with the rows equal to 
 * the number of rows in the SSM and the columns equal to the width of the band.
 * 
 * For multiplication and addition of the matricies, I converted the specials
 * back into SSMs to do the math. When the SSM was finished calculating, I converted them
 * back to specials. Since the constructor of my special already had a mandatory check to see
 * if the specials were valid, simply attempting to initialize the special with the resulting
 * product/sum was enough to check if the new result was valid.
 *  
 * For the set method, I do not allow the user to change anything that would cause the pattern
 * to break. For example, if the user replaces a 0 with a value that would break the pattern,
 * the compiler will respond with a refusal message and will not proceed with the set.
 * 
 * In there tester class, there is a prepared matrix for testing. It will test the getRows(),
 * getCols(), set(), get(), toString(), add(), multiply() methods.
 *  
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */

public class BandMatrix {
	private int width;
	private int rows, cols;
	private boolean valid = true;
	private int[][] compressedMatrix;
	
	/**
	 * Constructor that does all the checks/compressing
	 * @param inputMatrix The SSM that is imported
	 */
	public BandMatrix(SquareSparseMatrix inputMatrix) {
		findBandWidth(inputMatrix);
		checkValidBand(inputMatrix);
		
		if (valid) {
			compressMatrix(inputMatrix);	
		} else {
			System.out.println("This is not a valid band matrix");
		}
	}
	
	/**
	 * Gets the value from the array and returns
	 * @param row Inputted row from user
	 * @param col Inputted col from user
	 * @return returnValue This is the value at the row/col position
	 */
	public int get(int row, int col) {
		int returnValue = 0, spaceCounter = 0;
		
		if (row >= (width/2))
			spaceCounter = row - (width/2);
		if (col >= spaceCounter && col < width + spaceCounter) {
			returnValue = compressedMatrix[row][col - spaceCounter];
		}
		else
			returnValue = 0;
		return returnValue;
	}

	/**
	 * Returns the valid value
	 * @return valid The boolean of whether matrix is valid
	 */
	public boolean getValid() {
		return valid;
	}
	
	/**
	 * Returns the width of the band
	 * @return width The width of the band
	 */
	public int getWidth() {
		return width;
	}
	
	
	/**
	 * Sets the value at the given position. Must be inside the band.
	 * @param row Row position in the array
	 * @param col Col position in the array
	 * @param value Value that is being set
	 */
	public void set(int row, int col, int value) {
		int spaceCounter = 0;
		if (row >= (width/2))
			spaceCounter = row - (width/2);
		if (get(row, col) == 0) {
			System.out.println("Value must be inside the band. \nValue not set");
			return;
		} else {
			compressedMatrix[row][col - spaceCounter] = value;
		}
	}
	
	/**
	 * Adds one BandMatrix to another
	 * @param other The other band matrix
	 * @return sumBand a BandMatrix that contains the sum
	 */
	public BandMatrix add(BandMatrix other) {
		SquareSparseMatrix sumSSM = getSSM();
		SquareSparseMatrix resultSSM = sumSSM.plus(other.getSSM());
		BandMatrix sumBand = new BandMatrix(resultSSM);
		return sumBand;
	}
	
	/**
	 * Multiplies one BandMatrix to another
	 * @param other The other band matrix
	 * @return sumBand a BandMatrix that contains the product
	 */
	public BandMatrix multiply(BandMatrix other) {
		SquareSparseMatrix multiSSM = getSSM();
		SquareSparseMatrix resultSSM = multiSSM.times(other.getSSM());
		BandMatrix multiBand = new BandMatrix(resultSSM);
		return multiBand;
	}
	
	/**
	 * Returns the special array as a SquareSparseMatrix
	 * @return returnSSM the SquareSparseMatrix of the special
	 */
	public SquareSparseMatrix getSSM() {
		SquareSparseMatrix returnSSM = new SquareSparseMatrix(getRows());
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				returnSSM.set(r, c, get(r, c));
			}
		}
		return returnSSM;
	}
	
	/**
	 * Returns a string that displays the matrix nicely
	 * @return returnString Returns the matrix printed out
	 */
	public String toString() {
		String returnString = "";
		for (int r = 0; r < compressedMatrix.length; r++) {
			for (int c = 0; c < compressedMatrix[0].length; c++) {
				//Beginning
				if (r < width/2) {
					if (c < getCols()) {
						returnString += (compressedMatrix[r][c] + " ");
					}
					if (c == compressedMatrix[r].length - 1) {
						int addedZeros = getCols() - c;
						for (int x = 0; x < addedZeros -1; x++) {
							if (x + c >= getCols()) {
								break;
							}
							returnString += "0 ";
						}
					}	
				}
				//After beginning but before end
				else if (r >= width/2 && r < getRows() - width/2) {
					/*
					 * Calculate how many zeros go before and after
					 * the body of the compressedMatrix
					 */
					int addedZeroes = getCols() - compressedMatrix[r].length;
					int endZeroes = addedZeroes - (r - width/2);
					int beginZeroes = addedZeroes - endZeroes;
					//Add the zeros in the beginning
					if (c == 0) {
						for (int begin = 0; begin < beginZeroes; begin++) {
							returnString += "0 ";
						}
					}
					//Add the content of compressedMatrix and the ending zeros
					if (c < compressedMatrix[r].length) {
						if (c < getCols()) {
							returnString += (compressedMatrix[r][c] + " ");
						}
						//If the last one of the compressed Matrix, 
						if (c == compressedMatrix[r].length -1) {

							for (int x = 0; x < endZeroes; x++) {
								if (x + c >= getCols()) {
									break;
								}
								returnString += "0 ";
							}	
						}
					} 					
				}
				//End
				else {
					
					/*
					 * Calculate how many zeros go before and after
					 * the body of the compressedMatrix
					 */
					int addedZeroes = getCols() - compressedMatrix[r].length;
					int endZeroes = addedZeroes - (r - width/2);
					int beginZeroes = addedZeroes - endZeroes;
					
					//First loop through the last line and count how many zeros
					//Are in the compressedMatrix
					int zeroCounter = 0;
					if (c == 0) {
					
						for (int col = 0; col < compressedMatrix[r].length; col++) {
							if (compressedMatrix[r][col] == 0)
								zeroCounter++;
						}
						//Sets the proper number of zeros in the beginning
						for (int begin = 0; begin < beginZeroes; begin++) {
							returnString += "0 ";
						}	
						
					}
					if (compressedMatrix[r][c] != 0) {
						returnString += (compressedMatrix[r][c] + " ");	
					}
				}
			}
			returnString += "\n";
		}
		return returnString;
	}

	/**
	 * Returns the number of rows
	 * @return rows The number of rows
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * Returns the number of cols 
	 * @return cols The number of cols
	 */
	public int getCols() {
		return cols;
	}
	
	/**
	 * Compresses the SSM to a 2D array
	 * @param inputMatrix The SSM that is imported
	 */
	public void compressMatrix(SquareSparseMatrix inputMatrix) {
		compressedMatrix = new int[(int) inputMatrix.getRows()][width];
		for(int r = 0; r < inputMatrix.getRows(); r++) {
			int colCounter = 0;
			for (int c = 0; c < inputMatrix.getCols(); c++) {
				if (inputMatrix.get(r, c) != 0) {
					while (compressedMatrix[r][colCounter] != 0)
						colCounter++;
					compressedMatrix[r][colCounter] = (int) inputMatrix.get(r, c);
				}
			}			
		}
		rows = (int) inputMatrix.getRows(); //compressedMatrix.length
		cols = (int) inputMatrix.getCols();
	}
	
	/**
	 * Checks whether the band in the SSM is valid
	 * @param inputMatrix The SSM that is imported
	 */
	public void checkValidBand(SquareSparseMatrix inputMatrix) {
		//For every 0, make sure there is a corresponding 0 across the matrix
		for (int r = 0; r < inputMatrix.getRows(); r++) {
			for (int c = 0; c < inputMatrix.getCols(); c++) {
				if (inputMatrix.get(r, c) == 0) {
					int opposRow, opposCol;
					opposRow = (int) (inputMatrix.getRows() - r - 1);
					opposCol = (int) (inputMatrix.getCols() - c - 1);
					if (inputMatrix.get(opposRow, opposCol) != 0)
						valid = false;
				}
			}
		}
	}
	
	/**
	 * Sets width to the width of the band
	 * @param inputMatrix The SSM that is imported
	 */
	public void findBandWidth(SquareSparseMatrix inputMatrix) {
		
		int counter = 0;
		//Loop through all the elements of the array
		for (int r = 0; r < inputMatrix.getRows(); r++) {
			for (int c = 0; c < inputMatrix.getCols(); c++) {
				//If the first element
				if (c == 0 && r == 0) {
					//If the first element is 0, then illegal band, no width
					if (inputMatrix.get(r, c) == 0)
						width = 0;
					//Else, keep looping, incrementing cols until hit zero
					else
						while (inputMatrix.get(r, c + counter) != 0)
							counter++;
				}
			}
		}
		//Set the width field to the counter which is the width of the band
		width = counter * 2 -1;
	}
}
