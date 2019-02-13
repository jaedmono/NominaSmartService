package com.smartservice.nomina.rest;

import com.smartservice.nomina.model.EntidadParafiscal;
import com.smartservice.nomina.service.impl.EntidadParafiscalServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Collection;

@RestController
@RequestMapping("/SmartServiceWs/rest/entidad")
public class EntidadParaficalRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntidadParaficalRestController.class);

    private EntidadParafiscalServiceImpl entidadParafiscalService;

    private static final String SUCSESS_RESULT = "<result>success</result>";

    @Autowired
    EntidadParaficalRestController(EntidadParafiscalServiceImpl entidadParafiscalService){
        this.entidadParafiscalService = entidadParafiscalService;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    Collection<EntidadParafiscal> readEntidades(){
        LOGGER.info("Geting all of EntidadParafiscal entity method = RequestMethod.GET");
        return this.entidadParafiscalService.getAllEntidadesParafiscal();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public @ResponseBody Collection<EntidadParafiscal> getEntidad(@PathVariable String id){
        LOGGER.info("Geting EntidadParafiscal entity by tipoEntidad method = RequestMethod.GET");
        return this.entidadParafiscalService.getEntidadParafiscal(id);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody EntidadParafiscal insertEntidadParafiscal(@RequestBody EntidadParafiscal entidadParafiscal){
        LOGGER.info("Saving EntidadParafiscal method = RequestMethod.POST, "+ entidadParafiscal);
        return this.entidadParafiscalService.saveEntidadParafiscal(entidadParafiscal);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody EntidadParafiscal updateEntidadParafiscal(@RequestBody EntidadParafiscal entidadParafiscal){
        LOGGER.info("Saving EntidadParafiscal method = RequestMethod.PUT, "+ entidadParafiscal);
        return this.entidadParafiscalService.saveEntidadParafiscal(entidadParafiscal);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public String deleteEntidadParafiscal(@PathVariable long id){
        LOGGER.info("Deleting EntidadParafiscal " + id + " method = RequestMethod.DELETE");
        this.entidadParafiscalService.deleteEntidadParafiscal(id);
        return SUCSESS_RESULT;
    }

}
