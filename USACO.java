import java.util.*;
import java.io.*;

public class USACO{
public static int bronze(String filename)throws FileNotFoundException {
      //instead of a try/catch, you can throw the FileNotFoundException.
      //This is generally bad behavior

      try {
        File text = new File(filename);
        // can be a path like: "/full/path/to/file.txt" or "../data/file.txt"

        //inf stands for the input file
        Scanner inf = new Scanner(text);
        String topLine = inf.nextLine();
        String[] parts = topLine.split(" ");
        int rows = Integer.parseInt(parts[0]);
        int cols = Integer.parseInt(parts[1]);
        int waterlvl = Integer.parseInt(parts[2]);
        int instructionCount = Integer.parseInt(parts[2]);
        int counter = 0;
        int[][] field = new int[rows][cols];
        //System.out.println(rows);
        //System.out.println(cols);
        while(counter < rows){
            String line = inf.nextLine();
            //System.out.println(line);
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
        //System.out.println(instructionCount);
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
          //System.out.println(max_elevation);
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
          return total_water;
        }
        catch (FileNotFoundException ex){
          return -1;
        }
    }

    public static int silver(String filename)throws FileNotFoundException {

      try {
        File text = new File(filename);
        // can be a path like: "/full/path/to/file.txt" or "../data/file.txt"

        //inf stands for the input file
        Scanner inf = new Scanner(text);

        String[] topLine =  inf.nextLine().split(" ");
        int rows = Integer.parseInt(topLine[0]);
        int cols = Integer.parseInt(topLine[1]);
        int time = Integer.parseInt(topLine[2]);
        char[][] field = new char[rows][cols];
        int[][][] field_nums = new int[rows][cols][time + 1];
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
        int s_x = Integer.parseInt(line[0])-1;
        int s_y = Integer.parseInt(line[1])-1;
        int e_x = Integer.parseInt(line[2])-1;
        int e_y = Integer.parseInt(line[3])-1;
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
        //System.out.println(a.length);
        int[] b = a[e_y];
        int c = b[time];
        //System.out.println(field_nums[e_x][e_y][time]);
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
        return field_nums[e_x][e_y][time];
      }
      catch (FileNotFoundException ex){
        return -1;
      }
    }
}
