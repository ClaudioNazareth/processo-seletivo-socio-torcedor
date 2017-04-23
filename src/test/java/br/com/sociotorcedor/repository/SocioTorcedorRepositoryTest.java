package br.com.sociotorcedor.repository;

import br.com.sociotorcedor.domain.SocioTorcedor;
import br.com.sociotorcedor.repository.br.com.sociotorcedor.fixture.SocioTorcedorGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


/**
 * Teste para validar se o Repositório de Sócio Torcedor
 * @author : Claudio Nazareth  chtnazareth@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SocioTorcedorRepositoryTest {

    @Autowired
    private SocioTorcedorRepository socioTorcedorRepository;

    @Before
    public void setUp() throws Exception {
        socioTorcedorRepository.deleteAll();
        socioTorcedorRepository.save(SocioTorcedorGenerator.getSocioTorcedores());
    }

    @Test
    public void findByNomeCompleto() throws Exception {

        final Optional<SocioTorcedor> socioTorcedor = socioTorcedorRepository.findByNomeCompletoIgnoreCase("Claudio Nazareth");

        assertThat(socioTorcedor).as("O Optional não pode estar vazio").isNotEmpty();

        assertThat(socioTorcedor.get()).extracting("nomeCompleto", "email", "dataNascimento", "timeCoracao")
                .contains("Claudio Nazareth", "chtnazareth@gmail.com", LocalDate.of(1983,05,24), "Flamengo");
    }

    @Test
    public void findByNomeCompletoIgnoringCase() throws Exception {

        final Optional<SocioTorcedor> socioTorcedor = socioTorcedorRepository.findByNomeCompletoIgnoreCase("claudio nazareth");

        assertThat(socioTorcedor).as("O Optional não pode estar vazio").isNotEmpty();

        assertThat(socioTorcedor.get()).extracting("nomeCompleto", "email", "dataNascimento", "timeCoracao")
                .contains("Claudio Nazareth", "chtnazareth@gmail.com", LocalDate.of(1983,05,24), "Flamengo");
    }

    @Test
    public void findByNomeCompletoNaoDeveExistir() throws Exception {

        final Optional<SocioTorcedor> socioTorcedor = socioTorcedorRepository.findByNomeCompletoIgnoreCase("Pedro Amaral");

        assertThat(socioTorcedor).as("Não existe na base um Pedro Amaral, Optional deve ser vazio").isEmpty();
    }

    @Test
    public void naoDeveDeixarCadastrarSocioDuasVezes() throws Exception {
        assertThatExceptionOfType(DuplicateKeyException.class)
                .isThrownBy(() -> {         socioTorcedorRepository.save(new SocioTorcedor("Claudio Nazareth", "chtnazareth@gmail.com",
                        LocalDate.of(1983, 05 , 24), "Flamengo")); })
                .withMessageContaining("Write failed with error code 11000 and error message 'E11000 duplicate key error " +
                        "collection: test.socioTorcedor index: email dup key");

    }

}