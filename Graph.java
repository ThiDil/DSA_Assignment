/*************************************
 * Name: 22383055 - Thisal Dilmeth, 22397027 - Neerada Kulatillake
 * Date Modified - 2024 October
 * Created - 2024 September
 * Source - DSA Practical 6
 *************************************/

public class Graph {

    private LinkedList vertices;
    private int edgeCount;
    private int[][] matrix; 
    private String[] lookUp;
    
    public Graph() {
        vertices = new LinkedList();
        edgeCount = 0;
    }

    public LinkedList getVerticesList() {
        return vertices;
    }

    public void addVertex(String nLocation) {
        GraphNode newVertex = new GraphNode(nLocation);

        if(vertices.isEmpty()) {
            vertices.insertFirst(newVertex);
            System.out.println("Successfully added " + nLocation);
        }
        else {

            if(hasVertex(nLocation)) {
                System.out.println("This vertex already exists.");
            }

            else {
                GraphNode newVertex2 = new GraphNode(nLocation);
                vertices.insertLast(newVertex2);
                System.out.println("Successfully added " + nLocation);
            }
        }
    }

    public void deleteVertex(String nLocation) {
        LinkedList.DSAListNode currNd = vertices.head;
        GraphNode graphNode = (GraphNode)currNd.getValue();

        while (currNd.getNext() != null) {
            graphNode = (GraphNode)currNd.getValue();
            if(graphNode.getLocation() == nLocation) {
                vertices.deleteNode(currNd);
            }
            currNd = currNd.getNext();
        }

        LinkedList.DSAListNode currNd2 = vertices.head;
        
        while(currNd2 != null) {

            GraphNode graphNd = (GraphNode)currNd2.getValue();
            LinkedList list = graphNd.getAdjacent();
            LinkedList.DSAListNode adjList = list.head;
            Edge edge;
            
            while (adjList != null) {
                edge = (Edge)adjList.getValue();
                GraphNode adjNode = edge.getDestination();

                if(((GraphNode)edge.getDestination()).getLocation().equals(nLocation)) {
                    graphNd.deleteEdge(adjNode);
                }

                adjList = adjList.getNext();
            }
            currNd2 = currNd2.getNext();
        }
    }

    public void addEdge(String nLocation1, String nLocation2, double nDistance) {
        GraphNode vertex1 = getVertex(nLocation1);
        GraphNode vertex2 = getVertex(nLocation2);

        if(vertex1 != null && vertex2 != null) {
            vertex1.addEdge(vertex2, nDistance);
            vertex2.addEdge(vertex1, nDistance);
            edgeCount++;
        }
    }

    public void deleteEdge(String nLocation1, String nLocation2) {
        GraphNode vertex1 = getVertex(nLocation1);
        GraphNode vertex2 = getVertex(nLocation2);

        if(vertex1 != null && vertex2 != null) {
            vertex1.deleteEdge(vertex2);
            vertex2.deleteEdge(vertex1);
            edgeCount--;
        }
    }

    public boolean hasVertex(String nLocation) {
        
        boolean found = false;

        LinkedList.DSAListNode currNd = vertices.head;
            
        while(currNd != null) {
            GraphNode graphNd = (GraphNode)currNd.getValue();
            if(graphNd.getLocation().equals(nLocation)) {
                found = true;
            }    
            currNd = currNd.getNext();
        }

        return found;
    }

    public boolean hasVertexOther(String nLocation, LinkedList list) {
        
        boolean found = false;

        LinkedList.DSAListNode currNd = list.head;
            
        while(currNd != null) {
            GraphNode graphNd = (GraphNode)currNd.getValue();
            if(graphNd.getLocation().equals(nLocation)) {
                found = true;
            }    
            currNd = currNd.getNext();
        }

        return found;
    }

    public int getVertexCount() {
        return vertices.getcount();
    }


    public int getEdgeCount() {
        return edgeCount;
    }

    public GraphNode getVertex(String nLocation) {
        
        GraphNode findNd = null;
        LinkedList.DSAListNode currNd = vertices.head;
        
        while(currNd != null) {
            GraphNode graphNd = (GraphNode)currNd.getValue();
            
            if(graphNd.getLocation().equals(nLocation)) {
                findNd = graphNd;
            }
            currNd = currNd.getNext();
        }

        return findNd;
    }

    public GraphNode getVertexOther(String nLocation, LinkedList list) {
        
        GraphNode findNd = null;
        LinkedList.DSAListNode currNd = list.head;
        
        while(currNd != null) {
            GraphNode graphNd = (GraphNode)currNd.getValue();
            
            if(graphNd.getLocation().equals(nLocation)) {
                findNd = graphNd;
            }
            currNd = currNd.getNext();
        }

        return findNd;
    }

    public LinkedList getAdjacent(String nLocation) {

        GraphNode checkNd = getVertex(nLocation);   
        LinkedList checkNdList;  

        if(checkNd != null) {
            checkNdList = checkNd.getAdjacent();
        }
        else {
            checkNdList = null;
        }

        return checkNdList;
    }

    public boolean isAdjacent(String nLocation1, String nLocation2) {

        GraphNode node1 = getVertex(nLocation1); 
        boolean isAdjacent = false;

        if(node1 != null) {
            LinkedList node1List = (LinkedList)node1.getAdjacent();
            LinkedList.DSAListNode currNd = node1List.head;
        
            while(currNd != null) {
                Edge edge = (Edge)currNd.getValue();
                GraphNode graphNd = (GraphNode)edge.getDestination();

                if(graphNd.getLocation().equals(nLocation2)) {
                    isAdjacent = true;
                }

                currNd = currNd.getNext();
            }
        }

        else {
            System.out.println("Node 1 is null.");
        }

        return isAdjacent;
    }

    public void displayList() {

        LinkedList.DSAListNode currNd = vertices.head;
        
        while(currNd != null) {
            GraphNode graphNd = (GraphNode)currNd.getValue();
            System.out.print(graphNd.getLocation() + " --> ");
            
            LinkedList list = graphNd.getAdjacent();
            LinkedList.DSAListNode adjList = list.head;
            
            while (adjList != null) {

                Edge adjNd = (Edge)adjList.getValue();
                GraphNode graphNode = (GraphNode)adjNd.getDestination();

                System.out.print(graphNode.getLocation() + "( " + adjNd.getDistance() + " )" + " - ");

                adjList = adjList.getNext();
            }

            System.out.println("\n");

            currNd = currNd.getNext();
        }
    }


    public void displayMatrix() {
        int n = vertices.getcount();
        matrix = new int[n][n];
        lookUp = new String[n];

        LinkedList.DSAListNode currNd = vertices.head;

        for (int j = 0; j < lookUp.length; j++) {
            GraphNode graphNd = (GraphNode)currNd.getValue();
            
            String location = graphNd.getLocation();
            lookUp[j] = location;
            
            currNd = currNd.getNext();
        }

        System.out.println("\n\n||| Lookup Table");
        System.out.println("``````````````````");
        
        System.out.println(" _______");
        for (int j = 0; j < lookUp.length; j++) {
            System.out.println("| " + j + " | " + lookUp[j] + " |");
        }
        System.out.println(" ```````");

        System.out.println("\n||| Adjacency matrix.");
        System.out.println("```````````````````````\n");


        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i < matrix.length; i++) {  
                if(isAdjacent(lookUp[j], lookUp[i])) {
                    matrix[j][i] = 1;
                }
                else {
                    matrix[j][i] = 0;
                }
            }
        }

        System.out.print("    |");
        for (int j = 0; j < lookUp.length; j++) {
            System.out.print("  " + lookUp[j] + "  |");
        }
        System.out.print("\n");


        for (int j = 0; j < matrix.length; j++) {
            System.out.print(" " + lookUp[j] + "  |  ");
            System.out.print(matrix[j][0] + "  |");

            for (int i = 1; i < matrix.length; i++) {
                System.out.print("  " + matrix[j][i] + "  |");
            }

            System.out.print("\n");
        }

        System.out.print("\n");
    }

    public void displayAdjList(String nLocation) {

        GraphNode adjVertex = getVertex(nLocation);
        LinkedList list = adjVertex.getAdjacent();
        LinkedList.DSAListNode adjList = list.head;

        while (adjList != null) {

            Edge adjNd = (Edge)adjList.getValue();
            GraphNode graphNode = (GraphNode)adjNd.getDestination();
            
            System.out.print(graphNode.getLocation() + "( " + adjNd.getDistance() + " )" + " - ");
            adjList = adjList.next;
        }
    }

    public double DFS(Graph graph, String nLocation1, String nLocation2) {
        
        DSAStack stack = new DSAStack();
        LinkedList.DSAListNode currNd = graph.getVerticesList().head;
        GraphNode graphNd;
        double totalDistance = 0;
        boolean found = false;
            
        while(currNd != null) {
            graphNd = (GraphNode)currNd.getValue();
            graphNd.clearVisited();   
            currNd = currNd.getNext();
        }
        
        if(graph.hasVertex(nLocation1)) {
            graphNd = getVertex(nLocation1);
            stack.push(graphNd);
            graphNd.setVisited(true);
            
            while(!stack.isEmpty() && !found) {          
                graphNd = (GraphNode)stack.pop();
                if (graphNd.getLocation().equals(nLocation2)) {
                    found = true;
                }

                LinkedList adjList = graphNd.getAdjacent();
                LinkedList.DSAListNode listNd = adjList.head;
                
                while(listNd != null) {
                    Edge edge = (Edge)listNd.getValue();
                    GraphNode adjNd = edge.getDestination();
                    
                    if(!adjNd.getVisited()) {
                        stack.push(adjNd);
                        adjNd.setVisited(true);
                        totalDistance += edge.getDistance();

                        if (adjNd.getLocation().equals(nLocation2)) {
                            found = true;
                        }
                    }
                    listNd = listNd.getNext();  
                }
            }
        }

        else {
            System.out.println("This vertex does not exist in the graph.");
        }

        return totalDistance;
    }


    public boolean BFS(String nLocation1, String nLocation2) {

        boolean pathExists = false;

        DSAQueue visitedQueue = new DSAQueue();
        DSAQueue queue = new DSAQueue();

        LinkedList.DSAListNode currNd = vertices.head;
        GraphNode graphNd;
            
        while(currNd != null) {
            graphNd = (GraphNode)currNd.getValue();
            graphNd.clearVisited();   
            currNd = currNd.getNext();
        }

        if(hasVertex(nLocation1)) {

            graphNd = getVertex(nLocation1);
    
            queue.enqueue(graphNd.getLocation());
            visitedQueue.enqueue(graphNd.getLocation());
    
            graphNd.setVisited(true);
            
            while (!queue.isEmpty()) {
                
                graphNd = getVertex(String.valueOf(queue.peek()));
                queue.dequeue();
    
                LinkedList adjList = (LinkedList)graphNd.getAdjacent();
                LinkedList.DSAListNode listNd = adjList.head;
                
                while (listNd != null) {
    
                    Edge edge = (Edge)listNd.getValue();
                    GraphNode adjNd = (GraphNode)edge.getDestination();
                    
                    if(!adjNd.getVisited()) {
                        queue.enqueue(adjNd.getLocation());
                        adjNd.setVisited(true);
                        visitedQueue.enqueue(adjNd.getLocation());
                        if(adjNd == getVertex(nLocation2)) {
                            pathExists = true;
                        }
                    }
                    listNd = listNd.getNext();
                }
            }
        }

        else {
            System.err.println("This vertex does not exist in the graph.");
        }

        return pathExists;
    }
}