package com.argpro.argentinaprograma.controllers.SeccionController;

import com.argpro.argentinaprograma.controllers.GenericoController.BaseControllerImplem;
import com.argpro.argentinaprograma.models.SeccionModel.navbar.NavbarModel;
import com.argpro.argentinaprograma.models.SeccionModel.sobremi.SobremiModel;
import com.argpro.argentinaprograma.repositories.SeccionRepository.INavbarRepository;
import com.argpro.argentinaprograma.services.SeccionService.NavbarService;
import com.argpro.argentinaprograma.services.SeccionService.NavbarServiceImple;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/seccion/navbar")
public class NavbarController extends BaseControllerImplem<NavbarModel, NavbarServiceImple> {


    Logger logger = LoggerFactory.getLogger(NavbarController.class);

    @Autowired
    NavbarService navbarService;

    @Autowired
    INavbarRepository iNavbarRepository;

    //guardar en imagenmodel
    NavbarModel imagenModel = new NavbarModel();



    /*
    @PostMapping("/upload")
    public ResponseEntity<?> uplaodImage(@RequestParam("file") MultipartFile file) throws IOException {


        try{
            //byte[] base64 = sobremiService.compressBytes(file.getBytes());
            byte[] base64 = navbarService.compressBytes(file.getBytes());

            //intentamos pasarlo en descompress antes de enviarlo al observable de angular
            //byte[] base64 = usuarioService.decompressBytes(usuario.getImagenbyte()); --> ESTO DEVUELVE EL BASE64

            if(base64.length > 0){

                NavbarModel navbarModel = new NavbarModel(); // ORIGINAL
                navbarModel.setImagentitulo(file.getOriginalFilename());
                navbarModel.setImagenbyte(base64);

                // guardamos elmodelo en imagenModel de  UsuarioModel pero global
                // para pasarlo por el body entity de response entity
                this.imagenModel.setImagentitulo(navbarModel.getImagentitulo());
                this.imagenModel.setImagenbyte(navbarModel.getImagenbyte());

                //iUsuarioRepository.save(usuarioModel); --- POR AHORA NO GUARDAMOS LA IMAGEN EN LA ASE DE DATOS
                iNavbarRepository.save(this.imagenModel);
            }
            //iCrudimagenRepository.save(sobremiModel);
            //sobremiModel.setPortada(compressBytes(file));
            //sobremiRepository.save(sobremiModel);

            //return ResponseEntity.ok("sera succesful");
            return new ResponseEntity<NavbarModel>(this.imagenModel, HttpStatus.OK);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en navbar controller ... "+ex);
        }

    }
     */

    @PostMapping("/upload")
    public ResponseEntity<?> uplaodImage(@RequestParam("file") MultipartFile file) throws IOException {

        NavbarModel enviarInfoAngular = new NavbarModel();

        SobremiModel sobremi = new SobremiModel();

        try{
            //byte[] base64 = sobremiService.compressBytes(file.getBytes());
            byte[] base64 = navbarService.compressBytes(file.getBytes()); // convertir archivo a array para guardar en BASE DE DATOS

            //intentamos pasarlo en descompress antes de enviarlo al observable de angular
            //byte[] base64 = usuarioService.decompressBytes(usuario.getImagenbyte()); --> ESTO DEVUELVE EL BASE64

            //traerlo por id


              //si la imagen viene null desde la base de datos


              if(base64.length > 0){

                NavbarModel navbarModel = new NavbarModel(); // ORIGINAL

                navbarModel.setImagentitulo(file.getOriginalFilename());
                navbarModel.setImagenbyte(base64);

                // guardamos elmodelo en imagenModel de  UsuarioModel pero global
                // para pasarlo por el body entity de response entity

                //this.imagenModel.setImagenbyte(base64decompress);



                //iUsuarioRepository.save(usuarioModel); --- POR AHORA NO GUARDAMOS LA IMAGEN EN LA ASE DE DATOS
                  //iNavbarRepository.save(navbarModel);
                  iNavbarRepository.save(navbarModel);

                  byte[] base64decompress = navbarService.decompressBytes(base64); //convertir a imagen para devolver a angular

                  //traer el id de laimagen agregada
                  enviarInfoAngular.setId(iNavbarRepository.findAll().stream().findFirst().get().getId());
                  enviarInfoAngular.setImagentitulo(navbarModel.getImagentitulo());
                  enviarInfoAngular.setImagenbyte(base64decompress);

              }


              //iCrudimagenRepository.save(sobremiModel);
              //sobremiModel.setPortada(compressBytes(file));
              //sobremiRepository.save(sobremiModel);

            //return ResponseEntity.ok("sera succesful");
            return new ResponseEntity<NavbarModel>(enviarInfoAngular, HttpStatus.OK);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en navbar controller ... "+ex);
        }

    }

    @GetMapping("/getimagen")
    public ResponseEntity<?> getImage() throws IOException {
        NavbarModel sendNavbarModel = new NavbarModel(); // modelo para enviar a angular

        try{
            //Optional<NavbarModel> navbarData = iNavbarRepository.findById(1L);
            List<NavbarModel> navbarData = iNavbarRepository.findAll();

            if(!navbarData.isEmpty()) {
                //navbarData.stream().findFirst();

                if (navbarData.stream().findFirst().get().getImagenbyte().length > 0) {
                    byte[] base64decompress = navbarService.decompressBytes(navbarData.stream().findFirst().get().getImagenbyte()); //convertir a imagen para devolver a angular

                    sendNavbarModel.setId(navbarData.stream().findFirst().get().getId());
                    sendNavbarModel.setImagentitulo(navbarData.stream().findFirst().get().getImagentitulo());
                    sendNavbarModel.setImagenbyte(base64decompress);
                }

                //logger.info("que nos trae BASE DE DATOS:... " + navbarData.stream().findFirst().get().getId());
            }


            return new ResponseEntity<NavbarModel>(sendNavbarModel, HttpStatus.OK);

        }catch(Exception ex) {
            return new ResponseEntity<>(""+ex, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/update")
    public ResponseEntity<?> updateImage(@RequestParam("file") MultipartFile file, @RequestParam("imageid") Long imageid) throws IOException {

        NavbarModel nuevoNavbarModel = new NavbarModel();

        try{

            byte[] base64 = navbarService.compressBytes(file.getBytes()); // convertir archivo a array

            //este se guardara (escribira) en la base de datos
            Optional<NavbarModel> currentNavbarModel = iNavbarRepository.findById(imageid);

            if(currentNavbarModel.get().getImagenbyte().length == base64.length){
                //return new ResponseEntity<>(HttpStatus.IM_USED);
                return new ResponseEntity<>("Imagen ya existente", HttpStatus.IM_USED);
            }

            if(base64.length > 0){

                currentNavbarModel.get().setImagentitulo(file.getOriginalFilename());
                currentNavbarModel.get().setImagenbyte(base64);

                //convertir a imagen para devolver a angular
                byte[] base64decompress = navbarService.decompressBytes(base64);

                // este no se guarda en la base de datos solo retorna a angular
                nuevoNavbarModel.setImagentitulo(file.getOriginalFilename());
                nuevoNavbarModel.setImagenbyte(base64decompress);

                //guardamos en el currentNavbarModel los NUEVOS datos
                iNavbarRepository.save(currentNavbarModel.get());

            }

            return new ResponseEntity<NavbarModel>(nuevoNavbarModel, HttpStatus.OK);

        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en navbar controller ... "+ex);
        }

    }

    //ELIMINAR IMAGEN
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") Long id) throws IOException {

        NavbarModel nuevoNavbarModel = new NavbarModel();

        try{
            //byte[] base64 = navbarService.compressBytes(file.getBytes()); // convertir archivo a array
            //este se guardara (escribira) en la base de datos
            //Optional<NavbarModel> currentNavbarModel = iNavbarRepository.findById(1L);
            //iNavbarRepository.deleteById(id);
            iNavbarRepository.deleteAll();

            Optional<NavbarModel> imagenBorrada = iNavbarRepository.findById(id);

            if(imagenBorrada.isPresent()){
                return new ResponseEntity<>(false, HttpStatus.NOT_MODIFIED);
            }else{
                return new ResponseEntity<>(true, HttpStatus.OK);
            }


            /*
            if(currentNavbarModel.get().getImagenbyte().length > 0){
                currentNavbarModel.get().setImagentitulo("");
                currentNavbarModel.get().setImagenbyte(null);
                //guardamos en el currentNavbarModel los NUEVOS datos
                //return new ResponseEntity<>(HttpStatus.IM_USED);
            }
            iNavbarRepository.save(currentNavbarModel.get());

             */


        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en navbar controller ... "+ex);
        }

    }




}
