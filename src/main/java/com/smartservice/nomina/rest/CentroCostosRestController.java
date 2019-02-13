package com.smartservice.nomina.rest;

import com.smartservice.nomina.model.CentroCostos;
import com.smartservice.nomina.service.impl.CentroCostosServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Collection;

@RestController
@RequestMapping("/SmartServiceWs/rest/centroCostos")
public class CentroCostosRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CentroCostosRestController.class);

    private CentroCostosServiceImpl centroCostosService;

    private static final String SUCSESS_RESULT = "<result>success</result>";

    @Autowired
    CentroCostosRestController(CentroCostosServiceImpl centroCostosService){
        this.centroCostosService = centroCostosService;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public Collection<CentroCostos> readCentroCostos(){
        LOGGER.info("Geting all of CentroCostos entity method = RequestMethod.GET");
        return this.centroCostosService.getAllCentrosCostos();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public @ResponseBody Collection<CentroCostos> getCentroCostos(@PathVariable String id){
        LOGGER.info("Geting CentroCostos"+ id +" method = RequestMethod.DELETE");
        return this.centroCostosService.getCentroCostosByTipo(id);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody CentroCostos insertCentroCostos(@RequestBody CentroCostos centroCostos){
        LOGGER.info("Saving CentroCostos method = RequestMethod.POST, "+ centroCostos);
        return this.centroCostosService.saveCentroCostos(centroCostos);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody CentroCostos updateCentroCostos(@RequestBody CentroCostos centroCostos){
        LOGGER.info("Saving CentroCostos method = RequestMethod.POST, "+ centroCostos);
        return this.centroCostosService.saveCentroCostos(centroCostos);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public @ResponseBody String deleteCentroCostos(@PathVariable Long id){
        LOGGER.info("Deleting CentroCostos"+ id +" method = RequestMethod.DELETE");
        this.centroCostosService.deleteCentroCostos(id);
        return SUCSESS_RESULT;
    }

}
