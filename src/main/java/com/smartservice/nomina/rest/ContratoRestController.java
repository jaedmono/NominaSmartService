package com.smartservice.nomina.rest;

import com.smartservice.nomina.model.Contrato;
import com.smartservice.nomina.service.impl.ContratoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Collection;


@RestController
@RequestMapping("/SmartServiceWs/rest/contrato")
public class ContratoRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContratoRestController.class);

    private ContratoServiceImpl contratoService;

    private static final String SUCSESS_RESULT = "<result>success</result>";

    @Autowired
    ContratoRestController(ContratoServiceImpl contratoService){
        this.contratoService = contratoService;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    Collection<Contrato> readContratos(){
        LOGGER.info("Geting all of Banco entity method = RequestMethod.GET");
        return this.contratoService.getAllContratos();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{idEmpresa}/{estado}", produces = MediaType.APPLICATION_JSON)
    Collection<Contrato> readContratosByCompany(@PathVariable long idEmpresa, @PathVariable String estado){
        LOGGER.info("Geting all of Contracts entity method = RequestMethod.GET idEmpresa: "+idEmpresa + " estado: "+estado);
        return this.contratoService.getContratosByIdEmpresaAndEstatus(idEmpresa, estado);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Contrato insertContrato(@RequestBody Contrato contrato){
        LOGGER.info("Saving Contrato method = RequestMethod.POST, "+ contrato );
        return this.contratoService.saveContrato(contrato);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Contrato updateContrato(@RequestBody Contrato contrato){
        LOGGER.info("Saving Contrato method = RequestMethod.PUT, "+ contrato);
        return this.contratoService.saveContrato(contrato);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public @ResponseBody String deleteContrato(@PathVariable long id){
        LOGGER.info("Deleting Contrato "+ id +" method = RequestMethod.DELETE");
        this.contratoService.deleteContrato(id);
        return SUCSESS_RESULT;
    }

}
