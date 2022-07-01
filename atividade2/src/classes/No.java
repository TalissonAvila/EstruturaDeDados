package classes;

public class No {
	String valor;
	No anterior;
	No posterior;

	No() {
		anterior = null;
		posterior = null;
		valor = null;
	}

	No(String valor) {
		this();
		this.valor = valor;
	}

	public String toString() {
		return this.valor;
	}
}