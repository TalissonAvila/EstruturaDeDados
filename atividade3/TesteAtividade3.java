package atividade3;
import java.util.*;

/**
 *
 * @author talisson
 */
public class TesteAtividade3 {

    public static void main(String[] args) {

        int[] vetor = new int[9];	// tamanho do vetor

        Random random1 = new Random();
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = random1.nextInt(151);
        }
        System.out.println("Vetor não ordendao: " + Arrays.toString(vetor));
        Arrays.sort(vetor);
        System.out.println("Vetor pós ordenação: " + Arrays.toString(vetor));

        Conversao vetor2BTS = new Conversao();
        TreeNode arvore = vetor2BTS.arrayParaBTS(vetor);
        System.out.println("Vetor para BTS:\n "+ arvore.printArvore(0));
    }
}
