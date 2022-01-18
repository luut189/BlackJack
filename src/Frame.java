import javax.swing.JFrame;

public class Frame extends JFrame {

    Panel panel;
    Frame(String name, int width, int height) {
        panel = new Panel(width, height);
        this.setTitle(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
