package com.company;

import java.util.ArrayList;
import java.util.List;

public class Products {
    static List<Product> list = new ArrayList<>();

    public  static  Product getMax(){
        Product max_count = new Product("",0,"","","");
        for (Product product : list) {
            if (product.getNumber() > max_count.getNumber()) {
                max_count = product;
            }
        }
        return max_count;
    }
}
