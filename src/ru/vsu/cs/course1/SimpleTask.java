package ru.vsu.cs.course1;

import ru.vsu.cs.course1.Card;
import ru.vsu.cs.course1.Output;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class SimpleTask {
    public static SimpleLinkedListQueue2<Card> fillingInTheQueue1(){
        SimpleLinkedListQueue2<Card> cards = new SimpleLinkedListQueue2<>();
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
    public static SimpleLinkedListQueue2<Card> randomize(SimpleLinkedListQueue2<Card> queue) throws Exception {
        Card[] arr = new Card[36];
        SimpleLinkedListQueue2<Card> randoms = new SimpleLinkedListQueue2<>();
        int k = 0;
        for (int i = 0; i < queue.count(); i++) {
            arr[k] = queue.element();
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
    public static Output process(SimpleLinkedListQueue2<Card> queue) throws Exception {
        boolean haveValidCard = true;
        int numberOfMovies = 0;
        SimpleLinkedListStack2<Card> graveyard = new SimpleLinkedListStack2<>();
        Card active = queue.remove();
        graveyard.push(active);
        Card current;
        while (haveValidCard){
            if (queue.count() <= 9){
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
}