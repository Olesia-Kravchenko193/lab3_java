package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
    /* Вариант 14
   Определить товар, количество которого больше всего
   на складе, и напечатать все сведения о нем.*/
        Scanner scanner = new Scanner(System.in);
        int size;
        do {
            System.out.print("Введите кол-во продуктов: ");
            size = scanner.nextInt();
            scanner.nextLine();
        } while (size <= 0);
        Products.list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Products.list.add(new Product());
            do {
                System.out.print("Введите название товара:");
                Products.list.get(i).setName(scanner.nextLine());
            } while (!Products.list.get(i).getName().matches("^[a-zA-Z]+$")); //регулярное выражение
            System.out.print("Введите кол-во: ");
            Products.list.get(i).setNumber(scanner.nextInt());
            scanner.nextLine();
            do {
                System.out.print("Введите цену:");
                Products.list.get(i).setPrice(scanner.nextLine());
            } while (!Products.list.get(i).getPrice().matches("^[0-9]+$"));
            System.out.print("Введите год изготовления: ");
            Products.list.get(i).setYear(scanner.nextLine());
            System.out.print("Введите производителя: ");
            Products.list.get(i).setManufacturer(scanner.nextLine());
        }
        System.out.println("Products:");
        for (Product product : Products.list) {
            System.out.println(product);
        }
        System.out.println("Max count products:");

        for (Product product : Products.getMax()) {
            System.out.println(product);
        }

        Database db = new Database();
        //Text format Save/load
        db.save("db.txt");
        db.load("db.txt");

        //Java serialization/des
        db.serialize("db_s.txt");
        db.deserialize("db_s.txt");

        //Jackson serialization/des
        db.jacksonSerialize("students.json");
        db.jacksonDeserialize("students.json");

        //FASTJson serialization/des
        db.serializeFastJSON("db_fastjson.json");
        db.deserializeFastJSON("db_fastjson.json");
    }

}
