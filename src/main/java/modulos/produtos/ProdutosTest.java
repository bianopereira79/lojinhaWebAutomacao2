package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Teste Web Modulo Produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach() {
        // Abrir o navegador
        System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver-win64\\chromedriver.exe");
        this.navegador = new ChromeDriver();
        this.navegador.manage().window().maximize();
        //Vou definir um tempo de espera de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Navegar para a lojinha web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2");
    }

    @Test
    @DisplayName("Nao é permitido registrar produto com valor = R$ 0.00")
    public void testNaoEPermitidoCadastrarProdutoValorZero() {
        //Fazer login
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFOrmAdicionarNovoProduto()
                        .informarNomeDoProduto("Xbox One Auto")
                        .informarValorDoProduto("000")
                        .informarCoresDoProduto("Branco,Preto")
                        .submeterFormularioDeAdicaoComErro()
                        .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00",mensagemApresentada);
    }

    @Test
    @DisplayName("Nao é permitido registrar produto com valor maior que R$ 7000.00")
    public void testNaoEPermitidoCadastrarProdutoMaior7K() {
        //Fazer login
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFOrmAdicionarNovoProduto()
                .informarNomeDoProduto("Xbox One Auto")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("Branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00",mensagemApresentada);
    }

    @Test
    @DisplayName("Posso registrar produtos com valor dentro da faixa de R$ 0.01 a R$ 7000.00")
    public void testPossoCadastrarProdutoDentroDaFaixa() {
        //Fazer login
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFOrmAdicionarNovoProduto()
                .informarNomeDoProduto("Xbox One Auto")
                .informarValorDoProduto("500000")
                .informarCoresDoProduto("Branco")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso",mensagemApresentada);
    }


    @AfterEach
    public void afterEach() {
        //Fechando navegador
        navegador.quit();
    }
}
