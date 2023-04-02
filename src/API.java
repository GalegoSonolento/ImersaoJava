import com.sun.source.doctree.TextTree;

public enum API {
    // url certa imdb: https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json
    // TODO: montar um extrator e um item específico para a api das lingagens
    IMDB_TOP_MOVIES("http://localhost:8080/lista-linguagens", new ExtratorDeConteudoIMdB()),
    NASA("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14", new ExtratorDeConteudoNASA());

    private String url;
    private ExtratorDeConteudo exrator;

    API(String url, ExtratorDeConteudo extrator) {
        this.url = url;
        this.exrator = extrator;
    }

    public String getUrl(){
        return url;
    }

    public ExtratorDeConteudo getExrator() {
        return exrator;
    }
}
