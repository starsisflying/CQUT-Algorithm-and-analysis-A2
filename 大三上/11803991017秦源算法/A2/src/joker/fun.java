package joker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
class Tree extends JFrame{
    public Tree(){
        setTitle("分形树");
        add(new DrawTree(400, 50));
    }
    public static void main(String[] args){
        Tree jFrame = new Tree();//构造一个树
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(500,500);//宽度和高度
        jFrame.setLocation(700, 400);
        jFrame.setVisible(true);//设置图形界面可见
    }
}
class DrawTree extends JComponent {
    int min;
    List<ArrayList<Integer>> points = new ArrayList<>();
    ArrayList<Integer> xarray = new ArrayList<Integer>();//存储点的x坐标
    ArrayList<Integer> yarray = new ArrayList<Integer>();//存储点的y坐标
    public DrawTree(int max,int min){
        this.min = min;
        getPoint(250,460,max,0.0);
    }
    public void paint(Graphics g) {
        super.paintComponent(g);//绘制父类对像的界面
        for(int i=0;i< points.get(0).size();i+=2){//每两个元素为一个点的坐标
            g.drawLine(points.get(0).get(i), points.get(1).get(i), points.get(0).get(i+1), points.get(1).get(i+1));//画线段
        }

    }
    private void getPoint(int x,int y,int max1,double r){//坐标处理函数
        if(max1<=min){//分支长度小于设置的最小值
            points.add(xarray);
            points.add(yarray);
            return;
        }
        else{
            xarray.add(x);
            yarray.add(y);
            int x1 = (int) (max1*Math.cos(Math.PI/2-r)+x);//该线段的终点
            int y1 = (int) (y-max1*Math.sin(Math.PI/2-r));
            xarray.add(x1);
            yarray.add(y1);
            int maxRight = max1*2/3;//右分支长度为原来的2/3
            int x2 = x+(x1-x)/3;//分支1起点
            int y2 = y-(y-y1)/3;
            double r1 = r+Math.PI/6;//偏转角度
            int maxLeft = max1/3;//左分支为原来的1/3
            int x3 = x+(x1-x)*2/3;//分支2起点
            int y3 = y-(y-y1)*2/3;
            double r2 = r-Math.PI/6;
            getPoint(x2,y2,maxRight,r1);
            getPoint(x3,y3,maxLeft,r2);
        }
    }
}