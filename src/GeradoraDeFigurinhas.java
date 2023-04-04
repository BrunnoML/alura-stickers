import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
//import java.net.URL;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
  
  public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

  //public void cria() throws Exception {
    // leitura da imagem direto de uma pasta do computador
    //BufferedImage imagemOriginal = ImageIO.read(new File("entrada/filme.jpg"));
    

    // leitura da imagem de uma URL
    //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@.jpg").openStream();
    
    BufferedImage imagemOriginal = ImageIO.read(inputStream);


    // cria nova imagem em memória com transparência e com tamanho novo
    int largura = imagemOriginal.getWidth();
    int altura = imagemOriginal.getHeight();
    int novaAltura = altura + 200;
    BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

    // copiar a imagem original para nova imagem (em memória)
    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
    graphics.drawImage(imagemOriginal, 0, 0, null);

    // configurar a fonte
    var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 100);
    graphics.setColor(Color.BLUE);
    graphics.setFont(fonte);

    // escrever uma frase na nova imagem
    graphics.drawString("TOP!", 50, novaAltura -100);

    // escrever a nova imagem em um arquivo para pasta no computador
   // ImageIO.write(novaImagem, "png", new File("saida/figurinha.png"));

   // escrever a nova imagem em um arquivo
   ImageIO.write(novaImagem, "png", new File(nomeArquivo));


  }

/* para rodar o código indepentente do App.java
  public static void main(String[] args) throws Exception {
    var geradora = new GeradoraDeFigurinhas();
    geradora.cria();
  }
*/

}
