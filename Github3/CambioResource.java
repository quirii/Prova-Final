package br.espm.cambio;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CambioResource {

    @Autowired
    private MoedaService moedaService;

    @Autowired
    private CotacaoService cotacaoService;
    
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello ESPM! :)";
    }

    @GetMapping("/moeda")
    public List<Moeda> listMoeda(){
        
        return moedaService.listAll();     
    }

    @GetMapping("/moeda/{simbolo:[a-zA-Z]{3}}")
    public Moeda findMoedaBySimbolo(@PathVariable String simbolo) {
        return moedaService.findBySimbolo(simbolo);
    }

    @GetMapping("/moeda/{id:[a-f0-9]{8}(?:-[a-f0-9]{4}){4}[a-f0-9]{8}}")
    public Moeda findMoedaById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        return moedaService.findBy(uuid);
    }

    @PostMapping("/moeda")
    public void save(@RequestBody Moeda moeda){
        moedaService.create(moeda);
    }

    @DeleteMapping("/moeda/{id:[a-f0-9]{8}(?:-[a-f0-9]{4}){4}[a-f0-9]{8}}")
    public void delete(@PathVariable String id){
        moedaService.delete(id);
    }

    @DeleteMapping("/moeda/{simbolo:[A-Z]{3,}}")
    public ResponseEntity<HttpStatus> deleteBySimbolo(@PathVariable String simbolo){
        try {
            moedaService.deleteBySimbolo(simbolo);  
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
             
    }


    //Rotas da cotação
    @GetMapping("/cotacao/{simbolo:[a-zA-Z]{3}}")
    public List<Cotacao> findCotacaoBySimbolo(@PathVariable String simbolo) {
        List<Cotacao> cs = cotacaoService.findBySimbolo(simbolo);
        cs.forEach(c -> c.setId(null));
        return cs;
        //return cotacaoService.findBySimbolo(simbolo);
    }

    @RequestMapping(path = "/cotacao/{simbolo}/{ano}/{mes}/{dia}", method = RequestMethod.POST)
    public void save(@PathVariable String simbolo, @PathVariable String ano, @PathVariable String mes, @PathVariable String dia, @RequestBody Cotacao cotacao){
        LocalDate data = LocalDate.parse(ano + "-" + mes + "-" + dia);
        cotacao.setDtData(data);
        UUID id = moedaService.findBySimbolo(simbolo).getId();
        cotacao.setIdMoeda(id);
        cotacaoService.create(cotacao);
    }

      
}