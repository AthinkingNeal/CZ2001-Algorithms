package sorting;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.compare;

public class mergesort {
    private static int datasize;
    // return an integer array which contains n randomly generated numbers, where n = datasize
    public static int[] generateData(int datasize){
        Random rand = new Random();
        // dataset will be the returned array
        int[] dataset = new int[datasize];
        int temp;
        for(int i = 0; i < datasize; i++){
            temp = rand.nextInt(1000000);
            dataset[i] = temp;
        }
        return dataset;
    }
//    Unit test for data generator. Test passed.

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Please enter data size: ");
//        datasize  = sc.nextInt();
//        int [] dataset = generateData(datasize);
//        for (int i = 0; i < datasize; i++){
//            System.out.println(dataset[i]);
//        }
//    }



    public static void mergeSortUnmodified(int [] array,int first, int last){
        int mid = (first + last) / 2;
        if(last - first <= 0)
            return;
        else if (last - first >= 1){  // we can also use (last - first > 1) as the condition here, why?
            // Sort first and second halves
            mergeSortUnmodified(array, first, mid);
            mergeSortUnmodified(array, mid + 1, last);

        }
        // Merge the sorted halves
        mergeUnmodified(array,first,mid,last);
    }

    // Merge two subarrays of array[]
    // First subarray is array[first] -- array[mid]
    // Second subarray is array[mid + 1] -- array[last]

    // refer to the Mergesort Appendix in CZ2001 NTULearn for the following merge algorithm
    public static void mergeUnmodified(int [] array, int first, int mid, int last){
        // a indicates the index of the last element of the sorted list
        // b indicates the index of the first element of 2nd half of the list
        int a = first, b = mid + 1, i, temp;

        if(last - first <= 0) return;

        while (a <= mid && b <= last) {
            int cmp = compare(array[a],array[b]);
            if (cmp > 0) { // array[a] > array[b]
                temp = array[b++]; // store the key value of the 1st element of the 2nd half
                for (i = ++mid; i > a; i--) { // shift the 1st half of the array to the right for one slot
                    array[i] = array[i - 1];
                }
                array[a++] = temp; // move the 1st element of 2nd half to the end of the merged list
            }
            else if(cmp < 0) // array[a] < array[b]
                a++; //  1st element of 1st half joins the end of the merged list
            else{   // array[a] = array[b]
                if(a == mid && b == last){ // both of them are the last element
                    break;
                }
                temp = array[b++];
                a++;
                for (i = ++mid; i > a; i--){
                    array[i] = array[i-1];
                }
                array[a++] = temp;
            }
        }
    }

    //    Unit test for data generator + mergeSortUnmodified. Test passed.
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Please enter data size: ");
//        datasize  = sc.nextInt();
//        int [] dataset = generateData(datasize);
//        for (int i = 0; i < datasize; i++){
//            System.out.println(dataset[i]);
//        }
//        System.out.print("\n");
//        mergeSortUnmodified(dataset,0,datasize-1);
//        for (int i = 0; i < datasize; i++){
//            System.out.println(dataset[i]);
//        }
//    }

    public static void insertionSort(int[] array, int first, int last){

    }


}
