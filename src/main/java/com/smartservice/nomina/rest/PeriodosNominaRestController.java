package com.smartservice.nomina.rest;

import com.smartservice.nomina.model.PeriodoNomina;
import com.smartservice.nomina.service.impl.PeriodosNominaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Collection;

@RestController
@RequestMapping("/SmartServiceWs/rest/periodos")
public class PeriodosNominaRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodosNominaRestController.class);

    private PeriodosNominaServiceImpl periodosNominaService;

    @Autowired
    public PeriodosNominaRestController(final PeriodosNominaServiceImpl periodosNominaService){
        this.periodosNominaService = periodosNominaService;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{type}/{year}/{company}", produces = MediaType.APPLICATION_JSON)
    public  @ResponseBody
    Collection<PeriodoNomina> searchNomina(@PathVariable String type, @PathVariable int year, @PathVariable long company){
        LOGGER.info("Query periodos Nomina  "+ year +" method = RequestMethod.GET");
        return this.periodosNominaService.findPeriods(year, type, company);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{company}", produces = MediaType.APPLICATION_JSON)
    public  @ResponseBody
    Collection<PeriodoNomina> searchPeriodosByIdEmpresa(@PathVariable long company){
        LOGGER.info("Query periodos Nomina  "+ company +" method = RequestMethod.GET");
        return this.periodosNominaService.findPeriodsByIdEmpresa( company);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, value = "/{type}/{year}/{company}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody
    Collection<PeriodoNomina> generatePeriodosNomina(@PathVariable String type, @PathVariable int year, @PathVariable long company){
        LOGGER.info("Load novedad validada method = RequestMethod.PUT, "+ year +" Type " + type + " Empresa: "+ company);
        return this.periodosNominaService.generatePeriodosNomina( type, year, company);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody
    PeriodoNomina updatePeriodoNomina(@RequestBody PeriodoNomina periodoNomina){
        LOGGER.info("Saving Periodo method = RequestMethod.POST, "+ periodoNomina);
        return this.periodosNominaService.savePeriodoNomina(periodoNomina);
    }
}
