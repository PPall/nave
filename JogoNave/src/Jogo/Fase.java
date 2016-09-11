package Jogo;

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

public class Fase extends JPanel implements ActionListener{
    
    private Image fundo;
    private Nave nave;
    private Timer timer;
    private boolean emJogo;
    private list <Inimigos> inimigo;
    private int[][] coordenadas = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 },
{ 780, 109 }, { 580, 139 }, { 880, 239 }, { 790, 259 },
{ 760, 50 }, { 790, 150 }, { 1980, 209 }, { 560, 45 }, { 510, 70 },
{ 930, 159 }, { 590, 80 }, { 530, 60 }, { 940, 59 }, { 990, 30 },
{ 920, 200 }, { 900, 259 }, { 660, 50 }, { 540, 90 }, { 810, 220 },
{ 860, 20 }, { 740, 180 }, { 820, 128 }, { 490, 170 }, { 700, 30 },
{ 920, 300 }, { 856, 328 }, { 456, 320 } };
    
    public Fase(){
        
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TecladoAdapter());
        
        ImageIcon referencia1= new ImageIcon("src\\Imagens\\nebula.jpg");
        fundo = referencia1.getImage();
        
        nave = new Nave();
        emJogo=true;
        
        timer = new Timer(5,this);
        timer.start();
        
    }   
    public void inicializaInimigos(){
        inimigos=new ArrayList<Inimigos>();
        for (int i = 0; i < coordenadas.length; i++) {
            inimigos.add(new Inimigo(coodenadas[i][0],[i][1]);
        }   
    }
    @Override
    public void paint(Graphics g){
        
        Graphics2D graficos = (Graphics2D)g;
        graficos.drawImage(fundo, 0, 0, null);
        graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);
        
        List<Missil>misseis=nave.getMisseis();
        for(int i=0;i<misseis.size();i++){
            Missil m=(Missil)misseis.get(i);
            graficos.drawImage(m.getImagem(),m.getX(),m.getY(),this);
        }
        g.dispose();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        List<Missil>misseis=nave.getMisseis();
        for(int i=0;i<misseis.size();i++){
            Missil m=(Missil)misseis.get(i);
            if(m.isVisivel)
                m.mexer();
            else
                misseis.remove(i);
        }
        nave.mexer();
        repaint();
    }
    public void checarColisoes(){
        Rectangle formaNave=nave.getBounds();
        Rectangle formaInimigo;
        Rectangle formaMissil;
        for (int i = 0; i < inimigos.size(); i++) {
            Inimigo tempInimigo= inimigos.get(i);
            
        }
    }
    private class TecladoAdapter extends KeyAdapter{
        
        @Override
        public void keyPressed(KeyEvent e){
            nave.keyPressed(e);
        }
        
        @Override
        public void keyReleased(KeyEvent e){
            nave.keyReleased(e);
        }
        
    }
}