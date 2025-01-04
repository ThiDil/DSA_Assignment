class Edge {
    
    private GraphNode destination; //Stores destination as a GraphNode.
    private double distance; //Stores the distance as a double.

    //constructor
    public Edge(GraphNode nDestination, double nDistance) {
        destination = nDestination;
        distance = nDistance;
    }

    //Accessor for Destination.
    public GraphNode getDestination() {
        return destination;
    }

    //Accessor for Distance.
    public double getDistance() {
        return distance;
    }

    //Mutator for Destination.
    public void setDestination(GraphNode nDestination) {
        destination = nDestination;
    }

    //Mutator for Distance.
    public void setDistance(double nDistance) {
        distance = nDistance;
    }
}
