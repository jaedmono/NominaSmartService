package repositories;

import com.smartservice.nomina.config.SpringProfileInitializer;
import com.smartservice.nomina.model.Empleado;
import com.smartservice.nomina.service.impl.EmpleadoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class EmpleadoRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmpleadoRepositoryTest.class);

   /* @Autowired
    EmpleadoServiceImpl service;

    @Test
    public void readEmpleadosTest(){
        LOGGER.info("Inicio del test readEmpleadosTest");
        List<Empleado> empelados = service.getEmpleados();
        for (Empleado empleado:empelados){
            LOGGER.info("Empleado: "+empleado);
        }
    }
*/
}
