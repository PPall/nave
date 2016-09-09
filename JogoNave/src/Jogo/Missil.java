package Jogo;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Missil {
    private int x,y;
    boolean isVisivel;
    private Image imagem;
    
    private static final int COMPRIMENTO=420;
    private static final int VELOCIDADE=2;
    
    public Missil(int x,int y){
        this.x=x;
        this.y=y;
        ImageIcon referencia=new ImageIcon("C:\\Users\\Paulo Pereira\\Documents\\NetBeansProjects\\JogoNave\\src\\Imagens\\missil.png");
        imagem=referencia.getImage();
        isVisivel=true;
    }
    public void mexer(){
        this.y-=VELOCIDADE;
        if(y>COMPRIMENTO)
            isVisivel=false;
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
}
