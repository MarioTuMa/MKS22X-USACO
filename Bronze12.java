import java.util.*;
import java.io.*;
public class Bronze12{

    private int[][]field;
    private int waterlvl;
    private int rows;
    private int cols;
    private int instructionCount;
    private int[][] instructions;
    public Bronze12(String filename) throws FileNotFoundException {
        //instead of a try/catch, you can throw the FileNotFoundException.
        //This is generally bad behavior

        try {
          File text = new File(filename);
          // can be a path like: "/full/path/to/file.txt" or "../data/file.txt"

          //inf stands for the input file
          Scanner inf = new Scanner(text);
          String topLine = inf.nextLine();
          String[] parts = topLine.split(" ");
          rows = Integer.parseInt(parts[0]);
          cols = Integer.parseInt(parts[1]);
          waterlvl = Integer.parseInt(parts[2]);
          instructionCount = Integer.parseInt(parts[2]);
          int counter = 0;
          field = new int[rows][cols];
          //System.out.println(rows);
          //System.out.println(cols);
          while(counter < rows){
              String line = inf.nextLine();
              System.out.println(line);
              String[] lineparts = line.split(" ");
              for(int i = 0; i < cols; i ++){
                //System.out.println(Integer.parseInt(lineparts[i]));
                field[counter][i]=Integer.parseInt(lineparts[i]);
              }
              counter++;
          }
          Scanner inf1 = new Scanner(text);
          counter = 0;
          while(inf1.hasNextLine()){
              String line = inf1.nextLine();
              counter++;

          }
          counter -= 1;
          counter -= rows;
          instructionCount = counter;
          System.out.println(instructionCount);
          for(int i = 0; i < instructionCount; i ++){
            String[] instruction = inf.nextLine().split(" ");
            int left = Integer.parseInt(instruction[0])-1;
            int middle = left + 1;
            int right = left + 2;
            int top = Integer.parseInt(instruction[1])-1;
            int medium = top + 1;
            int bottom = top + 2;
            int max_elevation = 0;

            if(field[left][top]>max_elevation){
              max_elevation = field[left][top];
            }
            if(field[left][medium]>max_elevation){
              max_elevation = field[left][medium];
            }
            if(field[left][bottom]>max_elevation){
              max_elevation = field[left][bottom];
            }

            if(field[middle][top]>max_elevation){
              max_elevation = field[middle][top];
            }
            if(field[middle][medium]>max_elevation){
              max_elevation = field[middle][medium];
            }
            if(field[middle][bottom]>max_elevation){
              max_elevation = field[middle][bottom];
            }

            if(field[right][top]>max_elevation){
              max_elevation = field[right][top];
            }
            if(field[right][medium]>max_elevation){
              max_elevation = field[right][medium];
            }
            if(field[right][bottom]>max_elevation){
              max_elevation = field[right][bottom];
            }
            System.out.println(max_elevation);
            int new_elevation = max_elevation - Integer.parseInt(instruction[2]);
            if(field[left][top]>new_elevation){
              field[left][top]=new_elevation;
            }
            if(field[left][medium]>new_elevation){
              field[left][medium]=new_elevation;
            }
            if(field[left][bottom]>new_elevation){
              field[left][bottom]=new_elevation;
            }

            if(field[middle][top]>new_elevation){
              field[middle][top]=new_elevation;
            }
            if(field[middle][medium]>new_elevation){
              field[middle][medium]=new_elevation;
            }
            if(field[middle][bottom]>new_elevation){
              field[middle][bottom]=new_elevation;
            }

            if(field[right][top]>new_elevation){
              field[right][top]=new_elevation;
            }
            if(field[right][medium]>new_elevation){
              field[right][medium]=new_elevation;
            }
            if(field[right][bottom]>new_elevation){
              field[right][bottom]=new_elevation;
            }
          }
          int total_water = 0;
          for(int i = 0; i < rows;i++){
            for(int j = 0; j < cols; j++){
              if(field[i][j]<waterlvl){
                total_water+=(72 * 72 * (waterlvl-field[i][j]));
              }
            }
          }
          System.out.println(total_water);
        }
        catch (FileNotFoundException ex){
          System.out.println("hi");
        }
    }
    public static void main(String[] args){
      try{
        Bronze12 nm = new Bronze12("input.txt");


      }catch(FileNotFoundException e){
        System.out.println("file not found");
      }

    }
}
