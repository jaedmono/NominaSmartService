package com.smartservice.nomina.rest;

import com.smartservice.nomina.model.Empleado;
import com.smartservice.nomina.service.impl.EmpleadoServiceImpl;
import jersey.repackaged.com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Collection;

@RestController
@RequestMapping("/SmartServiceWs/rest/empleado")
public class EmpleadoRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmpleadoRestController.class);

    private final EmpleadoServiceImpl empleadoService;

    private static final String SUCSESS_RESULT = "<result>success</result>";

    @Autowired
    EmpleadoRestController(EmpleadoServiceImpl empleadoService){
        this.empleadoService = empleadoService;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    Collection<Empleado> readEmpleados(){
        LOGGER.info("Geting all of Empleado entity method = RequestMethod.GET");
        return Lists.newArrayList(this.empleadoService.getEmpleados());
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Empleado insertEmpleados(@RequestBody Empleado empleado){
        LOGGER.info("Saving Empleado entity method = RequestMethod.POST, "+ empleado.getEmail());
        return this.empleadoService.saveEmpleado(empleado);

    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Empleado updateEmpleados(@RequestBody Empleado empleado){
        LOGGER.info("Updating Empleado entity method = RequestMethod.PUT, "+ empleado);
        return this.empleadoService.saveEmpleado(empleado);

    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public String deleteEmpleados(@RequestBody Long id){
        LOGGER.info("Deleting Empleado "+ id +" method = RequestMethod.DELETE");
        this.empleadoService.deleteEmpleado(id);
        return SUCSESS_RESULT;
    }


}
