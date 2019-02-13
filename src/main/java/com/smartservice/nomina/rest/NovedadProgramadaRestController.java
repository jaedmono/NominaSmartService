package com.smartservice.nomina.rest;

import com.smartservice.nomina.model.NovedadProgramada;
import com.smartservice.nomina.service.impl.NovedadProgramadaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Collection;

@RestController
@RequestMapping("/SmartServiceWs/rest/novedadprogramada")
@CrossOrigin
public class NovedadProgramadaRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmpleadoRestController.class);

    private final NovedadProgramadaServiceImpl novedadProgramadaService;

    @Autowired
    NovedadProgramadaRestController(NovedadProgramadaServiceImpl novedadProgramadaService){
        this.novedadProgramadaService = novedadProgramadaService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    Collection<NovedadProgramada> readContratosByCompany(@RequestParam(value = "idCompany") long idCompany){
        LOGGER.info("Geting all of Banco entity method = RequestMethod.GET");
        return this.novedadProgramadaService.getNovedadProgramadaByIdEmpresa(idCompany);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    NovedadProgramada insertNovedadProgramada(@RequestBody NovedadProgramada novedadProgramada){
        LOGGER.info("Saving all of NovedadProgramada entity method = RequestMethod.POST, "+ novedadProgramada);
        return this.novedadProgramadaService.saveNovedadProgramada(novedadProgramada);

    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    NovedadProgramada updateNovedadProgramada(@RequestBody NovedadProgramada novedadProgramada){
        LOGGER.info("Saving all of NovedadProgramada entity method = RequestMethod.PUT, "+ novedadProgramada);
        return this.novedadProgramadaService.saveNovedadProgramada(novedadProgramada);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    void deleteNovedadProgramada(@PathVariable Long id){
        LOGGER.info("Deleting all of NovedadProgramada " +id+ " entity method = RequestMethod.DELETE");
        this.novedadProgramadaService.deleteNovedadProgramada(id);
    }
}
