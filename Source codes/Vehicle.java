public class Vehicle {

    //Attributes.
    private String vehicleID; 
    private String currentLocation; 
    private double distance;
    private String destination;
    private double batteryLevel;
    private Heap heap = new Heap();
    

    //Setters.
    public void setID(String nID) {
        vehicleID = nID;
    }

    public void setLocation(String nCurrentLocation) {
        currentLocation = nCurrentLocation;
    }

    public void setDestination(String nDestination) {
        destination = nDestination;
    }

    public void setBatteryLevel(Double nBatteryLevel) {
        batteryLevel = nBatteryLevel;
    }

    public void setDistance(Graph graph, String nLocation1, String nLocation2)  {
        distance = graph.DFS(graph ,nLocation1, nLocation2); //Uses a DFS traversal algorithm to find a path and get the total distance.
    }

    //Getters.
    public String getID() {
        return vehicleID;
    }

    public String getLocation() {
        return currentLocation;
    }

    public String getDestination() {
        return destination;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public double getDistance() {
        return distance;
    }

    //Sorts the vehicle list according to distnace.
    public Heap sortDistance(VehicleHashTable nVehicleTable) {

        //Add the non-null entries to a heap.
        for (int i = 0; i < nVehicleTable.getArraySize(); i++) {
            if(nVehicleTable.getIndexValue(i) != null) {
                heap.add(((Vehicle)nVehicleTable.getIndexValue(i)).getDistance(), ((Vehicle)nVehicleTable.getIndexValue(i)).getID());
            }
        }
        
        //heapsorts the heap.
        heap.heapSortThis();

        //display the heap.
        for (int i = 0; i < heap.getCount(); i++) {
            System.out.println("Vehicle ID - " + heap.getValue(i) + ", Distance - " + heap.getPriority(i));
        }

        return heap;
    }

    //displays the values of the element at the top of the heap.
    public void find_nearest_vehicle(Heap nHeap) {
        System.out.println("\nThe vehicle with the shortest Distance to Travel is: \n");
        System.out.println("Vehicle ID - " + nHeap.getValue(0));
        System.out.println("Distance - " + nHeap.getPriority(0));
    }

    //Sorts the vehicle list according to battery life.
    public Object[] sortBatteryLevel(Object[] vehicleArr) {
        QuickSort sort = new QuickSort();

        int count = 0;
        //get the number of non-null entries in the array.
        for (int i = 0; i < vehicleArr.length; i++) {
            if(vehicleArr[i] != null) {
                count++;
            }
        }

        //put all the non-null entries into another array.
        Object[] array = new Object[count];
        int index = 0;
        for (int i = 0; i < vehicleArr.length; i++) {
            if(vehicleArr[i] != null) {
                array[index++] = vehicleArr[i];
            }
        }

        //quick sort that array
        sort.quickSort(array, 0, array.length - 1);

        //display that array
        for (int i = 0; i < array.length; i++) {
                System.out.println("Vehicle ID - " + ((Vehicle)array[i]).getID() + ", Battery Level - " + ((Vehicle)array[i]).getBatteryLevel());
        }

        return array;
    }

    //display the value of entry at the top of the array.
    public void find_vehicle_with_highest_battery(Object[] arr) {
        System.out.println("\nThe vehicle with the highest Battery Level is: \n");
        System.out.println("Vehicle ID - " + ((Vehicle)arr[0]).getID());
        System.out.println("Battery Level - " + ((Vehicle)arr[0]).getBatteryLevel() + "\n");
    }

    //removes a given vehicle from the vehicle array.
    public Object[] removeVehicle(Object[] array, String nKey) {
        for (int i = 0; i < array.length; i++) {
            if(array[i] != null && ((Vehicle)array[i]).getID().equals(nKey)) {
                array[i] = null;
            }
        }

        return array;
    }
}

