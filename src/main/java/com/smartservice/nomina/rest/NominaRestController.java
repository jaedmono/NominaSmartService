package com.smartservice.nomina.rest;

import com.smartservice.nomina.exception.NominaException;
import com.smartservice.nomina.model.Empresa;
import com.smartservice.nomina.model.Nomina;
import com.smartservice.nomina.rest.response.NominaRestControllerResponse;
import com.smartservice.nomina.service.impl.NominaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Collection;

@RestController
@RequestMapping("/SmartServiceWs/rest/nomina")
public class NominaRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NominaRestController.class);

    private NominaServiceImpl nominaService;

    private static final String SUCSESS_RESULT = "{success: true}";

    @Autowired
    NominaRestController(NominaServiceImpl nominaService){
        this.nominaService = nominaService;
    }

    /*@CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    Collection<Nomina> readNominas(){
        LOGGER.info("Geting all of Nomina entity method = RequestMethod.GET");
        return this.nominaService.getAllNominas();
    }*/

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<Nomina> searchNominaByIdEmpresa(@RequestParam(value = "idEmpresa") long idEmpresa){
        LOGGER.info("Searching Nomina "+ idEmpresa +" method = RequestMethod.GET");
        return this.nominaService.findByIdEmpresa(idEmpresa);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public  @ResponseBody Nomina searchNomina(@PathVariable long id){
        LOGGER.info("Searching Nomina "+ id +" method = RequestMethod.DELETE");
        return this.nominaService.findByIdNomina(id);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ExceptionHandler({NominaException.class})
    public @ResponseBody Nomina insertNomina(@RequestBody Nomina nominaContrato){
        LOGGER.info("Saving Nomina method = RequestMethod.POST, "+ nominaContrato);
        return this.nominaService.insertNomina(nominaContrato);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Nomina updateNomina(@RequestBody Nomina nominaContrato){
        LOGGER.info("Saving Nomina method = RequestMethod.PUT, "+ nominaContrato);
        return this.nominaService.saveNomina(nominaContrato);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public  @ResponseBody
    NominaRestControllerResponse deleteNomina(@PathVariable long id){
        LOGGER.info("Deleting Nomina "+ id +" method = RequestMethod.DELETE");
        this.nominaService.deleteNomina(id);
        NominaRestControllerResponse response = new NominaRestControllerResponse();
        response.setState(true);
        return response;
    }
}
