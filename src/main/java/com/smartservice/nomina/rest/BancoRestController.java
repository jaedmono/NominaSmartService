package com.smartservice.nomina.rest;

import com.smartservice.nomina.model.Banco;
import com.smartservice.nomina.service.impl.BancoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Collection;


@RestController
@RequestMapping("/SmartServiceWs/rest/banco")
public class BancoRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BancoRestController.class);

    private BancoServiceImpl bancoService;

    private static final String SUCSESS_RESULT = "<result>success</result>";

    @Autowired
    BancoRestController(BancoServiceImpl bancoService){
        this.bancoService = bancoService;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Banco> readBancos(){
        LOGGER.info("Geting all of Banco entity method = RequestMethod.GET");
        return this.bancoService.getAllBancos();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Banco insertBanco(@RequestBody Banco banco){
        LOGGER.info("Saving Banco method = RequestMethod.POST, "+ banco);
        return this.bancoService.saveBanco(banco);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Banco updateBanco(@RequestBody Banco banco){
        LOGGER.info("Saving Banco method = RequestMethod.POST, "+ banco);
        return this.bancoService.saveBanco(banco);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public @ResponseBody String deleteBanco(@PathVariable Long id){
        LOGGER.info("Deleting Banco"+ id +" method = RequestMethod.DELETE");
        this.bancoService.deleteBanco(id);
        return SUCSESS_RESULT;
    }
}
