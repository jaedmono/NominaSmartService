package com.smartservice.nomina.rest;


import com.smartservice.nomina.service.impl.ArchivoNovedadServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/SmartServiceWs/rest/uploadfile")
public class UploadFileRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BancoRestController.class);

    private ArchivoNovedadServiceImpl archivoNovedadesService;

    @Autowired
    UploadFileRestController(ArchivoNovedadServiceImpl archivoNovedadesService){
        this.archivoNovedadesService = archivoNovedadesService;
    }


    @RequestMapping(method = RequestMethod.POST)
    public void uploadFile(MultipartHttpServletRequest request) throws IOException {
        LOGGER.info("Saving Banco method = RequestMethod.POST, ");
        Iterator<String> itr=request.getFileNames();
        MultipartFile file=request.getFile(itr.next());
        int empresaId = request.getIntHeader("empresa_id");
        String fileName=file.getOriginalFilename();
        File dir = new File("E:\\SmartServiceProjects\\NominaSmartService\\fileUpload");
        if (dir.isDirectory())
        {
            File serverFile = new File(dir,fileName);
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(file.getBytes());
            stream.close();
            this.archivoNovedadesService.saveArchivoNovedad(empresaId,fileName);
        }else {
            LOGGER.info("not");
        }

    }
}
