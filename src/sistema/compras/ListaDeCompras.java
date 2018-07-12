package sistema.compras;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sistema.comparadores.OrdemAlfabetica;
import sistema.comparadores.OrdemCategoria;
import sistema.produtos.*;

public class ListaDeCompras {

	private String descritor;
	private List<Produto> produtos;
	private String data;

	public ListaDeCompras(String descritor) {
		this.descritor = descritor;
		produtos = new ArrayList<>();
		this.data = "10/07/2018";
	}

	public void adicionaCompra(int qtd, Produto produto) {
		double valor = qtd;
		adicionaCompra(valor, produto);
	}

	public void adicionaCompra(double qtd, Produto produto) {
		String valor = String.valueOf(qtd);
		Produto novoProduto = null;
		if (produto instanceof ProdutoQuantidade) {
			novoProduto = new ProdutoQuantidade((ProdutoQuantidade) produto);
			novoProduto.atualizaItem("unidade", valor);

		} else if (produto instanceof ProdutoQuilo) {
			novoProduto = new ProdutoQuilo((ProdutoQuilo) produto);
			novoProduto.atualizaItem("kg", valor);

		} else if (produto instanceof ProdutoUnidade) {
			novoProduto = new ProdutoUnidade((ProdutoUnidade) produto);
			novoProduto.atualizaItem("unidade", valor);
		}
		produtos.add(novoProduto);
	}

	public String getDescritor() {
		return descritor;
	}

	public String getData() {
		return data;
	}
	
	public String pesquisaCompra(int id) {
		for (Produto produto : produtos) {
			if (produto.getId() == id)
				return produto.toString();
		}
		return null;
	}
	
	public void deletaCompra(int id) {
		for (Produto produto : produtos) {
			if (produto.getId() == id) {
				produtos.remove(produto);
				return;
			}
		}
	}

	@Override
	public String toString() {
		Collections.sort(produtos, new OrdemAlfabetica());
		Collections.sort(produtos, new OrdemCategoria());
		String temp = "";
		for (Produto produto : produtos) {
			temp += produto.toString(0) + System.lineSeparator();
		}
		temp.trim();

		return temp;
	}

}
