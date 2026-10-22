package filmnow;

/**
 * Representação de um filme (ou série) no sistema FilmNow.
 * Nome, ano de lançamento e o local onde pode ser assistido.
 *
 * Dois filmes são considerados iguais se tiverem o mesmo nome e o mesmo ano de
 * lançamento, independentemente do local.
 *
 * @author Davi
 */
public class Filme {

    /**
     * Nome do filme.
     */
    private String nome;

    /**
     * Ano de lançamento do filme.
     */
    private String ano;

    /**
     * Local onde o filme pode ser assistido
     */
    private String local;

    /**
     * Cria um filme a partir do seu nome, ano de lançamento e local.
     *
     * @param nome  Nome do filme.
     * @param ano   Ano de lançamento do filme.
     * @param local Local onde o filme pode ser assistido.
     */
    public Filme(String nome, String ano, String local) {
        this.nome = nome;
        this.ano = ano;
        this.local = local;
    }

    /**
     * Retorna o nome do filme.
     *
     * @return nome do filme.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Retorna o ano de lançamento do filme.
     *
     * @return O ano de lançamento.
     */
    public String getAno() {
        return this.ano;
    }

    /**
     * Retorna o local onde o filme pode ser assistido.
     *
     * @return O local do filme.
     */
    public String getLocal() {
        return this.local;
    }

    /**
     * Gera a representação detalhada do filme: nome e ano na primeira linha e o
     * local na segunda.
     *
     * @return Os detalhes do filme no formato "nome, ano\nlocal".
     */
    public String detalhar() {
        return this.toString() + "\n" + this.local;
    }

    /**
     * Gera a representação textual do filme.
     *
     * @return O filme no formato "nome, ano".
     */
    @Override
    public String toString() {
        return this.nome + ", " + this.ano;
    }

    /**
     * Verifica se este filme é igual a outro objeto. Dois filmes são iguais se
     * tiverem o mesmo nome e o mesmo ano de lançamento.
     *
     * @param o Objeto a ser comparado.
     * @return true se o objeto for um Filme com mesmo nome e ano, false caso nao sejam iguas
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Filme)) {
            return false;
        }
        Filme oFilme = (Filme) o;
        return this.nome.equals(oFilme.nome) && this.ano.equals(oFilme.ano);
    }

    /**
     * Gera o código hash do filme
     *
     * @return O código hash do filme.
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(this.nome, this.ano);
    }
}