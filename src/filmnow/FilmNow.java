package filmnow;

/**
 * Sistema que mantém os seus filmes prediletos. Podem existir até 100 filmes,
 * e até 10 deles podem estar na HotList (filmes que você precisa assistir
 * logo).
 *
 * Nenhum método desta classe imprime na tela: todos retornam Strings, e quem
 * imprime é a interface (MainFilmNow).
 *
 * @author Davi
 */
public class FilmNow {

    /**
     * Quantidade máxima de filmes no sistema.
     */
    private static final int TAMANHO = 100;

    /**
     * Quantidade máxima de filmes na HotList.
     */
    private static final int TAMANHO_HOT = 10;

    /**
     * Lista de filmes.
     */
    public Filme[] filmes;

    /**
     * HotList. Guarda referências aos mesmos objetos de {@code filmes}.
     */
    private Filme[] hotList;

    /**
     * Cria o FilmNow com a lista de filmes e a HotList vazias.
     */
    public FilmNow() {
        this.filmes = new Filme[TAMANHO];
        this.hotList = new Filme[TAMANHO_HOT];
    }

    /**
     * Adiciona um filme em uma posição. Se já existir filme na posição, ele é
     * substituído
     *
     * @param posicao Posição do filme (1 a 100).
     * @param nome    Nome do filme.
     * @param ano     Ano de lançamento do filme.
     * @param local   Local onde o filme pode ser assistido.
     * @return A mensagem com a operação.
     */
    public String cadastraFilme(int posicao, String nome, String ano, String local) {
        if (!posicaoValida(posicao, TAMANHO)) {
            throw new IllegalArgumentException("POSIÇÃO INVÁLIDA!");
        }
        if (nome == null || nome.trim().isEmpty() || local == null || local.trim().isEmpty()) {
            throw new IllegalArgumentException("FILME INVALIDO!");
        }

        Filme novo = new Filme(nome, ano, local);
        if (existeFilme(novo)) {
            throw new IllegalArgumentException("FILME JA ADICIONADO!");
        }

        Filme antigo = this.filmes[posicao - 1];
        if (antigo != null) {
            removeDaHotList(antigo);
        }

        this.filmes[posicao - 1] = novo;
        return "FILME ADICIONADO";
    }

    /**
     * Lista todos os filmes cadastrados, um por linha, no formato
     * "posição - nome".
     *
     * @return A listagem dos filmes, ou String vazia se não houver nenhum.
     */
    public String mostraTodos() {
        String saida = "";
        for (int i = 0; i < this.filmes.length; i++) {
            if (this.filmes[i] != null) {
                saida += (i + 1) + " - " + this.filmes[i].getNome() + "\n";
            }
        }
        return saida;
    }

    /**
     * Retorna os detalhes do filme de uma posição. Se o filme estiver na
     * HotList, os detalhes vem com 🔥.
     *
     * @param posicao Posição do filme (1 a 100).
     * @return Os detalhes do filme, ou "POSIÇÃO INVÁLIDA!" se a posição for
     *         inválida ou estiver vazia.
     */
    public String detalhaFilme(int posicao) {
        if (!existeFilmeNaPosicao(posicao)) {
            throw new IllegalArgumentException("POSIÇÃO INVÁLIDA!");
        }

        Filme filme = this.filmes[posicao - 1];
        String detalhes = filme.detalhar();

        if (estaNaHotList(filme)) {
            return "🔥 " + detalhes;
        }
        return detalhes;
    }

    /**
     * Coloca um filme na HotList. Se a posição da HotList já tiver um filme,
     * ele é substituído. Um filme não pode aparecer duas
     * vezes na HotList.
     *
     * @param posicaoFilme Posição do filme na lista de filmes (1 a 100).
     * @param posicaoHot Posição na HotList (1 a 10).
     * @return A mensagem com o resultado da operação.
     */
    public String atribuiHot(int posicaoFilme, int posicaoHot) {
        if (!existeFilmeNaPosicao(posicaoFilme) || !posicaoValida(posicaoHot, TAMANHO_HOT)) {
            throw new IllegalArgumentException("POSIÇÃO INVÁLIDA!");
        }

        Filme filme = this.filmes[posicaoFilme - 1];
        if (estaNaHotList(filme)) {
            throw new IllegalArgumentException("FILME JA ESTA NA HOTLIST!");
        }
        this.hotList[posicaoHot - 1] = filme;
        return "ADICIONADO À HOTLIST NA POSIÇÃO " + posicaoHot + "!";
    }

    /**
     * Remove o filme de uma posição da HotList. O filme continua na lista de
     * filmes, apenas deixa de ser hot.
     *
     * @param posicaoHot Posição na HotList (1 a 10).
     */
    public String removeHot(int posicaoHot) {
        if (!posicaoValida(posicaoHot, TAMANHO_HOT)) {
            throw new IllegalArgumentException("POSIÇÃO INVÁLIDA!");
        }
        this.hotList[posicaoHot - 1] = null;
        return "";
    }

    /**
     * Lista os filmes da HotList, um por linha, no formato
     * "posição - nome, ano".
     *
     * @return A listagem da HotList ou String vazia se ela estiver vazia.
     */
    public String exibeHotList() {
        String saida = "";
        for (int i = 0; i < this.hotList.length; i++) {
            if (this.hotList[i] != null) {
                saida += (i + 1) + " - " + this.hotList[i] + "\n";
            }
        }
        return saida;
    }

    /**
     * Verifica se uma posição informada pelo usuário está valida
     *
     * @param posicao Posição a ser verificada.
     * @param limite  Maior posição permitida.
     * @return true se a posição for válida, false caso contrário.
     */
    private boolean posicaoValida(int posicao, int limite) {
        return posicao >= 1 && posicao <= limite;
    }

    /**
     * Verifica se a posição é válida e se há um filme cadastrado nela.
     *
     * @param posicao Posição na lista de filmes (1 a 100).
     * @return true se houver filme na posição, false caso contrário.
     */
    private boolean existeFilmeNaPosicao(int posicao) {
        return posicaoValida(posicao, TAMANHO) && this.filmes[posicao - 1] != null;
    }

    /**
     * Verifica se um filme igual (mesmo nome e ano) já está cadastrado.
     *
     * @param filme Filme a ser procurado.
     * @return true se o filme já existir, false caso contrário.
     */
    private boolean existeFilme(Filme filme) {
        for (Filme f : this.filmes) {
            if (f != null && f.equals(filme)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se um filme está na HotList.
     *
     * @param filme Filme a ser procurado.
     * @return true se o filme estiver na HotList, false caso contrário.
     */
    private boolean estaNaHotList(Filme filme) {
        for (Filme f : this.hotList) {
            if (f != null && f.equals(filme)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retira um filme da HotList
     *
     * @param filme Filme a ser retirado.
     */
    private void removeDaHotList(Filme filme) {
        for (int i = 0; i < this.hotList.length; i++) {
            if (this.hotList[i] != null && this.hotList[i].equals(filme)) {
                this.hotList[i] = null;
            }
        }
    }
}