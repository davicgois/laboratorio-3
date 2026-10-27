package filmnow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FilmeTest {

    private Filme filme;

    @BeforeEach
    private void Filme(){
        String nome = "Avatar";
        String ano = "2009";
        String local = "Disney +";
        this.filme = new Filme(nome, ano, local);

    }


    @Test
    void getNome() {
        assert filme.getNome().equals("Avatar");
    }

    @Test
    void getAno() {
        assert filme.getAno().equals("2009");
    }

    @Test
    void getLocal() {
        assert filme.getLocal().equals("Disney +");
    }

    @Test
    void detalhar() {
        assert filme.detalhar().equals("Avatar, 2009\nDisney +");
    }

    @Test
    void testToString() {
        assert filme.toString().equals("Avatar, 2009");
    }

    @Test
    void testEquals() {
        Filme filme2 = new Filme("Vingadores", "2023", "Disney +");
        assert filme.toString().equals(filme2.toString());
    }

    @Test
    void testNomeNull() {
        NullPointerException e = assertThrows(NullPointerException.class,
                () -> new Filme(null, "2009", "Disney+"));
        assertEquals("FILME NULO", e.getMessage());
    }

}
