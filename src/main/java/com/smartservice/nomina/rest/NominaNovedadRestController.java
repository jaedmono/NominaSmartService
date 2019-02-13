package com.smartservice.nomina.rest;

import com.smartservice.nomina.model.Concepto;
import com.smartservice.nomina.model.Nomina;
import com.smartservice.nomina.model.NominaNovedad;
import com.smartservice.nomina.service.impl.NominaNovedadServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/SmartServiceWs/rest/novedad")
public class NominaNovedadRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NominaNovedadRestController.class);

    private NominaNovedadServiceImpl nominaNovedadService;

    private static final String SUCSESS_RESULT = "<result>success</result>";

    @Autowired
    NominaNovedadRestController(NominaNovedadServiceImpl nominaNovedadService){
        this.nominaNovedadService = nominaNovedadService;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Collection<NominaNovedad> readNominasNovedad(){
        LOGGER.info("Geting all of NominaNovedad entity method = RequestMethod.GET");
        return this.nominaNovedadService.getAllNominaNovedad();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public  @ResponseBody
    List<Concepto> searchNominaConcepts(@PathVariable long id){
        LOGGER.info("Deleting Nomina "+ id +" method = RequestMethod.DELETE");
        return this.nominaNovedadService.getConceptsPayroll(id);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody NominaNovedad insertNominaNovedad(@RequestBody NominaNovedad nominaNovedad){
        LOGGER.info("Saving NominaNovedad method = RequestMethod.POST, "+ nominaNovedad);
        return this.nominaNovedadService.saveNominaNovedad(nominaNovedad);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody NominaNovedad updateNominaNovedad(@RequestBody NominaNovedad nominaNovedad){
        LOGGER.info("Saving NominaNovedad method = RequestMethod.PUT, "+ nominaNovedad);
        return this.nominaNovedadService.saveNominaNovedad(nominaNovedad);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public @ResponseBody String deleteNominaNovedad(@PathVariable long id){
        LOGGER.info("Deleting NominaNovedad " +id+ " method = RequestMethod.DELETE");
        this.nominaNovedadService.deleteNominaNovedad(id);
        return SUCSESS_RESULT;
    }
}
