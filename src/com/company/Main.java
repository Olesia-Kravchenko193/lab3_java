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
        Database db = new Database();
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

        long timeStart = System.currentTimeMillis(), t1, t2, t3, t4;
        timeStart=System.currentTimeMillis();
        db.save("db.txt");
        db.load("db.txt");
        /*System.out.println("native:");
        for (Product product : Products.list) {
            System.out.println(product);
        }*/
        t1=System.currentTimeMillis()-timeStart;

        timeStart = System.currentTimeMillis();
        db.serialize("db_s.txt");
        db.deserialize("db_s.txt");
        t2=System.currentTimeMillis()-timeStart;

        timeStart = System.currentTimeMillis();
        db.jacksonSerialize("students.json");
        db.jacksonDeserialize("students.json");
        t3=System.currentTimeMillis()-timeStart;

        timeStart = System.currentTimeMillis();
        db.serializeFastJSON("db_fastjson.txt");
        db.deserializeFastJSON("db_fastjson.txt");
        t4=System.currentTimeMillis()-timeStart;

        System.out.println("Text format Save/load:		" + t1 + " ms");
        System.out.println("Java serialization/des:		" + t2 + " ms");
        System.out.println("Jackson serialization/des:	" + t3 + " ms");
        System.out.println("FASTJson serialization/des:	" + t4 + " ms");


    }

}
