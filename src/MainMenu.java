import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class MainMenu extends JPanel {

    private boolean play = false;

    public boolean getPlay() {
        return this.play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }
    

    public MainMenu() {
        setLayout(null);

        JButton playButton = new JButton("PLAY");
        JButton rulesButton = new JButton("RULES");

        playButton.setBounds(600, 480, 360, 200);
        rulesButton.setBounds(140, 480, 360, 200);

        Color buttonBg = new Color(60, 60, 60);
        playButton.setBackground(buttonBg);
        rulesButton.setBackground(buttonBg);

        playButton.setForeground(Color.WHITE);
        rulesButton.setForeground(Color.WHITE);

        Font buttonFont = new Font("Arial Rounded MT Bold", Font.PLAIN, 64);
        playButton.setFont(buttonFont);
        rulesButton.setFont(buttonFont);

        rulesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(getParent(), Constants.RULES_TEXT, "RULES", JOptionPane.INFORMATION_MESSAGE);
            }

        });

        playButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setPlay(true);
                setEnabled(false);
            }
 
        });

        add(playButton);
        add(rulesButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(GuiUtilities.IMAGES.get("mainMenu"), 0, 0, this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1100, 800);
    }
}
