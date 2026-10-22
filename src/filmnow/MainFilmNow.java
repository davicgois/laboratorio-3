package filmnow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular o sistema FilmNow.
 *
 * @author eliane
 * @author Davi
 */
public class MainFilmNow {

    /**
     * Ponto de entrada do programa. Carrega os filmes iniciais (se o arquivo
     * existir) e exibe o menu até o usuário escolher sair.
     */
    public static void main(String[] args) {
        FilmNow fn = new FilmNow();

        System.out.println("Carregando filmes ...");
        try {
            carregaFilmes("filmes_inicial.csv", fn);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro lendo arquivo: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String escolha = menu(scanner);
            comando(escolha, fn, scanner);
        }
    }

    /**
     * Exibe o menu e recebe a escolha do usuário.
     *
     * @param scanner Para receber da opção do usuário.
     * @return O comando escolhido em maiúsculo
     */
    private static String menu(Scanner scanner) {
        System.out.print(
                "\n(A)Adicionar filme\n"
                        + "(M)Mostrar todos\n"
                        + "(D)Detalhar filme\n"
                        + "(E)Exibir HotList\n"
                        + "(H)Atribuir Hot\n"
                        + "(R)Remover Hot\n"
                        + "(S)Sair\n"
                        + "\n"
                        + "Opção> ");
        if (!scanner.hasNextLine()) {
            return "S";
        }
        return scanner.nextLine().trim().toUpperCase();
    }

    /**
     * Usa a opção escolhida pelo usuário.
     *
     * @param opcao Opção digitada.
     * @param fn O sistema FilmNow que estamos manipulando.
     * @param scanner Scanner para o caso do comando precisar de mais entrada.
     */
    private static void comando(String opcao, FilmNow fn, Scanner scanner) {
        switch (opcao) {
            case "A":
                adicionaFilme(fn, scanner);
                break;
            case "M":
                mostrarFilmes(fn);
                break;
            case "D":
                detalharFilme(fn, scanner);
                break;
            case "E":
                mostrarHotList(fn);
                break;
            case "H":
                atribuirHot(fn, scanner);
                break;
            case "R":
                removerHot(fn, scanner);
                break;
            case "S":
                sai();
                break;
            default:
                System.out.println("OPÇÃO INVÁLIDA!");
        }
    }

    /**
     * Cadastra um filme no sistema passando posição, nome, ano e local.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para pedir as informações do filme.
     */
    private static void adicionaFilme(FilmNow fn, Scanner scanner) {
        int posicao = leInteiro(scanner, "\nPosição> ");
        System.out.print("Nome> ");
        String nome = scanner.nextLine();
        System.out.print("Ano> ");
        String ano = scanner.nextLine();
        System.out.print("Local> ");
        String local = scanner.nextLine();
        System.out.println(fn.cadastraFilme(posicao, nome, ano, local));
    }

    /**
     * Mostra a lista de filmes.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     */
    private static void mostrarFilmes(FilmNow fn) {
        System.out.print("\n" + fn.mostraTodos());
    }

    /**
     * Imprime os detalhes de um dos filmes.
     *
     * @param fn      O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para capturar a posição do filme.
     */
    private static void detalharFilme(FilmNow fn, Scanner scanner) {
        int posicao = leInteiro(scanner, "Filme> ");
        System.out.println("\n" + fn.detalhaFilme(posicao));
    }

    /**
     *mostra os filmes da HotList.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     */
    private static void mostrarHotList(FilmNow fn) {
        System.out.print("\n" + fn.exibeHotList());
    }

    /**
     * Coloca um filme na HotList, pedindo a posição do filme e a posição na
     * HotList.
     *
     * @param fn O sistema FilmNow a ser usado
     * @param scanner Scanner para capturar as posições.
     */
    private static void atribuirHot(FilmNow fn, Scanner scanner) {
        int posicaoFilme = leInteiro(scanner, "Filme> ");
        int posicaoHot = leInteiro(scanner, "Posicao> ");
        System.out.println(fn.atribuiHot(posicaoFilme, posicaoHot));
    }

    /**
     * Remove um filme da HotList
     *
     * @param fn O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para capturar a posição.
     */
    private static void removerHot(FilmNow fn, Scanner scanner) {
        int posicaoHot = leInteiro(scanner, "Posicao> ");
        String resultado = fn.removeHot(posicaoHot);
        if (!resultado.isEmpty()) {
            System.out.println(resultado);
        }
    }

    /**
     * Exibe um rótulo e lê um número inteiro. Se o usuário digitar algo que não
     * é número, retorna -1, que será tratado como posição inválida.
     *
     * @param scanner Scanner para ler a entrada.
     * @param rotulo  Texto exibido antes da leitura.
     * @return O número digitado, ou -1 se a entrada não for um número.
     */
    private static int leInteiro(Scanner scanner, String rotulo) {
        System.out.print(rotulo);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }



    private static void sai() {
        System.out.println("\nVlw flw o/");
        System.exit(0);
    }

    /**
     * Lê os filmes de um arquivo csv.
     *
     * @param arquivoFilmes O caminho para o arquivo;
     * @param fn O sistema FilmNow a ser populado com os dados
     * @throws FileNotFoundException Caso o arquivo não exista.
     * @throws IOException Caso o arquivo não possa ser lido
     */
    private static void carregaFilmes(String arquivoFilmes, FilmNow fn)
            throws FileNotFoundException, IOException {
        LeitorFilmNow leitor = new LeitorFilmNow();
        int carregados = leitor.carregaContatos(arquivoFilmes, fn);
        System.out.println("Carregamos " + carregados + " registros.");
    }
}