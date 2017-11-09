package listaproduto;

import java.util.ArrayList;

import dados.Produto;

public class ListaProdutos {

	public static ArrayList<Produto> listaProd = new ArrayList<Produto>();
	
	public static void incluirProd(Produto obj) {
		listaProd.add(obj);
	}
	
	public static void excluirProd (Produto obj) {
		listaProd.remove(obj);
	}
	
	public static Produto pesqProdCodigo(int codigo) {
		for (Produto obj : listaProd) {
			if (obj.getCodigo()==codigo) {
				return obj;
			}
		}
		return null;
	}
	
	public static ArrayList<Produto> pesqProdPreco(double menorPreco, double maiorPreco) {
		ArrayList<Produto> resp = new ArrayList<Produto>();
		for (Produto obj : listaProd) {
			if (obj.getPreco()>=menorPreco && obj.getPreco()<=maiorPreco){
				resp.add(obj);
			}
		}
		return resp;
	}
	
	public static ArrayList<Produto> pesqProdNome(String nome) {
		ArrayList<Produto> resp = new ArrayList<Produto>();
		for (Produto obj : listaProd) {
			if (obj.getNome().contains(nome)) {
				resp.add(obj);
			}
		}
		return resp;
	}
	
}





