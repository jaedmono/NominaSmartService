package com.smartservice.nomina.rest;

import com.smartservice.nomina.model.Usuario;
import com.smartservice.nomina.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Collection;

@RestController
@RequestMapping("/SmartServiceWs/rest/users")
public class UsersRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersRestController.class);

    private static final String SUCSESS_RESULT = "<result>success</result>";

    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    Collection<Usuario> readUsers(){
        LOGGER.info("Geting all of Users entity method = RequestMethod.GET");
        return this.usuarioService.getAllUsuarios();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{login}/{password}", produces = MediaType.APPLICATION_JSON)
    public  @ResponseBody
    Usuario searchUser(@PathVariable String login, @PathVariable String password){
        LOGGER.info("Searching User "+ login +" method = RequestMethod.GET");
        return this.usuarioService.findUsuarioByLoginAndPassword(login,password);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Usuario insertUser(@RequestBody Usuario usuario){
        LOGGER.info("Saving User method = RequestMethod.POST, "+ usuario);
        return this.usuarioService.saveUsuario(usuario);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Usuario updateNomina(@RequestBody Usuario usuario){
        LOGGER.info("Saving User method = RequestMethod.PUT, "+ usuario);
        return this.usuarioService.saveUsuario(usuario);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public  @ResponseBody String deleteUser(@PathVariable long id){
        LOGGER.info("Deleting User "+ id +" method = RequestMethod.DELETE");
        this.usuarioService.deleteUsuario(id);
        return SUCSESS_RESULT;
    }

}
