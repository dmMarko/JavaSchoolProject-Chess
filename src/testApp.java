import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;

class testApp extends JPanel {

    public static final Image boardImg = new ImageIcon("resources/Board.png").getImage();

    public testApp() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage("src/resources/Board.png");
        g.drawImage(i, 0, 0, this);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 800);
        testApp panel = new testApp();
        window.add(panel);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}