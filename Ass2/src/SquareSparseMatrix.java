import java.util.*;

/**
   A class for storing and operating on sparse two dimensional matrices.
   Note: A HashSet
   or TreeSet would be more efficient, but these aren't covered until ch. 8.
*/
public class SquareSparseMatrix
{
   /**
      Construct a Matrix object
      @param size the number of rows/cols in the matrix
   */
   public SquareSparseMatrix(int size)
   {
      if (size <= 0)
         throw new IllegalArgumentException("Can not construct this matrix");
      this.rows = size;
      this.cols = size;
      entries = new ArrayList<Entry>();
   }
   
   public SquareSparseMatrix(int[][] array) {
	   this.rows = array.length;
	   this.cols = array[0].length;
	   entries = new ArrayList<Entry>();
	   for (int r = 0; r < rows; r++) {
		   for (int c = 0; c < cols; c++) {
			  entries.add(new Entry(r, c, array[r][c]));
		   }
	   }
   }
   
   public int[][] getMatrix() {
	   int[][] returnMatrix = new int[rows][cols];
	   for (int r = 0; r < rows; r++) {
		   for (int c = 0; c < cols; c++) {
			   returnMatrix[r][c] = (int) get(r, c);
		   }
	   }
	   return returnMatrix;
   }

   /**
      Get the number of rows in this matrix
      @return the number of rows in the matrix
   */
   public double getRows()
   {
      return rows;
   }

   /**
      Get the number of columns in this matrix
      @return the number of columns in the matrix
   */
   public double getCols()
   {
      return cols;
   }

   /**
      Get the value of a particular element from this matrix
      @param r the row of the element to get
      @param c the column of the element to get
      @return the value of the specified element from the matrix
   */
   public double get(int r, int c)
   {
      for (Entry e : entries)
      {
         if (e.getRow() == r && e.getColumn() == c)
            return e.getValue();
      }
      return 0.0;
   }

   /**
      Set the value of a particular element from this matrix
      @param r the row of the element to get
      @param c the column of the element to get
      @param v the value to store in the specified location
   */
   public void set(int r, int c, double v)
   {
      for (Entry e : entries)
      {
         if (e.getRow() == r && e.getColumn() == c)
         {    
        	 e.setValue(v);
        	 return;
         }
      }
      entries.add(new Entry(r, c, v));
   }

   public String toString()
   {
      String r = "";
      for (int i = 0; i < rows; i++)
      {
         r += "[";
         for (int j = 0; j < cols; j++)
            r += String.format("%10.2f ", get(i, j));
         r += "]\n";
      }
      return r;
   }

   /**
      Add this matrix to another
      @param other the matrix to add to this one
      @return a matrix containing the sum of the two matrices
   */
   public SquareSparseMatrix plus(SquareSparseMatrix other)
   {
      if (other == null || rows != other.rows || cols != other.cols)
      {
         throw new IllegalArgumentException("Matrix is null");
      }

      SquareSparseMatrix result = new SquareSparseMatrix(rows);

      for (int i = 0; i < rows; i++)
         for (int j = 0; j < cols; j++)
            result.set(i, j, get(i, j) + other.get(i, j));
      return result;
   }

   /**
      Multiply this matrix by another
      @param other the matrix to muliply by this
      @return a matrix containing the product of the two matrices
   */
   public SquareSparseMatrix times(SquareSparseMatrix other)
   {
      if (other == null || cols != other.rows)
      {
         throw new IllegalArgumentException("Cannot multiply these matrices");
      }

      SquareSparseMatrix result = new SquareSparseMatrix(rows);
      for (int i = 0; i < rows; i++)
         for (int j = 0; j < other.cols; j++)
         {
            double temp = 0;
            for (int k = 0; k < cols; k++)
               temp = temp + get(i, k) * other.get(k,j);
            result.set(i, j, temp);
         }

      return result;
   }

   private ArrayList<Entry> entries;
   private int rows;
   private int cols;
}