import java.util.*;
import java.io.*;
public class Silver7{

    private char[][]field;
    private int[][][]field_nums;
    private boolean animate;//false by default
    private int rows;
    private int cols;
    private int time;
    private int s_x;
    private int s_y;
    private int e_x;
    private int e_y;
    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)
      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!

      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:
         throw a FileNotFoundException or IllegalStateException
    */
    public Silver7(String filename) throws FileNotFoundException {
        //instead of a try/catch, you can throw the FileNotFoundException.
        //This is generally bad behavior
        animate = true;
        try {
          File text = new File(filename);
          // can be a path like: "/full/path/to/file.txt" or "../data/file.txt"

          //inf stands for the input file
          Scanner inf = new Scanner(text);

          String[] topLine =  inf.nextLine().split(" ");
          rows = Integer.parseInt(topLine[0]);
          cols = Integer.parseInt(topLine[1]);
          time = Integer.parseInt(topLine[2]);
          field = new char[rows][cols];
          field_nums = new int[rows][cols][time + 1];
          int counter = 0;
          while(counter<rows){
              String[] line = inf.nextLine().split("");

              for(int i = 0; i < cols; i ++){
                char next_char = line[i].charAt(0);
                //System.out.println(next_char);
                field[counter][i]=next_char;
              }
              counter++;
          }

          String[] line = inf.nextLine().split(" ");
          s_x = Integer.parseInt(line[0])-1;
          s_y = Integer.parseInt(line[1])-1;
          e_x = Integer.parseInt(line[2])-1;
          e_y = Integer.parseInt(line[3])-1;
          //field_nums[s_x][s_y][0]=1;
          for(int j = 0; j < rows; j ++){
            for(int k = 0; k < cols; k ++){

                field_nums[j][k][0]=0;

            }
          }
          field_nums[s_x][s_y][0]=1;
          for(int i = 1; i < time + 1; i ++){
            for(int j = 0; j < rows; j ++){
              for(int k = 0; k < cols; k ++){
                if(field[j][k]=='.'){
                  int next_num = 0;
                  if(j > 0){
                    next_num+=field_nums[j - 1][k][i-1];
                  }
                  if(j < rows - 1){
                    next_num+=field_nums[j + 1][k][i-1];
                  }
                  if(k > 0){
                    next_num+=field_nums[j][k - 1][i-1];
                  }
                  if(k < cols - 1){
                    next_num+=field_nums[j][k + 1][i-1];
                  }
                  field_nums[j][k][i]=next_num;
                }
              }
            }
          }
          int[][] a = field_nums[e_x];
          System.out.println(a.length);
          int[] b = a[e_y];
          int c = b[time];
          System.out.println(field_nums[e_x][e_y][time]);
          for(int i = 0; i < time + 1; i ++){
            for(int j = 0; j < rows; j ++){
              String row_string = "";
              for(int k = 0; k < cols; k ++){
                if(field[j][k]=='*'){
                  row_string+="*";
                }
                else{
                  row_string+=field_nums[j][k][i];
                }
              }
              //System.out.println(row_string);
            }
            //System.out.println(" ");
          }
          System.out.println(field_nums[e_x][e_y][time]);
        }
        catch (FileNotFoundException ex){
          System.out.println("hi");
        }
    }

    public static void main(String[] args){
      try{
        Silver7 nm = new Silver7("data.txt");

      }catch(FileNotFoundException e){
        System.out.println("file not found");
      }

    }

}
