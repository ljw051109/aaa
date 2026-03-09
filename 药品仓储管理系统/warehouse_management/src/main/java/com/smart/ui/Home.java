package com.smart.ui;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Home extends JFrame {

    private DefaultTableModel model;
    private JTable table;

    private static final String PRODUCT_FILE = "src/main/java/com/smart/data/products.txt";

    public Home() {
        setTitle("仓储管理系统");
        setSize(900, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== 顶部搜索 =====
        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("按类型搜索");

        searchPanel.add(new JLabel("商品类型："));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // ===== 表格 =====
        String[] columnNames = {"ID", "Name", "Type", "Price", "Number", "Status"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setRowHeight(28);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== 底部按钮 =====
        JPanel btnPanel = new JPanel();

        JButton addBtn = new JButton("新增商品");
        JButton delBtn = new JButton("删除商品");
        JButton inBtn = new JButton("入库");
        JButton outBtn = new JButton("出库");
        JButton logoutBtn = new JButton("退出登录");

        btnPanel.add(addBtn);
        btnPanel.add(delBtn);
        btnPanel.add(inBtn);
        btnPanel.add(outBtn);
        btnPanel.add(logoutBtn);

        add(btnPanel, BorderLayout.SOUTH);

        // ===== 初始化加载 =====
        loadProducts(null);

        // ===== 事件 =====
        searchButton.addActionListener(e -> loadProducts(searchField.getText()));

        addBtn.addActionListener(e -> new ProductAddDialog(this));

        delBtn.addActionListener(e -> deleteProduct());

        inBtn.addActionListener(e -> updateStock(true));

        outBtn.addActionListener(e -> updateStock(false));

        logoutBtn.addActionListener(e -> {
            new Login().setVisible(true);
            dispose();
        });
    }

    // ===== 加载商品 =====
    public void loadProducts(String type) {
        model.setRowCount(0);

        try (BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 6) {
                    if (type == null || type.isEmpty() || p[2].contains(type)) {
                        model.addRow(p);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ===== 删除 =====
    private void deleteProduct() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "请选择商品");
            return;
        }

        String id = model.getValueAt(row, 0).toString();
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith(id + ",")) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PRODUCT_FILE))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadProducts(null);
    }

    // ===== 入库 / 出库 =====
    private void updateStock(boolean isIn) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "请选择商品");
            return;
        }

        String input = JOptionPane.showInputDialog("请输入数量：");
        if (input == null) return;

        int change = Integer.parseInt(input);

        String id = model.getValueAt(row, 0).toString();
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p[0].equals(id)) {
                    int stock = Integer.parseInt(p[4]);
                    stock = isIn ? stock + change : stock - change;
                    if (stock < 0) stock = 0;
                    p[4] = String.valueOf(stock);
                    line = String.join(",", p);
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PRODUCT_FILE))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadProducts(null);
    }
}
