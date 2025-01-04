/*************************************
 * Name - Thisal Dilmeth
 * Curtin ID - 22383055
 * Date Modified - 2024 October
 * Created - 2024 August
 * Source - DSA Practical 4
 *************************************/

public class LinkedList {
    
    DSAListNode head; //Create a head object.
    DSAListNode tail; //Create a tail object.
    DSAListNode currNd; //Temporary object.
    DSAListNode prevNd; //Temporary object.
    private int count = 0; //keep track of the number of nodes in the list.

    //Accessor for count.
    public int getcount() {
        return count;
    }

    public Object getValue() {
        Object nodeValue = null;

        if (currNd != null) {
            nodeValue = currNd.getValue();
        }

        return nodeValue; // or throw an exception if preferred
    }

    // Returns the next node in the list.
    public Object getNext() {
        Object nodeValue = null;
        
        if (currNd != null) {
            currNd = currNd.getNext();
            nodeValue = currNd.getValue();
            // returns the next node, or null if at the end
        }
        return nodeValue; // or throw an exception if preferred
    }

    //Insert a value to the front of the list.
    public void insertFirst(Object nValue) {
        DSAListNode newNd = new DSAListNode(nValue);

        //If the list is empty set the head and tail to the new Node.
        if(isEmpty()) {
            head = newNd;
            tail = newNd;
        }

        //If the list is not empty set new Nodes next to head and set the previous of head to new Node. After that set head to new Node.
        else {
            newNd.setNext(head);
            head.setPrev(newNd);
            head = newNd;
        }
        count++;
    }

    public void insertLast(Object nValue) {
        DSAListNode newNd = new DSAListNode(nValue);

        //If the list is empty set the head and tail to the new Node.
        if(isEmpty()) {
            head = newNd;
            tail = newNd;
        }

        //If the list is not empty go to the end of the list and set the cuurent nodes next value to new Node and new nodes previous value to current node.
        else {
            currNd = tail;// Set the current node to the tail
            tail.setNext(newNd); // Link the new node to the end of the list
            newNd.setPrev(currNd);
            newNd.setNext(null); // Set the new node's previous to the current tail
            tail = newNd; // Update the tail to be the new node
        }

        count++;
    }

    //checks is the list is empty.
    public boolean isEmpty() {
        boolean empty = false;
        if (head == null) {
            empty = true;
        }
        return empty;
    }

    //Returns the value of the first node in the list.
    public Object peekFirst() {
        Object nodeValue = null;

        if(!isEmpty()) {
            nodeValue = head.getValue();
        }
        
        return nodeValue;
    }

    //Returns the value of the last ode in the list.
    public Object peekLast() {
        Object nodeValue = null;

        if(isEmpty()) {
            throw new RuntimeException("The list is empty");
        }

        else {
            currNd = head;
            //Loop through the list to the end.
            while(currNd.getNext() != null) {
                currNd = currNd.getNext();
            }
        }
        nodeValue = currNd.getValue();
        
        return nodeValue;
    }

    //Removes the first node in the list.
    public Object removeFirst() {
        Object nodeValue = null;

        if(isEmpty()) {
            throw new RuntimeException("The list is empty.");
        }
        else if(head.getNext() == null) {
            nodeValue = head.getValue();
            head = null;
            tail = null;
        }
        else {
            nodeValue = head.getValue();
            head = head.getNext();
            head.setPrev(prevNd);
        }

        count--;
        return nodeValue;
    }

    //Removes the last node in the list. 
    public Object removeLast() {
        Object nodeValue = null;

        if(isEmpty()) {
            throw new RuntimeException("The list is empty.");
        }

        else if(head.getNext() == null) {
            nodeValue = head.getValue();
            head = null;
            tail = null;
        }
        else {
            prevNd = null;
            currNd = head;

            while(currNd.getNext() != null) {
                prevNd = currNd;
                currNd = currNd.getNext();
            }
            prevNd.setNext(null);
            nodeValue = currNd.getValue();
        }
        
        count--;
        return nodeValue;
    }

    // Deletes a node containing the specified value.
    public void deleteNode(Object deleteVal) {
        DSAListNode findNd = head;
        // If the list is empty, throw an exception
        if (isEmpty()) {
            System.out.println("The list is empty.");
        }

        else {
            currNd = head;
            
            // Traverse the list to find the node to delete
            do {
                if(currNd.equals((DSAListNode)deleteVal)) {
                    findNd = currNd;
                }
                currNd = currNd.getNext();
            } while (currNd != null);
    
            currNd = findNd;
            // If the node was not found
            if (currNd == null) {
                System.out.println("Value not found in the list.");
            }

            else {

                // If the node to delete is the head
                if (currNd == head) {
                    removeFirst();  // Use removeFirst to handle head deletion
                }
                // If the node to delete is the tail
                else if (currNd == tail) {
                    removeLast();   // Use removeLast to handle tail deletion
                }
                // If the node to delete is in the middle
                else {
                    DSAListNode nextNode = currNd.getNext();
                    DSAListNode prevNode = currNd.getPrev();
                    prevNode.setNext(nextNode);
                    nextNode.setPrev(prevNode);
                    count--;
                }
            }
        }
    }

    //displays the list.
    public void displayVertices() {
        Edge edge;
        currNd = head;

        if(isEmpty()){
            throw  new RuntimeException("Nothing to display.");
        }
        else {
            while(currNd.getNext() != null) {
                edge = (Edge)currNd.getValue();
                System.out.print(edge.getDestination() + "(" + edge.getDistance() + ")" + "---");
                currNd = currNd.getNext();
            }
            edge = (Edge)currNd.getValue();
            System.out.print(edge.getDestination() + "(" + edge.getDistance() + ")" + "---");
        }

    }

    //Private node class.
    class DSAListNode {
    
        public Object value;
        DSAListNode next;
        DSAListNode prev;
    
        //Defalut constructor for DSAListNode.
        public DSAListNode(Object nValue) {
            value = nValue;
            next = null;
            prev = null;
        }
    
        //Accessor for value.
        public Object getValue() {
            return value;
        }
    
        //Mutator for value.
        public void setValue(Object nValue) {
            value = nValue;
        }
    
        //Accessor for next.
        public DSAListNode getNext() {
            return (DSAListNode) next;
        }
    
        //Mutator for next.
        public void setNext(DSAListNode newNext) {
            next = newNext;
        }  

        //Accessor for prev.
        public DSAListNode getPrev() {
            return (DSAListNode) prev;
        }
    
        //Mutator for prev.
        public void setPrev(DSAListNode newPrev) {
            prev = newPrev;
        }  
    }
}
