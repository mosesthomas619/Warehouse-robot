import java.util.Scanner;


public class RobotOperations {

    Scanner scanner = new Scanner(System.in);
    RobotWarehouse robotWarehouse = new RobotWarehouse();

    public void userMenu() {
        Scanner scanner = new Scanner(System.in);

        boolean quit = false;

        while (!quit) {
            System.out.println(
                    "\nWarehouse robot Menu: " + "\n" + "--------------------\n" +
                            "press 0 -> Get current position of the robot in the grid" + "\n" +
                            "press 1 -> move the robot - Allowed options are N/S/W/E" + "\n" +
                            "press 2 -> Grab/Drop the crate - Allowed options are G/D" + "\n" +
                            "press 3 -> Get current position of crates in the grid" + "\n" +
                            "press 4 -> quit"+ "\n\n" + "Please enter your choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "0" : System.out.println(robotWarehouse.getCurrentPosition()); break;
                case "1" : moveInTheGrid(); break;
                case "2" : workWithCrate(); break;
                case "3" : robotWarehouse.getCurrentCratePosition(); break;
                case "4" : {
                    quit = true;
                    System.out.println("Robot shut down!");
                    scanner.close();
                    break;
                }
                default : System.out.println("Invalid option");
            }
        }
    }

    private void moveInTheGrid() {
        System.out.println("Please enter the direction in which robot must move: ");
        String directions = scanner.nextLine();

        try {
            System.out.println(robotWarehouse.moveRobot(directions));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void workWithCrate() {
        System.out.println("Press G to grab a crate or D to drop it");
        char command = scanner.next().charAt(0);
        robotWarehouse.crateOperations(command);
    }
}
