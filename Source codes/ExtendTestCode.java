public class ExtendTestCode {
    
    // Initialize Graph, Heap, VehicleHashTable, and Vehicle array for the simulation
    private static Graph graph = new Graph();
    private static Heap heap = new Heap();
    private static VehicleHashTable vehicleTable = new VehicleHashTable(10); // Increased size to accommodate more vehicles
    private static Object[] vehicleArr = new Object[8];
    private static Vehicle vehicle = new Vehicle();

    public static void main(String[] args) {
        
        System.out.println("\n||| Adding locations...");
        // Add locations to the graph as vertices
        String[] locations = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};
        for (String location : locations) {
            graph.addVertex(location);
        }

        System.out.println("\n||| Connecting locations...");
        // Define edges between locations with distances (creating a road network)
        graph.addEdge("A", "B", 210);
        graph.addEdge("A", "C", 100);
        graph.addEdge("C", "E", 180);
        graph.addEdge("B", "D", 240);
        graph.addEdge("E", "F", 300);
        graph.addEdge("F", "G", 90);
        graph.addEdge("H", "I", 120);
        graph.addEdge("I", "J", 200);
        graph.addEdge("J", "K", 150);
        graph.addEdge("L", "M", 50);
        graph.addEdge("M", "N", 220);
        graph.addEdge("N", "O", 130);
        graph.addEdge("O", "A", 140);
        graph.addEdge("G", "H", 170);
        graph.addEdge("K", "L", 100);

        System.out.println("\n||| Displaying the current Road Network.\n");
        graph.displayList(); // Display graph as adjacency list

        System.out.println("\n||| Displaying the current Road Network as a Adjacency Matrix.\n");
        graph.displayMatrix(); // Display graph as adjacency matrix

        System.out.println("\n||| Checking paths...");
        // Use BFS to check connectivity between different locations
        System.out.print("A to D - ");
        System.out.println(graph.BFS("A", "D")); // Expected: true 

        System.out.print("E to O - ");
        System.out.println(graph.BFS("E", "O")); // Expected: true 

        System.out.print("L to P - ");
        System.out.println(graph.BFS("L", "P")); // Expected: false 

        System.out.println("\n||| Adding Vehicles...");
        // Add vehicles with IDs, locations, destinations, battery levels, and calculated distances

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setID("AMR1");
        vehicle1.setLocation("A");
        vehicle1.setDestination("K");
        vehicle1.setBatteryLevel(100.0);
        vehicle1.setDistance(graph, "A", "K");
        vehicleTable.put("AMR1", vehicle1);
        vehicleArr[0] = vehicle1;

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setID("AMR2");
        vehicle2.setLocation("C");
        vehicle2.setDestination("J");
        vehicle2.setBatteryLevel(90.0);
        vehicle2.setDistance(graph, "C", "J");
        vehicleTable.put("AMR2", vehicle2);
        vehicleArr[1] = vehicle2;

        Vehicle vehicle3 = new Vehicle();
        vehicle3.setID("AMR3");
        vehicle3.setLocation("E");
        vehicle3.setDestination("G");
        vehicle3.setBatteryLevel(75.0);
        vehicle3.setDistance(graph, "E", "G");
        vehicleTable.put("AMR3", vehicle3);
        vehicleArr[2] = vehicle3;

        Vehicle vehicle4 = new Vehicle();
        vehicle4.setID("AMR4");
        vehicle4.setLocation("H");
        vehicle4.setDestination("L");
        vehicle4.setBatteryLevel(60.0);
        vehicle4.setDistance(graph, "H", "L");
        vehicleTable.put("AMR4", vehicle4);
        vehicleArr[3] = vehicle4;

        Vehicle vehicle5 = new Vehicle();
        vehicle5.setID("AMR5");
        vehicle5.setLocation("F");
        vehicle5.setDestination("O");
        vehicle5.setBatteryLevel(45.0);
        vehicle5.setDistance(graph, "F", "O");
        vehicleTable.put("AMR5", vehicle5);
        vehicleArr[4] = vehicle5;

        Vehicle vehicle6 = new Vehicle();
        vehicle6.setID("AMR6");
        vehicle6.setLocation("J");
        vehicle6.setDestination("A");
        vehicle6.setBatteryLevel(55.0);
        vehicle6.setDistance(graph, "J", "A");
        vehicleTable.put("AMR6", vehicle6);
        vehicleArr[5] = vehicle6;

        Vehicle vehicle7 = new Vehicle();
        vehicle7.setID("AMR7");
        vehicle7.setLocation("M");
        vehicle7.setDestination("C");
        vehicle7.setBatteryLevel(65.0);
        vehicle7.setDistance(graph, "M", "C");
        vehicleTable.put("AMR7", vehicle7);
        vehicleArr[6] = vehicle7;

        Vehicle vehicle8 = new Vehicle();
        vehicle8.setID("AMR8");
        vehicle8.setLocation("N");
        vehicle8.setDestination("F");
        vehicle8.setBatteryLevel(35.0);
        vehicle8.setDistance(graph, "N", "F");
        vehicleTable.put("AMR8", vehicle8);
        vehicleArr[7] = vehicle8;

        System.out.println("\n||| Displaying the Vehicle Table...");
        // Display all vehicles in the hash table
        vehicleTable.display();

        System.out.println("\n\n||| Removing a Vehicle...");
        // Remove vehicle AMR5 from both the vehicle array and hash table
        vehicle.removeVehicle(vehicleArr, "AMR5");
        vehicleTable.remove("AMR5");

        System.out.println("\n||| Vehicle Table After Removing AMR5...");
        // Display vehicle table after removal
        vehicleTable.display();

        System.out.println("\n\n||| Sorting Vehicles based on Distance to Destination...\n");
        // Sort vehicles based on distance to destination and display the nearest vehicle
        Vehicle vehicle = new Vehicle();

        heap = vehicle.sortDistance(vehicleTable);

        vehicle.find_nearest_vehicle(heap);

        System.out.println("\n||| Sorting Vehicles based on Battery Level...\n");
        // Sort vehicles based on battery level and display the vehicle with the highest battery
        Object[] array;

        array = vehicle.sortBatteryLevel(vehicleArr);
        vehicle.find_vehicle_with_highest_battery(array);
    }
}
