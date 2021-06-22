package com.zyn;
/**
 * JFrame框体
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author JD
 */
public class MainFrame extends JFrame {

    private snake Snake;

    private Timer timer;

    private JPanel jPanel;

    private node food;

    public MainFrame() throws HeadlessException {
        //初始化窗体参数
        initframe();
        //初始化游戏棋盘
        initGamePanel();
        //初始化蛇
        initsnake();
        //初始化食物
        initFood();
        //初始化定时器
        initTimer();
        //设置一个键盘监听,让蛇移动
        setKeyListener();
    }
    //初始化食物
    private void initFood() {
        food=new node();
        food.random();


    }


    //设置一个键盘监听,让蛇移动
    private void setKeyListener() {
        addKeyListener(new KeyAdapter() {  //当键盘按下时会调用此方法
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:    //上键
                        if(Snake.getDirection()!=Direction.DOWN) {
                            Snake.setDirection(Direction.UP);
                        }//修改蛇的运动方向
                        break;
                    case KeyEvent.VK_DOWN:    //下键
                        if(Snake.getDirection()!=Direction.UP){
                        Snake.setDirection(Direction.DOWN);}
                        break;
                    case KeyEvent.VK_LEFT:    //左键
                        if(Snake.getDirection()!=Direction.RIGHT){
                        Snake.setDirection(Direction.LEFT);}
                        break;
                    case KeyEvent.VK_RIGHT:    //右键
                        if(Snake.getDirection()!=Direction.LEFT){
                        Snake.setDirection(Direction.RIGHT);}
                        break;

                }
            }
        });
    }


    //初始化定时器
    private void initTimer() {
        //创建定时器对象
        timer=new Timer();
        //初始化定时任务
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                Snake.move();
                //判断蛇头是否和食物重合
                node head = Snake.getBody().getFirst();
                if(head.getX()== food.getX()&&head.getY()== food.getY()){
                    Snake.eat(food);
                    food.random();
                }
                //实时更新游戏棋盘
                jPanel.repaint();

            }
        };
        //每100毫秒执行一次定时任务
        timer.scheduleAtFixedRate(timerTask,0,100);
    }

    private void initsnake() {
        Snake=new snake();
    }

    private void initGamePanel() {      //初始化游戏棋盘

        jPanel=new JPanel(){
            //绘制游戏棋盘的内容
            @Override
            public void paint(Graphics g) {   //Graphics g 可绘制简单图形
                g.setColor(Color.BLACK);
                //清空棋盘
                g.clearRect(0,0,600,600);
                    //绘制横线
                   for (int i = 0; i <= 40; i++) {
                       g.drawLine(0,i*15,600,i*15);

                }
                    //绘制竖线
                    for (int i = 0; i <= 40; i++) {
                        g.drawLine(i*15,0,i*15,600);

                }
                    //绘制蛇
                LinkedList<node> body = Snake.getBody();    //调用方法getbody拿到蛇的集合
                for (node node : body) {
                    g.fillRect(node.getX()*15,getY()*15,15,15); //填充颜色
                }
                    //绘制食物
                g.fillRect(food.getX()*15, food.getY()*15,15,15 );
            }
        };
        //把棋盘装入主框架
        add(jPanel);

    }

    private void initframe() {      //初始化窗体参数
        setSize(600,600);   //设置窗体宽高

        setLocation(600,200);   //设置窗体位置

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置关闭按钮的作用

        setResizable(false);    //设置窗体大小不变
    }

    public static void main(String[] args) {

        new MainFrame().setVisible(true);   //创建窗体对象并显示
    }
}
