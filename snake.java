package com.zyn;

import java.util.LinkedList;

/**
 * 蛇身，出生时生成五个节点
 */
//linkedlist方便对头和尾进行操作

public class snake {
    //蛇的身体
    private LinkedList<node> body;
    //蛇运动的方向默认向左
    private Direction direction=Direction.LEFT;
    //蛇是否活着
    private boolean isliving=true;

    //构造方法，在创建snake对象时执行
    public snake() {
        //初始化蛇身
        initsnake();
    }
    //初始化蛇身
    private void initsnake() {
        body=new LinkedList<>();    //创建集合
        //创建五个节点，添加到集合中
        body.add(new node(14,30));
        body.add(new node(15,30));
        body.add(new node(16,30));
        body.add(new node(17,30));
        body.add(new node(18,30));
    }
    //蛇沿着蛇头方向移动
    //蛇移动即是蛇头填加一个节点尾部删除一个节点
    public void move(){
        if(isliving){
        //蛇头前进
        node head = body.getFirst();
        switch(direction){
            case UP:
                body.addFirst(new node(head.getX(), head.getY()-1));
                break;
            case DOWN:
                body.addFirst(new node(head.getX(), head.getY()+1));
                break;
            case LEFT:
                body.addFirst(new node(head.getX()-1, head.getY()));
                break;
            case RIGHT:
                body.addFirst(new node(head.getX()+1, head.getY()));
                break;
        }
        //删除蛇尾
        body.removeLast();
        //判断是否撞墙
            head=body.getFirst();
            if(head.getX()<0||head.getY()<0||head.getX()>=40||head.getY()>=40){
                isliving=false;
            }
            //判断是否追尾
            for (int i = 1; i < body.size(); i++) {
                node node = body.get(i);
                if(head.getX()==node.getX()&&head.getX()==node.getY()){
                    isliving=false;
                }
            }
        }
    }

    public LinkedList<node> getBody() {
        return body;
    }

    public void setBody(LinkedList<node> body) {
        this.body = body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    //吃食物，沿蛇头方向添加一个节点
    public void eat(node food) { //蛇头前进
        node head = body.getFirst();
        switch(direction){
            case UP:
                body.addFirst(new node(head.getX(), head.getY()-1));
                break;
            case DOWN:
                body.addFirst(new node(head.getX(), head.getY()+1));
                break;
            case LEFT:
                body.addFirst(new node(head.getX()-1, head.getY()));
                break;
            case RIGHT:
                body.addFirst(new node(head.getX()+1, head.getY()));
                break;}

    }
}
