/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snake;

import static com.mycompany.snake.GamePanel.DELAY;
import static com.mycompany.snake.GamePanel.SCREEN_HEIGHT;
import static com.mycompany.snake.GamePanel.SCREEN_WIDTH;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author serega
 */
public class GameButton extends JButton{
        int width  = 300;
        int height  = 70;
        GameButton(){
                this.setText("WANNA RESTART?");
		this.setFont(new Font("Comic Sans",Font.BOLD,25));
		this.setForeground(Color.cyan);
                this.setVisible(false);
                this.setEnabled(false);
                this.setPreferredSize(new Dimension(width, height));
                this.setBorder(BorderFactory.createEtchedBorder());
	} 
}
