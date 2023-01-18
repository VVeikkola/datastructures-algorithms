package oy.tol.tra;




public class Algorithms {
    public static <T extends Comparable<T>> void sort(T [] array){

         T temp;
         int i = 1;
         int j = 0;
         while (i < array.length) {
            temp = array[i];
            j = i - 1;
            while((j >= 0) && (array[j].compareTo(temp) > 0)){
                array[j + 1] = array[j];
                j = j - 1; 
            }
            array[j + 1] = temp;
            i = i + 1;
        }           
    } 
         
  
     
     // ...
    public static <T> void reverse(T [] array) {
        // toteutus tähän...
        T temp;
        int i = 0;
        while (i < array.length/2) {
           temp = array[i];
           array[i] = array[array.length-i-1];
           array[array.length-i-1] = temp;
           i++;
       }       

    }

    public static <T> void swap(T [] array, int first, int second) { 
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

}