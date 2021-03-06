package br.com.sociotorcedor.fixture;

import br.com.sociotorcedor.domain.SocioTorcedor;
import br.com.sociotorcedor.rest.domain.SocioTorcedorResource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para criar dados de sócio torcedor para os teste
 *
 * @author : Claudio Nazareth  chtnazareth@gmail.com
 */
public class SocioTorcedorGenerator {

    public static List<SocioTorcedor> getSocioTorcedores(){
        List<SocioTorcedor> socios = new ArrayList();
        socios.add(new SocioTorcedor("Claudio Nazareth", "chtnazareth@gmail.com",
                LocalDate.of(1983, 05 , 24), "Flamengo"));

        socios.add(new SocioTorcedor("Joao Silva", "jjsilva@gmaiil.com",
                LocalDate.of(1955, 02 , 22), "Vasco"));

        return socios;
    }

    public static SocioTorcedorResource criaSocioTorcedorResource(){

        return new SocioTorcedorResource("Claudio Nazareth", "chtnazareth@gmail.com",
                LocalDate.of(1983, 05 , 24), "TimeDoCoracao");
    }

}
