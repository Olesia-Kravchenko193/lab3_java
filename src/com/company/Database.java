package com.company;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database implements Serializable {
    @JsonProperty("wrapper")
    List<Product> dbList;

    public Database() {
        dbList = Products.list;
    }

    @Override
    public String toString() {
        return "Database{" + dbList + '}';
    }

    public void save(String filename) throws IOException {
        FileWriter outStream = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(outStream);
        for (Product product : dbList) {
            try {
                bw.write(product.getName());
                bw.write(System.lineSeparator());
                bw.write(String.valueOf(product.getNumber()));
                bw.write(System.lineSeparator());
                bw.write(product.getPrice());
                bw.write(System.lineSeparator());
                bw.write(product.getYear());
                bw.write(System.lineSeparator());
                bw.write(product.getManufacturer());
                bw.write(System.lineSeparator());
                bw.write("--------------------");
                bw.write(System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bw.close();
        outStream.close();
    }

    public void load(String filename) throws IOException {
        dbList.clear();
        Scanner scanner = new Scanner(new FileReader(filename));
        while (scanner.hasNextLine()) {
            Product product = new Product();
            product.setName(scanner.nextLine());
            product.setNumber(Integer.parseInt(scanner.nextLine()));
            product.setPrice(scanner.nextLine());
            product.setYear(scanner.nextLine());
            product.setManufacturer(scanner.nextLine());
            scanner.nextLine();
            dbList.add(product);
        }
        scanner.close();
    }

    public void serialize(String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(dbList);
            out.close();
            fileOut.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deserialize(String filename) {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            dbList = (ArrayList<Product>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Product class not found");
            c.printStackTrace();
        }
    }

    public void jacksonSerialize(String filename) throws IOException {
        new ObjectMapper().writeValue(new File(filename), this);
    }

    public void jacksonDeserialize(String filename) throws IOException {
        Database db1 = new ObjectMapper().readValue(new File(filename), Database.class);
        this.dbList = db1.dbList;
    }

    public void serializeFastJSON(String filename) throws IOException {
        FileWriter outStream = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(outStream);
        bw.write(JSON.toJSONString(dbList));
        bw.close();
        outStream.close();
    }

    public void deserializeFastJSON(String filename) throws IOException {
        Scanner scanner = new Scanner(new FileReader(filename));
        dbList.clear();
        List <JSONObject>JSONlist = JSON.parseObject(scanner.nextLine(), ArrayList.class);
        for (JSONObject st : JSONlist) {
            this.add(new Product(st.getString("name"), st.getIntValue("number"), st.getString("price"), st.getString("year"), st.getString("manufacturer")));
        }
        scanner.close();
    }

    public void add(Product product) {
        Products.list.add(product);
    }


}
