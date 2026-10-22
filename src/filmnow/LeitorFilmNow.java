package filmnow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Lê filmes de um arquivo CSV e os cadastra no FilmNow. Cada linha deve ter o
 * formato "posicao,nome,ano,local".
 *
 * @author Davi
 */
public class LeitorFilmNow {

    /**
     * Carrega os filmes de um arquivo CSV para o sistema.
     *
     * @param arquivo Caminho do arquivo CSV.
     * @param fn O sistema FilmNow a ser populado.
     * @return Quantidade de filmes adicionados com sucesso.
     */
    public int carregaContatos(String arquivo, FilmNow fn) throws IOException {
        int carregados = 0;
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] campos = linha.split(",");
                if (campos.length != 4) {
                    continue;
                }
                try {
                    int posicao = Integer.parseInt(campos[0].trim());
                    String resultado = fn.cadastraFilme(posicao, campos[1].trim(),
                            campos[2].trim(), campos[3].trim());
                    if (resultado.equals("FILME ADICIONADO")) {
                        carregados++;
                    }
                } catch (NumberFormatException e) {
                    // linha de cabeçalho ou posição inválida: ignora
                }
            }
        }
        return carregados;
    }
}