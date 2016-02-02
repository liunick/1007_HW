/**
 * The block matrix is a special kind of matrix where there are square blocks aligned on the
 * diagonal from the top left to the bottom right. The blocks can be of any size which my class
 * takes into account.
 * 
 * I stored the block matrix in a 3D array. 
 * 1st Parameter: The number of block it is
 * 2+3rd Parameter: The rows/cols coordinates for each block
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
 * 
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class BlockMatrix {
	
	
	private int rows, cols;
	private boolean valid = true;
	private int numOfBlocks = 0;
	private int[][][] compressedMatrix;
	
	/**
	 * Constructor of the block matrix
	 * @param inputMatrix This is the inputted SSM to be converted
	 */
	public BlockMatrix(SquareSparseMatrix inputMatrix) {
		checkValidBlock(inputMatrix);
		if (valid) {
			compressMatrix(inputMatrix);
		} else {
			System.out.println("This is not a valid block matrix");
		}
	}

	/**
	 * This formats the matrix into a fancy array
	 * @return returnString Returns the string of the array
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
	 * Multiplies 2 Block Matricies together
	 * @param other The other Block Matrix
	 * @return productBlock The block matrix that is returned
	 */
	public BlockMatrix multiply(BlockMatrix other) {
		SquareSparseMatrix productSSM = getSSM();
		SquareSparseMatrix resultSSM = productSSM.times(other.getSSM());
		BlockMatrix productBlock = new BlockMatrix(resultSSM);
		return productBlock;
	}
	
	/**
	 * Adds 2 Block Matricies together
	 * @param other The other Block Matrix
	 * @return sumBlock the Block matrix that is returned
	 */
	public BlockMatrix add(BlockMatrix other) {
		SquareSparseMatrix sumSSM = getSSM();
		SquareSparseMatrix resultSSM = sumSSM.plus(other.getSSM());
		BlockMatrix sumBlock = new BlockMatrix(resultSSM);
		return sumBlock;
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
	 * Returns whether the block matrix is valid or not
	 * @return valid A boolean that determines whether the matrix is valid or not
	 */
	public boolean getValid() {
		return valid;
	}
	
	/**
	 * Gets a value at a certain row/col
	 * @param r This is the row at which the value is being gotten
	 * @param c This is the col at which the value is being gotten
	 * @return returnValue This is the value at the given r/c
	 */
	public int get(int r, int c) {
		int returnValue = 0;
		int foundBlockRow = -1, foundBlockCol = -1;
		int sumOfLastRow = 0, sumOfLastCol = 0;
		
		//Find which block the row corresponds to
		for (int x = 1; x <= compressedMatrix.length; x++) {
			if (r >= sumOfLastRow && r < sumOfLastRow + compressedMatrix[x-1].length) {
				foundBlockRow = x-1;
				break;
			} else {
				sumOfLastRow += compressedMatrix[x-1].length;	
			}
		}
		//Find which block the col corresponds to
		for (int y = 1; y <= compressedMatrix.length; y++) {
			if (c >= sumOfLastCol && c < sumOfLastCol + compressedMatrix[y-1].length) {
				foundBlockCol = y - 1;
				break;
			} else {
				sumOfLastCol += compressedMatrix[y-1].length;	
			}
		}
		
		
		//If the row and col correspond then go further. Else, return 0
		if (foundBlockRow == -1 || foundBlockCol == -1) {
			returnValue = 0;
		}
		else if (foundBlockRow == foundBlockCol) {
			int relativeCoordRow = r - sumOfLastRow;
			int relativeCoordCol = c - sumOfLastCol;
			//System.out.println("r: " + relativeCoordRow + " c: " + relativeCoordCol);
			returnValue = compressedMatrix[foundBlockRow][relativeCoordRow][relativeCoordCol];
		} else {
			returnValue = 0;
		}
		return returnValue;
	}

	/**
	 * Sets the value at the given row/col position
	 * @param r The row at which the value is being set
	 * @param c The col at which the value is being set
	 * @param value The value that is being set
	 */
	public void set(int r, int c, int value) {
		int foundBlockRow = 0, foundBlockCol = 0;
		int sumOfLastRow = 0, sumOfLastCol = 0;
		
		//Find which block the row corresponds to
		for (int x = 1; x <= compressedMatrix.length; x++) {
			if (r >= sumOfLastRow && r < sumOfLastRow + compressedMatrix[x-1].length) {
				foundBlockRow = x-1;
				break;
			} else {
				sumOfLastRow += compressedMatrix[x-1].length;	
			}
		}
		//Find which block the col corresponds to
		for (int y = 1; y <= compressedMatrix.length; y++) {
			if (c >= sumOfLastCol && c < sumOfLastCol + compressedMatrix[y-1].length) {
				foundBlockCol = y - 1;
				break;
			} else {
				sumOfLastCol += compressedMatrix[y-1].length;	
			}
		}
		
		//If the row and col correspond then go further. Else, return 0
		if (foundBlockRow == foundBlockCol) {
			int relativeCoordRow = r - sumOfLastRow;
			int relativeCoordCol = c - sumOfLastCol;
			compressedMatrix[foundBlockRow][relativeCoordRow][relativeCoordCol] = value;
		} else {
			System.out.println("Your value would have broken the band.\nValue not added");
		}
	}
	
	/**
	 * Checks whether this is a valid block
	 * @param inputMatrix This is the inputted matrix
	 */
	public void checkValidBlock(SquareSparseMatrix inputMatrix) {
		int firstValue = (int)inputMatrix.get(0, 0);
		if (firstValue == 0) {
			valid = false;
		}
		
		//Checks the first block on the diagonal
		int sizeCounter = 0;
		while ((int)inputMatrix.get(0, sizeCounter) != 0) {
			sizeCounter++;
		}
		for (int r = 0; r < sizeCounter; r++) {
			for (int c = 0; c < sizeCounter; c++) {
				if (inputMatrix.get(r, c) == 0) {
					valid = false;
				}	
			}
		}
		numOfBlocks++;
		/*
		 * Following 2 for loops makes sure none directly horizontal and vertical of the block
		 * contains any number other than zero
		 */
		for (int r = sizeCounter; r < inputMatrix.getRows(); r++) {
			for (int c = 0; c < sizeCounter; c++) {
				if (inputMatrix.get(r, c) != 0) 
					valid = false;
			}
		}
		for (int r = 0; r < sizeCounter; r++) {
			for (int c = sizeCounter; c < inputMatrix.getCols(); c++) {
				if (inputMatrix.get(r, c) != 0) {
					valid = false;
				}
			}
		}
		
		int holdCounter = sizeCounter;
		//Checks for any more blocks along the diagonal based on the top left number
		//and whether it has a zero on the top and left
		for (int r = holdCounter - 1; r < inputMatrix.getRows(); r++) {
			for (int c = holdCounter - 1; c < inputMatrix.getCols(); c++) {
				
				//If the number above and to the left is a 0, check the block size
				if (inputMatrix.get(r, c) != 0 && inputMatrix.get(r-1, c) == 0 && inputMatrix.get(r, c-1) == 0) {
					numOfBlocks++;
					//This checks the block size
					sizeCounter = 0;
					while ((int)inputMatrix.get(r, c + sizeCounter) != 0) {
						sizeCounter++;
					}
					
					//Makes sure that none of the elements inside the block is a 0
					for (int row = r; row < r + sizeCounter; row++) {
						for (int col = c; col < c + sizeCounter; col++) {
							if (inputMatrix.get(row, col) == 0) {
								valid = false;
							}
						}
					}
					
					/*
					 * The following 2 for functions makes sure that nothing directly vertical and 
					 * horizontal contain any numbers other than zero
					 */
					for (int startR = r + sizeCounter; startR < inputMatrix.getRows(); startR++) {
						for (int startC = c; startC < c + sizeCounter; startC++) {
							if (inputMatrix.get(startR, startC) != 0) 
								valid = false;
						}
					}
					for (int startR = r; startR < r + sizeCounter; startR++) {
						for (int startC = c + sizeCounter; startC < inputMatrix.getCols(); startC++) {
							if (inputMatrix.get(startR, startC) != 0) {
								valid = false;
							}
						}
					}
				}
			}
		}	
	}
	
	/**
	 * Compresses the given matrix into a special 3D array
	 * @param inputMatrix This is the inputted matrix
	 */
	public void compressMatrix(SquareSparseMatrix inputMatrix) {
		rows = (int) inputMatrix.getRows();
		cols = (int) inputMatrix.getCols();
		int sizeCounter = 0, blockCounter = 0;
		compressedMatrix = new int[numOfBlocks][][];
		
		while ((int)inputMatrix.get(0, sizeCounter) != 0) {
			sizeCounter++;
		}
		compressedMatrix[blockCounter] = new int[sizeCounter][sizeCounter];
		for (int r = 0; r < sizeCounter; r++) {
			for (int c = 0; c < sizeCounter; c++) {
				compressedMatrix[blockCounter][r][c] = (int) inputMatrix.get(r, c);
			}
		}
		blockCounter++;
		int holdCounter = sizeCounter;
		for (int r = holdCounter - 1; r < inputMatrix.getRows(); r++) {
			for (int c = holdCounter - 1; c < inputMatrix.getCols(); c++) {
				
				//If the number above and to the left is a 0, check the block size
				if (inputMatrix.get(r, c) != 0 && inputMatrix.get(r-1, c) == 0 && inputMatrix.get(r, c-1) == 0) {
					
					//This checks the block size
					sizeCounter = 0;
					while ((int)inputMatrix.get(r, c + sizeCounter) != 0) {
						sizeCounter++;
					}
					
					compressedMatrix[blockCounter] = new int[sizeCounter ][sizeCounter];
					int innerRow = 0, innerCol = 0;
					for (int row = r; row < r + sizeCounter; row++) {
						innerCol = 0;
						for (int col = c; col < c + sizeCounter; col++) {
							compressedMatrix[blockCounter][innerRow][innerCol] = (int)inputMatrix.get(row, col);
							innerCol++;
						}
						innerRow++;
					}
					blockCounter++;
				}
			}
		}
	}
}
