package ru.vsu.cs.t4_18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static ru.vsu.cs.util.ArrayUtils.*;

public class BinarySearch {
    // возвращает индекс элемента в массиве со значением value
    // или -1, если такого нет
    public static <T extends Comparable<? super T>> int indexOf(T[] data, T value) {
        int low = 0;
        int high = data.length - 1;

        int index = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (data[mid].compareTo(value) < 0) {
                low = mid + 1;
            } else if (data[mid].compareTo(value) > 0) {
                high = mid - 1;
            } else if (data[mid].compareTo(value) == 0) {
                index = mid;
                break;
            }
        }
        return index;
    }

    // возвращает наименьший индекс элемента, строго большего value
    // или -1, если такого нет
    public static <T extends Comparable<? super T>> int indexOfHigher(T[] data, T value) {
        int index = binaryLastOrFirst(data, value, true);
        if (index != -1){
            return index != data.length - 1 ? index + 1 : -1;
        }

        return binaryLastOrFirstIfNoValue(data, value, false);
    }

    // возвращает наибольший индекс элемента, строго меньшего value
    // или -1, если такого нет
    public static <T extends Comparable<? super T>> int indexOfLower(T[] data, T value) {
        int index = binaryLastOrFirst(data, value, false);
        if (index != -1){
            return index != 0 ? index - 1 : -1;
        }

        return binaryLastOrFirstIfNoValue(data, value, true);
    }

    //ищет индекс большего или меньшего элемента, если value нет в массиве
    public static <T extends Comparable<? super T>> int binaryLastOrFirstIfNoValue(T[] data, T value, boolean less) {
        int low = 0;
        int high = data.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (data[mid].compareTo(value) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            if (low + 1 == high ) {
                return less ? low : high;
            }
        }
        return -1;
    }

    //ищет индекс первого или последнего дубликата элемента
    public static <T extends Comparable<? super T>> int binaryLastOrFirst(T[] data, T value, boolean last) {
        int low = 0;
        int high = data.length - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;

            if (data[mid].compareTo(value) < 0 || (last && data[mid].compareTo(value) == 0))
                low = mid + 1;
            else if (data[mid].compareTo(value) > 0 || (!last && data[mid].compareTo(value) == 0))
                high = mid - 1;
        }
        return last ? high : low;
    }

    //     и такие же методы для списков (по сути копия кода
    //     с разницей только в способе обращения к элементам)
    public static <T extends Comparable<? super T>> int indexOf(List<? super T> data, T value) {
        int low = 0;
        int high = data.size() - 1;

        int index = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            T curr = (T) data.get(mid);
            if (curr.compareTo(value) < 0) {
                low = mid + 1;
            } else if (curr.compareTo(value) > 0) {
                high = mid - 1;
            } else if (curr.compareTo(value) == 0) {
                index = mid;
                break;
            }
        }
        return index;
    }
    public static <T extends Comparable<? super T>> int indexOfHigher(List<? super T> data, T value) {
        int index = binaryLastOrFirst(data, value, true);
        if (index != -1){
            return index != data.size() - 1 ? index + 1 : -1;
        }

        return binaryLastOrFirstIfNoValue(data, value, false);
    }

    public static <T extends Comparable<? super T>> int indexOfLower(List<? super T> data, T value) {
        int index = binaryLastOrFirst(data, value, false);
        if (index != -1){
            return index != 0 ? index - 1 : -1;
        }

        return binaryLastOrFirstIfNoValue(data, value, true);
    }

    //ищет индекс большего или меньшего элемента, если value нет в массиве
    public static <T extends Comparable<? super T>> int binaryLastOrFirstIfNoValue(List<? super T> data, T value, boolean less) {
        int low = 0;
        int high = data.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            T curr = (T) data.get(mid);
            if (curr.compareTo(value) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            if (low + 1 == high ) {
                return less ? low : high;
            }
        }
        return -1;
    }

    //ищет индекс первого или последнего дубликата элемента
    public static <T extends Comparable<? super T>> int binaryLastOrFirst(List<? super T> data, T value, boolean last) {
        int low = 0;
        int high = data.size() - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            T curr = (T) data.get(mid);
            if (curr.compareTo(value) < 0 || (last && curr.compareTo(value) == 0))
                low = mid + 1;
            else if (curr.compareTo(value) > 0 || (!last && curr.compareTo(value) == 0))
                high = mid - 1;
        }
        return last ? high : low;
    }

//    enum ElementToChoose
//    {
//        First,
//        Last,
//        NoCare
//    }
//
//    /// <summary>
///// Finds element equal to value in sorted array in range [low, high)
///// </summary>
//    static int binarySearch(int value, int[] array, boolean ascendingOrder, ElementToChoose elementToChoose, int low, int high) {
//        // return valid invalid position
//        if (low >= high)
//            return -(low + 1);
//
//        // return first or last found element
//        if (elementToChoose == ElementToChoose.First)
//            if (value == array[low])
//                return low;
//
//        int last = high - 1;
//
//        if (elementToChoose == ElementToChoose.Last)
//            if (value == array[last])
//                return last;
//
//        int mid = low + (high - low) / 2;
//
//        // we have found some element
//        if (value == array[mid]) {
//            switch (elementToChoose) {
//                case ElementToChoose.NoCare:
//                    return mid;
//
//                case ElementToChoose.First:
//                    if (mid - low <= 1)
//                        // array[mid] is the earliest element in array, return it
//                        // because array[low] != value && array[low+1] == value, where mid == low + 1
//                        return mid;
//                    else
//                        // try to find first element
//                        // don't forget to capture current element {|0, 0|, 1} -> {0, 0}
//                        return binarySearch(value, array, ascendingOrder, elementToChoose, low, mid + 1);
//                case ElementToChoose.Last:
//                    if (last - mid <= 1)
//                        // array[mid] is the last element in array, return it
//                        // because array[last] != value && array[last - 1] == value, where mid == last - 1
//                        return mid;
//                    else
//                        // try to find last element
//                        // don't forget to capture current element {0, |0, 1|} -> {0, 1}
//                        return binarySearch(value, array, ascendingOrder, elementToChoose, mid, high);
//            }
//        }

        // choose left or right half, depending on sorting order & comparing value and mid
//        if ((value < array[mid]) ^ !ascendingOrder)
//            return binarySearch(value, array, ascendingOrder, elementToChoose, low, mid);
//        else
//            return binarySearch(value, array, ascendingOrder, elementToChoose, mid + 1, high);
//    }


    public static void main(String[] args) throws IOException {
        int[] arr = readIntArrayFromFile("3.txt");
        Arrays.sort(arr);

        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("Enter element: ");
        Scanner sc = new Scanner(System.in);
        Integer searchEl = sc.nextInt();
        System.out.println(indexOf(toInteger(arr), searchEl));
//        System.out.println(Arrays.binarySearch(arr, 14));
        System.out.println(indexOfHigher(toInteger(arr), searchEl));
        System.out.println(indexOfLower(toInteger(arr), searchEl));
        System.out.println();

        List<String> list = readLinesFromFile1("4.txt");
        Collections.sort(list);
        for(String s : list){
            System.out.println(s + " ");
        }
        System.out.println(indexOf(list, "bc"));
        System.out.println(indexOfHigher(list, "bc"));
        System.out.println(indexOfLower(list, "bc"));
        System.out.println();

        List<Product> products = readListFromFile("5.txt");
        products.sort(Comparator.comparing(Product::getName));
        for(Product p : products){
            System.out.println(p.toString() + " ");
        }
        Product pr = new Product("apple", 5);
//        System.out.println(indexOf(products, pr));
//        System.out.println(indexOfHigher(list, "bc"));
//        System.out.println(indexOfLower(list, "bc"));
    }

    public static Integer[] toInteger(int[] arr){
        int k = 0;
        Integer[] arr1 = new Integer[arr.length];
        for (int a : arr){
            arr1[k] = a;
            k++;
        }
        return arr1;
    }
}
