package com.company;
import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int number;
    private String price;
    private String year;
    private String manufacturer;

    public Product(String name, int number, String price, String year, String manufacturer) {
        this.name=name;
        this.number=number;
        this.price=price;
        this.year=year;
        this.manufacturer=manufacturer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", price='" + price + '\'' +
                ", year='" + year + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }

}
