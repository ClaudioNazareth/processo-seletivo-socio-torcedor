package br.com.sociotorcedor.exception;

/**
 * Será retornada pela API PUT quando já houver um usuário com o mesmo e-mail cadastrado
 * @author : Claudio Nazareth  chtnazareth@gmail.com
 */
public class SocioTorcedorJaCadastradoException extends RuntimeException  {

    public SocioTorcedorJaCadastradoException() {
        super("Usuário já cadastrado");
    }
}
