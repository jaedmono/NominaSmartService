package com.smartservice.nomina.rest;

import com.smartservice.nomina.model.NominaContrato;
import com.smartservice.nomina.model.ResponseNominaContrato;
import com.smartservice.nomina.service.impl.NominaContratoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Collection;

@RestController
@RequestMapping("/SmartServiceWs/rest/nominacontrato")
public class NominaContratoRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NominaContratoRestController.class);

    private NominaContratoServiceImpl nominaContratoService;
    private static final String SUCSESS_RESULT = "<result>success</result>";


    @Autowired
    NominaContratoRestController(NominaContratoServiceImpl nominaContratoService){
        this.nominaContratoService = nominaContratoService;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    Collection<NominaContrato> readNominaContrato(){
        LOGGER.info("Geting all of NominaContrato entity method = RequestMethod.GET");
        return this.nominaContratoService.getAllNominaContrato();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET,  value = "/{id}", produces = MediaType.APPLICATION_JSON)
    Collection<ResponseNominaContrato> readNominaContratoByIdNomina(@PathVariable long id){
        LOGGER.info("Geting all of NominaContrato by idNomina: "+ id +"entity method = RequestMethod.GET");
        return this.nominaContratoService.getNominaContratosByIdNomina(id);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody NominaContrato insertNominaContrato(@RequestBody NominaContrato nominaContrato){
        LOGGER.info("Saving NominaContrato method = RequestMethod.POST, "+ nominaContrato);
        return this.nominaContratoService.saveNominaContrato(nominaContrato);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody NominaContrato updateNominaContrato(@RequestBody NominaContrato nominaContrato){
        LOGGER.info("Saving NominaContrato method = RequestMethod.PUT, "+ nominaContrato);
        return this.nominaContratoService.saveNominaContrato(nominaContrato);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public String deleteNominaContrato(@PathVariable long id){
        LOGGER.info("Deleting NominaContrato " + id + " method = RequestMethod.DELETE");
        this.nominaContratoService.deleteNominaContrato(id);
        return SUCSESS_RESULT;
    }
}
