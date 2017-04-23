package br.com.sociotorcedor.rest.api;

import br.com.sociotorcedor.domain.SocioTorcedor;
import br.com.sociotorcedor.exception.SocioTorcedorJaCadastradoException;
import br.com.sociotorcedor.rest.domain.CampanhaResource;
import br.com.sociotorcedor.rest.domain.ErrorInfo;
import br.com.sociotorcedor.rest.domain.SocioTorcedorResource;
import br.com.sociotorcedor.service.SocioTorcedorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 *  Para documentação das apis favor ver /documentacao/index.html (Swegger ui)
 */
@RestController
@RequestMapping("/v1/socios")
public class SocioTorcedorController {

    private static final Logger logger = LoggerFactory.getLogger(SocioTorcedorController.class);

    @Autowired
    private SocioTorcedorService socioTorcedorService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CampanhaResource>> cadastrarSocioTorcedor(@Valid @RequestBody SocioTorcedorResource socioTorcedorResource){

        try {
            final SocioTorcedor socioTorcedor = socioTorcedorService.cadastrarSocioTorcedor(socioTorcedorResource.getNomeCompleto(), socioTorcedorResource.getEmail(),
                    socioTorcedorResource.getDataNascimento(), socioTorcedorResource.getTimeCoracao());

            if(logger.isDebugEnabled()){
                logger.debug("Sócio Torcedor : {} cadastrado com sucesso", socioTorcedor);
            }

            //Devo buscar as listas de Campanhas
            return new ResponseEntity<>(campanhaResources, HttpStatus.CREATED);
        }catch (DuplicateKeyException ex){
            if(logger.isDebugEnabled()){
                logger.debug("Sócio Torcedor com e-amil: {} ja cadastrado", socioTorcedorResource.getEmail());
            }
            throw new SocioTorcedorJaCadastradoException();
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SocioTorcedorJaCadastradoException.class)
    @ResponseBody ErrorInfo
    handleSocioTorcedorJaCadastradoException( SocioTorcedorJaCadastradoException ex) {
        return new ErrorInfo(ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString() ,ex);
    }




}
