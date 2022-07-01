package classes;

public class CalculoMatematico {

	public static String multiplicar(String nome1, String nome2) {
		ListaDuplamenteEncadeada lista1 = separar(nome1);
		ListaDuplamenteEncadeada lista2 = separar(nome2);
		
		No trailer1 = lista1.fim, trailer2 = lista2.fim;
		ListaDuplamenteEncadeada resultado = gerarListaDupla(trailer1, trailer2);
		int temporaria = 0;
		int numero1 = 0;
		int numero2 = 0;
		int resposta = 0;
		No auxiliar = resultado.fim;
		while (trailer1 != null) {
			trailer2 = lista2.fim;
			numero1 = Integer.parseInt(trailer1.valor);
			No auxiliar2 = auxiliar;
			while (trailer2 != null) {
				numero2 = Integer.parseInt(trailer2.valor);
				temporaria = Integer.parseInt(auxiliar2.valor);
				resposta = numero1 * numero2 + temporaria;
				temporaria = resposta / 100;
				resposta -= temporaria * 100;
				auxiliar2.valor = String.format("%02d", resposta);
				temporaria += Integer.parseInt(auxiliar2.anterior.valor);
				auxiliar2.anterior.valor = String.format("%02d", temporaria);
				auxiliar2 = auxiliar2.anterior;
				trailer2 = trailer2.anterior;
			}
			auxiliar = auxiliar.anterior;
			trailer1 = trailer1.anterior;
		}
		return formatacao(resultado.str());
	}

	private static String formatacao(String nome) {
		int tamanho = nome.length();
		int i = 0;
		for (i = 0; i < tamanho; i++) {
			if (nome.charAt(i) != '0')
				break;
		}
		return nome.substring(i, tamanho);
	}

	private static ListaDuplamenteEncadeada gerarListaDupla(No trailer1, No trailer2) {
		ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();
		while (trailer1 != null) {
			lista.adicionaInicio("0");
			while (trailer2 != null) {
				lista.adicionaInicio("0");
				trailer2 = trailer2.anterior;
			}
			trailer1 = trailer1.anterior;
		}
		return lista;
	}

	private static ListaDuplamenteEncadeada separar(String nome) {
		ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();
		int tamanho = nome.length();
		int inicio = 0;
		if (tamanho % 2 == 1)
			lista.adicionaFim(nome.charAt(inicio++));
		for (int i = inicio; i < tamanho; i += 2)
			lista.adicionaFim(nome.substring(i, i + 2));
		return lista;
	}

	private static String notacaoCientifica(String numero) {
		String nome = numero.charAt(0) + ".";
		int tamanho = numero.length();
		int tamanhoAuxiliar = (tamanho < 8) ? tamanho : 8;
		for (int i = 1; i < tamanhoAuxiliar; i++) {
			nome += numero.charAt(i);
		}
		nome += " *e^+" + (tamanho - 1);
		return nome;
	}

}