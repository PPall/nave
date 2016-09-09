package Jogo;

import javax.swing.JFrame;

public class Container extends JFrame {

    public Container() {
        add(new Fase());
        setSize(500,420);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Jogo da Nave");
    }

    public static void main(String[] args) {
        new Container();
    }
}
