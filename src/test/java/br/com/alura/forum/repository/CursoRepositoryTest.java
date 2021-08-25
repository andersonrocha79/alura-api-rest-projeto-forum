package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Curso;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
// @SpringBootTest
@DataJpaTest // anotação para classe de teste de 'repository'
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // indicamos ao Spring que os testes da interface ‘Repository` devem ser realizados em outro banco de dados que não seja o h2?
@ActiveProfiles("test") // utiliza o 'application-test.properties' na execução dos testes
public class CursoRepositoryTest
{

    @Autowired
    private CursoRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaCarregarUmCursoAoBuscarPeloSeuNome()
    {

        String nomeCurso = "HTML 5";

        Curso html5 = new Curso();
        html5.setNome(nomeCurso);
        html5.setCategoria("Programação");
        em.persist(html5);

        Curso curso = repository.findByNome(nomeCurso);

        // verifica se foi encontrado
        Assert.assertNotNull(curso);

        // verifica se o nome retornado é o mesmo
        Assert.assertEquals(nomeCurso, curso.getNome());

    }

    @Test
    public void naoDeveriaCarregarUmCursoCujoNomeNaoEstejaCadastrado()
    {

        String nomeCurso = "CURSO";

        Curso curso = repository.findByNome(nomeCurso);

        // verifica se foi encontrado
        Assert.assertNull(curso);

    }

}