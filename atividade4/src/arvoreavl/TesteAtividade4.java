package arvoreavl;
import java.util.Scanner;
public class TesteAtividade4 {
	public static void main(String[] args) {
		System.out.println("------------Arvore AVL------------");
		Scanner scanner1 = new Scanner(System.in);
		ArvoreAVL arvore = new ArvoreAVL(new No(scanner1.nextInt()));
		arvore.calcularBalance();
		arvore = arvore.verificaBalance();
		System.out.println(arvore.printArvore(0));	
		while(true) {
			//System.out.println("QUERO QUE ISSO SAIA");
			arvore = arvore.inereNo(new No(scanner1.nextInt()));
			arvore.calcularBalance();
			arvore = arvore.verificaBalance();
			System.out.println(arvore.printArvore(0));
		}
	}
}
