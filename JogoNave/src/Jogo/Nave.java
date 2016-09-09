package Jogo;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Nave {
    
    private int x;
    private int y;
    private int dx,dy;
    private List<Missil>misseis;
    private Image imagem;
    private boolean isVisivel;
    private int altura,largura;
    public Nave(){
        
        ImageIcon referencia = new ImageIcon("src\\Imagens\\nave.png");
	imagem = referencia.getImage();
        misseis=new ArrayList<Missil>();
        x = 100;
        y = 100;
        altura=imagem.getHeight(null);
        largura=imagem.getWidth(null);
    }

    public void mexer(){
        x += dx;
        y += dy;
        if(this.x<-4)//441 1 319
            this.x=-4;
        if(this.x>441)//441 1 319
            this.x=441;
        if(this.y<1)//441 1 319
            this.y=1;
        if(this.y>319)//441 1 319
            this.y=319;
    }
    public void atira(){
        this.getMisseis().add(new Missil(x+largura/3,y+altura/2));
    }
    
    public void keyPressed(KeyEvent tecla){
        int codigo = tecla.getKeyCode();
        if(codigo==KeyEvent.VK_SPACE)
            atira();
        if(codigo == KeyEvent.VK_UP){
            dy = -1;
        }
        
        if(codigo == KeyEvent.VK_DOWN){
            dy = 1;
        }
        
        if(codigo == KeyEvent.VK_LEFT){
            dx = -1;
        }
        
        if(codigo == KeyEvent.VK_RIGHT){
            dx = 1;
        }
    }
    
    public void keyReleased(KeyEvent tecla){
        int codigo = tecla.getKeyCode();
        
        if(codigo == KeyEvent.VK_UP){
            dy = 0;
        }
        
        if(codigo == KeyEvent.VK_DOWN){
            dy = 0;
        }
        
        if(codigo == KeyEvent.VK_LEFT){
            dx = 0;
        }
        
        if(codigo == KeyEvent.VK_RIGHT){
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
    public void setVisivel(boolean visivel)
	{
		isVisivel = visivel;
	}

    public List<Missil> getMisseis() {
        return misseis;
    }
     
}
