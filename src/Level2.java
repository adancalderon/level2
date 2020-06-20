import java.util.*;

public class Level2 {
    public static Maze maze = new Maze(19, 50); // creates a maze 20 x 51
    public static Random generator = new Random();
    public static Queue<String> q = new PriorityQueue<>(); //queue
    
 
    public static void main(String[] args) {
        int row = generator.nextInt(19);
        int col = generator.nextInt(50);
        maze.getStart(row,col);

        int exit_row = generator.nextInt(19);
        int exit_col = generator.nextInt(50);
        maze.getExit(exit_row, exit_col);
        
        maze.drawMaze(row,col,exit_row,exit_col);
        
        while(true){
            System.out.println("Enter coordinates for flood fill (Ex: AC or ACC)> " );

            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            char a = input.charAt(0);
            int a_n = (int)a - 65;
            int b_n = 0;

            if(input.length() == 2){
                char b = input.charAt(1);
                b_n = (int)b - 65;
            }
            if(input.length() == 3){
                char b = input.charAt(1);
                b_n = (int)b - 39;
            }

            int current_row = a_n;
            int current_col = b_n;

        System.out.println(current_row + "," + current_col);

        for(int i=0; i<maze.maze.length; i++){
            for(int j=0; j<maze.maze[0].length; j++){
                fill(current_row,current_col);
            }
        }
        while(!q.isEmpty()){
            String str = q.remove();
            String[] current = str.split(",");
            String currentRow = current[0];
            row = Integer.parseInt(currentRow);
            String currentCol = current[1];
            col = Integer.parseInt(currentCol);
            maze.maze[row][col] = '.';
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        maze.drawMaze(row,col,exit_row,exit_col);
        }

    }


    public static void fill(int row,int col){
        String s = row + "," + col;
        char target = maze.maze[row][col];
        char boundary;
        if(target == '_'){
            boundary = ' ';
        }
        if(target == ' '){
            boundary = '_';
        }
        if(target == '@'){
            boundary = '_';
        }
        if(target == 'E'){
            boundary = ' ';
        }
        maze.maze[row][col] = '.';
        
        
        if(maze.maze[row - 1][col] == maze.maze[row][col]){
            s = (row-1) + "," + col;
            q.add(s);
        }
        if(maze.maze[row + 1][col] == maze.maze[row][col]){
            s = (row+1) + "," + col;
            q.add(s);
        }
        if(maze.maze[row][col + 1] == maze.maze[row][col]){
            s = row + "," + (col+1);
            q.add(s);
        }
        if(maze.maze[row][col - 1] == maze.maze[row][col]){
            s = row + "," + (col-1);
            q.add(s);
        }
        
       
    }

}
