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
			System.out.println("\nTABELA DE PRE�OS");
			System.out.println("1-Incluir produto");
			System.out.println("2-Pesquisar produto pelo c�digo");
			System.out.println("3-Pesquisar produtos pelo nome");
			System.out.println("4-Pesquisar produtos pela faixa de pre�o");
			System.out.println("5-Excluir produto pelo c�digo");
			System.out.println("6-Alterar produto pelo c�digo");
			System.out.println("7-Estat�stica de produtos");
			System.out.println("0-Sair");
			opcao = Console.readInt("Informe a op��o: ");
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
					System.out.println("Op��o inv�lida.");
					break;
			}
			
		} while (opcao!=0);
		
	}

	private static void alterar() {
		System.out.println("\nAlterar Produto\n");
		int codigo = Console.readInt("Informe o c�digo: ");
		Produto obj = ListaProdutos.pesqProdCodigo(codigo);
		if (obj==null) {
			System.out.println("N�o existe produto para o c�digo.");
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
					System.out.println("J� existe o produto cadastrado.");
				} else {
					obj.setNome(nome);
					alteracao = true;
					break;
				}
			}
		}
		while (true) {
			preco = Console.readDouble("Pre�o: ");
			if (preco > 0){ 
				obj.setPreco(preco);
				alteracao = true;
				break;
			}else break;
		}
		
		if (alteracao){
			System.out.println("Produto alterado.");
		}else{
			System.out.println("Prduto n�o alterado.");		
		}
		
	}

	private static void estatistica() {
		System.out.println("\nESTAT�STICA\n");
		if (ListaProdutos.listaProd.isEmpty()) {
			System.out.println("N�o existe produto cadastrado");
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
		System.out.println("M�DIA: " + total/ListaProdutos.listaProd.size());
		System.out.println("MENOR: " + menor);
		System.out.println("MAIOR: " + maior);
	}

	private static void excluir() {
		System.out.println("\nExcluir produto pelo c�digo\n");
		int codigo = Console.readInt("Informe o c�digo: ");
		Produto obj = ListaProdutos.pesqProdCodigo(codigo);
		if (obj==null) {
			System.out.println("N�o existe produto para o c�digo.");
		} else {
			System.out.println(obj.toString());
			if (Console.readLine("Confirma exclus�o? S/N : ").equalsIgnoreCase("S")) {
				ListaProdutos.excluirProd(obj);
				System.out.println("Produto exclu�do.");
			}
		}
		
	}

	private static void pesqPreco() {
		System.out.println("\nPesquisa de produtos pela faixa de pre�o\n");
		double menorPreco = 0;
		double maiorPreco = 0;
		while (true) {
			menorPreco = Console.readDouble("Informe o menor pre�o: ");
			maiorPreco = Console.readDouble("Informe o maior pre�o: ");
			if (maiorPreco < menorPreco) {
				System.out.println("Faixa inv�lida.");
			} else break;
		}
		ArrayList<Produto> resp = ListaProdutos.pesqProdPreco(menorPreco, maiorPreco);
		if (resp.size()==0) {
			System.out.println("N�o existe produtos para a faixa.");
		} else {
			for (Produto obj : resp) System.out.println(obj.toString());
		}		
		
	}

	private static void pesqNome() {
		System.out.println("\nPesquisar produtos pelo nome\n");
		String nome = Console.readLine("Informe o nome: ");
		ArrayList<Produto> resp = ListaProdutos.pesqProdNome(nome);
		if (resp.isEmpty()) {
			System.out.println("N�o existe produtos para o nome.");
		} else {
			for (Produto obj : resp) System.out.println(obj.toString());
		}
		
	}

	private static void pesqCodigo() {
		System.out.println("\nPesquisar produto pelo c�digo\n");
		int codigo = Console.readInt("Informe o c�digo: ");
		Produto obj = ListaProdutos.pesqProdCodigo(codigo);
		if (obj==null) {
			System.out.println("N�o existe produto para o c�digo.");
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
					System.out.println("J� existe o produto cadastrado.");
				} else break;
			}
		}
		while (true) {
			preco = Console.readDouble("Pre�o: ");
			if (preco > 0) break;
			System.out.println("Pre�o inv�lido.");
		}
		ListaProdutos.incluirProd(new Produto(nome, preco));
		System.out.println("Produto cadastrado.");
		
	}

}
