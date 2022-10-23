package com.argpro.argentinaprograma.controllers.SeccionController;


import com.argpro.argentinaprograma.controllers.GenericoController.BaseControllerImplem;
import com.argpro.argentinaprograma.models.DTOs.GetCardlistDTO;
import com.argpro.argentinaprograma.models.DTOs.ImagencardPreviewDatabaseDTO;
import com.argpro.argentinaprograma.models.DTOs.RespuestaModel;
import com.argpro.argentinaprograma.models.SeccionModel.experiencia.ExperienciaModel;
import com.argpro.argentinaprograma.models.SeccionModel.experiencia.Experienciacard;
import com.argpro.argentinaprograma.models.SeccionModel.sobremi.SobremiModel;
import com.argpro.argentinaprograma.repositories.SeccionRepository.IExperienciaRepository;
import com.argpro.argentinaprograma.repositories.SeccionRepository.IExperienciacardRepository;
import com.argpro.argentinaprograma.services.SeccionService.ExperienciaService;
import com.argpro.argentinaprograma.services.SeccionService.ExperienciaServiceImple;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/seccion/experiencia")
public class ExperienciaController extends BaseControllerImplem<ExperienciaModel, ExperienciaServiceImple> {

    @Autowired
    ExperienciaService experienciaService;

    @Autowired
    IExperienciaRepository iExperienciaRepository;

    //repositorio de la cards
    @Autowired
    IExperienciacardRepository iExperienciacardRepository;
    Logger logger = LoggerFactory.getLogger(ExperienciaController.class);

    /*

    //TODO: actualizar campo de titulo de seccion EXPERIENCIA' - NO CARD
    @PostMapping("/update")
    public ResponseEntity<?> updatetitulo(@RequestParam("titulo") String titulo) throws IOException {

        ExperienciaModel enviarInfoAngular = new ExperienciaModel();
        enviarInfoAngular.setTituloexp(titulo);
        //enviarInfoAngular.getExperienciacards();


        //logger.info("hola"+enviarInfoAngular.getExperienciacards().stream().findFirst().get().getTitulocard());
        logger.info("hola: expcards "+titulo);

        try{
            iExperienciaRepository.save(enviarInfoAngular);

            return new ResponseEntity<ExperienciaModel>(enviarInfoAngular, HttpStatus.OK);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en navbar controller ... "+ex);
        }

    }

     */



    // traer info de cards
    @GetMapping("/getcardlist")
    public ResponseEntity<?> getCardlist() throws IOException {
        //List<Experienciacard> cardlists = new ArrayList<>();
        List<GetCardlistDTO> cardlistsDTO = new ArrayList<>();

        try {

            var findallTest = iExperienciacardRepository.findAll().stream().collect(Collectors.toList());
            //cardlistsDTO.add(new GetCardlistDTO("titulovacio", "subtitulovacio", ""));


            //iExperienciacardRepository.findAll().forEach(cardlistdata -> cardlists.add(cardlistdata));
            //List<Experienciacard> cardlists = new ArrayList<>();

            //var findallTest = iExperienciacardRepository.findAll().stream().collect(Collectors.toList());


            //var tipodeimagen = iExperienciacardRepository.findAll().get(0).getImagencard();
            //String string = new String(tipodeimagen, StandardCharsets.UTF_8);

            //System.out.println("find all tipo de imagen " + string);

            //var decodedWithMime = Base64.getMimeDecoder().decode(iExperienciacardRepository.findAll().get(0).getImagencard().toString());

            //logger.info(String.valueOf(Base64.getDecoder().decode(iExperienciacardRepository.findAll().get(0).getImagencard())));
            //System.out.println("find all test get() " + String.valueOf(findallTest.get(0).getImagencard()));

            //cardlistsDTO.addAll();
            //Collections.copy(Arrays.asList(findallTest.toArray()), cardlistsDTO);
            if(findallTest.isEmpty()){
                return ResponseEntity.ok().body("EXPERIENCE CARD ESTA VACIO");
            }

            AtomicInteger cont = new AtomicInteger();
            findallTest.stream().forEach(elemento -> {
                logger.info(String.valueOf(elemento.getImagencard()));
                cardlistsDTO.add(new GetCardlistDTO(Math.toIntExact(elemento.getId()), elemento.getTitulocard(), elemento.getSubtitulocard(), new String(elemento.getImagencard(), StandardCharsets.UTF_8)));

                cont.getAndIncrement();

            });


            /*
           findallTest.stream().collect(HashMap::new, (h, o) -> h.put(h.size(), o), (h, o) -> {})
                   .forEach((i, o) -> {

                      cardlistsDTO.add(o);

                      cardlistsDTO.get((Integer) i).setImagencardString(Base64.getDecoder().decode(o).setImagencardString();));

                        //logger.info("i es: "+i+ "o es:" +o.toString());
                   });

             */

            /*
            int[] intArr = { 0,1,2,3,4 };
            for (int num : intArr) {
                System.out.println("Enhanced for-each loop: i = " + num);
            }

             */

            /*
            Integer i = 1;
            for (Experienciacard findalltest : findallTest){
                //cardlistsDTO.addAll((Collection<? extends GetCardlistDTO>) findalltest);

                cardlistsDTO.get(0).setImagencardString(String.valueOf(Base64.getDecoder().decode(findalltest.getImagencard())));

                //cardlistsDTO.forEach();
                //cardlistsDTO.get(i).setSubtitulo(findalltest.getSubtitulocard());
                //cardlistsDTO.get(i).setImagencardString(String.valueOf(Base64.getDecoder().decode(findalltest.getImagencard())));

               i++;
            }

            */

            System.out.println("Cuantos elementos hay en la lista " + cardlistsDTO.size());

            //System.out.println("Enhanced for-each loop: i = " + cardlistsDTO.get(0));
             return ResponseEntity.ok().body(cardlistsDTO);//);


            /*
            var findallTest = iExperienciacardRepository.findAll()
                    .stream()
                    .map(
                            imagen -> new String(imagen.getImagencard(), StandardCharsets.UTF_8)


                    )
                    .collect(Collectors.toList()); //

             */


            //var findall = iExperienciacardRepository.findAll().stream().findFirst().get();

            //ArrayList<GetCardlistDTO> enviarAngular = new ArrayList<>();
            //enviarAngular.add(findallTest);

            //findallTest.stream().collect(Collectors.toList());
            //logger.info(findallTest.stream().findAny().get().getImagencard().toString());






             ////var algoimg = findallTest.stream().map(imagen -> imagen.getImagencard().toString()).collect(Collectors.toList());
            //var algoimg = findallTest.stream().map(imagen -> new String(imagen.getImagencard(), StandardCharsets.UTF_8)).collect(Collectors.toList());





            //logger.info(algoimg.get(0).toString());
            //(logger.info(algoimg.get(1).toString());
            //String imagencardString = new String(findall.getImagencard(), StandardCharsets.UTF_8);

            //enviarAngular.addAll(findallTest.stream().collect(Collectors.toList()));

            //logger.info(findallTest.get(0).getTitulocard());
            //logger.info(String.valueOf(findallTest.stream().count()));

            //findallTest.stream().map(GetCardlistDTO::getImagencardString).collect(Collectors.toList());
            //findallTest.stream().count();
            /*
            if(!findallTest.isEmpty()){
                for(int i = 0; i < 7; i++){
                    for(int j = 0; j < 6; j++) {
                        enviarAngular.get(i).setTitulo(findallTest.get(j).getTitulocard());
                        enviarAngular.get(i).setSubtitulo(findallTest.get(j).getSubtitulocard());
                        enviarAngular.get(i).setImagencardString(String.valueOf(findallTest.get(j).getImagencard()));
                    }

                }
            }

             */

            //enviarAngular.stream().map(dto -> new findallTest(dto.getTitulo()))


            //enviarAngular.setTitulo(findallTest.stream());

            //enviarAngular.setTitulo(findall.getTitulocard());
            //enviarAngular.setSubtitulo(findall.getSubtitulocard());

            //String imagencardString = new String(findall.getImagencard(), StandardCharsets.UTF_8);
            //enviarAngular.setImagencardString(imagencardString);

            //cardlists.add(findall.stream().findFirst().get());

            //logger.info(enviarAngular.get(0).getImagencardString());


            //if(cardlists.isEmpty()){
              //  return null;
           // }

            //return new ResponseEntity<Experienciacard>(cardlists, HttpStatus.OK);


        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR al traer las cardlistr ... "+ex);
        }
    }

    /*
    private List<GetCardlistDTO> convertToDto(List<Experienciacard> experienciacards) {
        List<GetCardlistDTO> getCardlistDTO = modelMapper.map(experienciacards, GetCardlistDTO.class);
        getCardlistDTO.get(0).setImagencardString(experienciacards.get(0).getImagencard().toString());
        return postDto;
    }


     */
    @DeleteMapping("/deletecards")
    public ResponseEntity<?> DeleteCardlist() throws IOException {

       iExperienciacardRepository.deleteAll();

        var borrado = iExperienciacardRepository.findAll();

        if(borrado.isEmpty()){
            return ResponseEntity.ok().body("BORRADO");
        }

        return ResponseEntity.noContent().build();

    }




    /*
    //imagenes de la card
    @PostMapping("/subirimagencard")
    public ResponseEntity<?> uplaodImage(@RequestParam("imagen") MultipartFile imagen) throws IOException {

        Experienciacard enviarInfoAngular = new Experienciacard();

        try{
            //byte[] base64 = sobremiService.compressBytes(file.getBytes());
            byte[] base64 = experienciaService.compressBytes(imagen.getBytes()); // convertir archivo a array para guardar en BASE DE DATOS

            //intentamos pasarlo en descompress antes de enviarlo al observable de angular
            //byte[] base64 = usuarioService.decompressBytes(usuario.getImagenbyte()); --> ESTO DEVUELVE EL BASE64

            //traerlo por id


            //si la imagen viene null desde la base de datos


            if(base64.length > 0){

                Experienciacard experienciacard = new Experienciacard(); // ORIGINAL

                //experienciacard.setPortadatitulo(imagen.getOriginalFilename());
                experienciacard.setImagencard(base64);

                // guardamos elmodelo en imagenModel de  UsuarioModel pero global
                // para pasarlo por el body entity de response entity

                //this.imagenModel.setImagenbyte(base64decompress);



                //iUsuarioRepository.save(usuarioModel); --- POR AHORA NO GUARDAMOS LA IMAGEN EN LA ASE DE DATOS
                //iNavbarRepository.save(navbarModel);
                iExperienciacardRepository.save(experienciacard);

                byte[] base64decompress = sobremiService.decompressBytes(base64); //convertir a imagen para MOSTRAR DISPLAY en angular

                //traer el id de laimagen agregada
                enviarInfoAngular.setId(sobremiRepository.findAll().stream().findFirst().get().getId());
                enviarInfoAngular.setPortadatitulo(experienciacard.getPortadatitulo());
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

     */

    //imagenes de la card
    @PostMapping("/getImgcardPreview")
    public ResponseEntity<?> getImgcardPreview(

            @RequestPart("imagencardpreview") MultipartFile imagencardpreview)
            throws IOException {  //   @RequestParam("id") Integer id,   QUITAR POR ALS DUDAS ID

        logger.info("imagencardpreview.getBytes(): "+imagencardpreview.getBytes());

        ImagencardPreviewDatabaseDTO imagencardPreviewDatabaseDTO = new ImagencardPreviewDatabaseDTO();

        try{

            //var imagencard = iExperienciacardRepository.findById(Long.valueOf(String.valueOf(id)));  //QUITAR POR ALS DUDAS

            //convertir archivo a array para guardar en BASE DE DATOS
            byte[] imgcardpreview_db = experienciaService.compressBytes(imagencardpreview.getBytes());

            //convertir a imagen para MOSTRAR DISPLAY en angular
            byte[] imgcardpreview_ts = experienciaService.decompressBytes(imgcardpreview_db);

            //imagencard.get().setImagencard(imgcardpreview_db);  //QUITAR POR ALS DUDAS


            if(imgcardpreview_db.length > 0 || imgcardpreview_ts.length > 0){

                Experienciacard experienciacard = new Experienciacard(); //ORIGINAL

                //experienciacard.setPortadatitulo(imagen.getOriginalFilename());
                imagencardPreviewDatabaseDTO.setImagencardPreviewdb(imgcardpreview_db);
                imagencardPreviewDatabaseDTO.setImagecardPreviewts(imgcardpreview_ts);

                //List<Experienciacard> imagencardlist = new ArrayList<>();
                //Set<byte[]> img = iExperienciacardRepository.findByImagencard();
                //var imagescardlist = iExperienciacardRepository.findByImagencard();
                var imagescardlist = iExperienciacardRepository.findAll();

                List<Experienciacard> cardlists = new ArrayList<>();
                List<String> listString = new ArrayList<>();
                List<byte[]> listByte = new ArrayList<>();

                //imagencardPreviewDatabaseDTO.setImgcardpurodb(imagescardlist);
                for(int i = 0; i < imagescardlist.size(); i++){

                    //logger.info(imagescardlist.get(i).getImagencard().toString());
                    //logger.info(imagescardlist.get(i).getTitulocard());

                    //listString.add(imagescardlist.get(i).getTitulocard());
                    listByte.add(imagescardlist.get(i).getImagencard());

                    //imagencardPreviewDatabaseDTO.setTitulocardtest(Collections.singletonList(imagescardlist.get(i).getTitulocard()));
                    //(imagescardlist.get(i).getTitulocard());
                    //imagencardlist.forEach(imagenes -> imagenes.setImagencard(experienciaService.decompressBytes(imagescardlist)));
                    //experienciaService.decompressBytes(imagescardlist);
                    //cardlists.get(0).setImagencard( experienciaService.decompressBytes(imagescardlist.get(0).getImagencard()));

                }

                //iExperienciacardRepository.save(imagencard.get()); //QUITAR POR ALS DUDAS

                imagencardPreviewDatabaseDTO.setImgcardpurodb(listByte);
                logger.info(listByte.toString());

                // guardamos elmodelo en imagenModel de  UsuarioModel pero global
                // para pasarlo por el body entity de response entity
                //this.imagenModel.setImagenbyte(base64decompress);

                //iUsuarioRepository.save(usuarioModel); --- POR AHORA NO GUARDAMOS LA IMAGEN EN LA ASE DE DATOS
                //iNavbarRepository.save(navbarModel);
                //iExperienciacardRepository.save(experienciacard);

                //traer el id de laimagen agregada
                //enviarInfoAngular.setId(sobremiRepository.findAll().stream().findFirst().get().getId());
                //enviarInfoAngular.setPortadatitulo(experienciacard.getPortadatitulo());
                //enviarInfoAngular.setPortada(base64decompress);

            }

            //iCrudimagenRepository.save(sobremiModel);
            //sobremiModel.setPortada(compressBytes(file));
            //sobremiRepository.save(sobremiModel);

            //return ResponseEntity.ok("sera succesful");
            return new ResponseEntity<ImagencardPreviewDatabaseDTO>(imagencardPreviewDatabaseDTO, HttpStatus.OK);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en experiencia controller ... "+ex);
        }

    }

    //crear nuevos cards
    //desde aca las cards
    @PostMapping("/crearnuevacard")
    public ResponseEntity<?> crearcards(
            @RequestParam("titulocard") String titulocard,
            @RequestParam("subtitulocard") String subtitulocard,
            @RequestParam("imagecardPreviewts") String imagecardPreviewts
    ) throws IOException {


        logger.info(String.valueOf(imagecardPreviewts));

        Experienciacard experienciacard = new Experienciacard();
        experienciacard.setTitulocard(titulocard);
        experienciacard.setSubtitulocard(subtitulocard);
        experienciacard.setImagencard(imagecardPreviewts.getBytes(StandardCharsets.UTF_8));

        ImagencardPreviewDatabaseDTO enviarInfoAngular = new ImagencardPreviewDatabaseDTO();

        enviarInfoAngular.setImagencardString(imagecardPreviewts);
        //Set<Integer> sourceSet = Sets.newHashSet(0, 1, 2, 3, 4, 5);

        //String s = new String( , StandardCharsets.UTF_8);

        //logger.info("hola"+enviarInfoAngular.getExperienciacards().stream().findFirst().get().getTitulocard());
        //logger.info("hola: expcards "+expcards.get(0).getTitulocard());

        try{

            //enviarInfoAngular.setExperienciacards(Collections.singleton(experienciacard));
            iExperienciacardRepository.save(experienciacard);

            var expcards = iExperienciacardRepository.findAll().stream().collect(Collectors.toList());

            //return ResponseEntity.ok("sera succesful");
            return new ResponseEntity<List<Experienciacard>>(expcards, HttpStatus.OK);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en navbar controller ... "+ex);
        }

    }


    //TODO: Update titulo del card ACTUALIZAR titulo de la card ya existente



    @PostMapping("/updatetitulocard")
    public ResponseEntity<?> updatetext(
            @RequestParam("id") Integer id,
            @RequestParam("tituloeditcard") String tituloeditcard)
            throws IOException {

        SobremiModel sobremiModel = new SobremiModel();

        try{
            if(tituloeditcard.isEmpty()){
                return new ResponseEntity<>("error: titulo y subtitulo vacios", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Optional<Experienciacard> experienciacardsDatabase = iExperienciacardRepository.findById(Long.valueOf(id));
            var textosDatabase = experienciacardsDatabase.get().getTitulocard();

            if(textosDatabase.isEmpty()){
                return new ResponseEntity<>("sobremi DB estan vacios", HttpStatus.NOT_MODIFIED);
            }

            experienciacardsDatabase.get().setTitulocard(tituloeditcard);

            /*
            if(texto.get("titulo").equals(textosDatabase.get().getTitulo())){
                return ResponseEntity.status(HttpStatus.IM_USED).body("El titulo Ingresado ya existe Ingrese otro nuevo.");
            }

            if(texto.get("subtitulo").equals(textosDatabase.get().getSubtitulo())){
                return ResponseEntity.status(HttpStatus.IM_USED).body("El subtitulo Ingresado ya existe Ingrese otro nuevo.");
            }
            */


            //sobremiModel.setTitulo(texto.get("titulo"));



            /*
            if(!subtitulo.isEmpty()){
                //sobremiModel.setSubtitulo(texto.get("subtitulo"));
                textosDatabase.get().setSubtitulo(subtitulo);
            }
            */
            var guardarresultado = iExperienciacardRepository.save(experienciacardsDatabase.get());



            logger.info("que nos trae  el parametro titulo:... " + tituloeditcard);
            //logger.info("que nos trae  el parametro subtitulo:... " + subtitulo);


            return new ResponseEntity<>(guardarresultado, HttpStatus.OK);

        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en sobremi titulo controller ... "+ex);
        }


    }


    //TODO: updatesubtitulo del card ACTUALIZAR subtitulo de card ya existente
    //TODO: actualizar campo de SUBTITULO
    @PostMapping("/updatesubtitulocard")
    public ResponseEntity<?> updatesubtitulo(
            @RequestParam("id") String id,
            @RequestParam("subtituloeditcard") String subtituloeditcard)
            throws IOException {

        try{
            if(subtituloeditcard.isEmpty()){
                return new ResponseEntity<>("error: subtitulo vacios", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Optional<Experienciacard> experienciacardsDatabase = iExperienciacardRepository.findById(Long.valueOf(id));
            var textosDatabase = experienciacardsDatabase.get().getSubtitulocard();

            if(textosDatabase.isEmpty()){
                return new ResponseEntity<>("sobremi DB estan vacios", HttpStatus.NOT_MODIFIED);
            }

            experienciacardsDatabase.get().setSubtitulocard(subtituloeditcard);

            /*
            if(texto.get("titulo").equals(textosDatabase.get().getTitulo())){
                return ResponseEntity.status(HttpStatus.IM_USED).body("El titulo Ingresado ya existe Ingrese otro nuevo.");
            }

            if(texto.get("subtitulo").equals(textosDatabase.get().getSubtitulo())){
                return ResponseEntity.status(HttpStatus.IM_USED).body("El subtitulo Ingresado ya existe Ingrese otro nuevo.");
            }
            */


            //sobremiModel.setTitulo(texto.get("titulo"));
            experienciacardsDatabase.get().setSubtitulocard(subtituloeditcard);


            /*
            if(!subtitulo.isEmpty()){
                //sobremiModel.setSubtitulo(texto.get("subtitulo"));
                textosDatabase.get().setSubtitulo(subtitulo);
            }
            */
            var guardarresultado = iExperienciacardRepository.save(experienciacardsDatabase.get());

            logger.info("que nos trae  el parametro titulo:... " + subtituloeditcard);
            //logger.info("que nos trae  el parametro subtitulo:... " + subtitulo);


            return new ResponseEntity<>(guardarresultado, HttpStatus.OK);

        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en sobremi subtitulo controller ... "+ex);
        }

    }


    //ACTUALIZAR IMAGEN
    //imagenes de la card
    @PostMapping("/actualizarimg")
    public ResponseEntity<?> getImgcardPreview(
            @RequestParam("id") Integer id,
            @RequestPart("imagencardpreview") MultipartFile imagencardpreview)
            throws IOException {  //   @RequestParam("id") Integer id,   QUITAR POR ALS DUDAS ID

        logger.info("imagencardpreview.getBytes().toString(): "+imagencardpreview.getBytes().toString());

        logger.info("encode stream: "+Base64.getEncoder().encodeToString(imagencardpreview.getBytes()));

        var base64encoder = Base64.getEncoder().encodeToString(imagencardpreview.getBytes());

        //ImagencardPreviewDatabaseDTO imagencardPreviewDatabaseDTO = new ImagencardPreviewDatabaseDTO();

        try{

            var imagencard = iExperienciacardRepository.findById(Long.valueOf(id));  //QUITAR POR ALS DUDAS

            //convertir archivo a array para guardar en BASE DE DATOS
            byte[] imgcardpreview_db = experienciaService.compressBytes(imagencardpreview.getBytes());

            logger.info("Base64.getEncoder().encodeToString(imgcardpreview_db): "+Base64.getEncoder().encodeToString(imgcardpreview_db));

            //convertir a imagen para MOSTRAR DISPLAY en angular
            byte[] imgcardpreview_ts = experienciaService.decompressBytes(imgcardpreview_db);

            logger.info("imgcardpreview_ts: "+imgcardpreview_ts);

            //imagencard.get().setImagencard(Base64.getDecoder().decode(imagencardpreview.getBytes()));  //QUITAR POR ALS DUDAS
            imagencard.get().setImagencard(Base64.getEncoder().encode(imagencardpreview.getBytes()));
            //imagencard.get().setImagencard(Base64.getEncoder().encodeToString(imagencardpreview.getBytes()));


            if(imgcardpreview_db.length > 0 || imgcardpreview_ts.length > 0){

                Experienciacard experienciacard = new Experienciacard(); //ORIGINAL

                //experienciacard.setPortadatitulo(imagen.getOriginalFilename());
                //imagencardPreviewDatabaseDTO.setImagencardPreviewdb(imgcardpreview_db);
                //imagencardPreviewDatabaseDTO.setImagecardPreviewts(imgcardpreview_ts);

                //List<Experienciacard> imagencardlist = new ArrayList<>();
                //Set<byte[]> img = iExperienciacardRepository.findByImagencard();
                //var imagescardlist = iExperienciacardRepository.findByImagencard();



                //imagencardPreviewDatabaseDTO.setImgcardpurodb(imagescardlist);


                iExperienciacardRepository.save(imagencard.get()); //QUITAR POR ALS DUDAS

                //imagencardPreviewDatabaseDTO.setImgcardpurodb(listByte);
                //logger.info(listByte.toString());


            }

            return new ResponseEntity<Experienciacard>(imagencard.get(), HttpStatus.OK);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en experiencia controller ... "+ex);
        }

    }

    //ACTUALIZAR IMAGEN
    //imagenes de la card
    @DeleteMapping("/borrarcardporid/{id}")
    public ResponseEntity<?> borrarcardporid(@PathVariable("id") Integer id) throws IOException {

           RespuestaModel respuesta = new RespuestaModel("", false);
           try{
                if(!iExperienciacardRepository.existsById(id.longValue())){
                    respuesta.setMensaje("La card seleccionada no existe");
                    respuesta.setBorrado(false);
                }

               if(iExperienciacardRepository.existsById(id.longValue())){
                   iExperienciacardRepository.deleteById(id.longValue());
                   respuesta.setMensaje("La card se ha eliminado correctamente!!!");
                   respuesta.setBorrado(true);
               }

               //respuesta.setMensaje("La card se ha eliminado correctamente!!!");
               //respuesta.setBorrado(true);


               return new ResponseEntity<RespuestaModel>(respuesta, HttpStatus.OK);

           }catch(Exception exception){
               return ResponseEntity.badRequest().body("ERROR en experiencia ... "+exception);
           }

    }



}