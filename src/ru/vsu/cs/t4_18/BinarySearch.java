package ru.vsu.cs.t4_18;

import java.io.FileNotFoundException;
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
            int mid = (low + high) / 2;

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
//    enum ElementToChoose {
//        First,
//        Last,
//        TOP,
//        BOTTOM
//    }

    public static void main(String[] args) throws IOException {
//        System.out.println("Демонстрация работы с массивом чисел: ");
//        int[] arr = readIntArrayFromFile("3.txt");
//        Arrays.sort(arr);
//
//        for(int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] + " ");
//        }
//        System.out.println();
//        System.out.println("Enter element: ");
//        Scanner sc = new Scanner(System.in);
//        Integer searchEl = sc.nextInt();
//        System.out.println("Индекс эелемента со значением " + searchEl + " - " + indexOf(toInteger(arr), searchEl));
////        System.out.println(Arrays.binarySearch(arr, 14));
//        System.out.println("Индекс эелемента со значением строго больше чем " + searchEl + " - " + indexOfHigher(toInteger(arr), searchEl));
//        System.out.println("Индекс эелемента со значением строго меньше чем " + searchEl + " - " + indexOfLower(toInteger(arr), searchEl));
//        System.out.println();
//
//        System.out.println("Демонстрация работы со списком строк: ");
//        List<String> list = readLinesFromFile1("4.txt");
//        Collections.sort(list);
//        for(String s : list){
//            System.out.print(s + ", ");
//        }
//        System.out.println();
//        String check = "bc";
//        System.out.println("Индекс эелемента со значением " + check + " - " + indexOf(list, check));
//        System.out.println("Индекс эелемента со значением строго больше чем " + check + " - " + indexOfHigher(list, check));
//        System.out.println("Индекс эелемента со значением строго меньше чем " + check + " - " + indexOfLower(list, check));
//        System.out.println();
//
//        System.out.println("Демонстрация работы со списком товаров: ");
//        List<Product> products = readListFromFile("5.txt");
//        products.sort(Comparator.comparing(Product::getName).thenComparing(Product::getPrice));
//
//        for(Product p : products){
//            System.out.println(p.toString() + " ");
//        }
//        Product pr = new Product("mug", 6);
//        System.out.println("Индекс эелемента со значением " + pr.toString() + " - " + indexOf(products, pr));
//        System.out.println("Индекс эелемента со значением строго больше чем " + pr.toString() + " - " + indexOfHigher(products, pr));
//        System.out.println("Индекс эелемента со значением строго меньше чем " + pr.toString() + " - " + indexOfLower(products, pr));
        test();
    }

    public static void test() throws FileNotFoundException {
        System.out.println("Демонстрация работы с массивом чисел: ");
        int[] arr = readIntArrayFromFile("3.txt");
        Arrays.sort(arr);

        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("Enter element: ");
        Scanner sc = new Scanner(System.in);
        Integer searchEl = sc.nextInt();
        System.out.println("Индекс эелемента со значением " + searchEl + " - " + indexOf(toInteger(arr), searchEl));
//        System.out.println(Arrays.binarySearch(arr, 14));
        System.out.println("Индекс эелемента со значением строго больше чем " + searchEl + " - " + indexOfHigher(toInteger(arr), searchEl));
        System.out.println("Индекс эелемента со значением строго меньше чем " + searchEl + " - " + indexOfLower(toInteger(arr), searchEl));
        System.out.println();

        System.out.println("Демонстрация работы со списком строк: ");
        List<String> list = readLinesFromFile1("4.txt");
        Collections.sort(list);
        for(String s : list){
            System.out.print(s + ", ");
        }
        System.out.println();
        String check = "bc";
        System.out.println("Индекс эелемента со значением " + check + " - " + indexOf(list, check));
        System.out.println("Индекс эелемента со значением строго больше чем " + check + " - " + indexOfHigher(list, check));
        System.out.println("Индекс эелемента со значением строго меньше чем " + check + " - " + indexOfLower(list, check));
        System.out.println();

        System.out.println("Демонстрация работы со списком товаров: ");
        List<Product> products = readListFromFile("5.txt");
        products.sort(Comparator.comparing(Product::getName).thenComparing(Product::getPrice));

        for(Product p : products){
            System.out.println(p.toString() + " ");
        }
        Product pr = new Product("mug", 6);
        System.out.println("Индекс эелемента со значением " + pr.toString() + " - " + indexOf(products, pr));
        System.out.println("Индекс эелемента со значением строго больше чем " + pr.toString() + " - " + indexOfHigher(products, pr));
        System.out.println("Индекс эелемента со значением строго меньше чем " + pr.toString() + " - " + indexOfLower(products, pr));
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
