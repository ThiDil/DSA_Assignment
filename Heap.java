/*************************************
 * Name - Thisal Dilmeth
 * Curtin ID - 22383055
 * Date Modified - 2024 October
 * Created - 2024 September
 * Source - DSA Practical 8
 *************************************/

public class Heap {
    private DSAHeapEntry[] heapArr;
    private int count;
    private int SIZE = 100;

    public Heap() {
        heapArr = new DSAHeapEntry[SIZE];
        count = 0;
    }

    public Heap(int arrSize) {
        System.out.println(arrSize);
        heapArr = new DSAHeapEntry[arrSize];
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public double getPriority(int i) {
        return heapArr[i].getPriority();
    }

    public Object getValue(int i) {
        return heapArr[i].getValue();
    }

    public void add(double priority, Object value) {

        if(count == 0) {
            DSAHeapEntry newHeap = new DSAHeapEntry(priority, value);
            heapArr[count] = newHeap;
            count++;
        }

        else {
            DSAHeapEntry newHeap = new DSAHeapEntry(priority, value);
            heapArr[count] = newHeap;
            trickleUp(heapArr, count);
            count++;
        }
    }

    public DSAHeapEntry remove() {
        DSAHeapEntry deleteNode = heapArr[0];
        heapArr[0] = heapArr[count-1];
        heapArr[count - 1] = null;
        trickleDown(heapArr, count, 0);
        count--;
        return deleteNode;
    }

    public void display() {
        for(int i = 0; i < count; i++) {
            System.out.println(i + " -> [ " + heapArr[i].getPriority() + " ]--[" + heapArr[i].getValue() + "]");
        }
    }

    public void heapifyThis() {
        heapify(heapArr, count);
    }

    public void heapify(Object[] array, int num) {
        for (int i = (num / 2) - 1; i >= 0; i--) {
            trickleDown(array, num, i);
        }
    }

    public void heapSortThis() {
        heapSort(heapArr, count);
    }

    public Object[] heapSort(Object[] array, int elements) {
        heapify(array, elements);

        for (int i = elements - 1; i > 0; i--) {
            swap(array, 0, i);
            trickleDown(array, i, 0);
        }
        return array;
    }

    private void trickleUp(DSAHeapEntry[] array, int currIdx) {
        DSAHeapEntry temp;
        int parentIdx = (currIdx - 1)/2;

        if(currIdx > 0) {
            if(array[currIdx].getPriority() > array[parentIdx].getPriority()) {
                temp = heapArr[parentIdx];
                heapArr[parentIdx] = heapArr[currIdx];
                heapArr[currIdx] = temp;
                trickleUp(array, parentIdx);
            }
        }
    }

    private void trickleDown(Object[] array, int items, int currIdx) {
        int lChildIdx, rChildIdx, largeIdx;
    
        lChildIdx = currIdx * 2 + 1;
        rChildIdx = lChildIdx + 1;   
    
        if (lChildIdx < items && array[lChildIdx] != null) {
            largeIdx = lChildIdx;
    
            if (rChildIdx < items && array[rChildIdx] != null && ((Heap.DSAHeapEntry) array[lChildIdx]).getPriority() < ((Heap.DSAHeapEntry) array[rChildIdx]).getPriority()) {
                largeIdx = rChildIdx;
            }
    
            if (((Heap.DSAHeapEntry) array[largeIdx]).getPriority() > ((Heap.DSAHeapEntry) array[currIdx]).getPriority()) {
                swap(array, largeIdx, currIdx);
                trickleDown(array, items, largeIdx);
            }
        }
    }

    private void swap(Object[] heapArray, int idx1, int idx2) {
        Object temp = heapArray[idx1];
        heapArray[idx1] = heapArray[idx2];
        heapArray[idx2] = temp;
    }

    class DSAHeapEntry {

        private double priority;
        private Object value;

        public DSAHeapEntry(double iPriority, Object iValue) {
            priority = iPriority;
            value = iValue;
        }

        public double getPriority() {
            return priority;
        }

        public void setPriority(double tPriority) {
            priority = tPriority;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object tValue) {
            value = tValue;
        }

    }
}