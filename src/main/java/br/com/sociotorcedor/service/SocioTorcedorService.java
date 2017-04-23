package br.com.sociotorcedor.service;

import br.com.sociotorcedor.domain.SocioTorcedor;
import org.springframework.dao.DuplicateKeyException;

import java.time.LocalDate;

/**
 * Interface para o serviço de Sócio Torcedor
 * @author : Claudio Nazareth  chtnazareth@gmail.com
 */
public interface SocioTorcedorService {
    SocioTorcedor cadastrarSocioTorcedor(String nome, String email, LocalDate dataNascimento, String timeCoracao)
            throws DuplicateKeyException;
}
