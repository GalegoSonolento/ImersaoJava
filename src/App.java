import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!");

        //Realizar conexão https e buscar top 250 filmes
        //String imdbKey = System.getenv("IMDB_GIT_HANDLER");

        //String url = imdbKey;

        //String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        //String url = "https://URLBIZONHAapi.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        API api = API.IMDB_TOP_MOVIES;

        String url = api.getUrl();
        ExtratorDeConteudo extrator = api.getExrator();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);


        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var dir = new File("figurinhas/");
        dir.mkdir();

        //exibir e manipular os dados
        var geradora = new GeradoraDeSticker();
        for (int i = 0; i < 5; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "figurinhas/" + conteudo.titulo() + ".png";


            geradora.cria(inputStream, nomeArquivo);
            System.out.println(conteudo.titulo());
        }
    }
}
