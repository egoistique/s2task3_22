package ru.vsu.cs.t3_22;

public class Card {
    String suit;
    int dignity;

    Card(String suit, int dignity) {
        this.suit = suit;
        this.dignity = dignity;
    }

    public String getSuit() {
        return suit;
    }

    public int getDignity() {
        return dignity;
    }
    @Override
    public String toString() {
        return "Card{" +
                "name='" + suit + "' dignity = " + dignity+ ' ' +
        '}';
    }
}