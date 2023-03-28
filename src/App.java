import java.net.URI;
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

        //exibir e manipular os dados
        for (int i = 0; i < 10; i++) {
            Map<String, String> filme = listaDeFilmes.get(i);
            System.out.println("\u001b[1mTitulo: \u001b[m" + filme.get("title"));
            System.out.println("\u001b[1mImagem: \u001b[m" + filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            double numDoubleEstrelas = Double.parseDouble(filme.get("imDbRating"));
            int numEstrela = (int) numDoubleEstrelas;
            for (int j = 1; j <= numEstrela; j++){
                System.out.print("⭐");
            }
            System.out.println("\n");
        }
    }
}
