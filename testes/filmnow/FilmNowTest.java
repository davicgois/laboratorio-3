package filmnow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;

class FilmNowTest {
    private FilmNow fn;
    private Filme filme;
    private Filme[] hotList;



    @BeforeEach
    private void Filme(){
        String nome = "Avatar";
        String ano = "2009";
        String local = "Disney +";
        this.filme = new Filme(nome, ano, local);
        this.fn = new FilmNow();
        this.hotList = new Filme[10];

    }

    @Test
    void cadastraFilme() {
        String nome = "Avatar";
        String ano = "2009";
        String local = "Disney +";
        this.filme = new Filme(nome, ano, local);
        fn.cadastraFilme(0, "Avatar", "2009", "Disney +").equals("FILME ADICIONADO");
    }

    @Test
    void mostraTodos() {
        assert fn.mostraTodos().equals("1 - Avatar\n");
    }

    @Test
    void detalhaFilme() {
        assert fn.detalhaFilme(0).equals("Avatar, 2009");
    }

    @Test
    void atribuiHot() {
    }

    @Test
    void removeHot() {
    }

    @Test
    void exibeHotList() {
    }
}