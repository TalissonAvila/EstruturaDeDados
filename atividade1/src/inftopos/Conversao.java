package inftopos;

import java.util.Stack;

/**
 *
 * @author talisson
 */
public class Conversao {

    public static String posFixa(String inFixa) {
        String posfixa = "";
        Stack<Integer> pilha = new Stack();
        int contador = 0;
        char carcactere;

        while (contador < inFixa.length()) {
            carcactere = inFixa.charAt(contador);
            switch (carcactere) {
                case '*':
                case '/':
                case '+':
                case '-':
                case '^':
                    while ((!pilha.empty()) && prioridade(carcactere) <= prioridade((char) ((int) pilha.peek()))) {
                        posfixa += (char) ((int) pilha.pop());
                    }

                    pilha.add((int) carcactere);
                    break;
                case '(':
                    pilha.add((int) carcactere);
                    break;
                case ')':
                    while ((char) ((int) pilha.peek()) != '(') {
                        posfixa += (char) ((int) pilha.pop());
                    }
                    if ((char) ((int) pilha.peek()) == '(') {
                        pilha.pop();
                    }
                    break;
                default:
                    posfixa += inFixa.charAt(contador);

            }
            contador++;

        }
        while (pilha.size() > 0) {
            if ((char) ((int) pilha.peek()) != '(') {
                posfixa += (char) ((int) pilha.pop());
            } else {
                pilha.pop();
            }
        }
        return posfixa;
    }

    public static int prioridade(char elemento) {
        int prioridade;
        switch (elemento) {
            case '+':
            case '-':
                prioridade = 1;
                break;

            case '*':
            case '/':
                prioridade = 2;
                break;

            case '^':
                prioridade = 3;
                break;

            default:
                prioridade = 0;
                break;
        }

        return prioridade;
    }
}
