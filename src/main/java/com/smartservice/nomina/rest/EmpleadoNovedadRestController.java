package com.smartservice.nomina.rest;

import com.smartservice.nomina.model.Concepto;
import com.smartservice.nomina.model.NominaNovedad;
import com.smartservice.nomina.service.impl.NominaNovedadServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/SmartServiceWs/rest/empleadoNovedad")
public class EmpleadoNovedadRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmpleadoNovedadRestController.class);

    private NominaNovedadServiceImpl nominaNovedadService;

    @Autowired
    public EmpleadoNovedadRestController(NominaNovedadServiceImpl nominaNovedadService){
        this.nominaNovedadService = nominaNovedadService;
    }


    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{idContract}/{idPayroll}", produces = MediaType.APPLICATION_JSON)
    public  @ResponseBody
    List<NominaNovedad> searchChangesByIdConctract(@PathVariable long idContract, @PathVariable long idPayroll){
        LOGGER.info("Searchin changes payroll by Id employee: "+ idContract +" method = RequestMethod.GET");
        return this.nominaNovedadService.getChangesPayrollByIdContract(idContract, idPayroll);
    }
}
