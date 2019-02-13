package com.smartservice.nomina.rest;

import com.smartservice.nomina.model.ArchivoNovedad;
import com.smartservice.nomina.service.impl.ArchivoNovedadServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Collection;

@RestController
@RequestMapping("/SmartServiceWs/rest/archivonovedad")
public class ArchivoNovedadRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArchivoNovedadRestController.class);

    private ArchivoNovedadServiceImpl archivoNovedadesService;

    private static final String SUCSESS_RESULT = "<result>success</result>";

    @Autowired
    ArchivoNovedadRestController(ArchivoNovedadServiceImpl archivoNovedadesService){
        this.archivoNovedadesService = archivoNovedadesService;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public Collection<ArchivoNovedad> readArchivoNovedad(){
        LOGGER.info("Geting all of ArchivoNovedad entity method = RequestMethod.GET");
        return this.archivoNovedadesService.getAllArchivoNovedad();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody
    ArchivoNovedad insertArchivoNovedades(@RequestBody ArchivoNovedad archivoNovedad){
        LOGGER.info("Saving ArchivoNovedad method = RequestMethod.POST, "+ archivoNovedad);
        return this.archivoNovedadesService.saveArchivoNovedad(archivoNovedad);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody ArchivoNovedad updateArchivoNovedad(@RequestBody ArchivoNovedad archivoNovedad){
        LOGGER.info("Saving ArchivoNovedad method = RequestMethod.POST, "+ archivoNovedad);
        return this.archivoNovedadesService.saveArchivoNovedad(archivoNovedad);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public @ResponseBody String deleteArchivoNovedad(@PathVariable Long id){
        LOGGER.info("Deleting ArchivoNovedad"+ id +" method = RequestMethod.DELETE");
        this.archivoNovedadesService.deleteArchivoNovedad(id);
        return SUCSESS_RESULT;
    }
}
