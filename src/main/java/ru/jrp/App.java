package ru.jrp;

import ru.jrp.model.Product;

public class App {
    public static void main(String[] args) {
        Product book = new Product();
        book.setPrice(120.0);

        Product computer = new Product(1L, "Laptop", 149699.99, 25.0);

        Product keyboard = new Product();
        keyboard.setDescription("Wireless Keyboard");


        System.out.println(book);
        System.out.println(computer);
        System.out.println(keyboard);
        System.out.println("Changing branch");
    }
}