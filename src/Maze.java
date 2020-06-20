import java.util.*;
public class Maze {

    public char[][] maze;
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String BG_ANSI_GREEN = "\u001B[42m";
    public static final String ANSI_BLUE = "\u001B[44m";
    
    public static Random generator = new Random(); 
    
    public Maze(int rows, int cols)
    {
        maze = new char[rows][cols];
    }
    
    public void drawMaze(int row,int col,int exit_row,int exit_col)
    {
        char a = 'A';
        System.out.println("                           ABCDEFGHIJKLMNOPQRSTUVWX");
        System.out.println(" ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWX");
        for(int i=0; i<maze.length; i++){
            for(int j=0; j<maze[0].length; j++){
                if(i == row && j == col){
                    maze[i][j] = '@';
                }
                if(i == exit_row && j == exit_col){
                    maze[i][j] = 'E';
                }
                else{
                    int num = generator.nextInt(2);
                    if(num == 0){
                        maze[i][j] = '_';
                    }
                    else{
                        maze[i][j] = ' ';
                    }
                }
            }
        }
        for (char[] chars : maze) {
            System.out.print(a);
            for (int j = 0; j < maze[0].length; j++) {
                if (chars[j] == '@') {
                    System.out.print(BG_ANSI_GREEN + chars[j] + ANSI_RESET);
                }
                if (chars[j] == 'E') {
                    System.out.print(ANSI_BLUE + chars[j] + ANSI_RESET);
                }
                if (chars[j] == '_') {
                    System.out.print(ANSI_BLUE + chars[j] + ANSI_RESET);
                }
                if (chars[j] == ' ') {
                    System.out.print(BG_ANSI_GREEN + chars[j] + ANSI_RESET);
                }
            }
            System.out.print(a);
            a++;
            System.out.println();
        }
        System.out.println(" ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWX");
    }
    public void getStart(int row, int col){
        while(maze[row][col] == '_'){
            row = generator.nextInt(maze.length);
            col = generator.nextInt(maze[0].length);
        }
    }
    public void getExit(int exit_row,int exit_col){
        while(maze[exit_row][exit_col] == ' '){
            exit_row = generator.nextInt(maze.length);
            exit_col = generator.nextInt(maze[0].length);
        }
    }

}