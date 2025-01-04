/*************************************
 * Name - Thisal Dilmeth
 * Curtin ID - 22383055
 * Date Modified - 2024 October
 * Created - 2024 August
 * Source - DSA Practical 3
 *************************************/

public class DSAStack
{
    private final Object[] stackArray;
    private Object topVal;
    private int count;
    private final int DEFAULT_CAPACITY = 100;

    //default constructor.
    public DSAStack() {
        stackArray = new Object[DEFAULT_CAPACITY]; //declares the array size.
        count = 0;
    }

    //Accessor for count.
    public int getCount() { return count; }

    //Accessor for DEFALUT_CAPACITY.
    public int getDEFAULT_CAPACITY() { return DEFAULT_CAPACITY; }

    //Check whether the stack is empty.
    public boolean isEmpty() {
        boolean empty = false;
        if(count == 0){
            empty = true;
        }
        return empty; //return true is the stack is empty. Else returns false.
    }
    
    //Check whether the stack is full.
    public boolean isFull() {
        boolean full = false;
        if(count == stackArray.length){
            full = true;
        }
        return full;
    }

    //Push a value into the stack.
    public void push(Object value) {
        
        if(isFull()) {
            throw new RuntimeException("Stack overflow: the stack is full.");
        }
        else {
            stackArray[count] = value;
            count++;
        }
    }

    //Pops the top element in the stack.
    public Object pop() {
        topVal = top();
        count--;
        return topVal;
    }

    //Displays the top element in the stack.
    public Object top() {

        if(isEmpty()){
            throw new RuntimeException("Stack underflow: the stack is empty.");
        }
        else {
            topVal = stackArray[count - 1];
            return topVal;
        }
    }

    //Displays the stack.
    public void display() {
        if(isEmpty()) {
            System.out.println("The stack is empty. Nothing to display.");
        }
        else {
            for(int i = 0; i <= stackArray.length - 1; i++) {
                if(stackArray[i] != null) {
                    System.out.print(stackArray[i] + " ");
                }
            }
        }
    }

}