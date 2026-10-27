package filmnow;

/**
 * Representação de um filme (ou série) no sistema FilmNow.
 * Nome, ano de lançamento e os locais onde pode ser assistido (de 1 a 5).
 *
 * Dois filmes são considerados iguais se tiverem o mesmo nome e o mesmo ano de
 * lançamento, independentemente dos locais.
 *
 * @author Davi
 */
public class Filme {

    /**
     * Quantidade máxima de locais por filme.
     */
    public static final int MAX_LOCAIS = 5;

    /**
     * Nome do filme.
     */
    private String nome;

    /**
     * Ano de lançamento do filme.
     */
    private String ano;

    /**
     * Locais onde o filme pode ser assistido. Os locais ocupam sempre as
     * primeiras posições do array, sem buracos.
     */
    private String[] locais;

    /**
     * Quantidade de locais atualmente cadastrados.
     */
    private int qtdLocais;

    /**
     * Cria um filme a partir do seu nome, ano de lançamento e local.
     *
     * @param nome  Nome do filme.
     * @param ano   Ano de lançamento do filme.
     * @param local Local onde o filme pode ser assistido.
     * @throws NullPointerException     Se nome, ano ou local forem nulos.
     * @throws IllegalArgumentException Se nome, ano ou local forem vazios ou
     *                                  compostos apenas por espaços.
     */
    public Filme(String nome, String ano, String local) {
        if (nome == null) {
            throw new NullPointerException("FILME NULO");
        }
        if (ano == null) {
            throw new NullPointerException("ANO NULO");
        }
        if (nome.trim().isEmpty()) {
            throw new IllegalArgumentException("NOME INVALIDO");
        }
        if (ano.trim().isEmpty()) {
            throw new IllegalArgumentException("ANO INVALIDO");
        }
        validaLocal(local);

        this.nome = nome;
        this.ano = ano;
        this.locais = new String[MAX_LOCAIS];
        this.locais[0] = local;
        this.qtdLocais = 1;
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
     * Retorna os locais onde o filme pode ser assistido, separados por vírgula.
     *
     * @return Os locais do filme no formato "local1, local2, ...".
     */
    public String getLocal() {
        String saida = "";
        for (int i = 0; i < this.qtdLocais; i++) {
            if (i > 0) {
                saida += ", ";
            }
            saida += this.locais[i];
        }
        return saida;
    }

    /**
     * Retorna a quantidade de locais cadastrados para o filme.
     *
     * @return A quantidade de locais (de 1 a {@link #MAX_LOCAIS}).
     */
    public int getQuantidadeLocais() {
        return this.qtdLocais;
    }

    /**
     * Verifica se o filme já possui um local (ignorando maiúsculas/minúsculas e
     * espaços nas pontas).
     *
     * @param local Local a ser procurado.
     * @return true se o local já estiver cadastrado, false caso contrário.
     */
    public boolean temLocal(String local) {
        return indiceDoLocal(local) != -1;
    }

    /**
     * Gera a representação detalhada do filme: nome e ano na primeira linha e os
     * locais na segunda.
     *
     * @return Os detalhes do filme no formato "nome, ano\nlocal1, local2".
     */
    public String detalhar() {
        return this.toString() + "\n" + this.getLocal();
    }

    /**
     * Adiciona um novo local onde o filme pode ser assistido. O filme não é
     * alterado se o local já existir ou se já houver {@link #MAX_LOCAIS} locais.
     *
     * @param novoLocal Local a ser adicionado.
     * @return true se o local foi adicionado, false se ele já existia ou se o
     *         limite de locais foi atingido.
     * @throws NullPointerException     Se o local for nulo.
     * @throws IllegalArgumentException Se o local for vazio ou só tiver espaços.
     */
    public boolean adicionaLocal(String novoLocal) {
        validaLocal(novoLocal);
        if (this.qtdLocais == MAX_LOCAIS || temLocal(novoLocal)) {
            return false;
        }
        this.locais[this.qtdLocais] = novoLocal;
        this.qtdLocais++;
        return true;
    }

    /**
     * Remove um local do filme. Um filme sempre precisa ter ao menos um local,
     * então o último local não pode ser removido.
     *
     * @param antigoLocal Local a ser removido.
     * @return true se o local foi removido, false se ele não existe no filme ou
     *         se era o único local.
     * @throws NullPointerException     Se o local for nulo.
     * @throws IllegalArgumentException Se o local for vazio ou só tiver espaços.
     */
    public boolean removeLocal(String antigoLocal) {
        validaLocal(antigoLocal);
        int indice = indiceDoLocal(antigoLocal);
        if (indice == -1 || this.qtdLocais == 1) {
            return false;
        }
        for (int i = indice; i < this.qtdLocais - 1; i++) {
            this.locais[i] = this.locais[i + 1];
        }
        this.qtdLocais--;
        this.locais[this.qtdLocais] = null;
        return true;
    }

    /**
     * Gera a representação textual do filme.
     *
     * @return O filme "nome, ano".
     */
    @Override
    public String toString() {
        return this.nome + ", " + this.ano;
    }

    /**
     * Verifica se este filme é igual a outro objeto. Dois filmes são iguais se
     * tiverem o mesmo nome e o mesmo ano de lançamento.
     *
     * @return true se o objeto for um Filme com mesmo nome e ano, false caso
     *         contrário.
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
     * @return O código hash
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(this.nome, this.ano);
    }

    /**
     * Valida um local: não pode ser nulo, vazio nem só espaços.
     */
    private void validaLocal(String local) {
        if (local == null) {
            throw new NullPointerException("LOCAL NULO");
        }
        if (local.trim().isEmpty()) {
            throw new IllegalArgumentException("LOCAL INVALIDO");
        }
    }

    /**
     * Procura o índice de um local no array.
     *
     * @return O índice do local, ou -1 se ele não existir.
     */
    private int indiceDoLocal(String local) {
        if (local == null) {
            return -1;
        }
        for (int i = 0; i < this.qtdLocais; i++) {
            if (this.locais[i].trim().equalsIgnoreCase(local.trim())) {
                return i;
            }
        }
        return -1;
    }
}