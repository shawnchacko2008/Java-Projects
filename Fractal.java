//author: Shawn Chacko
//Project : Fractals "Gallery"

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
public class Fractal extends JFrame {

    static JPanel controls = new JPanel();

    static JSlider size = new JSlider();
    static JSlider angle_fractal = new JSlider();
    static JSlider sides = new JSlider();

    static JTextArea recursion_level = new JTextArea("Recursion Level");
    static JTextArea angle = new JTextArea("Branch Angle");

    static JTextArea num_sides = new JTextArea("Number Of Sides");


    static boolean c = false;
    static boolean cv2 = false;
    static boolean t = false;
    static boolean s = false;
    static boolean dc = false;
    static boolean dcv2 = false;

    static JButton C = new JButton("Custom");

    static JButton CV2 = new JButton("Custom V2");

    static JButton T = new JButton("Tree");

    static JButton S = new JButton("Sierpinski");

    static JButton DC = new JButton("Dragon Curve");
    static JButton DCV2 = new JButton("Dragon Curve Spiral");



    public Fractal() {
        setTitle("Fractals");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel();
        buttons();
        initGUI();
        pack();
        buttons();
    }

    public void panel(){
        add(controls, BorderLayout.SOUTH);
        controls.setPreferredSize(new Dimension(getWidth(),100 ));
        controls.setVisible(true);
        controls.setBackground(Color.WHITE);
    }

    public void buttons(){
        C.setPreferredSize(new Dimension(100, 50));
        CV2.setPreferredSize(new Dimension(100, 50));
        T.setPreferredSize(new Dimension(100, 50));
        S.setPreferredSize(new Dimension(100, 50));
        DC.setPreferredSize(new Dimension(200, 50));
        DCV2.setPreferredSize(new Dimension(200, 50));

        controls.add(C, BorderLayout.CENTER);
        controls.add(CV2, BorderLayout.CENTER);
        controls.add(T, BorderLayout.CENTER);
        controls.add(S, BorderLayout.CENTER);
        controls.add(DC, BorderLayout.CENTER);
        controls.add(DCV2, BorderLayout.CENTER);
        controls.add(size, BorderLayout.CENTER);
        controls.add(recursion_level, BorderLayout.CENTER);

        C.addActionListener(ae -> {
            angle_fractal.setVisible(false);
            sides.setVisible(false);
            num_sides.setVisible(false);
            angle.setVisible(false);
            size.setMaximum(5);
            size.setMinimum(1);
            c = true;
            cv2 = false;
            t = false;
            s = false;
            dc = false;
            dcv2 = false;
            repaint();
        });
        CV2.addActionListener(ae -> {
            controls.add(sides);
            sides.setVisible(true);
            controls.add(num_sides);
            num_sides.setVisible(true);
            sides.setMaximum(20);
            sides.setMinimum(2);
            angle_fractal.setVisible(false);
            angle.setVisible(false);
            size.setMaximum(12);
            size.setMinimum(3);
            c = false;
            cv2 = true;
            t = false;
            s = false;
            dc = false;
            dcv2 = false;
            repaint();
        });
        T.addActionListener(ae -> {
            controls.add(angle_fractal, BorderLayout.CENTER);
            controls.add(angle, BorderLayout.CENTER);
            sides.setVisible(false);
            num_sides.setVisible(false);
            angle.setVisible(true);
            angle_fractal.setVisible(true);
            angle_fractal.setMaximum(360);
            angle_fractal.setMinimum(1);
            size.setMaximum(9);
            size.setMinimum(1);
            c = false;
            cv2 = false;
            t = true;
            s = false;
            dc = false;
            dcv2 = false;
            repaint();
        });
        S.addActionListener(ae -> {
            angle_fractal.setVisible(false);
            sides.setVisible(false);
            num_sides.setVisible(false);
            angle.setVisible(false);
            size.setMaximum(8);
            size.setMinimum(1);
            c = false;
            cv2 = false;
            t = false;
            s = true;
            dc = false;
            dcv2 = false;
            repaint();
        });
        DC.addActionListener(ae -> {
            angle_fractal.setVisible(false);
            sides.setVisible(false);
            num_sides.setVisible(false);
            angle.setVisible(false);
            size.setMaximum(18);
            size.setMinimum(1);
            c = false;
            cv2 = false;
            t = false;
            s = false;
            dc = true;
            dcv2 = false;
            repaint();
        });

        DCV2.addActionListener(ae -> {
            angle_fractal.setVisible(false);
            sides.setVisible(false);
            num_sides.setVisible(false);
            angle.setVisible(false);
            size.setMaximum(16);
            size.setMinimum(1);
            c = false;
            cv2 = false;
            t = false;
            s = false;
            dc = false;
            dcv2 = true;
            repaint();
        });

        size.addChangeListener( cl -> {
            repaint();
        });

        angle_fractal.addChangeListener( cl -> {
            repaint();
        });
        sides.addChangeListener( cl -> {
            repaint();
        });

    }


    public void initGUI(){

        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel);
    }

    static class DrawingPanel extends JPanel{
        public DrawingPanel() {
            setPreferredSize(new Dimension(2400, 1000)); // Set the size of the drawing panel
            setBackground(Color.BLACK);
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            g.setColor(Color.WHITE);
            if (c) {
                customFractal(size.getValue(), getWidth()/2, getHeight()/2, 100, g);
            } else if (cv2) {
                customFractalv2(sides.getValue(), size.getValue(), getWidth()/2, getHeight()/2, 0, 200, g2);
            } else if (t) {
                fractal_Tree(size.getValue(), getWidth()/2, 800, -90, 300, g2);
            } else if (s) {
                Sierpinski(getWidth()/2-200, 600, 400, size.getValue(), g);
            } else if (dc) {
                dragon_Curve(size.getValue(), getWidth()/2-300, 600, 600, 315, g2, Color.RED);
            }else if(dcv2){
                dragon_Curve_v2(g2);
            }
        }




        public static void customFractal(int level, int x, int y, int length, Graphics g) {
            if(level != 0){
                g.setColor(Color.WHITE);
                g.drawLine(x,y,x, y-length);
                g.drawLine(x,y-length, x-length, y-(length*2));
                g.drawLine(x,y-length, x+length, y-(length*2));
                g.setColor(Color.CYAN);
                g.drawLine(x,y,x+length, y);
                g.drawLine(x+length,y, x+(length*2), y-length);
                g.drawLine(x+length,y, x+(length*2), y+length);
                g.setColor(Color.CYAN);
                g.drawLine(x,y,x-length, y);
                g.drawLine(x-length,y, x-(length*2), y-length);
                g.drawLine(x-length,y, x-(length*2), y+length);
                g.setColor(Color.WHITE);
                g.drawLine(x,y,x, y+length);
                g.drawLine(x,y+length, x-length, y+(length*2));
                g.drawLine(x,y+length, x+length, y+(length*2));
                customFractal(level -1, x-length,y-(length*2),length/2, g );
                customFractal(level -1, x+length,y-(length*2),length/2, g );
                customFractal(level -1, x+(length*2),y+length,length/2, g );
                customFractal(level -1, x+(length*2),y-length,length/2, g );
                customFractal(level -1, x-(length*2),y+length,length/2, g );
                customFractal(level -1, x-(length*2),y-length,length/2, g );
                customFractal(level -1, x-length,y+(length*2),length/2, g );
                customFractal(level -1, x+length,y+(length*2),length/2, g );
            }
        }

        public void subFractal(int level, int x, int y, int angle,  int length,  Graphics2D g) {
            if(level != 0){
                int x2 = x + (int) (length * Math.cos(Math.toRadians(angle)));
                int y2 = y + (int) (length * Math.sin(Math.toRadians(angle)));
                int x3 = x2 + (int) (length/2 * Math.cos(Math.toRadians(angle+60)));
                int y3 = y2 + (int) (length/2 * Math.sin(Math.toRadians(angle+60)));
                g.setColor(Color.WHITE);
                g.drawLine(x,y,x2,y2);
                g.setColor(Color.CYAN);
                g.drawLine(x2,y2,x3,y3);
                subFractal(level-1, x2,y2, angle+30,(int)(length/1.5), g );
                subFractal(level-1, x3,y3, angle+90,(int)(length/1.5), g );
                subFractal(level-1, (x2+x)/2,(y2+y)/2, angle+45,length/3, g );
            }
        }

        public void customFractalv2(int sides,int level, int x, int y, int angle,  int length,  Graphics2D g){
            for (int i = 0; i < sides; i++) {
                subFractal(level-1, x,y, angle,length, g );
                angle += 360/sides;
            }
        }
        public void fractal_Tree(int level, int x, int y, int angle,  int length,  Graphics2D g) {
            if (level != 0) {
                g.setColor(Color.GREEN);
                int x2 = x + (int) (length * Math.cos(Math.toRadians(angle)));
                int y2 = y + (int) (length * Math.sin(Math.toRadians(angle)));
                g.drawLine(x, y, x2, y2);
                fractal_Tree(level - 1, x2, y2, angle - angle_fractal.getValue(), (length / 2), g);
                fractal_Tree(level - 1, x2, y2, angle + angle_fractal.getValue(), (length / 2), g);
                fractal_Tree(level - 1, x2, y2, angle - angle_fractal.getValue() * 2, (length / 2), g);
                fractal_Tree(level - 1, x2, y2, angle + angle_fractal.getValue() * 2, (length / 2), g);
            }
        }

        public void Sierpinski(int x, int y, int length, int level, Graphics g){
            if(level !=0){
                int x2 = x + (int) (length * Math.cos(Math.toRadians(300)));
                int y2 = y + (int) (length * Math.sin(Math.toRadians(300)));
                int x3 = x2 + (int) (length * Math.cos(Math.toRadians(60)));
                int y3 = y2 + (int) (length * Math.sin(Math.toRadians(60)));
                g.drawLine(x,y,x2,y2);
                g.drawLine(x2,y2,x3,y3);
                g.drawLine(x3,y3,x,y);
                Sierpinski(x,y,length/2, level-1, g);
                Sierpinski((x2+x)/2, (y2+y)/2, length/2, level-1, g);
                Sierpinski((x3+x)/2, y, length/2, level-1, g);
            }

        }

        public void dragon_Curve(int level, int x, int y, int length,int angle, Graphics2D g, Color c){
            if(level != 0){
                int x2 = x + (int) (length * Math.cos(Math.toRadians(angle)));
                int y2 = y + (int) (length * Math.sin(Math.toRadians(angle)));
                int x3 = x2 + (int) (length * Math.cos(Math.toRadians(angle+90)));
                int y3 = y2 + (int) (length * Math.sin(Math.toRadians(angle+90)));
                g.setColor(Color.BLACK);
                g.drawLine(x,y,(x3),y3);
                g.setColor(c);
                g.drawLine(x,y,x2,y2);
                g.drawLine(x2,y2,x3,y3);
                dragon_Curve(level-1, x,y,(int)(length/(Math.sqrt(2))), angle-45, g, c);
                dragon_Curve(level-1, x3,y3,(int)(length/(Math.sqrt(2))), angle+225, g, c);
            }
        }

        public void dragon_Curve_v2(Graphics2D g){
            dragon_Curve(size.getValue(), getWidth()/2,400,200, 315, g, Color.RED);
            dragon_Curve(size.getValue(), getWidth()/2,400,200, 315+90, g, Color.CYAN);
            dragon_Curve(size.getValue(), getWidth()/2,400,200, 315+180, g, Color.GREEN);
            dragon_Curve(size.getValue(), getWidth()/2,400,200, 315+270, g, Color.WHITE);
        }




    }

    public static void main(String[] args) {
        new Fractal();
    }
}