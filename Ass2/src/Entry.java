/**
   An entry in a sparse matrix.
*/
public class Entry
{
   /**
      Constructs an entry in a sparse matrix.
      @param r the row
      @param c the column
      @param v the value
   */
   public Entry(int r, int c, double v)
   {
      row = r;
      col = c;
      val = v;
   }

   /**
      Gets the row of this entry.
      @return the row
   */
   public int getRow()
   {
      return row;
   }

   /**
      Gets the column of this entry.
      @return the column
   */
   public int getColumn()
   {
      return col;
   }

   /**
      Sets the value of this entry.
      @param v the new value
   */
   public void setValue(double v)
   {
      val = v;
   }

   /**
      Gets the value of this entry.
      @return the value
   */
   public double getValue()
   {
      return val;
   }

   public String toString()
   {
      return "(" + row + "," + col + "," + val + ")";
   }

   private int row;
   private int col;
   private double val;
}