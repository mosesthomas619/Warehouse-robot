import exception.InvalidDataException;
import exception.OutOfBoundaryException;


 /* xAxis - denotes North/south movement
  yAxis - denotes West/East movement
  Initialized the position of the robot to (9,0) which is south-west corner of the grid.
 */
public class RobotWarehouse {

    int xAxis = 9;
    int yAxis = 0;
    boolean holdingCrate = false;

    int x = xAxis;
    int y = yAxis;

    boolean[][] crateGrid = new boolean[10][10];

     RobotWarehouse() {
         crateGrid[5][4] = true;   // one crate placed at the centre
         crateGrid[0][9] = true;   // one crate placed in the northeast corner
     }

     String getCurrentPosition() {
        return "The robot is at the position (" + xAxis + "," + yAxis + ") on the grid at the moment.";
     }

     void getCurrentCratePosition() {

         System.out.println("Current co-ordinates of the crates: ");
         for(int i=0; i < crateGrid.length; i++) {
             for(int j=0; j < crateGrid[i].length; j++) {
                 if(crateGrid[i][j]) {
                     System.out.println("(" + i +"," + j +")");
                 }
             }
         }
     }


    //takes direction from the user and moves accordingly
    String moveRobot(String move) throws Exception {

        String newDirections = move.replace(" ", "");
        char[] directions = newDirections.toCharArray();

        for (char direction : directions) {
            switch (direction) {
                case RobotConstants.NORTH : xAxis = moveWestOrNorthAlongAxis(xAxis); break;
                case RobotConstants.EAST : yAxis = moveEastOrSouthAlongAxis(yAxis); break;
                case RobotConstants.SOUTH : xAxis = moveEastOrSouthAlongAxis(xAxis); break;
                case RobotConstants.WEST : yAxis = moveWestOrNorthAlongAxis(yAxis); break;
                default : throw new InvalidDataException("Invalid Input");
            }
        }

        if (xAxis == x && yAxis == y) {
            return "Robot is back at the starting point (" + xAxis + "," + yAxis + ")";
        } else if (xAxis == 5 && yAxis == 4) {
            return "The robot is at the centre of the grid (" + xAxis + "," + yAxis + ")";
        } else {
            return "Robot is at the position:  (" + xAxis + "," + yAxis + ")";
        }
    }

    /*
      moveEastOrSouthAlongAxis function increments the axis when robot moves towards south and East
      axis - parameter to increment
     */
    public int moveEastOrSouthAlongAxis(int axis) throws Exception {
        if ((axis + 1) > 9) {
            throw new OutOfBoundaryException("Cannot move out of the grid!");
        }
        axis++;
        return axis;
    }

    /*
      moveWestOrNorthAlongAxis function decrements the axis when the robot moves towards north and West
      axis - parameter to decrement
     */
    public int moveWestOrNorthAlongAxis(int axis) throws Exception {
        if ((axis-1) < 0) {
            throw new OutOfBoundaryException("Cannot move out of the grid!");
        }
        axis--;
        return axis;
    }



    //chooses to pick or drop the crate based on the user input
    public void crateOperations(char input) {
        switch (input) {
            case RobotConstants.GRAB: grabCrate(); break;
            case RobotConstants.DROP: dropCrate(); break;
            default : System.out.println("Invalid input");
        }
    }


    public void grabCrate() {
        boolean isCratePresent = crateGrid[xAxis][yAxis];
        if (isCratePresent && !holdingCrate) {
            holdingCrate = true;
            crateGrid[xAxis][yAxis] = false;
            System.out.println("Successfully picked up the crate");
        } else if(holdingCrate) {
            System.out.println("Already holding one crate. Please drop it first to pick another.");
        } else {
            System.out.println("A crate is not present in the current position.");
        }
    }


    public void dropCrate() {
        boolean isCratePresent = crateGrid[xAxis][yAxis];
        if (!isCratePresent && holdingCrate) {
            holdingCrate = false;
            crateGrid[xAxis][yAxis] = true;
            System.out.println("Successfully dropped the crate");
        } else if(!holdingCrate) {
            System.out.println("Not holding any crate at the moment. Please pick one.");
        } else {
            System.out.println("A crate is already present in the current position (" + xAxis + "," + yAxis + ") please choose a different location");
        }
    }
}
