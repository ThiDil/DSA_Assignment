/*************************************
 * Name: 22383055 - Thisal Dilmeth, 22397027 - Neerada Kulatillake
 * Date Modified - 2024 October
 * Created - 2024 September
 * Source - DSA Practical 6
 *************************************/

public class GraphNode {
    
    private String location;
    private boolean visited;
    private LinkedList links;

    public GraphNode(String nLocation) {
        location = nLocation;
        links = new LinkedList();
        visited = false;
    }

    public String getLocation() {
        return location;
    }

    public LinkedList getAdjacent() {
        return links;
    }

    public void addEdge(GraphNode nDestination, double nDistance) {
        Edge edge = new Edge(nDestination, nDistance);

        if(links.isEmpty()) {
            links.insertFirst(edge);
        }
        else {
            links.insertLast(edge);
        }
    }

    public void deleteEdge(GraphNode nVertex) {
        LinkedList.DSAListNode currNd = links.head;
        LinkedList.DSAListNode findNd = links.head;

        while (currNd.getNext() != null) {
            System.out.println((((Edge)findNd.getValue()).getDestination()).getLocation());
            GraphNode gNode = ((Edge)currNd.getValue()).getDestination();

            if(gNode.getLocation().equals(nVertex.getLocation())) {
                findNd = currNd;
            }

            currNd = currNd.getNext();
        }

        if(links.isEmpty()) {
            System.out.println("There is no edge.");
        }
        else {
            
            links.deleteNode(findNd);
        }
    }

    public void setVisited(boolean isVisited) {
        visited = isVisited;
    }

    public void clearVisited() {
        visited = false;
    }

    public boolean getVisited() {
        return visited;
    }
}
