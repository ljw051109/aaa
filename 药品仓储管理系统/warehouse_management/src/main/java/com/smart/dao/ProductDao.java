package com.smart.dao;

import com.smart.endity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private static final String FILE_PATH = "src/main/java/com/smart/data/products.txt";

    // 读取所有商品
    public List<Product> readProductsFromFile() {
        List<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 5) {
                    Product product = new Product(
                            fields[0],
                            fields[1],
                            Double.parseDouble(fields[2]),
                            Integer.parseInt(fields[3]),
                            fields[4]
                    );
                    products.add(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    // 覆盖写入
    public void writeProductsToFile(List<Product> products) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Product product : products) {
                bw.write(product.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 追加写入
    public void writeProductToFile(Product product) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(product.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
