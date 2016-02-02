/**
 * The TriangleMatrix is a special kind of matrix where there are numbers in a triangular format
 * on the bottom left or top right of the grid. However, the diagonal in the middle can either be filled
 * with numbers or zeros, which my class takes into account. 
 * 
 * I stored the SSM in a 1D array and stored the numbers counting horizontally.
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
public class TriangleMatrix {
	
	private boolean valid = true;
	private boolean isRight = false, isDiagonal = false;
	private int rows, cols;
	private int[] compressedMatrix;
	
	/**
	 * The constructor of the TriangleMatrix
	 * @param inputMatrix The SSM that is being inputted
	 */
	public TriangleMatrix(SquareSparseMatrix inputMatrix) {
		checkValidTriangle(inputMatrix);
		if (valid) {
			compressMatrix(inputMatrix);
		} else {
			System.out.println("This is not a valid triangle matrix");
		}
	}
	
	/**
	 * Gets the value at the given coordinates
	 * @param row The row that the value is at
	 * @param col The col that the value is at
	 * @return returnValue the value that is returned
	 */
	public int get(int row, int col) {
		int returnValue = 0;
		int arrayIndex = 0;
		
		if (row > col || (row >= col && isRight)) {
			if (isRight) {
				arrayIndex = ((row + 1) * row / 2) + col;
				returnValue = compressedMatrix[arrayIndex];
			} else {
				returnValue = 0;
			}
		} else if (col > row || (col >= row && isRight)) {
			if (!isRight && isDiagonal) {
				arrayIndex = row * getRows() - (row - 1) * ((row - 1) + 1)/2 + col - row;
				//Formula for this was derived from: http://www.codeguru.com/cpp/cpp/algorithms/general/article.php/c11211/TIP-Half-Size-Triangular-Matrix.htm
				returnValue = compressedMatrix[arrayIndex];
			} else {
				returnValue = 0;
			}
		} else if (row == col) {
			if (isDiagonal) {
				arrayIndex = row * (getRows()-1) - (row-1)*(row)/2 + col - row - 1;
				returnValue = compressedMatrix[arrayIndex];
			}
		}
		return returnValue;
	}
	
	/**
	 * Sets the value at the row/col position to the inputted value
	 * @param row The row that the value is at
	 * @param col The col that the value is at
	 * @param value The value that is going to be replacing the set value
	 */
	public void set (int row, int col, int value) {
		int returnValue = 0;
		int arrayIndex = 0;
		
		if (row > col || (row >= col && isRight)) {
			if (isRight) {
				arrayIndex = ((row + 1) * row / 2) + col;
				compressedMatrix[arrayIndex] = value;
			} else {
				System.out.println("Value not added as it would break triangle");
			}
		} else if (col > row || (col >= row && isRight)) {
			if (!isRight && isDiagonal) {
				arrayIndex = row * getRows() - (row - 1) * ((row - 1) + 1)/2 + col - row;
				//Formula for this was derived from: http://www.codeguru.com/cpp/cpp/algorithms/general/article.php/c11211/TIP-Half-Size-Triangular-Matrix.htm
				compressedMatrix[arrayIndex] = value;
			} else {
				System.out.println("Value not added as it would break triangle");
			}
		} else if (row == col) {
			if (isDiagonal) {
				arrayIndex = row * (getRows()-1) - (row-1)*(row)/2 + col - row - 1;
				compressedMatrix[arrayIndex] = value;
			}
		}
	}
	
	/**
	 * Returns a string of a nicely formatted triangle matrix
	 * @return returnString the string of nicely formatted matrix
	 */
	public String toString() {
		String returnString = "";
		for (int r = 0; r < getRows(); r++) {
			for (int c = 0; c < getCols(); c++) {
				returnString += get(r, c) + " ";
			}
			returnString += "\n";
		}
		return returnString;
	}
	
	/**
	 * Multiplies 2 Triangle Matricies together
	 * @param other The other Triangle Matrix
	 * @return productTri The triangle matrix that is returned
	 */
	public TriangleMatrix multiply(TriangleMatrix other) {
		SquareSparseMatrix productSSM = getSSM();
		SquareSparseMatrix resultSSM = productSSM.times(other.getSSM());
		TriangleMatrix productTri = new TriangleMatrix(resultSSM);
		return productTri;
	}
	
	/**
	 * Adds 2 Triangle Matricies together
	 * @param other The other Triangle Matrix
	 * @return sumTri the Triangle matrix that is returned
	 */
	public TriangleMatrix add(TriangleMatrix other) {
		SquareSparseMatrix sumSSM = getSSM();
		SquareSparseMatrix resultSSM = sumSSM.plus(other.getSSM());
		TriangleMatrix sumTri = new TriangleMatrix(resultSSM);
		return sumTri;
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
	 * Gets the number of rows
	 * @return rows The number of rows
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * Gets the number of columns
	 * @return cols The number of columns
	 */
	public int getCols() {
		return cols;
	}
	
	/**
	 * Compresses the matrix into a 1D array
	 * @param inputMatrix
	 */
	public void compressMatrix(SquareSparseMatrix inputMatrix) {
		int initializer = 0;
		for (int r = 0; r < inputMatrix.getRows(); r++) {
			for (int c = 0; c < inputMatrix.getCols(); c++) {
				if (inputMatrix.get(r, c) != 0) {
					initializer++;
				}
			}
		}
		compressedMatrix = new int[initializer + 1];
		int counter = 0;
		for (int r = 0; r < inputMatrix.getRows(); r++) {
			
			if (isRight && isDiagonal) {
				for (int c = 0; c < r; c++) {
					compressedMatrix[counter] = (int) inputMatrix.get(r, c);
					counter++;
				}
			} else if (isRight && !isDiagonal) {
				for (int c = 0; c < r + 1; c++) {
					compressedMatrix[counter] = (int) inputMatrix.get(r, c);
					counter++;
				}
			} else if (!isRight && isDiagonal) {
				for (int c = r + 1; c < inputMatrix.getCols(); c++) {
					compressedMatrix[counter] = (int) inputMatrix.get(r, c);
					counter++;
				}
			} else {
				for (int c = r; c < inputMatrix.getCols(); c++) {
					compressedMatrix[counter] = (int) inputMatrix.get(r, c);
					counter++;
				}
			}
		}
		rows = (int) inputMatrix.getRows();
		cols = (int) inputMatrix.getCols();
	}

	/**
	 * Gets the validity of the TriangleMatrix
	 * @return valid Boolean of validity
	 */
	public boolean getValid() {
		return valid;
	}
	
	/**
	 * This checks the validity of the SSM inputted
	 * @param inputMatrix This is the inputted SSM that tests if it is a valid TriangleMatrix
	 */
	public void checkValidTriangle(SquareSparseMatrix inputMatrix) {

		//Checks where the triangle of zeros is pointing
		int nextZeroRight = (int) inputMatrix.get(0, 1);
		int nextZeroBot = (int) inputMatrix.get(1, 0);
		if (nextZeroRight == 0 && nextZeroBot != 0)
			isRight = true;
		else if (nextZeroBot == 0 && nextZeroRight != 0)
			isRight = false;
		else
			valid = false;
		
		if (isRight) {
			if (inputMatrix.get(0, 0) == 0) {
				isDiagonal = true;
				for (int r = 0; r < inputMatrix.getRows(); r++) {
					for (int c = r; c < inputMatrix.getCols(); c++) {
						if (inputMatrix.get(r, c) != 0) {
							valid = false;
						}
					}
				}
			} else {
				isDiagonal = false;
				for (int r = 0; r < inputMatrix.getRows(); r++) {
					for (int c = r + 1; c < inputMatrix.getCols(); c++) {
						if (inputMatrix.get(r, c) != 0) {
							valid = false;
						}
					}
				}
			}
		} else {
			if (inputMatrix.get(0, 0) == 0) {
				isDiagonal = true;
				for (int r = 0; r < inputMatrix.getRows(); r++) {
					for (int c = 0; c < r + 1; c++) {
						if (inputMatrix.get(r, c) != 0) {
							valid = false;
						}
					}
				}
			} else {
				isDiagonal = false;
				for (int r = 0; r < inputMatrix.getRows(); r++) {
					for (int c = 0; c < r; c++) {
						if (inputMatrix.get(r, c) != 0) {
							valid = false;
						}
					}
				}
			}
		}
	}
}
