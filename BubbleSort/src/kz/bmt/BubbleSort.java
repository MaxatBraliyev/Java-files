package kz.bmt;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {

        int[] mas = {5, 7, 2, 6, 1, 4};
        int buf;

        for (int out = mas.length-1; out>=0; out--) {
            for (int i = 0; i < out; i++) {
                if (mas[i] > mas[i + 1]) {

                    buf = mas[i];
                    mas[i] = mas[i + 1];
                    mas[i + 1] = buf;
                }
            }
        }
        System.out.println(Arrays.toString(mas));
    }
}