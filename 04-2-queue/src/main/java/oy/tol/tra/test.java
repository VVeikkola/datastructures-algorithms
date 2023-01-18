package oy.tol.tra;
public class test {

    public static void main(String[] args) {
        int [] array = {1,2,3,4,5,6,7,8,9,10};
        int temp;
        int i = 0;
        System.out.println(array.length);
        while (i <= array.length/2) {
           temp = array[i];
           array[i] = array[array.length-i-1];
           array[array.length-i-1] = temp;
           i++;
        }
        i = 0;
        while (i < array.length) {
            System.out.println(array[i]);
            i++;
        }
     
    }
}
