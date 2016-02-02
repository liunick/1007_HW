/**
 * A class to test SSM and the 3 special matrix classes.
 * 
 * BlockMatrix Testing Strategy Defense:
 * For the BlockMatrix I used a 6 by 6 matrix with 2 blocks. The first block has
 * a size of 3x3 and the second block has a size of 2x2. The blocks have different
 * sizes to show that this variability has been accounted for. Secondly, instead of
 * making the matrix simply 5x5 I added an extra exterior layer to show that it still
 * will work if second block is encased by zeros. To show that exceptions are handled, 
 * I tried setting an illegal value and the compiler refused to do so and did not set
 * the value.
 * 
 * Triangle Testing Strategy Defense:
 * For the TriangleMatrix I used a 5 by 5 matrix with the diagonal being part of the 
 * triangle. My code is set up so that the diagonal can either be zeros or actually
 * part of the triangle. In my code, you can see that I always have four cases set up
 * for the 4 different ways of having a triangle on top or bottom and whether it 
 * includes the diagonal or not. To show that exceptions are handled, I tried setting an
 * illegal value and the compiler refused to do so and did not set the value.
 * 
 * BandMatrix Testing Strategy Defense:
 * For the Band matrix, I preset two different matricies because I felt that the most 
 * could go wrong with this type of matrix. They are both 5 x 5 matricies but one of them
 * has a band of 3 and the other has a band of 5. However, Both work perfectly fine. Like 
 * the other patterns, I tried setting an illegal value and it would not accept it or 
 * set it. I also think that the multiplication is a good example of the compatibility
 * of my BandMatrix as when multiplied, an error is not thrown even when the zeroes are
 * replaced because the product is a bandMatrix itself, simply with a wider band.
 * 
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class SquareSparseMatrixTester
{
   /**
      Creates and uses a SquareSparseMatrix object 
      @arg args unused
   */
   public static void main(String[] args)
   {
	   
	   /* BLOCK MATRIX TEST */

	   SquareSparseMatrix block1 = new SquareSparseMatrix(6);
	   block1.set(0, 0, 1);
	   block1.set(0, 1, 2);
	   block1.set(0, 2, 3);
	   block1.set(0, 3, 0);
	   block1.set(0, 4, 0);
	   block1.set(0, 5, 0);
	   block1.set(1, 0, 4);
	   block1.set(1, 1, 5);
	   block1.set(1, 2, 6);
	   block1.set(1, 3, 0);
	   block1.set(1, 4, 0);
	   block1.set(1, 5, 0);
	   block1.set(2, 0, 7);
	   block1.set(2, 1, 8);
	   block1.set(2, 2, 9);
	   block1.set(2, 3, 0);
	   block1.set(2, 4, 0);
	   block1.set(2, 5, 0);
	   block1.set(3, 0, 0);
	   block1.set(3, 1, 0);
	   block1.set(3, 2, 0);
	   block1.set(3, 3, 1);
	   block1.set(3, 4, 2);
	   block1.set(3, 5, 0);
	   block1.set(4, 0, 0);
	   block1.set(4, 1, 0);
	   block1.set(4, 2, 0);
	   block1.set(4, 3, 3);
	   block1.set(4, 4, 4);
	   block1.set(4, 5, 0);
	   block1.set(5, 0, 0);
	   block1.set(5, 1, 0);
	   block1.set(5, 2, 0);
	   block1.set(5, 3, 0);
	   block1.set(5, 4, 0);
	   block1.set(5, 5, 0);
	   
	   BlockMatrix blockMatrix1 = new BlockMatrix(block1);
	   System.out.println("There are " + blockMatrix1.getRows() + " rows and " + blockMatrix1.getCols() + " cols in this matrix.");
	   System.out.println("Using toString to print out the matrix: ");
	   System.out.println(blockMatrix1.toString());
	   System.out.println("Using the get method to retrieve object at position (3, 4): ");
	   System.out.println(blockMatrix1.get(3, 4));
	   System.out.println("Using the set method to set object at position (2, 1) to 4");
	   blockMatrix1.set(2, 1, 4);
	   System.out.println("Showing result...");
	   System.out.println(blockMatrix1.toString());
	   System.out.println("Illegally using the set method to set object at (4, 0) to 1");
	   blockMatrix1.set(4, 0, 1);
	   System.out.println("Adding this matrix to itself: ");
	   System.out.println(blockMatrix1.add(blockMatrix1).toString());
	   System.out.println("Multiplying this matrix by itself: ");
	   System.out.println(blockMatrix1.multiply(blockMatrix1).toString());
	   
	   
	   /* TRIANGLE MATRIX TEST*/

	   SquareSparseMatrix tri1 = new SquareSparseMatrix(5);
	   tri1.set(0, 0, 1);
	   tri1.set(0, 1, 0);
	   tri1.set(0, 2, 0);
	   tri1.set(0, 3, 0);
	   tri1.set(0, 4, 0);
	   tri1.set(1, 0, 2);
	   tri1.set(1, 1, 1);
	   tri1.set(1, 2, 0);
	   tri1.set(1, 3, 0);
	   tri1.set(1, 4, 0);
	   tri1.set(2, 0, 2);
	   tri1.set(2, 1, 2);
	   tri1.set(2, 2, 1);
	   tri1.set(2, 3, 0);
	   tri1.set(2, 4, 0);
	   tri1.set(3, 0, 1);
	   tri1.set(3, 1, 2);
	   tri1.set(3, 2, 2);
	   tri1.set(3, 3, 2);
	   tri1.set(3, 4, 0);
	   tri1.set(4, 0, 1);
	   tri1.set(4, 1, 2);
	   tri1.set(4, 2, 3);
	   tri1.set(4, 3, 4);
	   tri1.set(4, 4, 5);
	   
	   TriangleMatrix triangleMatrix = new TriangleMatrix(tri1);
	   System.out.println("There are " + triangleMatrix.getRows() + " rows and " + triangleMatrix.getCols() + " cols in this matrix.");
	   System.out.println("Using toString to print out the matrix: ");
	   System.out.println(triangleMatrix.toString());
	   System.out.println("Using the get method to retrieve object at position (3, 4): ");
	   System.out.println(triangleMatrix.get(3, 4));
	   System.out.println("Using the set method to set object at position (2, 1) to 4");
	   triangleMatrix.set(2, 1, 4);
	   System.out.println("Showing result...");
	   System.out.println(triangleMatrix.toString());
	   System.out.println("Illegally using the set method to set object at (1, 3) to 1");
	   triangleMatrix.set(1, 3, 1);
	   System.out.println("Adding this matrix to itself: ");
	   System.out.println(triangleMatrix.add(triangleMatrix).toString());
	   System.out.println("Multiplying this matrix by itself: ");
	   System.out.println(triangleMatrix.multiply(triangleMatrix).toString());

	   
	   /*BAND MATRIX TEST */
	 
	   SquareSparseMatrix a = new SquareSparseMatrix(5);
	   a.set(0, 0, 6);
	   a.set(0, 1, 6);
	   a.set(0, 2, 0);
	   a.set(0, 3, 0);
	   a.set(0, 4, 0);
	   a.set(1, 0, 6);
	   a.set(1, 1, 2);
	   a.set(1, 2, 4);
	   a.set(1, 3, 0);
	   a.set(1, 4, 0);
	   a.set(2, 0, 0);
	   a.set(2, 1, 2);
	   a.set(2, 2, 4);
	   a.set(2, 3, 6);
	   a.set(2, 4, 0);
	   a.set(3, 0, 0);
	   a.set(3, 1, 0);
	   a.set(3, 2, 4);
	   a.set(3, 3, 6);
	   a.set(3, 4, 8);
	   a.set(4, 0, 0);
	   a.set(4, 1, 0);
	   a.set(4, 2, 0);
	   a.set(4, 3, 6);
	   a.set(4, 4, 8);

	   SquareSparseMatrix b = new SquareSparseMatrix(5);
	   b.set(0, 0, 3);
	   b.set(0, 1, 3);
	   b.set(0, 2, 3);
	   b.set(0, 3, 0);
	   b.set(0, 4, 0);
	   b.set(1, 0, 3);
	   b.set(1, 1, 1);
	   b.set(1, 2, 2);
	   b.set(1, 3, 3);
	   b.set(1, 4, 0);
	   b.set(2, 0, 2);
	   b.set(2, 1, 1);
	   b.set(2, 2, 2);
	   b.set(2, 3, 3);
	   b.set(2, 4, 4);
	   b.set(3, 0, 0);
	   b.set(3, 1, 1);
	   b.set(3, 2, 2);
	   b.set(3, 3, 3);
	   b.set(3, 4, 4);
	   b.set(4, 0, 0);
	   b.set(4, 1, 0);
	   b.set(4, 2, 2);
	   b.set(4, 3, 3);
	   b.set(4, 4, 4);
	   
	   BandMatrix bandMatrix = new BandMatrix(a);
	   System.out.println("There are " + bandMatrix.getRows() + " rows and " + bandMatrix.getCols() + " cols in this matrix.");
	   System.out.println("The width of the band is: " + bandMatrix.getWidth());
	   System.out.println("Using toString to print out the matrix: ");
	   System.out.println(bandMatrix.toString());
	   System.out.println("Using the get method to retrieve object at position (3, 4): ");
	   System.out.println(bandMatrix.get(3, 4));
	   System.out.println("Using the set method to set object at position (2, 1) to 4");
	   bandMatrix.set(2, 1, 4);
	   System.out.println("Showing result...");
	   System.out.println(bandMatrix.toString());
	   System.out.println("Illegally using the set method to set object at (1, 3) to 1");
	   bandMatrix.set(1, 3, 1);
	   System.out.println("Adding this matrix to itself: ");
	   System.out.println(bandMatrix.add(bandMatrix).toString());
	   System.out.println("Multiplying this matrix by itself: ");
	   System.out.println(bandMatrix.multiply(bandMatrix).toString());
	   
	   BandMatrix bandMatrix2 = new BandMatrix(b);
	   System.out.println("There are " + bandMatrix2.getRows() + " rows and " + bandMatrix2.getCols() + " cols in this matrix.");
	   System.out.println("The width of the band is: " + bandMatrix.getWidth());
	   System.out.println("Using toString to print out the matrix: ");
	   System.out.println(bandMatrix2.toString());
	   System.out.println("Using the get method to retrieve object at position (3, 4): ");
	   System.out.println(bandMatrix2.get(3, 4));
	   System.out.println("Using the set method to set object at position (2, 1) to 4");
	   bandMatrix2.set(2, 1, 4);
	   System.out.println("Showing result...");
	   System.out.println(bandMatrix2.toString());
	   System.out.println("Illegally using the set method to set object at (4, 0) to 1");
	   bandMatrix2.set(4, 0, 1);
	   System.out.println("Adding this matrix to itself: ");
	   System.out.println(bandMatrix2.add(bandMatrix).toString());
	   System.out.println("Multiplying this matrix by itself: ");
	   System.out.println(bandMatrix2.multiply(bandMatrix).toString());
	   

   }
}