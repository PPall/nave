package Jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;

public class Fase1 extends JPanel implements ActionListener {

    private Image fundo;
    private Nave nave;
    private Timer timer;
    private boolean emJogo, continuar;
    private List<Inimigo> inimigo;
    private int[][] coordenadas = {{2380, 29}, {2600, 59}, {1380, 89},
    {780, 109}, {580, 139}, {880, 239}, {790, 259},
    {760, 50}, {790, 150}, {1980, 209}, {560, 45}, {510, 70},
    {930, 159}, {590, 80}, {530, 60}, {940, 59}, {990, 30},
    {920, 200}, {900, 259}, {660, 50}, {540, 90}, {810, 220},
    {860, 20}, {740, 180}, {820, 128}, {490, 170}, {700, 30},
    {920, 300}, {856, 328}, {456, 320}};

    public Fase1() {

        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TecladoAdapter());

        ImageIcon referencia = new ImageIcon("src\\Imagens\\nebula.jpg");
        fundo = referencia.getImage();

        nave = new Nave();
        emJogo = true;
        continuar = true;

        inicializaInimigos();

        timer = new Timer(5, this);
        timer.start();

    }

    public void inicializaInimigos() {
        inimigo = new ArrayList<Inimigo>();
        for (int i = 0; i < coordenadas.length; i++) {
            inimigo.add(new Inimigo(coordenadas[i][1], -coordenadas[i][0]));
        }
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);

        if (!continuar) {

            ImageIcon continuar = new ImageIcon("src\\Imagens\\continuar.png");
            graficos.drawImage(continuar.getImage(), 0, 0, null);

        } else if (emJogo) {

            graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);

            List<Missil> misseis = nave.getMisseis();
            for (int i = 0; i < misseis.size(); i++) {
                Missil m = (Missil) misseis.get(i);
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }

            for (int i = 0; i < inimigo.size(); i++) {
                Inimigo in = (Inimigo) inimigo.get(i);
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }
            graficos.setColor(Color.WHITE);
            graficos.drawString("INIMIGOS " + inimigo.size(), 5, 15);

        } else {
            ImageIcon gameover = new ImageIcon("src\\Imagens\\game_over.jpg");
            graficos.drawImage(gameover.getImage(), 0, 0, null);

        }

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inimigo.size() == 0) {
            continuar = false;
        }

        List<Missil> misseis = nave.getMisseis();
        for (int i = 0; i < misseis.size(); i++) {
            Missil m = (Missil) misseis.get(i);
            if (m.isVisivel) {
                m.mexer();
            } else {
                misseis.remove(i);
            }
        }

        for (int i = 0; i < inimigo.size(); i++) {
            Inimigo in = (Inimigo) inimigo.get(i);
            if (in.isVisivel) {
                in.mexer();
            } else {
                inimigo.remove(i);
            }
        }
        nave.mexer();
        checarColisoes();
        repaint();
    }

    public void checarColisoes() {
        Rectangle formaNave = nave.getBounds();
        Rectangle formaInimigo;
        Rectangle formaMissil;
        for (int i = 0; i < inimigo.size(); i++) {
            Inimigo tempInimigo = inimigo.get(i);
            formaInimigo = tempInimigo.getBounds();

            if (formaNave.intersects(formaInimigo)) {
                nave.setVisivel(false);
                tempInimigo.setIsVisivel(false);

                emJogo = false;
            }

        }

        List<Missil> misseis = nave.getMisseis();

        for (int i = 0; i < misseis.size(); i++) {

            Missil tempMissil = misseis.get(i);
            formaMissil = tempMissil.getBounds();

            for (int j = 0; j < inimigo.size(); j++) {

                Inimigo tempInimigo = inimigo.get(j);
                formaInimigo = tempInimigo.getBounds();

                if (formaMissil.intersects(formaInimigo)) {

                    tempInimigo.setIsVisivel(false);
                    tempMissil.setIsVisivel(false);

                }

            }

        }

    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (emJogo & continuar) {

                nave.keyPressed(e);

            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                emJogo = true;
                continuar = true;
                nave = new Nave();
                inicializaInimigos();
                nave.keyPressed(e);
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            nave.keyReleased(e);
        }

    }
}
