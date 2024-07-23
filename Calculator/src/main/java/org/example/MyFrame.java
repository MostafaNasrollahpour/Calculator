package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame implements ActionListener {
    private final Color invisible = new Color(0,0,0,0);

    private JPanel resultPanel;
    private JFrame frame;
    private JPanel buttonPanel;
    private JButton[][] lineButtons = new JButton[5][4];
    private JLabel label;

    public MyFrame(){
        ImageIcon icon = new ImageIcon("myFiles/images/icon.png");

        frame = new JFrame("Calculator");
        frame.setBounds(300,100,400,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(icon.getImage());
        frame.setLayout(null);

        resultPanel = new JPanel();
        resultPanel.setBackground(new Color(36, 60, 138));
        resultPanel.setLayout(new GridLayout());

        label = new JLabel("Try Something");
        label.setForeground(Color.white);
        label.setFont(new Font(null, Font.PLAIN, 26));
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        resultPanel.add(label);

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
                lineButtons[i][j].addActionListener(this);
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

    public void updateResult(char number){
        if(label.getText().contains("Try")){
            label.setText(number + "");
        }else{
            String tmp = label.getText() + number;
            label.setText(tmp);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        char number = ' ';
        if(e.getSource() == lineButtons[1][0]){
            number = '7';
        }else if(e.getSource() == lineButtons[1][1]){
            number = '8';
        }else if(e.getSource() == lineButtons[1][2]){
            number = '9';
        }else if(e.getSource() == lineButtons[2][0]){
            number = '4';
        }else if(e.getSource() == lineButtons[2][1]){
            number = '5';
        }else if(e.getSource() == lineButtons[2][2]){
            number = '6';
        }else if(e.getSource() == lineButtons[3][0]){
            number = '1';
        }else if(e.getSource() == lineButtons[3][1]){
            number = '2';
        }else if(e.getSource() == lineButtons[3][2]){
            number = '3';
        }
        else if(e.getSource() == lineButtons[4][1]){
            number = '0';
        }
        updateResult(number);
    }


    public static void main(String[] args) {
        new MyFrame();
    }
}
