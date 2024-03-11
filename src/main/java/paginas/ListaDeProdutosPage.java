package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListaDeProdutosPage {
    private WebDriver navegador;

    public ListaDeProdutosPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public FormularioDeAdicaoDeProdutoPage acessarFOrmAdicionarNovoProduto(){
        //Ir para a página de cadastro de produtos
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        return new FormularioDeAdicaoDeProdutoPage(navegador);
    }

    public String capturarMensagemApresentada() {
        //Validar que a mensagem de erro foi apresentada
        return navegador.findElement(By.cssSelector(".toast.rounded")).getText();
    }
}
