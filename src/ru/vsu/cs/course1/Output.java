package ru.vsu.cs.course1;

import java.util.Queue;
import java.util.Stack;

public class Output {
    Queue<Card> cardsOnTable;
    Stack<Card> cardsOnGraveYard;
    int numberOfMovies;

    public Output(Queue<Card> cards, Stack<Card> cardsOnGraveYard, int numberOfMovies) {
        this.cardsOnTable = cards;
        this.cardsOnGraveYard = cardsOnGraveYard;
        this.numberOfMovies = numberOfMovies;
    }
}
