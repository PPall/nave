package Jogo;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Missil {

    private int x, y;
    boolean isVisivel;
    private int largura, altura;
    private Image imagem;

    private static final int VELOCIDADE = 2;

    public Missil(int x, int y) {
        this.x = x;
        this.y = y;
        
        ImageIcon referencia = new ImageIcon("src\\Imagens\\inimigo_2.png");
        imagem = referencia.getImage();
        
        largura = imagem.getWidth(null);
        altura = imagem.getHeight(null);
        
        isVisivel = true;
    }

    public void mexer() {
        this.y -= VELOCIDADE;
        if (y < 0) {
            isVisivel = false;
        }
    }

    public boolean isIsVisivel() {
        return isVisivel;
    }

    public void setIsVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
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

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }
}
