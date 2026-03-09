package com.smart;

import com.smart.ui.Login;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        // 使用 Swing 事件分发线程启动界面（规范写法）
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}
