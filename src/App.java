import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        

        // extrair só os dados que interessam (título, poster(imagem), classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
    
        //cria diretorio para arquivar as imagens caso ainda não exista
        var diretorio = new File("figurinhas/");
        diretorio.mkdir();

        // exibire manipular os dados
        // para cada um dos titulos, pega a url
        var geradora = new GeradoraDeFigurinhas();
        
        // for para todos os filmes
       // for (Map<String,String> filme : listaDeFilmes) {

        // for para os cinco primeiros filmes
        for (int index = 0; index < 5; index++) {
            var filme = listaDeFilmes.get(index);
            
        

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "figurinhas/" + titulo + ".png";

            
            geradora.cria(inputStream, nomeArquivo, "TOPZERA");   

            
            System.out.println(titulo);
            System.out.println();
        }
    }

}

