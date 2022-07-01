package classes;

interface Lista {
	public boolean adicionaFim(String valor);

	public boolean adicionaInicio(String valor);

	public boolean removeFim();

	public boolean removeInicio();

	public boolean remove(String valor);

	public boolean listaVazia();

	public boolean insereInicio(String valor1, String valor2);

	public boolean insereFim(String valor1, String valor2);

	public boolean deletaTudo(String valor);
}

public class ListaDuplamenteEncadeada implements Lista {

	No inicio;
	No fim;

	public String str() {
		String nome = "";
		No no = this.inicio;
		while (no != null) {
			nome += no.valor;
			no = no.posterior;
		}
		return nome;
	}
	
	public String toString(boolean inverso) {
		if (!inverso) {
			return this.toString();
		}
		String nome = "";
		No no = this.fim;
		while (no != null) {
			nome += no.valor + " ";
			no = no.anterior;
		}
		return nome;
	}

	public String toString() {
		No no = this.inicio;
		String nome = "";
		while (no != null) {
			nome += no.valor + " ";
			no = no.posterior;
		}
		return nome;
	}	
	
	public ListaDuplamenteEncadeada() {
		this.inicio = null;
		this.fim = null;
	}

	public boolean adicionaFim(char valor) {
		return adicionaFim(String.format("%c", valor));
	}

	public boolean adicionaFim(String valor) {
		if (valor == null) {
			return false;
		}
		No no = new No(valor);
		if (listaVazia()) {
			no.anterior = null;
			no.posterior = null;
			this.inicio = no;
			this.fim = no;
			return true;
		}
		no.posterior = null;
		no.anterior = this.fim;
		this.fim.posterior = no;
		this.fim = no;
		return true;
	}

	public boolean adicionaInicio(int valor) {
		return adicionaInicio(String.format("%d", valor));
	}

	public boolean adicionaInicio(char valor) {
		return adicionaInicio(String.format("%c", valor));
	}

	public boolean adicionaInicio(double valor) {
		return adicionaInicio(String.format("%.0f", valor));
	}

	public boolean adicionaInicio(String valor) {
		if (valor == null) {
			return false;
		}
		No no = new No(valor);
		if (listaVazia()) {
			no.anterior = null;
			no.posterior = null;
			this.inicio = no;
			this.fim = no;
			return true;
		}
		no.posterior = this.inicio;
		this.inicio.anterior = no;
		this.inicio = no;

		return true;
	}

	public boolean removeFim() {
		if (listaVazia()) {
			return false;
		}
		if (this.fim.equals(this.inicio)) {
			this.inicio = null;
			this.fim = null;
			return true;
		}
		this.fim = this.fim.anterior;
		this.fim.posterior = null;
		return true;
	}

	public boolean removeInicio() {
		if (listaVazia()) {
			return false;
		}
		if (this.fim.equals(this.inicio)) {
			this.inicio = null;
			this.fim = null;
			return true;
		}
		this.inicio = this.inicio.posterior;
		this.inicio.anterior = null;
		return false;
	}

	public boolean remove(String valor) {
		if (listaVazia()) {
			return true;
		}
		if (this.inicio.valor.equals(valor)) {
			return removeInicio();
		}
		if (this.fim.valor.equals(valor)) {
			return removeFim();
		}
		No no = this.inicio.posterior;
		while (no.posterior != null) {
			if (no.valor.equals(valor)) {
				No anterior = no.anterior;
				No posterior = no.posterior;
				anterior.posterior = posterior;
				posterior.anterior = anterior;
				return true;
			}
			no = no.posterior;
		}

		return false;
	}

	public boolean listaVazia() {
		return (this.inicio == null && this.fim == null);
	}

	public boolean insereInicio(String valor1, String valor2) {
		if (valor2.equals(this.inicio.valor)) {
			return adicionaInicio(valor1);
		}
		No no = this.inicio;
		while (no != null) {
			if (no.valor.equals(valor2)) {
				No novoNo = new No(valor1);
				novoNo.posterior = no;
				novoNo.anterior = no.anterior;
				no.anterior = novoNo;
				novoNo.anterior.posterior = novoNo;
				return true;
			}
			no = no.posterior;
		}
		return false;
	}

	public boolean insereFim(String valor1, String valor2) {
		No no = this.inicio;
		while (no.posterior != null) {
			if (no.valor.equals(valor2)) {
				No novoNo = new No(valor1);
				novoNo.posterior = no.posterior;
				novoNo.anterior = no;
				no.posterior.anterior = novoNo;
				no.posterior = novoNo;
				return true;
			}
			no = no.posterior;
		}
		if (valor2.equals(this.fim.valor)) {
			return adicionaFim(valor1);
		}
		return false;
	}

	public boolean deletaTudo(String valor) {
		if (listaVazia()) {
			return false;
		}
		boolean removido = false;
		while (this.inicio.valor.equals(valor)) {
			if (removeInicio()) {
				removido = true;
			}
			if (listaVazia()) {
				return removido;
			}
		}
		while (this.fim.valor.equals(valor)) {
			if (removeFim()) {
				removido = true;
			}
			if (listaVazia()) {
				return removido;
			}
		}
		No no = this.inicio;
		int contador = 0;

		while (no != null) {
			System.out.println(contador++ + "no: " + no);
			if (no.valor.equals(valor)) {
				No anterior = no.anterior;
				No posterior = no.posterior;
				anterior.posterior = posterior;
				posterior.anterior = anterior;
				removido = true;
			}
			no = no.posterior;
		}

		return removido;
	}
}