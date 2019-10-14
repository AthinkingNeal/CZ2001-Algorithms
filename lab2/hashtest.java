package sorting;
import java.util.Scanner;
import java.lang.Math;
import java.util.Random;

public class hashtest{
    private static int table_size = 1000;
    private static double avgTime=0;
    private static double avgComp=0;
    private static int numSearch=0;
    private static int data_size;
    private static int curr_size = 0;
    private static int hashTable1[][] = new int [table_size][2];
    private static int hashTable2[][] = new int [table_size][2];
    private static int hashTable3[][] = new int [table_size][2];

    public static boolean isFull()
    {
        return curr_size == table_size;
    }

    public static int hash1(int key) {
        return (key%table_size);
    }

    public static int rehash1(int key) {
        return (key%11+1);
    }

    public static int rehash2(int key) {
        return (key%10);
    }

    private static int rehash3(int x)
    {
        long v;
        v = (long) x * (long) x; //squaring the key
        v = v / 10000; //shifting the bits to the right by 5 places
        v = v % 100000000; //extracting the middle 8 digits
        v = v % table_size;
        return (int) v;
    }


    public static void insertHash1(int key, int val) {

//		if hash table is full
        if (isFull()) {
            return;
        }

        int index = hash1(key);
//		if collision occurs
        if (hashTable1[index][0]!=-1) {
//			rehash using double hashing
            int index2 = rehash1(key);
//			i is the number of collisions
            int i=1;
            while(i<table_size) {
//				get new index
                int newIndex = (index + i*index2)%table_size;
//				if no more collisions
                if (hashTable1[newIndex][0]==-1) {
                    hashTable1[newIndex][0]=key;
                    hashTable1[newIndex][1]=val;
                    break;
                }
                i++;
            }
        }
        else {
            hashTable1[index][0]=key;
            hashTable1[index][1]=val;
        }
        curr_size++;
    }

    public static void insertHash2(int key, int val) {

//		if hash table is full
        if (isFull()) {
            return;
        }

        int index = hash1(key);
//		if collision occurs
        if (hashTable2[index][0]!=-1) {
//			rehash using double hashing
            int index2 = rehash2(key);
//			i is the number of collisions
            int i=1;
            while(i<table_size) {
//				get new index
                int newIndex = (index + i*index2)%table_size;
//				if no more collisions
                if (hashTable2[newIndex][0]==-1) {
                    hashTable2[newIndex][0]=key;
                    hashTable2[newIndex][1]=val;
                    break;
                }
                i++;
            }
        }
        else {
            hashTable2[index][0]=key;
            hashTable2[index][1]=val;
        }
    }
    public static void insertHash3(int key, int val) {

//		if hash table is full
        if (isFull()) {
            return;
        }

        int index = hash1(key);
//		if collision occurs
        if (hashTable3[index][0]!=-1) {
//			rehash using double hashing
            int index2 = rehash3(key);
//			i is the number of collisions
            int i=1;
            while(i<table_size) {
//				get new index
                int newIndex = index2;
//				if no more collisions
                if (hashTable3[newIndex][0]==-1) {
                    hashTable3[newIndex][0]=key;
                    hashTable3[newIndex][1]=val;
                    break;
                }
                i++;
            }
        }
        else {
            hashTable3[index][0]=key;
            hashTable3[index][1]=val;
        }
    }

    public static void printHashTable1()
    {
        int i;
        System.out.println("\nHash Table 1: ");
        for (i=0;i<table_size;i++) {
            System.out.println("Handphone: " + hashTable1[i][0] +" Membership: " + hashTable1[i][1]);
        }
    }
    public static void printHashTable2()
    {
        int i;
        System.out.println("\nHash Table 2: ");
        for (i=0;i<table_size;i++) {
            System.out.println("Handphone: " + hashTable2[i][0] +" Membership: " + hashTable2[i][1]);
        }
    }
    public static void printHashTable3()
    {
        int i;
        System.out.println("\nHash Table 3: ");
        for (i=0;i<table_size;i++) {
            System.out.println("Handphone: " + hashTable3[i][0] +" Membership: " + hashTable3[i][1]);
        }
    }
    public static void search1()
    {
        numSearch++;
        int comp=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the search value: ");
        //1
        Random rand = new Random();
        int search = (rand.nextInt(19999999)+80000000);
        //2
        //int search = sc.nextInt();
        //3
        //int search=key;
        long start = System.nanoTime();
        //Finding CPU time taken to search the table
        int i=1;
        int searchIndex = hash1(search);
        int temp=searchIndex;
        if (hashTable1[searchIndex][0]==search) {
            comp++;
            System.out.println("Search value found!");
            System.out.println("Handphone: " + hashTable1[searchIndex][0] +" Membership: " + hashTable1[searchIndex][1]);
        }
        else {
            int searchIndex2 = rehash1(search);

            //int searchIndex2 = rehash2(search);
            //int searchIndex2 = rehashtemp(search);
            while(i<table_size) {
                int newIndex = (searchIndex + i*searchIndex2)%table_size;
                if(newIndex==temp||hashTable1[newIndex][0]==-1) {
                    System.out.println("Search value not found!");

                    break;
                }
                //int newIndex = searchIndex2;
                if (hashTable1[newIndex][0]==search) {
                    comp++;
                    System.out.println("Search value found!");
                    System.out.println("Handphone: " + hashTable1[searchIndex][0] +" Membership: " + hashTable1[searchIndex][1]);
                    searchIndex = newIndex;
                    break;
                }
                i++;
            }
            if (i>=table_size){
                System.out.println("Search value not found!");
            }
            comp++;
        }

        long end = System.nanoTime();

        avgTime += (end-start);

        //Find number of comparisons needed to find a key, i is the number of collisions
        avgComp += i;

        if (numSearch>1) {
            avgTime /= 2;
            avgComp /= 2;
        }
        System.out.println("CPU Time: " + avgTime + " ns, Avg. comparisons: " + avgComp);
    }


    public static void search2()
    {

        int comp=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the search value: ");
        //1
        Random rand = new Random();
        int search = (rand.nextInt(19999999)+80000000);
        //2
        //int search = sc.nextInt();
        //3
        //int search=key;
        long start = System.nanoTime();
        //Finding CPU time taken to search the table
        int i=1;
        int searchIndex = hash1(search);
        int temp=searchIndex;
        if (hashTable2[searchIndex][0]==search) {
            comp++;
            System.out.println("Search value found!");
            System.out.println("Handphone: " + hashTable2[searchIndex][0] +" Membership: " + hashTable2[searchIndex][1]);
        }
        else {
            //int searchIndex2 = rehash1(search);
            int searchIndex2 = rehash2(search);
            //int searchIndex2 = rehashtemp(search);
            while(i<table_size) {
                int newIndex = (searchIndex + i*searchIndex2)%table_size;
                //int newIndex = searchIndex2;
                if(newIndex==temp||hashTable2[newIndex][0]==-1){
                    System.out.println("Search value not found!");

                    break;
                }
                if (hashTable2[newIndex][0]==search) {
                    comp++;
                    System.out.println("Search value found!");
                    System.out.println("Handphone: " + hashTable2[searchIndex][0] +" Membership: " + hashTable2[searchIndex][1]);
                    searchIndex = newIndex;
                    break;
                }
                i++;
            }
            if (i>=table_size){
                System.out.println("Search value not found!");
            }
            comp++;
        }

        long end = System.nanoTime();

        avgTime += (end-start);

        //Find number of comparisons needed to find a key, i is the number of collisions
        avgComp += i;

        if (numSearch>1) {
            avgTime /= 2;
            avgComp /= 2;
        }
        System.out.println("CPU Time: " + avgTime + " ns, Avg. comparisons: " + avgComp);
    }

    public static void search3()
    {
        int comp=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the search value: ");
        //1
        Random rand = new Random();
        int search = (rand.nextInt(19999999)+80000000);
        //2
        //int search = sc.nextInt();
        //3
        //int search=key;
        long start = System.nanoTime();
        //Finding CPU time taken to search the table
        int i=1;
        int searchIndex = hash1(search);
        int temp=searchIndex;
        if (hashTable3[searchIndex][0]==search) {
            comp++;
            System.out.println("Search value found!");
            System.out.println("Handphone: " + hashTable3[searchIndex][0] +" Membership: " + hashTable3[searchIndex][1]);
        }
        else {
            //int searchIndex2 = rehash1(search);
            //int searchIndex2 = rehash2(search);
            int searchIndex2 = rehash3(search);

            while(i<table_size) {
                int newIndex = searchIndex2;
                //int newIndex = searchIndex2;
                if(newIndex==temp||hashTable3[newIndex][0]==-1){
                    System.out.println("Search value not found!");

                    break;
                }
                if (hashTable3[newIndex][0]==search) {
                    comp++;
                    System.out.println("Search value found!");
                    System.out.println("Handphone: " + hashTable3[searchIndex][0] +" Membership: " + hashTable3[searchIndex][1]);
                    searchIndex = newIndex;
                    break;
                }
                i++;
            }
            if (i>=table_size){
                System.out.println("Search value not found!");
            }
            comp++;
        }

        long end = System.nanoTime();

        avgTime += (end-start);

        //Find number of comparisons needed to find a key, i is the number of collisions
        avgComp += i;

        if (numSearch>1) {
            avgTime /= 2;
            avgComp /= 2;
        }
        System.out.println("CPU Time: " + avgTime + " ns, Avg. comparisons: " + avgComp);
    }

    /*

    public static void search()
    {
        numSearch++;
        int comp=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the search value: ");
        //1
        Random rand = new Random();
        int search = (rand.nextInt(19999999)+80000000);
        //2
        //int search = sc.nextInt();
        long start = System.nanoTime();
        //Finding CPU time taken to search the table
        int i=1;
        int searchIndex = hash1(search);
        if (hashTable[searchIndex][0]==search) {
            comp++;
            System.out.println("Search value found!");
            System.out.println("Handphone: " + hashTable[searchIndex][0] +" Membership: " + hashTable[searchIndex][1]);
        }
        else {
            int searchIndex2a = rehash1(search);
            //int searchIndex2b = rehash2(search);
            //int searchIndex2 = rehashtemp(search);
            while(i<table_size) {
                int newIndex = (searchIndex + i*searchIndex2)%table_size;
                //int newIndex = searchIndex2;
                if (hashTable[newIndex][0]==search) {
                    comp++;
                    System.out.println("Search value found!");
                    System.out.println("Handphone: " + hashTable[searchIndex][0] +" Membership: " + hashTable[searchIndex][1]);
                    searchIndex = newIndex;
                    break;
                }
                i++;
            }
            if (i>=table_size){
                System.out.println("Search value not found!");
            }
            comp++;
        }

        long end = System.nanoTime();

        avgTime += (end-start);

        //Find number of comparisons needed to find a key, i is the number of collisions
        avgComp += i;

        if (numSearch>1) {
            avgTime /= 2;
            avgComp /= 2;
        }
        System.out.println("CPU Time: " + avgTime + " ns, Avg. comparisons: " + avgComp);
    }
    */
    public static void main(String[] args) {
        int i,x,y;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter data size:");
        data_size = sc.nextInt();
        float loadFactor = (float)data_size/(float)table_size;
        System.out.println("Load Factor = " + loadFactor);
        for (i=0;i<table_size;i++) {
            hashTable1[i][0]=-1;
            hashTable1[i][1]=-1;
            hashTable2[i][0]=-1;
            hashTable2[i][1]=-1;
            hashTable3[i][0]=-1;
            hashTable3[i][1]=-1;
        }
        int keys[] = new int [data_size];
//  	Generating random data set of handphone numbers
        int vals[] = new int [data_size];
//		Generating random data set of associated membership numbers
        Random rand = new Random();
        for (i=0;i<data_size;i++) {
            x = (rand.nextInt(19999999)+80000000);
            y =  (rand.nextInt(2000));
            keys[i]=x;
            vals[i]=y;
        }
//		for (i=0;i<data_size;i++) {
//			System.out.println(keys[i]);
//		}

        for (i=0;i<data_size;i++) {
            insertHash1(keys[i], vals[i]);
            insertHash2(keys[i], vals[i]);
            insertHash3(keys[i], vals[i]);
        }
        printHashTable1();
        printHashTable2();
        printHashTable3();
        //only w 2

        for(i=0; i<5; i++) {
            search1();
            search2();
            search3();

        }

    }
}
