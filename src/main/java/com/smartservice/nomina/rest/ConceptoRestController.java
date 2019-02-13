package com.smartservice.nomina.rest;

import com.smartservice.nomina.model.Concepto;
import com.smartservice.nomina.service.impl.ConceptoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/SmartServiceWs/rest/concepto")
public class ConceptoRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConceptoRestController.class);

    private ConceptoServiceImpl conceptoService;

    private static final String SUCSESS_RESULT = "<result>success</result>";

    @Autowired
    ConceptoRestController(ConceptoServiceImpl conceptoService){
        this.conceptoService = conceptoService;
    }

    @CrossOrigin
    @RequestMapping(value = "/ALL",method = RequestMethod.GET)
    public List<Concepto> readConcepto(){
        LOGGER.info("Geting all of Concepto entity method = RequestMethod.GET");
        return this.conceptoService.getAllConceptos();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<Concepto> searchConcpetoByModoLiquidacion(@RequestParam(value = "relacionadoCon") String relacionadoCon){
        LOGGER.info("Searching Concept by "+ relacionadoCon +"  = RequestMethod.GET");
        return this.conceptoService.findByRelacionadoCon(relacionadoCon);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Concepto insertConcepto(@RequestBody Concepto concepto){
        LOGGER.info("Insert Concepto entity method = RequestMethod.POS, "+concepto );
        return this.conceptoService.saveConcepto(concepto);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Concepto updateConcepto(@RequestBody Concepto concepto){
        LOGGER.info("Update Concepto entity method = RequestMethod.PUT, "+concepto );
        return this.conceptoService.saveConcepto(concepto);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public @ResponseBody String deleteConcepto(@PathVariable long id){
        LOGGER.info("Delete ConceptoId"+ id +" entity method = RequestMethod.DELETE");
         this.conceptoService.deleteConcepto(id);
        return SUCSESS_RESULT;
    }
}
