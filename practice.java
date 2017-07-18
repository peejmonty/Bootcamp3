import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class practice
{
   public static void main(String[] args) throws IOException
   {
      final int MAX = 6;
      int[] array = new int[MAX];
      String filename = "lab10input.txt";
      File file = new File(filename);
      Scanner inputFile = new Scanner(file);
      int i = 0;
      while(inputFile.hasNext() && i < MAX)
      {
         array[i] = inputFile.nextInt();
         System.out.println(i + ", " + array[i]);
         i++;
      }

      Boolean sweep = sweep(array);
   }
   public static Boolean sweep(int[] array)
   {
      int temp; 
      boolean found;
      for(int i = 0; i < array.length-1; i++)
      {
         if (array[i] > array[i+1])
         {
            temp = array[i+1];
            array[i + 1] = array[i];
            array[i] = temp;
            found = true;
         }
      }
      return false;
   }
};