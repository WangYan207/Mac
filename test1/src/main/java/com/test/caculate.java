package com.test;


import java.util.Arrays;
import java.util.Scanner;

public class caculate {
    public static void main(String[] args) {
        int[] arr={5,3,1,2,4,7,6};
        System.out.println("请输入1到"+arr.length+"间的数");
        int k = new Scanner(System.in). nextInt();
        sort(arr,k-1);
    }

    private static void sort(int[] arr, int k) {
        int length = arr.length;
        int a = -1;

        for(int i = 0; i < length; i++) {
            for(int j = 0;j<arr.length-1;j++){
            if(arr[j]>arr[j+1]) {
                a = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = a;
            }
            }

        }
        System.out.println(Arrays.toString(arr));
        System.out.println(arr[k]);
    }

}
