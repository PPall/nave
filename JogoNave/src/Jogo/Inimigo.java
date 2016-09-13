package Jogo;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Inimigo {

    private int x, y;
    boolean isVisivel;
    private int largura, altura;
    private Image imagem;
    private int retorno;

    private static final int COMPRIMENTO = 600;
    private static final int VELOCIDADE = 2;
    private static int contador = 0;

    public Inimigo(int x, int y) {
        this.x = x;
        this.y = y;
        retorno = y;

        ImageIcon referencia;
        if (contador++ % 3 == 0) {

            referencia = new ImageIcon("src\\Imagens\\inimigo_1.png");
            
        }else{
            
            referencia = new ImageIcon("src\\Imagens\\inimigo_2.png");
            
        }
        imagem = referencia.getImage();
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
        isVisivel = true;
    }

    public void mexer() {
        if (this.y > 600) {
            this.y = retorno;
        } else {
            this.y += VELOCIDADE;
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
