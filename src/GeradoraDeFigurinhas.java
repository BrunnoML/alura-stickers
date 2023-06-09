import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.awt.BasicStroke;
//import java.net.URL;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
  
  public void cria(InputStream inputStream, String nomeArquivo, String texto) throws Exception {

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
    var fonte = new Font("Impact", Font.BOLD, 100);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(fonte);

    // escrever uma frase na nova imagem
    //String texto = "TOPZERA"; RETIRADO para receber o texto de fora
    FontMetrics fontMetrics = graphics.getFontMetrics();
    Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
    int larguraTexto = (int) retangulo.getWidth();
    int posicaoTextoX = (largura - larguraTexto) / 2;
    int posicaoTextoY = novaAltura - 100;
    graphics.drawString(texto, posicaoTextoX, novaAltura -100);

    //adicionar contorno na imagem
    FontRenderContext fontRenderContext = graphics.getFontRenderContext();
    var textLayout = new TextLayout(texto, fonte, fontRenderContext);

    Shape outline = textLayout.getOutline(null);
    AffineTransform transform = graphics.getTransform();
    transform.translate(posicaoTextoX, posicaoTextoY);
    graphics.setTransform(transform);

    var outlineStroke = new BasicStroke(largura * 0.004f);
    graphics.setStroke(outlineStroke);

    graphics.setColor(Color.BLACK);
    graphics.draw(outline);
    graphics.setClip(outline);



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
