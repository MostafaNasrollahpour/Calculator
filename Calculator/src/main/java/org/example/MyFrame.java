package org.example;

import javax.swing.*;
import java.awt.*;

public class MyFrame{
    private final Color invisible = new Color(0,0,0,0);

    private JPanel resultPanel;
    private JFrame frame;
    private JPanel buttonPanel;
    private JButton[][] lineButtons = new JButton[5][4];

    public MyFrame(){
        ImageIcon icon = new ImageIcon("myFiles/images/icon.png");

        frame = new JFrame("Calculator");
        frame.setBounds(300,100,400,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(icon.getImage());
        frame.setLayout(null);

        resultPanel = new JPanel();
        resultPanel.setBackground(new Color(36, 60, 138));

        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(61, 71, 162));
        buttonPanel.setLayout(new GridLayout(5,4));
        createButtons();
        setTextToButtons();

        frame.add(buttonPanel);
        frame.add(resultPanel);
        normalization();
        frame.setVisible(true);
    }

    public void normalization(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int frameHeight;
                int height;
                int width;
                while (true){
                    width = frame.getWidth();
                    frameHeight = frame.getHeight();
                    height = (int) (frameHeight * 0.2);
                    resultPanel.setBounds(0,0,width,height);
                    buttonPanel.setBounds(0,height,width-5,frameHeight-height-38);
                }
            }
        }).start();
    }

    public void createButtons(){
        Color buttonColor = new Color(88, 100, 202);
        Font font = new Font(null,Font.PLAIN,24);
        for(int i=0; i<lineButtons.length; i++){
            for(int j=0; j<lineButtons[i].length; j++){
                lineButtons[i][j] = new JButton();
                lineButtons[i][j].setBackground(buttonColor);
                lineButtons[i][j].setFocusable(false);
                lineButtons[i][j].setFont(font);
                lineButtons[i][j].setForeground(Color.white);
                buttonPanel.add(lineButtons[i][j]);
            }
        }
    }

    public void setTextToButtons(){
        for(int i=1; i<lineButtons.length-1; i++){
            int x;
            int y = 7 - (i-1) *3;
            for(int j=0; j<lineButtons[i].length-1; j++){
                x = y+j;
                lineButtons[i][j].setText(x + "");
            }
        }
        lineButtons[4][0].setText(".");
        lineButtons[4][1].setText("0");
        lineButtons[4][2].setText("/");
        lineButtons[4][3].setText("*");

        lineButtons[3][3].setText("+");
        lineButtons[2][3].setText("-");
        lineButtons[1][3].setText("=");

        lineButtons[0][0].setText("C");
        Color tmp = new Color(147, 19, 19);
        lineButtons[0][0].setForeground(tmp);
        lineButtons[0][1].setText("sqrt");
        lineButtons[0][2].setText("%");
        lineButtons[0][3].setText("X");
        lineButtons[0][3].setForeground(tmp);


    }

    public static void main(String[] args) {
        new MyFrame();
    }
}
