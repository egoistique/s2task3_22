package ru.vsu.cs.t4_18;

public class Product implements Comparable<Product> {
    protected String name;
    protected Integer price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(Product o) {
        int result = this.name.compareTo(o.name);
        if (result == 0) {
            result = this.price.compareTo(o.price);
        }
        return result;
//        return result == 0 ? this.price.compareTo(o.price) : result;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
