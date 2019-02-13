package com.smartservice.nomina.rest;

import com.smartservice.nomina.model.Empresa;
import com.smartservice.nomina.repository.EmpresaRepository;
import com.smartservice.nomina.service.EmpresaService;
import com.smartservice.nomina.service.impl.EmpresaServiceImpl;
import jersey.repackaged.com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/SmartServiceWs/rest/empresa")
public class EmpresaRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmpresaRestController.class);

    private final EmpresaService empresaService;

    @Autowired
    EmpresaRestController(EmpresaServiceImpl empresaService){this.empresaService = empresaService;}

    @RequestMapping(method = RequestMethod.GET, value = "/{idUsuario}", produces = MediaType.APPLICATION_JSON)
    public @ResponseBody Collection<Empresa> readEmpresasByUsuario(@PathVariable long idUsuario){
        LOGGER.info("Geting all of  Empresa entity method = RequestMethod.GET");
        return this.empresaService.getAllEmpresasByIdUsuario(idUsuario);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Collection<Empresa> readEmpresas(){
        LOGGER.info("Geting all of  Empresa entity method = RequestMethod.GET");
        return Lists.newArrayList( this.empresaService.getAllEmpresas());
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Empresa insertEmpresa(@RequestBody Empresa empresa){
        LOGGER.info("Saving Empresa method = RequestMethod.POST, "+ empresa);
        return this.empresaService.saveEmpresa(empresa);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Empresa udateEmpresa(@RequestBody Empresa empresa){
        LOGGER.info("Updating Empresa method = RequestMethod.PUT, "+empresa);
        return this.empresaService.saveEmpresa(empresa);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public @ResponseBody  Long deleteEmpresa(@PathVariable Long id){
        LOGGER.info("Deleting Empresa "+ id+ " method = RequestMethod.DELETE");
        this.empresaService.deleteEmpresa(id);
        return id;
    }
}
