/*************************************
 * Name - Thisal Dilmeth
 * Curtin ID - 22383055
 * Date Modified - 2024 October
 * Created - 2024 September
 * Source - DSA Practical 8
 *************************************/

public class QuickSort {

    public void quickSort(Object[] arr, int leftIdx, int rightIdx) {
        int pivotIdx, newPivotIdx;

        if(rightIdx > leftIdx) {
            pivotIdx = (leftIdx+rightIdx) / 2;
            newPivotIdx = doPartitioning(arr, leftIdx, rightIdx, pivotIdx);

            quickSort(arr, leftIdx, newPivotIdx-1);
            quickSort(arr,newPivotIdx+1, rightIdx);
        }
    }

    private int doPartitioning(Object[] arr, int leftIdx, int rightIdx, int pivIdx) {
        int currIdx, newPivotIdx;
        Object pivVal, temp;
        
        pivVal = arr[pivIdx];
        arr[pivIdx] = arr[rightIdx];
        arr[rightIdx] = pivVal;

        currIdx = leftIdx;

        for (int i = leftIdx; i < rightIdx; i++) {
            if(arr[i] != null && ((Vehicle)arr[i]).getBatteryLevel() > ((Vehicle)pivVal).getBatteryLevel()) {
                temp = arr[i];
                arr[i] = arr[currIdx];
                arr[currIdx] = temp;
                currIdx++;
            }
        }

        newPivotIdx = currIdx;
        arr[rightIdx] = arr[newPivotIdx];
        arr[newPivotIdx] = pivVal;

        return newPivotIdx;
    }
}