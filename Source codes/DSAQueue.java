/*************************************
 * Name - Thisal Dilmeth
 * Curtin ID - 22383055
 * Date Modified - 2024 October
 * Created - 2024 August
 * Source - DSA Practical 3
 *************************************/

public class DSAQueue {
    private final Object[] queue;
    private int count, front = -1, back = -1;
    private final int DEFAULT_CAPACITY = 100;

    public DSAQueue() {
        queue = new Object[DEFAULT_CAPACITY]; // Declares the array size.
        count = 0;
    }

    // Accessor for count.
    public int getCount() { return count; }

    // Setter for count.
    public void setCount(int count){
        this.count = count;
    }

    // Accessor for front.
    public int getFront() { return front; }

    // Setter for front.
    public void setFront(int front){
        this.front = front;
    }

    // Accessor for back.
    public int getBack() { return back; }

    // Setter for back.
    public void setBack(int back){
        this.back = back;
    }

    // Check whether the queue is empty.
    public boolean isEmpty() {
        return front == -1 && back == -1;
    }

    // Checks whether the queue is full.
    public boolean isFull() {
        return back == queue.length - 1;
    }

    // Peeks the first element in the queue.
    public Object peek() {
        if(isEmpty()){
            throw new RuntimeException("The queue is empty. Nothing to peek");
        }
        return queue[front];
    }

    // Enqueue a value into the queue.
    public void enqueue(Object value) {
        if(isFull()) {
            throw new RuntimeException("The queue is full.");
        } 
        else if(isEmpty()) {
            front = 0;
            back = 0;
        } 
        else {
            back++;
        }
        queue[back] = value;
        count++;
    }

    // Dequeues a value from the queue.
    public Object dequeue() {
        Object temp = null;

        if(isEmpty()) {
            throw new RuntimeException("The queue is empty. Nothing to dequeue.");
        } 
        else if(front == back) {
            queue[front] = null;
            front = -1;
            back = -1;
        } 
        else {
            temp = queue[front];
            queue[front] = null;  // Remove front element
            front++;
        }
        count--;

        return temp;
    }

    // Displays the queue.
    public void display() {
        if(isEmpty()) {
            System.out.println("The queue is empty. Nothing to display.");
        } 
        else {
            for(int i = front; i <= back; i++) {
                System.out.print(queue[i] + " ");
            }
        }
    }
}
