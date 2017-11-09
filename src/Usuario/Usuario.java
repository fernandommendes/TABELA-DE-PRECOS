package usuario;

import java.util.ArrayList;

import dados.Produto;
import listaproduto.ListaProdutos;
import utilitarios.Console;

public class Usuario {

	public static void main(String[] args) {
		menu();
		System.out.println("\nFinal do aplicativo.");
		System.exit(0);
	}

	private static void menu() {
		int opcao = 0;
		do {
			System.out.println("\nTABELA DE PREÇOS");
			System.out.println("1-Incluir produto");
			System.out.println("2-Pesquisar produto pelo código");
			System.out.println("3-Pesquisar produtos pelo nome");
			System.out.println("4-Pesquisar produtos pela faixa de preço");
			System.out.println("5-Excluir produto pelo código");
			System.out.println("6-Alterar produto pelo código");
			System.out.println("7-Estatística de produtos");
			System.out.println("0-Sair");
			opcao = Console.readInt("Informe a opção: ");
			switch (opcao) {
				case 1:
					incluir();
					break;
				case 2:
					pesqCodigo();
					break;
				case 3:
					pesqNome();
					break;
				case 4:
					pesqPreco();
					break;
				case 5:
					excluir();
					break;
				case 6:
					alterar();
					break;
				case 7:
					estatistica();
					break;
				case 0 : break;	
				default:
					System.out.println("Opção inválida.");
					break;
			}
			
		} while (opcao!=0);
		
	}

	private static void alterar() {
		System.out.println("\nAlterar Produto\n");
		int codigo = Console.readInt("Informe o código: ");
		Produto obj = ListaProdutos.pesqProdCodigo(codigo);
		if (obj==null) {
			System.out.println("Não existe produto para o código.");
			return;
		} else {
			System.out.println(obj.toString());
		}
		String nome;
		double preco = 0;
		boolean alteracao = false;
		while (true) {
			nome = Console.readLine("Nome: ").trim();
			if (nome.isEmpty()) {
				break;
			} else {
				if (ListaProdutos.pesqProdNome(nome).size() > 0) {
					System.out.println("Já existe o produto cadastrado.");
				} else {
					obj.setNome(nome);
					alteracao = true;
					break;
				}
			}
		}
		while (true) {
			preco = Console.readDouble("Preço: ");
			if (preco > 0){ 
				obj.setPreco(preco);
				alteracao = true;
				break;
			}else break;
		}
		
		if (alteracao){
			System.out.println("Produto alterado.");
		}else{
			System.out.println("Prduto não alterado.");		
		}
		
	}

	private static void estatistica() {
		System.out.println("\nESTATÍSTICA\n");
		if (ListaProdutos.listaProd.isEmpty()) {
			System.out.println("Não existe produto cadastrado");
			return;
		}
		System.out.println("TOTAL DE PRODUTOS: " + ListaProdutos.listaProd.size());
		double total = 0;
		double menor = ListaProdutos.listaProd.get(0).getPreco();
		double maior = ListaProdutos.listaProd.get(0).getPreco();
		for (Produto obj : ListaProdutos.listaProd) {
			total += obj.getPreco();
			if (obj.getPreco() < menor) menor = obj.getPreco();
			if (obj.getPreco() > maior) maior = obj.getPreco();
		}
		System.out.println("MÉDIA: " + total/ListaProdutos.listaProd.size());
		System.out.println("MENOR: " + menor);
		System.out.println("MAIOR: " + maior);
	}

	private static void excluir() {
		System.out.println("\nExcluir produto pelo código\n");
		int codigo = Console.readInt("Informe o código: ");
		Produto obj = ListaProdutos.pesqProdCodigo(codigo);
		if (obj==null) {
			System.out.println("Não existe produto para o código.");
		} else {
			System.out.println(obj.toString());
			if (Console.readLine("Confirma exclusão? S/N : ").equalsIgnoreCase("S")) {
				ListaProdutos.excluirProd(obj);
				System.out.println("Produto excluído.");
			}
		}
		
	}

	private static void pesqPreco() {
		System.out.println("\nPesquisa de produtos pela faixa de preço\n");
		double menorPreco = 0;
		double maiorPreco = 0;
		while (true) {
			menorPreco = Console.readDouble("Informe o menor preço: ");
			maiorPreco = Console.readDouble("Informe o maior preço: ");
			if (maiorPreco < menorPreco) {
				System.out.println("Faixa inválida.");
			} else break;
		}
		ArrayList<Produto> resp = ListaProdutos.pesqProdPreco(menorPreco, maiorPreco);
		if (resp.size()==0) {
			System.out.println("Não existe produtos para a faixa.");
		} else {
			for (Produto obj : resp) System.out.println(obj.toString());
		}		
		
	}

	private static void pesqNome() {
		System.out.println("\nPesquisar produtos pelo nome\n");
		String nome = Console.readLine("Informe o nome: ");
		ArrayList<Produto> resp = ListaProdutos.pesqProdNome(nome);
		if (resp.isEmpty()) {
			System.out.println("Não existe produtos para o nome.");
		} else {
			for (Produto obj : resp) System.out.println(obj.toString());
		}
		
	}

	private static void pesqCodigo() {
		System.out.println("\nPesquisar produto pelo código\n");
		int codigo = Console.readInt("Informe o código: ");
		Produto obj = ListaProdutos.pesqProdCodigo(codigo);
		if (obj==null) {
			System.out.println("Não existe produto para o código.");
		} else {
			System.out.println(obj.toString());
		}
		
	}

	private static void incluir() {
		System.out.println("\nIncluir produto\n");
		String nome;
		double preco = 0;
		while (true) {
			nome = Console.readLine("Nome: ").trim();
			if (nome.isEmpty()) {
				System.out.println("Falta o nome.");
			} else {
				if (ListaProdutos.pesqProdNome(nome).size() > 0) {
					System.out.println("Já existe o produto cadastrado.");
				} else break;
			}
		}
		while (true) {
			preco = Console.readDouble("Preço: ");
			if (preco > 0) break;
			System.out.println("Preço inválido.");
		}
		ListaProdutos.incluirProd(new Produto(nome, preco));
		System.out.println("Produto cadastrado.");
		
	}

}
