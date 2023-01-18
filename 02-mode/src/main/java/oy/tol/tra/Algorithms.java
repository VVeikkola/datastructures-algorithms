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
           i = i+1;
    }
    }

     public static <T> void reverse(T [] array) {
        int i = 0;
        while ( i < array.length/2) {
            swap(array, i, (array.length - i - 1));
            i++;
        }
     }

     public static <T> void swap(T [] array, int first, int second) {
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
    
    public static class ModeSearchResult<T> {
        public T theMode;
        public int count = 0;
    }

public static <T extends Comparable<T>> ModeSearchResult<T> findMode(T [] array) {
    ModeSearchResult<T> result = new ModeSearchResult<>();
     // ...
     result.theMode = null;
     result.count = -1;

    if (array == null || array.length < 2) {
        return result;
    }
    sort(array);

    result.theMode = array[0];
    result.count = 1;

    int topFrequency = 1;
    int tempFrequency = 1;
    int indexF = 1;

    while (indexF < array.length){
        if (array[indexF].compareTo(array[indexF - 1]) == 0) {
            tempFrequency++;
        } else {
            if (tempFrequency > topFrequency) {
                result.count = tempFrequency;
                result.theMode = array[indexF - 1];
                topFrequency = tempFrequency;
            }
            tempFrequency = 1;
        }
        indexF++;
        }
        if (tempFrequency > topFrequency) {
                result.count = tempFrequency;
                result.theMode = array[indexF - 1];
                topFrequency = tempFrequency;
        }
        return result;
}
}
