package com.company;

import java.util.ArrayList;
import java.util.List;

public class Products {
    public static List<Product> list;

    public  static  List<Product> getMax(){
        List<Product> maxCountProducts = new ArrayList<>();
        int max_count = 0;
        for (Product product : list) {
            if (product.getNumber() > max_count) {
                max_count = product.getNumber();
            }
        }
        for (Product product : list) {
            if (product.getNumber() == max_count) maxCountProducts.add(product);
        }
        return maxCountProducts;
    }
}
