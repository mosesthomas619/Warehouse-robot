import java.util.Scanner;


public class RobotOperations {

    Scanner scanner = new Scanner(System.in);
    RobotWarehouse robotWarehouse = new RobotWarehouse();

    public void userMenu() {
        Scanner scanner = new Scanner(System.in);

        boolean quit = false;

        while (!quit) {
            System.out.println(
                    "\n\nWarehouse robot Menu: " + "\n" + "--------------------\n" +
                            "press 1 -> move the robot - Allowed options are N/S/W/E" + "\n" +
                            "press 2 -> Grab/Drop the crate - Allowed options are G/D" + "\n" +
                            "press 3 -> quit"+ "\n\n" + "Please enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 : moveInTheGrid(); break;
                case 2 : workWithCrate(); break;
                case 3 : {
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
        String command = scanner.nextLine();
        robotWarehouse.crateOperations(command);
    }
}
