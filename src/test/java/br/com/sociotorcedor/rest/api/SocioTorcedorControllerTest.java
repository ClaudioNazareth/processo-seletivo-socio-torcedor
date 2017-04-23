package br.com.sociotorcedor.rest.api;

import br.com.sociotorcedor.exception.SocioTorcedorJaCadastradoException;
import br.com.sociotorcedor.repository.SocioTorcedorRepository;
import br.com.sociotorcedor.fixture.CampanhaGenerator;
import br.com.sociotorcedor.fixture.SocioTorcedorGenerator;
import br.com.sociotorcedor.rest.domain.CampanhaResource;
import br.com.sociotorcedor.service.CampanhaService;
import feign.RetryableException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;

/**
 * Teste para validar  a API Rest de SocioTorcedor
 * @author : Claudio Nazareth  chtnazareth@gmail.com
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SocioTorcedorControllerTest {

    @Autowired
    private SocioTorcedorController socioTorcedorController;

    @Autowired
    private SocioTorcedorRepository socioTorcedorRepository;

    @MockBean
    private CampanhaService campanhaService;

    @Before
    public void setUp() throws Exception {
        HttpServletRequest mockRequest = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
        socioTorcedorRepository.deleteAll();
        given(this.campanhaService.getCampanhasByTimeCoracao("TimeDoCoracao"))
                .willReturn(CampanhaGenerator.getCampanhasResource());
    }

    @Test
    public void quandoSocioTorcedorCadastradoDeveRetornarListaDeCampanhas() throws Exception {
        final ResponseEntity<List<CampanhaResource>> responseEntity =
                socioTorcedorController.cadastrarSocioTorcedor(SocioTorcedorGenerator.criaSocioTorcedorResource());

        assertThat(responseEntity).as("O Sócio torcedor deve ser criado com sucesso").isNotNull();
        assertThat(responseEntity.getStatusCode()).as("O Status code deve ser created").isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).as("Deve retornar a lista de campanhas").hasSize(2);
    }

    @Test
    public void quandoServicoDeCampanhasEstiverForaDeveCadastrarSocioETrazerLink() throws Exception {

        given(this.campanhaService.getCampanhasByTimeCoracao("TimeDoCoracao"))
                .willThrow(RetryableException.class);

        final ResponseEntity<List<CampanhaResource>> responseEntity =
                socioTorcedorController.cadastrarSocioTorcedor(SocioTorcedorGenerator.criaSocioTorcedorResource());

        assertThat(responseEntity).as("O Sócio torcedor deve ser criado com sucesso").isNotNull();
        assertThat(responseEntity.getStatusCode()).as("O Status code deve ser created").isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getHeaders().get("location")).as("Deve retornar a URL do Socio Torcedor criado").isNotNull();
    }

    @Test
    public void naoDeveCadastrarDoisUsuariosComMesmoEmail() throws Exception{
        socioTorcedorRepository.save(SocioTorcedorGenerator.getSocioTorcedores());
        assertThatExceptionOfType(SocioTorcedorJaCadastradoException.class)
                .isThrownBy(() ->  socioTorcedorController.cadastrarSocioTorcedor(SocioTorcedorGenerator.criaSocioTorcedorResource()))
                .withMessageContaining("Usário já cadastrado");

    }

}