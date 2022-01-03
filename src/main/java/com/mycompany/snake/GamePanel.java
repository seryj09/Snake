/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snake;

//import java.awt.Graphics;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.JPanel;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;


public class GamePanel extends JPanel implements ActionListener{

	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
	static final int DELAY = 60;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts;
	int applesEaten;
	int appleX;
	int appleY;
	char direction;
	static boolean running = false;
        static boolean gameOn = false; 
	Timer timer;
	Random random;
        GameButton button;
        JButton button2;
        static final boolean walls = false; //Is there walls around frame?
        
        String First_place;
        int First_score = 0;
        Scanner sc=new Scanner(System.in);  
	
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
                button = new GameButton();
                button.addActionListener(this);
                add(button, BorderLayout.CENTER);
                
                button2 = new JButton();
                button2.addActionListener(this);
                add(button2, BorderLayout.CENTER);
                button2.setVisible(true);
                button2.setEnabled(true);
                button2.setPreferredSize(new Dimension(50, 25));
                
                startGame();
	}
	public void startGame() {
                bodyParts = 300;
                applesEaten = 0;
                direction = 'R';
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
        @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		
		if(running) {
                        
//                        g.setColor(Color.darkGray);
//                        g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
//			for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE || i<SCREEN_WIDTH/UNIT_SIZE;i++) {
//				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
//				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
//			}
			
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
			for(int i = 0; i< bodyParts;i++) {
				if(i == 0) {
					g.setColor(Color.green);
					g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				else {
					g.setColor(new Color(45,180,0));
//					g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
					g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}			
			}
			g.setColor(Color.red);
			g.setFont( new Font("Ink Free",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		}
		else {
			gameOver(g);
		}
		
	}
	public void newApple(){
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
                for(int i = 0; i< bodyParts;i++) {
                        if (x[i] == appleX && y[i] == appleY)
                                newApple();
                }
	}
	public void move(){
		for(int i = bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
                        if(y[0] < 0 && !walls) {
                                y[0] = SCREEN_HEIGHT - UNIT_SIZE;
                        }
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
                        if(y[0] == SCREEN_HEIGHT && !walls) {
                                y[0] = 0;
                        }
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
                        if(x[0] < 0 && !walls) {
                                x[0] = SCREEN_WIDTH - UNIT_SIZE;
                        }
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
                        if(x[0] == SCREEN_WIDTH && !walls) {
                                x[0] = 0;
                        }
			break;
		}
		
	}
	public void checkApple() {
		if((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}
	public void checkCollisions() {
		//checks if head collides with body
		for(int i = bodyParts;i>0;i--) {
			if((x[0] == x[i])&& (y[0] == y[i])) {
				running = false;
			}
		}
		//check if head touches left wall
		if(x[0] < 0 && walls) {
			running = false;
		}
		//check if head touches right wall
		if(x[0] == SCREEN_WIDTH && walls) {
			running = false;
		}
		//check if head touches top wall
		if(y[0] < 0 && walls) {
			running = false;
		}
		//check if head touches bottom wall
		if(y[0] == SCREEN_HEIGHT && walls) {
			running = false;
		}		
		if(!running) {
			timer.stop();
		}
	}
	public void gameOver(Graphics g) {
		//Score
		g.setColor(Color.red);
		g.setFont( new Font("TimesNewRoman",Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		//Game Over text
		g.setColor(Color.red);
		g.setFont( new Font("Courier",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
                //results
//                if (applesEaten > First_score) {
//                    First_place = sc.nextLine();
//                    First_score = applesEaten;
//                }
//                g.setFont( new Font("TimesNewRoman",Font.BOLD, 40));
//                g.drawString("1. " + First_place + " " + First_score, 
//                            (SCREEN_WIDTH - metrics1.stringWidth("1. " + First_place + " " + First_score))/2, 
//                            SCREEN_HEIGHT/4);
                //button coordinates
                button.setBounds((SCREEN_WIDTH - button.width)/2, 3*SCREEN_HEIGHT/4   , button.width, button.height);
                button.setVisible(true);
                button.setEnabled(true);
                
        } 
        
        public void pause() {
		GamePanel.gameOn = true;
		timer.stop();
	}

	public void resume() {
		GamePanel.gameOn = false;
		timer.start();
	}

        
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint(); //запускает новый процесс рисования.
                if(e.getSource()==button) {
                        button.setVisible(false);
                        button.setEnabled(false);
                        for(int i = 0; i< bodyParts;i++) {
                                x[i] = 0;
                                y[i] = 0;
                        }
                        startGame();
		}	
                //здесь можно добавить вторую опцию без потоков
                if(e.getSource()==button2) {
                        button2.setVisible(false);
                        button2.setEnabled(false);
                        button2.setVisible(true);
                        button2.setEnabled(true);
                        
                        if (running){
				if(GamePanel.gameOn) {
					resume();
				} else {
					pause();
				}
                            }
		}	
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
                        case KeyEvent.VK_SPACE:
                            if (running){
				if(GamePanel.gameOn) {
					resume();
				} else {
					pause();
				}
				break;
                            }
                            else {//нажимаем на кнопку рестарта
                                button.getModel().setArmed(true);
                                button.getModel().setPressed(true);
                                button.getModel().setPressed(false);
                                button.getModel().setArmed(false);
                                break;
                            }
                        case KeyEvent.VK_SHIFT: 
                                timer.setDelay(3*DELAY);
				break;
			}
		}
                @Override
                public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode()) {
                            case KeyEvent.VK_SHIFT: 
                                timer.setDelay(DELAY);
				break;
			}
                }
	}
}