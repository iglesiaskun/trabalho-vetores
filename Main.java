import java.util.Scanner;

class Main {

	static Scanner console = new Scanner(System.in);

	static final int TOTAL_AVALIACOES = 3;
	static final String[] NOMES_AVALIACOES = { "A1", "A2", "A3" };
	static final double[] NOTA_MAX_AVALIACOES = { 30.00, 30.00, 40.00 };

	static double[] notas = new double[TOTAL_AVALIACOES];

	static double lerNota(String mensagem, double notaMaxima) {

		double nota = 0.0;

		do {

			System.out.printf("%s = ", mensagem);
			nota = console.nextDouble();

		} while (nota < 0.00 || nota > notaMaxima);

		return nota;
	}

	/**
	 * Atualiza o valor da respectiva nota do estudante
	 * 
	 * @param indiceNota um número inteiro representando o índice (posição) da nota
	 *                   no vetor
	 */
	static void atualizarNota(int indiceNota) {

		System.out.println();
		notas[indiceNota] = lerNota(NOMES_AVALIACOES[indiceNota], NOTA_MAX_AVALIACOES[indiceNota]);

	} // Fim do método atualizarNota

	/**
	 * @param notaFinal A soma de todas as avalições feita pelo estudante ao longo
	 *                  do semestre
	 * @return uma string representando o status final do estudante, são eles:
	 *         APROVADO, REPROVADO, EM RECUPERAÇÃO.
	 */
	static String avaliarSituacao(double notaFinal) {

		if (notaFinal < 30)
			return "REPROVADO";
		else if (notaFinal < 70)
			return "EM RECUPERAÇÃO";
		else
			return "APROVADO";

	} // Fim do método avaliarSituacao()

	static double calcularMedia(double[] notas) {
		double media = 0.0;

		for (int i = 0; i < TOTAL_AVALIACOES; i++) {
			media += notas[i];
		}
		media /= TOTAL_AVALIACOES;

		return media;
	}

	static void provaAI(double[] notas) {
		double notaAi = 0.0;
		int piorProva = notas[0] <= notas[1] ? 0 : 1; // Verifica a menor nota entre a A1 e A2.

		System.out.println("\nDigite a nota da Prova AI");
		notaAi = lerNota("\nAI", NOTA_MAX_AVALIACOES[piorProva]);
		System.out.println();

		if (notaAi > notas[piorProva]) {
			notas[piorProva] = notaAi;
			System.out.printf("A Avaliação %s foi substituida pela nota da Avaliação AI\n",
					NOMES_AVALIACOES[piorProva]);
		} else {
			System.out.println("A nota da Avaliação AI não foi suficiente para alterar alguma prova");
		}

	} // Fim do método provaAI()

	/**
	 * Verifica o usuário deseja cadastrar a nota da avaliação AI
	 */
	static void opcaoProvaAI(double[] notas) {

		if (calcularMedia(notas) <= 23.0 && calcularMedia(notas) >= 10.0 && (notas[0] + notas[1]) < 60) {
			System.out.println("\n\nDeseja cadastrar nota da Avaliação AI?");
			System.out.print("\nDigite SIM ou NÃO:  \s");
			String opcao = console.next();

			boolean check = false;
			while (!check) {
				if (opcao.equalsIgnoreCase("sim")) {
					provaAI(notas);
					mostrarNotas();
					check = true;
				} else if (opcao.equalsIgnoreCase("não") || opcao.equalsIgnoreCase("nao")) {
					System.out.println("Opção Digitada: Não");
					check = true;
				} else {
					System.out.println("Opção Invalida");
					System.out.print("Digite SIM ou NÃO: ");
					opcao = console.next();
				}
			}

		}

	} // Fim do método opcaoProvaAI()

	static String maiorNota(double[] notas) {

		String maiorNota = "";
		double verificarMaiorNota = 0.0;
		for (int i = TOTAL_AVALIACOES - 1; i >= 0; i--) {
			if (notas[i] > verificarMaiorNota) {
				verificarMaiorNota = notas[i];
				maiorNota = NOMES_AVALIACOES[i];
			}
		}
		return maiorNota;
	}

	/**
	 * Mostra na tela um relatório das notas do estudante
	 */
	static void mostrarNotas() {

		double notaFinal = 0.0;

		System.out.println("\n\t\tNOTAS");
		System.out.println();

		for (int i = 0; i < TOTAL_AVALIACOES; i++) {

			System.out.printf("Avaliação %s = %.2f pts", NOMES_AVALIACOES[i], notas[i]);
			System.out.println();
			notaFinal += notas[i];

		}

		System.out.printf("\n  Nota Final = %.2f pts", notaFinal);
		System.out.printf("\n    Situação = %s", avaliarSituacao(notaFinal));

	} // Fim do método mostrarNotas()

	/**
	 * Exibe o menu principal da aplicação
	 */
	static void mostrarMenu() {

		System.out.println("\n\n");
		System.out.println("\t\tMENU");
		System.out.println();

		System.out.println("[1] Cadastrar Nota A1");
		System.out.println("[2] Cadastrar Nota A2");
		System.out.println("[3] Cadastrar Nota A3");
		System.out.println("[4] Mostrar Notas");
		System.out.println("[0] SAIR");

		System.out.print("\nDigite uma opção:  ");
		byte opcao = console.nextByte();

		switch (opcao) {

		case 0:
			System.exit(0);
			break;

		case 1:
			atualizarNota(0);
			break;

		case 2:
			atualizarNota(1);
			break;

		case 3:
			atualizarNota(2);
			break;

		case 4:
			mostrarNotas();
			opcaoProvaAI(notas);
			break;

		default:
			mostrarMenu();
			break;

		}

		mostrarMenu();

	} // Fim do método mostrarMenu()

	public static void main(String[] args) {

		mostrarMenu();

	} // Fim do método main();

} // Fim da classe Main
