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

public class Fase extends JPanel implements ActionListener {

    private Image fundo;
    private Nave nave;
    private Timer timer;
    private boolean emJogo, continuar;
    private List<Inimigo> inimigo;
    private int[][] coordenadas = {{2680, 129}, {2900, 159}, {1680, 189},
    {1080, 409}, {880, 239}, {1180, 339}, {1090, 359},
    {1060, 150}, {1090, 250}, {2280, 309}, {860, 145}, {810, 170},
    {1230, 259}, {890, 180}, {830, 160}, {1240, 159}, {1290, 130},
    {1220, 300}, {1200, 359}, {960, 150}, {840, 190}, {1110, 320},
    {1160, 120}, {1040, 280}, {1120, 228}, {790, 270}, {1000, 130},
    {1220, 400}, {1156, 428}, {756, 420}};

    public Fase() {

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
