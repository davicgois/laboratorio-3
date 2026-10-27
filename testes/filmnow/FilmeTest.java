package filmnow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


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
    public void testNomeNull() {
        try {
            Filme filmeInvalido = new Filme(null, "2009", "Disney+");
            fail("Uma exceção era esperada ao passar nome nulo");
        } catch (NullPointerException npe) {
            assertEquals("FILME NULO", npe.getMessage());
        }
    }

}
