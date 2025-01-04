import java.util.*;

public class Menu {
    private static Graph graph = new Graph();
    private static Heap heap = new Heap();
    private static VehicleHashTable vehicleTable = new VehicleHashTable(5);
    private static Object[] vehicleArr = new Object[100];
    private static int k = 0;
    
    public static void main(String[] args) {

        System.out.println("WELCOME!!!");
        System.out.println("Autonomous Vehicle Management System");
    
        System.out.println("\n");
        System.out.println("Operations");
        System.out.println("~~~~~~~~~~~");
        System.out.print("\n");
        System.out.println("1.) Add a Location.");
        System.out.println("2.) Add a Road.");
        System.out.println("3.) Get the neighbours of a location.");
        System.out.println("4.) Display Road Network.");
        System.out.println("5.) Check path existence.");
        System.out.println("6.) Add Vehicle.");
        System.out.println("7.) Remove Vehicle.");
        System.out.println("8.) Display Vehicle List.");
        System.out.println("9.) Sort Vehicles based on Distance to Destination.(Ascending)");
        System.out.println("10.) Sort Vehicles based on Battery Level.(Descending)");
        System.out.println("0.) Exit.");
        
        menu();
    }

    public static int menu() {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\nChoose an operation.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        
        int choice = 0;
        boolean flag = false;
        while(!flag) {
            try {
                System.out.print("--> ");
                choice = sc.nextInt();
                flag = true;
                
                if(choice < 0 || choice > 10) {
                    System.out.println("##Please enter a valid choice.##");
                    flag = false;
                }
                
            } catch (InputMismatchException e) {
                System.out.println("##Please enter a valid choice.##");
                sc.next();
                flag = false;
            }
        }
        
        process(choice);
        sc.close();
        return choice;
    }

    //private static ;

    public static void process(int choice) {

        Scanner sc = new Scanner(System.in);

        switch (choice) {

            case 1:
                System.out.println("\n||| Enter Location Name.");

                System.out.print("Location --> ");
                String location = sc.nextLine();

                graph.addVertex(location);

                menu();
                break;

            case 2:
                System.out.println("\n||| Enter the two locations.");

                System.out.print("Location 1 --> ");
                String location1 = sc.nextLine();

                while (!graph.hasVertex(location1)) {
                    System.out.println("That location does not exist in the graph.");
                    System.out.println("Please enter another location.");
                    System.out.print("Location 1 --> ");
                    location1 = sc.nextLine();
                }

                System.out.print("Location 2 --> ");
                String location2 = sc.nextLine();

                while (!graph.hasVertex(location2)) {
                    System.out.println("That location does not exist in the graph.");
                    System.out.println("Please enter another location.");
                    System.out.print("Location 2 --> ");
                    location2 = sc.nextLine();
                }

                System.out.print("Distance --> ");
                double distance = sc.nextDouble();

                while (distance <= 0) {
                    System.out.println("Please enter a valid distance.");
                    System.out.print("Distance --> ");
                    distance = sc.nextDouble();
                }

                graph.addEdge(location1, location2, distance);

                menu();
                break;

            case 3:
                System.out.println("\n||| Enter the location.");

                System.out.print("Location --> ");
                String locationAdj = sc.nextLine();

                while (!graph.hasVertex(locationAdj)) {
                    System.out.println("That location does not exist in the graph.");
                    System.out.println("Please enter another location.");
                    System.out.print("Location --> ");
                    locationAdj = sc.nextLine();
                }

                System.out.print("\n");
                graph.displayAdjList(locationAdj);
                System.out.print("\n");

                menu();
                break;

            case 4:
                System.out.println("\n||| Displaying the Road network...");
                graph.displayList();
                
                menu();
                break;

            case 5:
                System.out.println("\n||| Enter the two locations.");
                
                System.out.print("Location 1 --> ");
                String loc1 = sc.nextLine();

                while (!graph.hasVertex(loc1)) {
                    System.out.println("That location does not exist in the graph.");
                    System.out.println("Please enter another location.");
                    System.out.print("Location 1 --> ");
                    loc1 = sc.nextLine();
                }

                System.out.print("Location 2 --> ");
                String loc2 = sc.nextLine();

                while (!graph.hasVertex(loc2)) {
                    System.out.println("That location does not exist in the graph.");
                    System.out.println("Please enter another location.");
                    System.out.print("Location 2 --> ");
                    loc2 = sc.nextLine();
                }

                boolean pathExists = false;
                pathExists = graph.BFS(loc1, loc2);

                if(pathExists) {
                    System.out.println("\nA road from " + loc1 + " to " + loc2 + " does exist.");
                }
                else {
                    System.out.println("\nA road from " + loc1 + " to " + loc2 + " does NOT exist.");
                }
                
                menu();
                break;

            case 6:
                Vehicle vehicle = new Vehicle();
                System.out.println("\n||| Enter Vehicle Details.");

                System.out.print("Vehicle ID --> ");
                String vehicleID = sc.nextLine();

                while (vehicleTable.find(vehicleID)) {
                    System.out.println("That vehicle already exists.");
                    System.out.println("Please enter another vehicle ID.");
                    System.out.print("Vehicle ID --> ");
                    vehicleID = sc.nextLine();
                }

                System.out.print("Current location --> ");
                String currlocation = sc.nextLine();

                while (!graph.hasVertex(currlocation)) {
                    System.out.println("That location does not exist in the graph.");
                    System.out.println("Please enter another location.");
                    System.out.print("Current location --> ");
                    currlocation = sc.nextLine();
                }

                System.out.print("Destination --> ");
                String destination = sc.nextLine();

                while (!graph.hasVertex(destination)) {
                    System.out.println("That location does not exist in the graph.");
                    System.out.println("Please enter another location.");
                    System.out.print("Destination --> ");
                    destination = sc.nextLine();
                }

                Double batteryLevel = 0.0;
                boolean flag = false;
                while(!flag) {
                    try {
                        System.out.print("Battery Level --> ");
                        batteryLevel = sc.nextDouble();
                        flag = true;
                        
                        if(batteryLevel < 0 || batteryLevel > 100) {
                            System.out.println("\nPlease enter a valid battery level.");
                            flag = false;
                        }  
                    } 
                    catch (InputMismatchException e) {
                        System.out.println("\nPlease enter a valid battery level.");
                        sc.next();
                        flag = false;
                    }
                }

                vehicle.setID(vehicleID);
                vehicle.setLocation(currlocation);
                vehicle.setDestination(destination);
                vehicle.setBatteryLevel(batteryLevel);
                vehicle.setDistance(graph, currlocation, destination);
                vehicleTable.put(vehicleID, vehicle);
                vehicleArr[k] = vehicle;
                k++;
                
                menu();
                break;
            
            case 7:
                System.out.println("\n||| Enter the ID of the vehicle to be removed.\n");
                Vehicle vehicleTemp3 = new Vehicle();

                if(vehicleTable.getNumOfEntries() != 0) {
                    System.out.print("Vehicle ID --> ");
                    String ID = sc.nextLine();
    
                    while (!vehicleTable.find(ID)) {
                        System.out.println("That vehicle does not exist in the table.");
                        System.out.println("Please enter another vehicle ID.");
                        System.out.print("Vehicle ID --> ");
                        ID = sc.nextLine();
                    }
    
                    vehicleTemp3.removeVehicle(vehicleArr, ID);
                    vehicleTable.remove(ID);
                }

                else {
                    System.out.println("The vehicle list is empty.");
                }

                menu();
                break;

            case 8:
                System.out.println("\n||| Displaying the Vehicle List...");

                if(vehicleTable.getNumOfEntries() != 0) {
                    vehicleTable.display();
                    System.out.print("\n");
                }
                else {
                    System.out.println("The vehicle list is empty.");
                }

                menu();
                break;

            case 9:
                Vehicle vehicleTemp = new Vehicle();
                System.out.println("\n||| Sorting Vehicles based on Distance to Destination...\n");

                if(vehicleTable.getNumOfEntries() != 0) {
                    heap = vehicleTemp.sortDistance(vehicleTable);
                    vehicleTemp.find_nearest_vehicle(heap);
                }
                else {
                    System.out.println("The vehicle list is empty.");
                }

                menu();
                break;

            case 10:
                System.out.println("\n||| Sorting Vehicles based on Battery Level...\n");
                Vehicle vehicleTemp2 = new Vehicle();
                Object[] array;

                if(vehicleTable.getNumOfEntries() != 0) {
                    array = vehicleTemp2.sortBatteryLevel(vehicleArr);
                    vehicleTemp2.find_vehicle_with_highest_battery(array);
                }
                else {
                    System.out.println("The vehicle table is empty.");
                }

                menu();
                break;

            case 0:
                System.out.println("########EXIT########");
                break;

            default:
                System.out.println("####Invalid choice####");
                break;
        }

        sc.close();
    }
}
