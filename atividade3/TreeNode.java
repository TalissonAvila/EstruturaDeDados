package atividade3;

public class TreeNode {
	TreeNode esq;
	TreeNode dir;
	int valor;

	TreeNode(int valor){
		this.valor = valor;
	}

	public String printArvore(int nivel) {
		String string1 = this.toString() + "\n";
		for(int i = 0; i < nivel;i++) {
			string1 += "\t";
		}
		if(this.esq != null) {
			string1 += "<- ESQ: " + this.esq.printArvore(nivel + 1);
		} else {
			string1 += "<- ESQ: NULL";
		}
		string1 += "\n";
		for(int i = 0; i < nivel; i++) {
			string1 += "\t";
		}
		if(this.dir != null) {
			string1 += "->DIR: " + this.dir.printArvore(nivel + 1);
		} else {
			string1 += "->DIR: NULL";
		}
		string1 += "\n";
		return string1;
	}
	@Override
	public String toString() {
		return (" valor [" + this.valor +"]");
	}	
}
