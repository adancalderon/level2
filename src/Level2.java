import java.util.*;

public class Level2 {
    public static Maze maze = new Maze(19, 50); // creates a maze 20 x 51
    public static Random generator = new Random();
    public static Queue<String> q = new PriorityQueue<>(); //queue
    public static Scanner scan = new Scanner(System.in);
    public static String currentPos;
    public static String coords;
    
 
    public static void main(String[] args) throws ArrayIndexOutOfBoundsException {
        int row = generator.nextInt(maze.maze.length);
        int col = generator.nextInt(maze.maze[0].length);
        maze.getStart(row,col);

        int exit_row = generator.nextInt(maze.maze.length);
        int exit_col = generator.nextInt(maze.maze[0].length);
        maze.getExit(exit_row, exit_col);


        while(true) {
            maze.drawMaze(row,col,exit_row,exit_col);
            System.out.println("Enter coordinates for flood fill (Ex: AC or ACC)");
             coords = scan.nextLine();

            String input = coords.toUpperCase();
            char row_cord = input.charAt(0);
            int row_num = row_cord - 65;
            int col_num;

            if (input.length() < 3) {
                char col_cord = input.charAt(1);
                col_num = col_cord - 65;

            } else {
                char col_cord1 = input.charAt(1);
                col_num = col_cord1 - 39;
            }

            currentPos = (row_num + "," + (col_num));
            System.out.println(currentPos);
            fill(currentPos);

        }

    }

    public static void fill (String currentPos) {
        String[] current = currentPos.split(",");
        String currentRow = current[0];
        int row = Integer.parseInt(currentRow);
        String currentCol = current[1];
        int col = Integer.parseInt(currentCol);

        //Looking at maze CURRENT POSITION MAKING IT a "Target Variable"
        char target = maze.maze[row][col]; //target variable from currentPos
        char boundary;


        if(target == '_'){   // if target is blue
             boundary = ' ';  //boundary is green
        }
        else if (target == ' ') { // if target is green
             boundary = '_'; // boundary is blue
        }

        else{
            boundary = '.'; // should never be this
            System.out.println("debuggg cell is neither space or underscore");
        }

        maze.maze[row][col] = boundary; // make this position equal to the boundary

            //LOOK UP
        int up_row = row - 1;
        if (up_row > -1) {
            try {
                if (maze.maze[up_row][col] == target) { // if spot is equal to target
                    q.add(up_row + "," + col);  //enqueue up coordinate
                }

            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }

        int down_row = row + 1;
        if (down_row<19) {
            //LOOK DOWN and ENQUEUE IF GREEN TOO (' ")
            try {

                if (maze.maze[down_row][col] == target) { // if spot is equal to target
                    q.add(down_row + "," + col);  //enqueue up coordinate
                }

            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }

            //LOOK LEFT and ENQUEUE IF GREEN TOO (' ")
        int left_col = col - 1; // checks cell directly above it
        if(left_col>-1)
            try {
                if (maze.maze[row][left_col] == target) { // if spot is equal to target then
                    q.add(row+","+left_col);  //enqueue up coordinate
                }

            } catch (ArrayIndexOutOfBoundsException e) {}

            //LOOK RIGHT and ENQUEUE IF GREEN TOO (' ")
        int right_col = col + 1; // checks cell directly above it
        if(right_col<50)
            try {
                if (maze.maze[row][right_col] == target) { // if spot is equal to target
                    q.add(row+","+right_col);  //enqueue up coordinate
                }

            } catch (ArrayIndexOutOfBoundsException e) {}

                //STEP 4

            if(!q.isEmpty()){
               currentPos = q.remove();
               fill(currentPos);  //recursive call from just dequeued position
            }
        }


    }

