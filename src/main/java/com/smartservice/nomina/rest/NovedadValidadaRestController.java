package com.smartservice.nomina.rest;

import com.smartservice.nomina.model.NovedadValidada;
import com.smartservice.nomina.service.impl.NovedadValidadaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Collection;

@RestController
@RequestMapping("/SmartServiceWs/rest/novedadValidada")
public class NovedadValidadaRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NovedadValidadaRestController.class);

    private NovedadValidadaServiceImpl novedadValidadaService;

    private static final String SUCSESS_RESULT = "<result>success</result>";

    @Autowired
    NovedadValidadaRestController(NovedadValidadaServiceImpl novedadValidadaService){
        this.novedadValidadaService = novedadValidadaService;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public Collection<NovedadValidada> readAllNovedadesVAlidadas(){
        LOGGER.info("Geting all of ArchivoNovedad entity method = RequestMethod.GET");
        return this.novedadValidadaService.getAllNovedadesValidadas();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/{type}")
    public Collection<NovedadValidada> readNovedadesVAlidadasById(@PathVariable long id, @PathVariable String type){
        LOGGER.info("Geting all of ArchivoNovedad entity method = RequestMethod.GET");
        return this.novedadValidadaService.getAllNovedadesValidadasById(id, type);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody NovedadValidada insertNovedadValidada(@RequestBody NovedadValidada archivoNovedad){
        LOGGER.info("Saving ArchivoNovedad method = RequestMethod.POST, "+ archivoNovedad);
        return this.novedadValidadaService.saveNovedadValidada(archivoNovedad);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/{type}/{company}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody NovedadValidada updateNovedadVAlidada(@PathVariable long id, @PathVariable String type, @PathVariable long company){
        LOGGER.info("Load novedad validada method = RequestMethod.PUT, "+ id +" Type " + type + " Empresa: "+ company);
        return this.novedadValidadaService.saveNovedadValidada(id, type, company);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public @ResponseBody String deleteNovedadValidada(@PathVariable Long id){
        LOGGER.info("Deleting ArchivoNovedad"+ id +" method = RequestMethod.DELETE");
        this.novedadValidadaService.deleteNovedadValidada(id);
        return SUCSESS_RESULT;
    }
}
