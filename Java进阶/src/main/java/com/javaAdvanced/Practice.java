package com.javaAdvanced;

/**
 * @ClassName Practice
 * @Description TODO
 * @Author 陈杰
 * @Date 2019/9/27 002716:53
 * @Version 1.0
 **/
public class Practice  {

    public static void main(String [] args){
        int[] intArr = {1,2,5,8,10,25,36,64};
        int val= binarySearch(intArr,0,intArr.length-1,5);
        System.out.println(val);
    }

    public static int   binarySearch(int[] arr, int left, int right, int findVal){
        int midIndex =0;
        do {
            midIndex = (left + right) / 2;
            if (left > right) {
                return -1;
            }
            if (findVal < arr[midIndex]) {
                right=midIndex;
                //binarySearch(arr, left, midIndex, findVal);
            }
            if (findVal > arr[midIndex]) {
                left=midIndex;

                //binarySearch(arr, midIndex, right, findVal);
            }
        } while(findVal != arr[midIndex]);

        return arr[midIndex];
    }






    public void bubbleSortbubbleSort(int[] data){
        for (int i = 0; i < data.length-1; i++) {
            for (int i1 = 0; i1 < data.length-1-i; i1++) {
                if (data [i1]>data [i1+1]){
                    int temp = data [i1];
                    data [i1] = data [i1+1];
                    data [i1+1] = temp;
                }
            }
        }
    }



}
