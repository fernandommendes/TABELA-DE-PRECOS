package dados;

public class Produto {
	
	private static int seq = 0;
	
	private int codigo;
	private String nome;
	private double preco;
	
	public Produto(String nome, double preco) {
		codigo = ++seq;
		this.nome = nome;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public String toString() {
		return 
				"Código : " + codigo + "\n" +
				"Nome: " + nome + "\n" +
				"Preço: " + preco + "\n";
	}
	
}
