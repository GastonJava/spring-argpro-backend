package com.argpro.argentinaprograma.controllers.SeccionController;

import com.argpro.argentinaprograma.models.SeccionModel.sobremi.SobremiModel;
import com.argpro.argentinaprograma.repositories.SeccionRepository.SobremiRepository;
import com.argpro.argentinaprograma.services.SeccionService.SobremiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/seccion/sobremi")
public class SobremiController {

    Logger logger = LoggerFactory.getLogger(SobremiController.class);

    @Autowired
    SobremiService sobremiService;

    @Autowired
    SobremiRepository sobremiRepository;

    @PostMapping("/upload")
    public ResponseEntity<?> uplaodImage(@RequestParam("file") MultipartFile file) throws IOException {

        SobremiModel enviarInfoAngular = new SobremiModel();

        SobremiModel sobremi = new SobremiModel();

        try{
            //byte[] base64 = sobremiService.compressBytes(file.getBytes());
            byte[] base64 = sobremiService.compressBytes(file.getBytes()); // convertir archivo a array para guardar en BASE DE DATOS

            //intentamos pasarlo en descompress antes de enviarlo al observable de angular
            //byte[] base64 = usuarioService.decompressBytes(usuario.getImagenbyte()); --> ESTO DEVUELVE EL BASE64

            //traerlo por id


            //si la imagen viene null desde la base de datos


            if(base64.length > 0){

                SobremiModel sobremiModel = new SobremiModel(); // ORIGINAL

                sobremiModel.setPortadatitulo(file.getOriginalFilename());
                sobremiModel.setPortada(base64);

                // guardamos elmodelo en imagenModel de  UsuarioModel pero global
                // para pasarlo por el body entity de response entity

                //this.imagenModel.setImagenbyte(base64decompress);



                //iUsuarioRepository.save(usuarioModel); --- POR AHORA NO GUARDAMOS LA IMAGEN EN LA ASE DE DATOS
                //iNavbarRepository.save(navbarModel);
                sobremiRepository.save(sobremiModel);

                byte[] base64decompress = sobremiService.decompressBytes(base64); //convertir a imagen para devolver a angular

                //traer el id de laimagen agregada
                enviarInfoAngular.setId(sobremiRepository.findAll().stream().findFirst().get().getId());
                enviarInfoAngular.setPortadatitulo(sobremiModel.getPortadatitulo());
                enviarInfoAngular.setPortada(base64decompress);

            }


            //iCrudimagenRepository.save(sobremiModel);
            //sobremiModel.setPortada(compressBytes(file));
            //sobremiRepository.save(sobremiModel);

            //return ResponseEntity.ok("sera succesful");
            return new ResponseEntity<SobremiModel>(enviarInfoAngular, HttpStatus.OK);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en Sobremi controller ... "+ex);
        }

    }

    //ACTUALIZAR IMAGEN
    @PostMapping("/update")
    public ResponseEntity<?> updateImage(@RequestParam("file") MultipartFile file,
            @RequestParam("sobremiid") Long sobremiid) throws IOException {

        SobremiModel nuevoSobremiModel = new SobremiModel(); //creamos est
        try{
            byte[] base64 = sobremiService.compressBytes(file.getBytes()); // convertir archivo a array

            //este se guardara (escribira) en la base de datos
            Optional<SobremiModel> currentSobremiModel = sobremiRepository.findById(sobremiid);

            if(currentSobremiModel.get().getPortada().length == base64.length){
                //return new ResponseEntity<>(HttpStatus.IM_USED);
                return new ResponseEntity<>("Imagen ya existente", HttpStatus.IM_USED);
            }

            if(base64.length > 0){

                currentSobremiModel.get().setPortadatitulo(file.getOriginalFilename());
                currentSobremiModel.get().setPortada(base64);

                //convertir a imagen para devolver a angular
                byte[] base64decompress = sobremiService.decompressBytes(base64);

                // este no se guarda en la base de datos solo retorna a angular
                nuevoSobremiModel.setPortadatitulo(file.getOriginalFilename());
                nuevoSobremiModel.setPortada(base64decompress);

                //guardamos en el currentNavbarModel los NUEVOS datos
                sobremiRepository.save(currentSobremiModel.get());
            }

            return new ResponseEntity<SobremiModel>(nuevoSobremiModel, HttpStatus.OK);

        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en sobremi controller ... "+ex);
        }

    }

    @GetMapping("/getimagen")
    public ResponseEntity<?> getImage() throws IOException {
        SobremiModel sendSobremiModel = new SobremiModel(); // modelo para enviar a angular

        try{
            //Optional<NavbarModel> navbarData = iNavbarRepository.findById(1L);
            List<SobremiModel> sobremiData = sobremiRepository.findAll();

            if(!sobremiData.isEmpty()) {
                //navbarData.stream().findFirst();

                var sobremidb = sobremiData.stream().findFirst().get();

                if (sobremidb.getPortada().length > 0) {
                    byte[] base64decompress = sobremiService.decompressBytes(sobremidb.getPortada()); //convertir a imagen para devolver a angular

                    sendSobremiModel.setId(sobremidb.getId());
                    sendSobremiModel.setPortadatitulo(sobremidb.getPortadatitulo());
                    sendSobremiModel.setPortada(base64decompress);
                }

                sendSobremiModel.setTitulo(sobremidb.getTitulo());
                sendSobremiModel.setSubtitulo(sobremidb.getSubtitulo());

                //logger.info("que nos trae BASE DE DATOS:... " + navbarData.stream().findFirst().get().getId());
            }


            return new ResponseEntity<SobremiModel>(sendSobremiModel, HttpStatus.OK);

        }catch(Exception ex) {
            return new ResponseEntity<>(""+ex, HttpStatus.BAD_REQUEST);
        }
    }

    //ELIMINAR IMAGEN
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") Long id) throws IOException {

        SobremiModel nuevoSobremiModel = new SobremiModel();

        try{
            Optional<SobremiModel> imagenBorrada = sobremiRepository.findById(id);
            if(imagenBorrada.isPresent()){
                sobremiRepository.deleteAll();
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.NOT_MODIFIED);
            }

        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en sobremi controller ... "+ex);
        }

    }

    //@RequestBody MultiValueMap<String, String> texto - @RequestParam("texto") Object[] texto -
    //TODO: actualizar campo de titulo
    @PostMapping("/updatetitulo")
    public ResponseEntity<?> updatetext(
            @RequestParam("titulo") String titulo)
            throws IOException {

         SobremiModel sobremiModel = new SobremiModel();

        try{
            if(titulo.isEmpty()){
                return new ResponseEntity<>("error: titulo y subtitulo vacios", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            List<SobremiModel> sobremiDatabase = sobremiRepository.findAll();
            var textosDatabase = sobremiDatabase.stream().findFirst();

            if(textosDatabase.isEmpty()){
                return new ResponseEntity<>("sobremi DB estan vacios", HttpStatus.NOT_MODIFIED);
            }

            /*
            if(texto.get("titulo").equals(textosDatabase.get().getTitulo())){
                return ResponseEntity.status(HttpStatus.IM_USED).body("El titulo Ingresado ya existe Ingrese otro nuevo.");
            }

            if(texto.get("subtitulo").equals(textosDatabase.get().getSubtitulo())){
                return ResponseEntity.status(HttpStatus.IM_USED).body("El subtitulo Ingresado ya existe Ingrese otro nuevo.");
            }
            */


            //sobremiModel.setTitulo(texto.get("titulo"));
            textosDatabase.get().setTitulo(titulo);


            /*
            if(!subtitulo.isEmpty()){
                //sobremiModel.setSubtitulo(texto.get("subtitulo"));
                textosDatabase.get().setSubtitulo(subtitulo);
            }
            */
            var guardarresultado = sobremiRepository.save(textosDatabase.get());

            logger.info("que nos trae  el parametro titulo:... " + titulo);
            //logger.info("que nos trae  el parametro subtitulo:... " + subtitulo);


            return new ResponseEntity<>(guardarresultado, HttpStatus.OK);

        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en sobremi titulo controller ... "+ex);
        }


    }


    //TODO: actualizar campo de SUBTITULO
    @PostMapping("/updatesubtitulo")
    public ResponseEntity<?> updatesubtitulo(
            @RequestParam("subtitulo") String subtitulo)
            throws IOException {

        try{
            if(subtitulo.isEmpty()){
                return new ResponseEntity<>("error: subtitulo vacios", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            List<SobremiModel> sobremiDatabase = sobremiRepository.findAll();
            var textosDatabase = sobremiDatabase.stream().findFirst();

            if(textosDatabase.isEmpty()){
                return new ResponseEntity<>("sobremi DB estan vacios", HttpStatus.NOT_MODIFIED);
            }

            /*
            if(texto.get("titulo").equals(textosDatabase.get().getTitulo())){
                return ResponseEntity.status(HttpStatus.IM_USED).body("El titulo Ingresado ya existe Ingrese otro nuevo.");
            }

            if(texto.get("subtitulo").equals(textosDatabase.get().getSubtitulo())){
                return ResponseEntity.status(HttpStatus.IM_USED).body("El subtitulo Ingresado ya existe Ingrese otro nuevo.");
            }
            */


            //sobremiModel.setTitulo(texto.get("titulo"));
            textosDatabase.get().setSubtitulo(subtitulo);


            /*
            if(!subtitulo.isEmpty()){
                //sobremiModel.setSubtitulo(texto.get("subtitulo"));
                textosDatabase.get().setSubtitulo(subtitulo);
            }
            */
            var guardarresultado = sobremiRepository.save(textosDatabase.get());

            logger.info("que nos trae  el parametro titulo:... " + subtitulo);
            //logger.info("que nos trae  el parametro subtitulo:... " + subtitulo);


            return new ResponseEntity<>(guardarresultado, HttpStatus.OK);

        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en sobremi subtitulo controller ... "+ex);
        }


    }

}