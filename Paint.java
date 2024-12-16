//Authors: Shawn Chacko and Luke Goree
// Date: 1/29/24
// Course: Computer Science IIK (Computer Science AP)
// Purpose: Make a lab that has similar properties and functions as the microsoft paint app


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Objects;

public class Paint extends JFrame implements MouseMotionListener {

    /**Below are all the components and variables that were in the code**/

    JPanel canvas = new JPanel();
    JPanel controls = new JPanel();
    JButton red = new JButton("RED");
    JButton orange = new JButton("ORANGE");
    JButton yellow = new JButton("YELLOW");
    JButton green = new JButton("GREEN");
    JButton blue = new JButton("BLUE");
    JButton purple = new JButton("PURPLE");
    JButton black = new JButton("BLACK");
    JButton era = new JButton("ERASER");

    JButton brush = new JButton("PEN");

    JButton circle = new JButton("CIRCLE");

    JButton line = new JButton("LINE");

    JButton clear = new JButton("CLEAR");


    JButton rect = new JButton("RECT");


    boolean r = false;
    boolean l = false;
    boolean c = false;
    boolean b = true;

    int mxe =0;
    int mye =0;

    int w;
    int h;

    JSlider size = new JSlider();
    Color p = new Color(119, 8, 155);




    Color dc = new Color(0,0,0);


    /**This is the main window below**/

    public Paint(){

        setTitle("Painter");
        setSize(1500, 1000);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        panel();
        buttons();
        drawShape();
        addMouseMotionListener(this);

    }

    /**The method below calls the functions to ask for the dimensions of the shapes and
     draws the shapes afterward.**/

    public void drawShape(){
        Graphics g = getGraphics();
        addMouseListener(new MouseAdapter() {
                             @Override
                             public void mouseClicked(MouseEvent e) {
                                 if(!b){
                                     requestShapeSize();
                                     if(r){
                                         g.drawRect(e.getX(), e.getY(), w,h);
                                     }
                                     if(c){
                                         g.drawOval(e.getX(), e.getY(), w,h);
                                     }
                                     if(l){
                                         g.drawLine(e.getX(), e.getY(), e.getX()+ w,e.getY()+h);

                                     }
                                 }
                             }
                         }
        );

    }

    /**This method makes sure whether you want to draw a certain shape. If so, it will ask you
     for the dimensions of the shape that you want to draw. If you want to draw with the
     brush/pen after using a button, you will have to press the brush button again.**/
    public void requestShapeSize(){
        JOptionPane shapeConfirm = new JOptionPane();
        if(r){
            String confirm = shapeConfirm.showInputDialog("Are you sure you want to draw a rectangle? (yes or no)");
            if(confirm.equals("yes")){
                w = Integer.parseInt(shapeConfirm.showInputDialog("Enter rectangle width:"));
                h = Integer.parseInt(shapeConfirm.showInputDialog("Enter rectangle height:"));
            }
            else{
                shapeConfirm.setVisible(false);
                w=0;
                h=0;
            }
        }
        if(c){
            String confirm = shapeConfirm.showInputDialog("Are you sure you want to draw a circle? (yes or no)");
            if(confirm.equals("yes")){
                w = Integer.parseInt(shapeConfirm.showInputDialog("Enter circle width:"));
                h = Integer.parseInt(shapeConfirm.showInputDialog("Enter circle height:"));
            }
            else{
                shapeConfirm.setVisible(false);
                w=0;
                h=0;
            }
        }
        if(l){
            String confirm = shapeConfirm.showInputDialog("Are you sure you want to draw a line? (yes or no)");
            if(confirm.equals("yes")){
                w = Integer.parseInt(shapeConfirm.showInputDialog("How far right should the end of the line be:"));
                h = Integer.parseInt(shapeConfirm.showInputDialog("How far down should the end of the line be:"));
            }
            else{
                shapeConfirm.setVisible(false);
                w=0;
                h=0;
            }
        }


    }

    /**The method below does the most work due to it having to establish the functions and
     positions of every single button on the interface**/
    private void buttons(){
        red.setBackground(Color.red);
        orange.setBackground(new Color(250,123,24));
        yellow.setBackground(Color.yellow);
        green.setBackground(Color.green);
        blue.setBackground(Color.blue);
        purple.setBackground(p);
        black.setBackground(Color.black);
        black.setForeground(Color.white);
        era.setBackground(Color.white);
        clear.setBackground(new Color(65, 61, 61));
        clear.setForeground(Color.WHITE);
        rect.setBackground(new Color(65, 61, 61));
        rect.setForeground(Color.WHITE);
        circle.setBackground(new Color(65, 61, 61));
        circle.setForeground(Color.WHITE);
        brush.setBackground(new Color(65, 61, 61));
        brush.setForeground(Color.WHITE);
        line.setBackground(new Color(65, 61, 61));
        line.setForeground(Color.WHITE);
        red.setPreferredSize(new Dimension(100, 50));
        orange.setPreferredSize(new Dimension(100, 50));
        yellow.setPreferredSize(new Dimension(100, 50));
        green.setPreferredSize(new Dimension(100, 50));
        blue.setPreferredSize(new Dimension(100, 50));
        purple.setPreferredSize(new Dimension(100, 50));
        black.setPreferredSize(new Dimension(100, 50));
        era.setPreferredSize(new Dimension(100, 50));
        clear.setPreferredSize(new Dimension(100, 50));
        rect.setPreferredSize(new Dimension(100, 50));
        circle.setPreferredSize(new Dimension(100, 50));
        brush.setPreferredSize(new Dimension(100, 50));
        line.setPreferredSize(new Dimension(100, 50));

        JPanel title = new JPanel();
        title.setBackground(Color.gray);
        title.setPreferredSize(new Dimension(300,100));
        controls.add(title, BorderLayout.NORTH);
        JLabel cont = new JLabel("CONTROLS");
        cont.setFont(new Font(Font.SERIF, Font.PLAIN, 50));
        title.add(cont, BorderLayout.CENTER);




        controls.add(red, BorderLayout.CENTER);
        controls.add(orange, BorderLayout.CENTER);
        controls.add(yellow, BorderLayout.CENTER);
        controls.add(green, BorderLayout.CENTER);
        controls.add(blue, BorderLayout.CENTER);
        controls.add(purple, BorderLayout.CENTER);
        controls.add(black, BorderLayout.CENTER);
        controls.add(era, BorderLayout.CENTER);
        controls.add(size, BorderLayout.SOUTH);
        controls.add(clear, BorderLayout.SOUTH);
        controls.add(rect, BorderLayout.SOUTH);
        controls.add(circle, BorderLayout.SOUTH);
        controls.add(line, BorderLayout.SOUTH);
        controls.add(brush, BorderLayout.SOUTH);




        red.addActionListener(ae -> dc = Color.red);
        orange.addActionListener(ae -> dc = new Color(250,123,24));
        yellow.addActionListener(ae -> dc = Color.yellow);
        green.addActionListener(ae -> dc = Color.green);
        blue.addActionListener(ae -> dc = Color.blue);
        purple.addActionListener(ae -> dc = p);
        black.addActionListener(ae -> dc = Color.black);
        era.addActionListener(ae -> dc = Color.white);
        clear.addActionListener(ae -> dispose());
        clear.addActionListener(ae -> new Paint());


        rect.addActionListener(ae -> r = true);
        rect.addActionListener(ae -> b = false);
        rect.addActionListener(ae -> l = false);
        rect.addActionListener(ae -> c = false);


        brush.addActionListener(ae -> b = true);
        brush.addActionListener(ae -> r = false);
        brush.addActionListener(ae -> l = false);
        brush.addActionListener(ae -> c = false);


        circle.addActionListener(ae -> c = true);
        circle.addActionListener(ae -> b = false);
        circle.addActionListener(ae -> l = false);
        circle.addActionListener(ae -> r = false);


        line.addActionListener(ae -> l = true);
        line.addActionListener(ae -> b = false);
        line.addActionListener(ae -> r = false);
        line.addActionListener(ae -> c = false);


    }
    public static void main(String[] args) {
        new Paint();
    }



    /**The method below places the drawing panel and the control panel in the correct spots**/
    public void panel(){
        canvas.setBackground(Color.white);
        controls.setBackground(Color.gray);
        canvas.setPreferredSize(new Dimension(1200, 500));
        controls.setPreferredSize(new Dimension(300, 500));
        getContentPane().add(canvas, BorderLayout.WEST);
        getContentPane().add(controls, BorderLayout.EAST);
    }



    /**The method below allows you to draw with the brush**/
    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics g = getGraphics();
        g.setColor(dc);
        if(b){
            if(e.getX()<1200-size.getValue()+10){
                g.fillOval(e.getX(), e.getY(), size.getValue(), size.getValue());
            }
        }
        else{
            mxe = e.getX();
            mye = e.getY();

        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}


