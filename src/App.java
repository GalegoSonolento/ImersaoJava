import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!");

        //Realizar conexão https e buscar top 250 filmes
        //String imdbKey = System.getenv("IMDB_GIT_HANDLER");
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //String url = imdbKey;
        URI endereco =  URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        //System.out.println(body);

        //Pegar dados interessantes (título, poster, classificação - nota)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        var dir = new File("figurinhas/");
        dir.mkdir();

        //exibir e manipular os dados
        var geradora = new GeradoraDeSticker();
        for (int i = 0; i < 5; i++) {
            Map<String, String> filme = listaDeFilmes.get(i);

            String urlimagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlimagem).openStream();
            String nomeArquivo = "figurinhas/" + titulo + ".png";

            System.out.println("\u001b[1mTitulo: \u001b[m" + filme.get("title"));
            System.out.println("\u001b[1mImagem: \u001b[m" + filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            double rating = Double.parseDouble(filme.get("imDbRating"));
            int numEstrela = (int) rating;
            for (int j = 1; j <= numEstrela; j++){
                System.out.print("⭐");
            }
            System.out.println("\n");
            String textoFigurinha;
            InputStream imagemSelo;
            if (rating >= 8) {
                textoFigurinha = "Brabo";
                imagemSelo = new FileInputStream(new File("selos/topper.jpg"));
            }
            else {
                textoFigurinha = "HMMMMMMM.......";
                imagemSelo = new FileInputStream(new File("selos/ruim.jpg"));
            }

            geradora.cria(inputStream, nomeArquivo, textoFigurinha, imagemSelo);
        }
    }
}
