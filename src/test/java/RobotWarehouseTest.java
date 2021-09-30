import exception.MovingOutOfGridException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class RobotWarehouseTest {

    RobotWarehouse robotWarehouse;

    @BeforeEach
    public void createObject() {
        robotWarehouse = new RobotWarehouse();
    }

    //Move back to origin if N E S W is given as input
    @Test
    public void testIfRobotMovesBackToOrigin() throws Exception {
        String message = robotWarehouse.moveRobot("N E S W");
        Assertions.assertEquals("Robot is back at the starting point (9,0)", message);
    }

    //Move to the centre of the grid when N E N E N E N E is given as input
    @Test
    public void testIfRobotMovesToCentreOfTheGrid() throws Exception {
        String message = robotWarehouse.moveRobot("N E N E N E N E");
        Assertions.assertEquals("The robot is at the centre of the grid (5,4)", message);
    }

    //exception is thrown when robot moves out of the grid
    @Test
    public void testIfExceptionIsThrownWhenRobotMovesOutOfGrid() throws Exception {

        Exception exception = Assertions.assertThrows(MovingOutOfGridException.class, () -> robotWarehouse.incrementAxis(9, 0, 10));

        String expectedMessage = "Trying to move out of the grid, robot moved to initial place (9,0)";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
