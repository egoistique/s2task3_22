package ru.vsu.cs.course1;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

import static java.lang.System.in;
import static java.lang.System.out;

public class Task {
    //22.	Смоделировать следующую игру. Берется колода карт, перемешивается. Верхняя карта кладется на стол.
    // Далее берется следующая карта, если она той же масти или достоинства, то кладется на карту на столе, в противном случае
    // перекладывается вниз стопки карт. Действия продолжаются, пока в стопке карт остаются карты, которые можно положить
    // на самую верхнюю карту на столе по описанным правилам. Необходимо найти, а) сколько будет сделано ходов,
    // б) какие карты и в каком порядке окажутся на столе, в) какие карты и в каком порядке останутся в стопке карт.
    //«Рисовать» анимацию не требуется, только в каком-либо виде показать, какие карты и в каком порядке были вначале,
    // а также результат «игры» (пункты а, б, в).

    // здесь реализация с использованием реализации стека / очереди, которая уже есть в стандартной библиотеки языка Java.
    public static Queue<Card> fillingInTheQueue1(){
        Queue<Card> cards = new ArrayDeque<>();
        for (int i = 0; i < 9; i++){
            cards.add(new Card("peaks", i + 6));
        }
        for (int i = 0; i < 9; i++){
            cards.add(new Card("cross", i + 6));
        }
        for (int i = 0; i < 9; i++){
            cards.add(new Card("hearts", i + 6));
        }
        for (int i = 0; i < 9; i++){
            cards.add(new Card("diamonds", i + 6));
        }
        return cards;
    }

    //перемешивание карт
    public static Queue<Card> randomize(Queue<Card> queue) {
        Card[] arr = new Card[36];
        Queue<Card> randoms = new ArrayDeque<>();
        int k = 0;
        for (Card element: queue) {
            arr[k] = element;
            k++;
        }
        Random rnd = new Random();
        for (int i = 1; i < arr.length; i++) {
            int j = rnd.nextInt(i);
            Card temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        for (int i = 0; i < 36; i++){
            randoms.add(arr[i]);
        }
        return randoms;
    }

    //игровой процесс
    public static Output process(Queue<Card> queue){
        boolean haveValidCard = true;
        int numberOfMovies = 0;
        Stack<Card> graveyard = new Stack<>();
        Card active = queue.remove();
        graveyard.push(active);
        Card current;
        while (haveValidCard){
            if (queue.size() <= 9){
                for (Card card: queue) {
                    if (card.dignity != active.dignity && !(card.suit.equals(active.suit))){
                        haveValidCard = false;
                    } else {
                        haveValidCard = true;
                        break;
                    }
                }
                if (!haveValidCard) {
                    System.out.println(active);
                    System.out.println();
                    break;
                }
            }
            if (queue.size() > 0) {
                current = queue.remove();
                if (current.dignity == active.dignity || current.suit.equals(active.suit)) {
                    graveyard.push(current);
                } else {
                    queue.add(current);
                }
                numberOfMovies++;
                active = graveyard.peek();
            } else break;
        }
        Output out = new Output(queue, graveyard, numberOfMovies);
        return out; //оставшиеся на столе карты P.S. вывод
    }

    public static int[] listToArr(ArrayList<Integer> list){
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++){
            arr[i] = list.get(i);
        }
        return arr;
    }
}
