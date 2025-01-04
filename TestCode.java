public class TestCode {
    
    // Initializing graph, heap, vehicle table, and vehicle array
    private static Graph graph = new Graph();
    private static Heap heap = new Heap();
    private static VehicleHashTable vehicleTable = new VehicleHashTable(10); // Increased size to accommodate more vehicles
    private static Object[] vehicleArr = new Object[6];
    private static Vehicle vehicle = new Vehicle();

    public static void main(String[] args) {
        
        // Adding locations to the graph
        System.out.println("\n||| Adding locations...");
        String[] locations = {"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "AA", "BB", "CC", "DD"};
        for (String location : locations) {
            graph.addVertex(location);
        }

        // Connecting locations with edges and weights
        System.out.println("\n||| Connecting locations...");
        graph.addEdge("P", "Q", 220);
        graph.addEdge("Q", "R", 180);
        graph.addEdge("R", "S", 160);
        graph.addEdge("S", "T", 110);
        graph.addEdge("U", "V", 90);
        graph.addEdge("W", "X", 140);
        graph.addEdge("X", "Y", 200);
        graph.addEdge("Y", "Z", 170);
        graph.addEdge("Z", "AA", 130);
        graph.addEdge("AA", "BB", 120);
        graph.addEdge("BB", "CC", 100);
        graph.addEdge("CC", "DD", 150);
        graph.addEdge("DD", "P", 95);
        graph.addEdge("V", "W", 210);
        graph.addEdge("Y", "S", 85);
        graph.addEdge("U", "R", 110);

        // Displaying the graph structure in list and matrix form
        System.out.println("\n||| Displaying the current Road Network.\n");
        graph.displayList();

        System.out.println("\n||| Displaying the current Road Network as a Adjacency Matrix.\n");
        graph.displayMatrix();

        // Checking if paths exist between certain locations using BFS
        System.out.println("\n||| Checking paths...");

        System.out.print("P to T - ");
        System.out.println(graph.BFS("P", "T")); // Should print true if path exists

        System.out.print("AA to DD - ");
        System.out.println(graph.BFS("AA", "DD")); // Should print true if path exists

        System.out.print("U to Z - ");
        System.out.println(graph.BFS("U", "Z")); // Should print true if path exists

        // Adding vehicles with specified IDs, locations, destinations, and battery levels
        System.out.println("\n||| Adding Vehicles...");

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setID("RB20");
        vehicle1.setLocation("T");
        vehicle1.setDestination("P");
        vehicle1.setBatteryLevel(100.0);
        vehicle1.setDistance(graph, "T", "P");
        vehicleTable.put("RB20", vehicle1);
        vehicleArr[0] = vehicle1;

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setID("RB19");
        vehicle2.setLocation("U");
        vehicle2.setDestination("X");
        vehicle2.setBatteryLevel(90.0);
        vehicle2.setDistance(graph, "U", "X");
        vehicleTable.put("RB19", vehicle2);
        vehicleArr[1] = vehicle2;

        Vehicle vehicle3 = new Vehicle();
        vehicle3.setID("RB18");
        vehicle3.setLocation("R");
        vehicle3.setDestination("DD");
        vehicle3.setBatteryLevel(75.0);
        vehicle3.setDistance(graph, "R", "DD");
        vehicleTable.put("RB18", vehicle3);
        vehicleArr[2] = vehicle3;

        Vehicle vehicle4 = new Vehicle();
        vehicle4.setID("RB17");
        vehicle4.setLocation("BB");
        vehicle4.setDestination("W");
        vehicle4.setBatteryLevel(60.0);
        vehicle4.setDistance(graph, "BB", "W");
        vehicleTable.put("RB17", vehicle4);
        vehicleArr[3] = vehicle4;

        Vehicle vehicle5 = new Vehicle();
        vehicle5.setID("RB16");
        vehicle5.setLocation("R");
        vehicle5.setDestination("Z");
        vehicle5.setBatteryLevel(45.0);
        vehicle5.setDistance(graph, "R", "Z");
        vehicleTable.put("RB16", vehicle5);
        vehicleArr[4] = vehicle5;

        Vehicle vehicle6 = new Vehicle();
        vehicle6.setID("RB15");
        vehicle6.setLocation("Q");
        vehicle6.setDestination("V");
        vehicle6.setBatteryLevel(55.0);
        vehicle6.setDistance(graph, "Q", "V");
        vehicleTable.put("RB15", vehicle6);
        vehicleArr[5] = vehicle6;

        // Displaying all vehicles in the table
        System.out.println("\n||| Displaying the Vehicle Table...");
        vehicleTable.display();

        // Removing a vehicle and updating the vehicle table
        System.out.println("\n\n||| Removing a Vehicle...");
        vehicleTable.remove("RB17");
        vehicle.removeVehicle(vehicleArr, "RB17");

        System.out.println("\n||| Vehicle Table After Removing RB19...");
        vehicleTable.display();

        // Sorting vehicles based on distance to destination
        System.out.println("\n\n||| Sorting Vehicles based on Distance to Destination...\n");

        heap = vehicle.sortDistance(vehicleTable);

        // Finding the nearest vehicle based on sorted data
        vehicle.find_nearest_vehicle(heap);

        // Sorting vehicles based on battery level
        System.out.println("\n||| Sorting Vehicles based on Battery Level...\n");
        Object[] array;

        array = vehicle.sortBatteryLevel(vehicleArr); // Sorting vehicles by battery level
        vehicle.find_vehicle_with_highest_battery(array); // Finding vehicle with highest battery level
    }
}