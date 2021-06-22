package com.zyn;

import java.util.Random;

/**
 * 蛇的节点
 */
public class node {
    private int x;
    private int y;

    public node() {
    }

    public node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    //随机生成位置的方法（借鉴）
    public void random(){
        //创建Random对象
        Random r=new Random();
        //生成随机坐标
        int x=r.nextInt(40);
        int y=r.nextInt(40);

    }
}
