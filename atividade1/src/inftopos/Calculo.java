package inftopos;

import java.util.Stack;

/**
 *
 * @author talisson
 */
public class Calculo {

    public static Double resultado(String posfixa) {
        double resposta;
        int contador = -1;
        double auxiliar = 0;
        Stack<Double> pilha = new Stack<>();
        for (int i = 0; i < posfixa.length(); i++) {
            if(posfixa.length() == 1){
                auxiliar = Character.getNumericValue(posfixa.charAt(i));
            }
            switch (posfixa.charAt(i)) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    
                    pilha.push((double) Character.getNumericValue(posfixa.charAt(i)));
                    
                    contador++;
                    break;
                case '+':
                    double res = 0;
                    for (int aux = contador; aux >= 0; aux--) {
                        if (pilha.isEmpty()) {
                            break;
                        }
                        res += pilha.pop();
                    }
                    pilha.push(res);
                    auxiliar = res;
                    break;
                case '-':
                    if (pilha.isEmpty()) {
                        break;
                    }
                    double res2 = pilha.pop();
                    double res1 = pilha.pop() - res2;
                    pilha.push(res1);
                    auxiliar = res1;
                    break;
                case '*':
                    if (pilha.isEmpty()) {
                        break;
                    }
                    res2 = pilha.pop();
                    res1 = pilha.pop() * res2;
                    pilha.push(res1);
                    auxiliar = res1;
                    break;
                case '/':
                    if (pilha.isEmpty()) {
                        break;
                    }
                    res2 = pilha.pop();
                    res1 = pilha.pop() / res2;
                    pilha.push(res1);
                    auxiliar = res1;
                    break;
            }
        }
        resposta = auxiliar;
        return resposta;
    }
}
