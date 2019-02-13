package com.smartservice.nomina.rest;

import com.smartservice.nomina.model.Descuento;
import com.smartservice.nomina.service.impl.DescuentoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/SmartServiceWs/rest/descuento")
public class DescuentoRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DescuentoRestController.class);

    private DescuentoServiceImpl descuentoService;

    private static final String SUCSESS_RESULT = "<result>success</result>";

    @Autowired
    DescuentoRestController(DescuentoServiceImpl descuentoService){
        this.descuentoService = descuentoService;
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    Collection<Descuento> readDescuentos(@RequestParam(value = "idCompany") long idCompany,
                                         @RequestParam(value = "relacionadoCon") String relacionadoCon){
        LOGGER.info("Geting all of Descuento entity method = RequestMethod.GET");
        return this.descuentoService.getDescuentosByCompany(idCompany, relacionadoCon);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody  Descuento  insertDescuento(@RequestBody Descuento descuento){
        LOGGER.info("Saving Descuento method = RequestMethod.POST");
        return this.descuentoService.saveDescuento(descuento);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Descuento updateDescuento(@RequestBody Descuento descuento){
        LOGGER.info("Saving Descuento method = RequestMethod.PUT");
        return this.descuentoService.saveDescuento(descuento);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public String deleteDescueto(@PathVariable long id){
        LOGGER.info("Deleting Descuento method = RequestMethod.DELETE");
        this.descuentoService.deleteDescuento(id);
        return SUCSESS_RESULT;
    }
}
