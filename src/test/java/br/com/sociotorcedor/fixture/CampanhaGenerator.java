package br.com.sociotorcedor.fixture;



import br.com.sociotorcedor.rest.domain.CampanhaResource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe para criar dados de teste
 *
 * @author : Claudio Nazareth  chtnazareth@gmail.com
 */
public class CampanhaGenerator {

    public static List<CampanhaResource> getCampanhasResource(){
        List<CampanhaResource> campanhas = new ArrayList<>();
        campanhas.add(new CampanhaResource("Campanha 2",  "TimeDoCoracao",
                LocalDate.of(2017,10,05),LocalDate.of(2017,10,9)));

        campanhas.add(new CampanhaResource("Campanha 4",  "TIME-1004",
                LocalDate.of(2017,10,10),LocalDate.of(2017,10,20)));

        return campanhas;
    }

}
