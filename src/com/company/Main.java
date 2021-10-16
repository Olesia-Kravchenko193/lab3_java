package com.company;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
    /* Вариант 14
   Определить товар, количество которого больше всего
   на складе, и напечатать все сведения о нем.*/
        Scanner scanner = new Scanner(System.in);
        Database db = new Database();
        int size;
        do {
            System.out.print("Введите кол-во продуктов: ");
            size = scanner.nextInt();
            scanner.nextLine();
        } while (size <= 0);
        Product[] products = new Product[size];
        for (int i = 0; i < size; i++) {
            products[i] = new Product();
            do {
                System.out.print("Введите название товара:");
                products[i].setName(scanner.nextLine());
            } while (!products[i].getName().matches("^[a-zA-Z]+$")); //регулярное выражение
            System.out.print("Введите кол-во: ");
            products[i].setNumber(scanner.nextInt());
            scanner.nextLine();
            do {
                System.out.print("Введите цену:");
                products[i].setPrice(scanner.nextLine());
            } while (!products[i].getPrice().matches("^[0-9]+$"));
            System.out.print("Введите год изготовления: ");
            products[i].setYear(scanner.nextLine());
            System.out.print("Введите производителя: ");
            products[i].setManufacturer(scanner.nextLine());
        }
        int max = Products.getMax();
        System.out.println(product[max]);
    }
}
