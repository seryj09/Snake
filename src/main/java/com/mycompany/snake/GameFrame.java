/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snake;

/**
 *
 * @author serega
 */
import javax.swing.JButton;
import javax.swing.JFrame;


public class GameFrame extends JFrame{
    
    GameFrame(){
        this.add(new GamePanel());
        this.setTitle("snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//выход из приложения с помощью метода System exit.
        this.setResizable(false);//возможность менять размер окна
        this.pack();//устанавливает такой минимальный размер контейнера, который достаточен для отображения всех компонентов
        this.setVisible(true);
        this.setLocationRelativeTo(null);//центрирует окно
        this.setLayout(null);
    }
}
