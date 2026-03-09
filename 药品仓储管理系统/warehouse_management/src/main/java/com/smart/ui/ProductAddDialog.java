package com.smart.ui;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProductAddDialog extends JDialog {

    public ProductAddDialog(Home home) {
        setTitle("新增商品");
        setSize(300, 300);
        setLocationRelativeTo(home);
        setModal(true);
        setLayout(null);

        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField type = new JTextField();
        JTextField price = new JTextField();
        JTextField num = new JTextField();

        JButton addBtn = new JButton("添加");

        add(new JLabel("ID")).setBounds(20, 20, 80, 25);
        id.setBounds(100, 20, 150, 25);
        add(id);

        add(new JLabel("Name")).setBounds(20, 50, 80, 25);
        name.setBounds(100, 50, 150, 25);
        add(name);

        add(new JLabel("Type")).setBounds(20, 80, 80, 25);
        type.setBounds(100, 80, 150, 25);
        add(type);

        add(new JLabel("Price")).setBounds(20, 110, 80, 25);
        price.setBounds(100, 110, 150, 25);
        add(price);

        add(new JLabel("Number")).setBounds(20, 140, 80, 25);
        num.setBounds(100, 140, 150, 25);
        add(num);

        addBtn.setBounds(100, 180, 80, 30);
        add(addBtn);

        addBtn.addActionListener(e -> {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/com/smart/data/products.txt", true))) {
                bw.write(id.getText() + "," + name.getText() + "," +
                        type.getText() + "," + price.getText() + "," +
                        num.getText() + ",在库");
                bw.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            home.loadProducts(null);
            dispose();
        });

        setVisible(true);
    }
}
