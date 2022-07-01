package arvoreavl;

public class ArvoreAVL {
	private No no;
	private ArvoreAVL dir;
	private ArvoreAVL esq;
	private int balance;
	
	public ArvoreAVL() {
		this.no = null;
		this.dir = null;
		this.esq = null;
		this.balance = 0;
	}
	
	public ArvoreAVL(No node) {
		this.no = node;
		this.dir = null;
		this.esq = null;
		this.balance = 0;
	}
	//Verificar se um nó é folha
	public boolean isFolha() {
		if(this.dir == null && this.esq == null) {
			return true;
		} else return false;
	}
	//Calculando a altura da arvore
	public int calcularAltura() {
		if(isFolha()) {
			return 1;
		}
		else if(this.esq != null && this.dir == null) {
			return 1 + this.esq.calcularAltura();
		}
		else if(this.esq == null && this.dir != null) {
			return 1 + this.dir.calcularAltura();
		} else {
			return 1 + Math.max(this.esq.calcularAltura(), this.dir.calcularAltura());
		}
	}
	//Calculando o balancemanto da arvore
	public void calcularBalance() {
		if(isFolha()) {
			this.balance = 0;
		}
		else if(this.esq != null && this.dir == null) {
			this.balance = 0 - this.esq.calcularAltura();
		}
		else if(this.esq == null && this.dir != null) {
			this.balance = this.dir.calcularAltura() - 0;
		} else {
			this.balance = this.dir.calcularAltura() - this.esq.calcularAltura();
		}
		if(this.dir != null) {
			this.dir.calcularBalance();
		}
		if(this.esq != null) {
			this.esq.calcularBalance();
		}
	}
	//Verificacao e rotação
	public ArvoreAVL verificaBalance() {
		if(this.balance >=2 || this.balance <= -2) {
			if(this.balance >= 2) {
				if(this.balance * this.dir.getBalance() > 0) {
					System.out.println("Rotação simples para dierita.");
					return rotacaoSimplesDireita();
				} else {
					System.out.println("Rotação dupla para direita.");
					return rotacaoDuplaDireita();
				}
			} else {	// balanceamento <= -2
				if( this.balance * this.esq.getBalance() > 0) {
					System.out.println("Rotação simples para esquerda.");
					return rotacaoSimplesEsquerda();
				} else {
					System.out.println("Rotação dupla para esquerda.");
					return rotacaoDuplaEsquerda();
				}
			}
		}
		this.calcularBalance();
		if(this.esq != null) {
			this.esq = this.esq.verificaBalance();
		}
		if(this.dir != null) {
			this.dir = this.dir.verificaBalance();
		}
		return this;
	}
	
	public ArvoreAVL rotacaoSimplesDireita() {
		ArvoreAVL filhoDir;
		ArvoreAVL neto = null;
		filhoDir = this.getDir();
		if(this.dir != null) {
			if(this.dir.getEsq() != null) {
				neto = filhoDir.getEsq();
			}
		}
		filhoDir.setEsq(this);
		this.setDir(neto);
		return filhoDir;
	}
	public ArvoreAVL rotacaoSimplesEsquerda() {
		ArvoreAVL filhoEsq;
		ArvoreAVL neto = null;
		filhoEsq = this.getEsq();
		if(this.esq != null) {
			if(this.esq.getDir() != null) {
				neto = filhoEsq.getDir();
			}
		}
		filhoEsq.setDir(this);
		this.setEsq(neto);
		return filhoEsq;
	}
	public ArvoreAVL rotacaoDuplaDireita() {
		ArvoreAVL arvore = this;
		ArvoreAVL filhoDir = this.getDir();
		ArvoreAVL neto = filhoDir.getEsq();
		ArvoreAVL noInserido = neto.getDir();
		
		// alinhando os nós
		filhoDir.setEsq(noInserido);
		neto.setDir(filhoDir);
		this.setDir(neto);
		// tornando filho a direita a nova raiz
		ArvoreAVL novoFilhoDir = this.getDir();
		arvore.setDir(null);
		novoFilhoDir.setEsq(arvore);
		return novoFilhoDir;
	}
	public ArvoreAVL rotacaoDuplaEsquerda() {
		ArvoreAVL arvore = this;
		ArvoreAVL filhoEsq = this.getEsq();
		ArvoreAVL neto = filhoEsq.getDir();
		ArvoreAVL noInserido = neto.getEsq();
		// alinhando os nós
		filhoEsq.setDir(noInserido);
		neto.setEsq(filhoEsq);
		this.setEsq(neto);
		//tornando filho a esquerda a nova raiz
		ArvoreAVL novoFilhoEsq = this.getEsq();
		arvore.setEsq(null);
		novoFilhoEsq.setDir(arvore);
		return novoFilhoEsq;
	}
	// métodos para controle
	public boolean isEmpty() {
		return (this.no == null);
	}
	public ArvoreAVL inereNo(No novoNo) {
		if(isEmpty()) {
			this.no = novoNo;
		} else {
			ArvoreAVL novaArvore = new ArvoreAVL(novoNo);
			if(novoNo.getValor() < this.no.getValor()) { // insere a esquerda
				if(this.esq == null) {	// verifica se é folha
					this.esq = novaArvore;
				}
				else {
					this.esq = this.esq.inereNo(novoNo);
				}
			}
			else if(novoNo.getValor() > this.no.getValor()) {	//	insere a direita
				if(this.dir == null) {
					this.dir = novaArvore;
				} else {
					this.dir = this.dir.inereNo(novoNo);
				}
			}
		}
		return this;
	}
	public boolean buscaNo(int valor) {
		if(isEmpty()) {
			return false;
		}
		if(this.no.getValor() == valor) {
			return true;
		} else {
			if(valor < this.no.getValor()) {
				if(this.esq == null) {
					return false;
				} else {
					return this.esq.buscaNo(valor);
				}
			}
			else if(valor > this.no.getValor()) {
				if(this.dir == null) {
					return false;
				} else {
					return this.dir.buscaNo(valor);
				}
			}
			return false;
		}
	}	
	
	//getters and setters
	public No getNo() {
		return no;
	}

	public void setNo(No no) {
		this.no = no;
	}

	public ArvoreAVL getDir() {
		return dir;
	}

	public void setDir(ArvoreAVL dir) {
		this.dir = dir;
	}

	public ArvoreAVL getEsq() {
		return esq;
	}

	public void setEsq(ArvoreAVL esq) {
		this.esq = esq;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	// metodo para depurar
	public String printArvore(int nivel) {
		String string1 = this.toString()+"\n";
		for(int i = 0; i < nivel; i++) {
			string1 += "\t";
		}
		if(this.esq != null) {
			string1 += "<-ESQ: " + this.esq.printArvore(nivel + 1);
		} else {
			string1 += "<-ESQ: NULL";
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
	
	public String toString() {
		return "[" + this.no.getValor() + "] (" + this.balance + ")";
	}
	
}
