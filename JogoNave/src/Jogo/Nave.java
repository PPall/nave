package Jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Nave {

    private int x;
    private int y;
    private int dx, dy;
    private List<Missil> misseis;
    private Image imagem;
    private boolean isVisivel;
    private int altura, largura;

    public Nave() {

        ImageIcon referencia = new ImageIcon("src\\Imagens\\nave.png");
        imagem = referencia.getImage();
        misseis = new ArrayList<Missil>();
        x = 370;
        y = 500;
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);

    }

    public void mexer() {
        
        x += dx;
        y += dy;
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.x > 740) {
            this.x = 740;
        }
        if (this.y < 1) {
            this.y = 1;
        }
        if (this.y > 500) {
            this.y = 500;
        }
    }

    public void atira() {
        this.misseis.add(new Missil(x + 19 , y - 25));
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_SPACE) {
            atira();
        }
        if (codigo == KeyEvent.VK_UP) {
            dy = -3;
        }

        if (codigo == KeyEvent.VK_DOWN) {
            dy = 3;
        }

        if (codigo == KeyEvent.VK_LEFT) {
            dx = -3;
        }

        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 3;
        }
    }

    public void keyReleased(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (codigo == KeyEvent.VK_DOWN) {
            dy = 0;
        }

        if (codigo == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setVisivel(boolean visivel) {
        isVisivel = visivel;
    }

    public List<Missil> getMisseis() {
        return misseis;
    }

}
