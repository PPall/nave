package Jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Inimigo {
    private int x,y;
    boolean isVisivel;
    private int largura, altura;
    private Image imagem;
    
    private static final int COMPRIMENTO=420;
    private static final int VELOCIDADE=2;
    
    public Inimigo(int x,int y){
        this.x=x;
        this.y=y;
        ImageIcon referencia=new ImageIcon("src\\Imagens\\inimigo_1.png");
        this.largura=imagem.getWidth(null);
        this.altura=imagem.getHeight(null);
        imagem=referencia.getImage();
        isVisivel=true;
    }
    public void mexer(){
        if(this.y<0)
            this.y=COMPRIMENTO;
        else
            this.y-=VELOCIDADE;
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
    public Rectangle getBounds(){
        return new Rectangle(x,y,largura,altura);
    }
}
