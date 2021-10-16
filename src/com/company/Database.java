package com.company;
import com.alibaba.fastjson.JSON;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.FileReader;


public class Database {
    public ArrayList<Product> list;

    public Database() {
        list = new ArrayList<>();
    }

    public void add(Product product) {
        this.list.add(product);
    }
    public  void  add(String name, int number, String price, String year, String manufacturer){
        this.list.add(new Product(name,number,price,year,manufacturer));
    }

    public Product get(int index) {
        return this.list.get(index);
    }

    public Product remove(int index) {
        return this.list.remove(index);
    }

    @Override
    public String toString() {
        return "Database{" + list + '}';
    }

    //нативная сериализация
    public static void SerializationSave(String filename) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            objectOutputStream.writeObject(list);
        } catch (IOException e) {
            System.out.println("Ошибка записи: " + e);
        }
    }

    public static void SerializationLoad(String filename) {
        Products.list.clear();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename))) {
            list=((ArrayList<Product>)objectInputStream.readObject());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //сериализация CSV
    public void CSV_save(String filename) throws IOException {
        FileWriter outStream = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(outStream);
        bw.write("Name;Number;Price;Year;Manufacturer;");
        bw.write(System.lineSeparator());
        for (Product product : list) {
            try {
                bw.write(product.getName() + ";");
                bw.write(product.getNumber() + ";");
                bw.write(product.getPrice() + ";");
                bw.write(product.getYear() + ";");
                bw.write(product.getManufacturer() + ";");
                bw.write(System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bw.close();
        outStream.close();
    }

    public static void CSV_load(String filename) throws IOException, ParseException {
        Products.list.clear();
        Scanner scanner = new Scanner(new FileReader(filename));
        String str;
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            str=scanner.nextLine();
            String[] strings = str.split(";");
            list.add(new Product())

        }
        scanner.close();
    }

    //сериализация JSON
    public static void JSON_save (String filename) throws IOException {
        FileWriter outStream = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(outStream);
        bw.write(JSON.toJSONString(list, true));
        bw.close();
        outStream.close();
    }

    public static void JSON_load(String filename) {
        Products.list.clear();
        try (Stream<String> lines = Files.lines(Paths.get(filename))) {
            String content = lines.collect(Collectors.joining());
            Products.list = JSON.parseArray(content, Product.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
