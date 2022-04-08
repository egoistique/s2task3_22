package ru.vsu.cs.t4_18;

import java.util.Arrays;
import java.util.List;

import static ru.vsu.cs.util.ArrayUtils.readIntArrayFromFile;

public class BinarySearch {
    // возвращает индекс элемента в массиве со значением value
    // или -1, если такого нет
    public static int <T extends Comparable<? super T>> indexOf(T[] data, T value) {
        for (int i = 0; i < data.length; i++){

        }
    }

    // возвращает наименьший индекс элемента, строго большего value
    // или -1, если такого нет
    public static int <T extends Comparable<? super T>> indexOfHigher(T[] data, T value) {

    }

    // возвращает наибольший индекс элемента, строго меньшего value
    // или -1, если такого нет
    public static int <T extends Comparable<? super T>> indexOfLower(T[] data, T value) {

    }

    // и такие же методы для списков (по сути копия кода
    // с разницей только в способе обращения к элементам)
    public static int <T extends Comparable<? super T>> indexOf(List<? super T> data, T value) {

    }
    public static int <T extends Comparable<? super T>> indexOfHigher(List<? super T> data, T value) {

    }
    public static int <T extends Comparable<? super T>> indexOfHigher(List<? super T> data, T value) {

    }

    public static void main(String[] args) {
        int[] arr = readIntArrayFromFile("1.txt");
        Arrays.sort(arr);

    }
}
