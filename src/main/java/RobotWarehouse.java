import exception.InvalidDataException;
import exception.MovingOutOfGridException;


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



    //takes direction from the user and moves accordingly
    String moveRobot(String move) throws Exception {

        String newDirections = move.replace(" ", "");
        char[] directions = newDirections.toCharArray();

        for (char direction : directions) {
            switch (direction) {
                case RobotConstants.NORTH : xAxis = decrementAxis(x, y, xAxis); break;
                case RobotConstants.EAST : yAxis = incrementAxis(x, y, yAxis); break;
                case RobotConstants.SOUTH : xAxis = incrementAxis(x, y, xAxis); break;
                case RobotConstants.WEST : yAxis = decrementAxis(x, y, yAxis); break;
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
      Increment Axis function increments the axis when robot moves towards south and East
      x  - retains the original value of xAxis
      y - retains the original value of yAxis
      axis - parameter to increment
     */
    public int incrementAxis(int x, int y, int axis) throws Exception {
        axis++;
        if (axis > 9) {
            xAxis = x;
            yAxis = y;
            throw new MovingOutOfGridException("Trying to move out of the grid, robot moved to initial place (" + x + "," + y + ")");
        }
        return axis;
    }

    /*
      Decrement Axis function decrements the axis when the robot moves towards north and West
      x - retains the original value of xAxis
      y - retains the original value of yAxis
      axis - parameter to decrement
     */
    public int decrementAxis(int x, int y, int axis) throws Exception {
        axis--;
        if (axis < 0) {
            xAxis = x;
            yAxis = y;
            throw new MovingOutOfGridException("Trying to move out of the grid, robot moved to initial place (" + x + "," + y + ")");
        }
        return axis;
    }



    //chooses to pick or drop the crate based on the user input
    public void crateOperations(String input) {
        crateGrid[5][4] = true;   // one crate placed at the centre
        crateGrid[0][9] = true;   // one crate placed in the northeast corner
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
