package com.argpro.argentinaprograma.controllers.SeccionController;

import com.argpro.argentinaprograma.controllers.GenericoController.BaseControllerImplem;
import com.argpro.argentinaprograma.models.DTOs.ImagencardPreviewDatabaseDTO;
import com.argpro.argentinaprograma.models.DTOs.educacion.EducacioncardDto;
import com.argpro.argentinaprograma.models.DTOs.educacion.ImagencardEduPreviewDTO;
import com.argpro.argentinaprograma.models.SeccionModel.educacion.EducacionModel;
import com.argpro.argentinaprograma.models.SeccionModel.educacion.Educacioncard;
import com.argpro.argentinaprograma.models.SeccionModel.experiencia.Experienciacard;
import com.argpro.argentinaprograma.repositories.SeccionRepository.IEducacionRepository;
import com.argpro.argentinaprograma.repositories.SeccionRepository.IEducacioncardRepository;
import com.argpro.argentinaprograma.services.SeccionService.EducacionService;
import com.argpro.argentinaprograma.services.SeccionService.EducacionServiceImple;


import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SQLOutputImpl;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/seccion/educacion")
public class EducacionController extends BaseControllerImplem<EducacionModel, EducacionServiceImple> {

    @Autowired
    EducacionService educacionService;

    @Autowired
    IEducacionRepository iEducacionRepository;

    //repositorio de la cards
    @Autowired
    IEducacioncardRepository iEducacioncardRepository;

    Logger logger = LoggerFactory.getLogger(EducacionController.class);

    //TODO: backend de Educacion

    //TODO: Camnbiar titulo de Educacion
    @PostMapping("/tituloupdate")
    public ResponseEntity<?> updatetitulo(
            @RequestParam("tituloupdate") String tituloupdate)
            throws IOException {

        try{
            if(tituloupdate.isEmpty()){
                return new ResponseEntity<>("error: titulo y subtitulo vacios", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Optional<EducacionModel> educacionsDatabase = iEducacionRepository.findById(1L);
            var textosDatabase = educacionsDatabase.get().getTitulo();

            if(textosDatabase.isEmpty()){
                educacionsDatabase.get().setTitulo("titulo de educacion");
                return new ResponseEntity<>("sobremi DB estan vacios", HttpStatus.NOT_MODIFIED);
            }

            educacionsDatabase.get().setTitulo(tituloupdate);

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
            var guardarresultado = iEducacionRepository.save(educacionsDatabase.get());

            logger.info("que nos trae  el parametro titulo:... " + tituloupdate);
            //logger.info("que nos trae  el parametro subtitulo:... " + subtitulo);


            return new ResponseEntity<>(guardarresultado, HttpStatus.OK);

        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en sobremi titulo controller ... "+ex);
        }
    }

                                            //TODO: ACA EMPEZAMOS LA CARD....

    // IMAGEN PREVIEW
    //imagenes de la card
    @PostMapping("/getThumbnailPreview")
    public ResponseEntity<?> getThumbnailPreview(
            @RequestPart("thumbnailPreview") MultipartFile imagencardpreview)
            throws IOException {  //   @RequestParam("id") Integer id,   QUITAR POR ALS DUDAS ID

        logger.info("imagencardpreview.getBytes(): "+imagencardpreview.getBytes());

        ImagencardEduPreviewDTO imagencardEduPreviewDTO = new ImagencardEduPreviewDTO();

        try{

            //var imagencard = iExperienciacardRepository.findById(Long.valueOf(String.valueOf(id)));  //QUITAR POR ALS DUDAS

            //convertir archivo a array para guardar en BASE DE DATOS
            byte[] imgcardpreview_db = educacionService.compressBytes(imagencardpreview.getBytes());

            //convertir a imagen para MOSTRAR DISPLAY en angular
            byte[] imgcardpreview_ts = educacionService.decompressBytes(imgcardpreview_db);

            //imagencard.get().setImagencard(imgcardpreview_db);  //QUITAR POR ALS DUDAS


            if(imgcardpreview_db.length > 0 || imgcardpreview_ts.length > 0){

                Educacioncard educacioncard = new Educacioncard(); //ORIGINAL

                //experienciacard.setPortadatitulo(imagen.getOriginalFilename());
                imagencardEduPreviewDTO.setImagencardeduPreviewdb(imgcardpreview_db);
                imagencardEduPreviewDTO.setImagecardeduPreviewts(imgcardpreview_ts);

                //List<Experienciacard> imagencardlist = new ArrayList<>();
                //Set<byte[]> img = iExperienciacardRepository.findByImagencard();
                //var imagescardlist = iExperienciacardRepository.findByImagencard();
                var imagescardlist = iEducacioncardRepository.findAll();

                //List<Experienciacard> cardlists = new ArrayList<>();
                //List<String> listString = new ArrayList<>();
                List<byte[]> listByte = new ArrayList<>();

                //imagencardPreviewDatabaseDTO.setImgcardpurodb(imagescardlist);
                for(int i = 0; i < imagescardlist.size(); i++){

                    //logger.info(imagescardlist.get(i).getImagencard().toString());
                    //logger.info(imagescardlist.get(i).getTitulocard());

                    //listString.add(imagescardlist.get(i).getTitulocard());
                    listByte.add(imagescardlist.get(i).getThumbnail());

                    //imagencardPreviewDatabaseDTO.setTitulocardtest(Collections.singletonList(imagescardlist.get(i).getTitulocard()));
                    //(imagescardlist.get(i).getTitulocard());
                    //imagencardlist.forEach(imagenes -> imagenes.setImagencard(experienciaService.decompressBytes(imagescardlist)));
                    //experienciaService.decompressBytes(imagescardlist);
                    //cardlists.get(0).setImagencard( experienciaService.decompressBytes(imagescardlist.get(0).getImagencard()));

                }

                //iExperienciacardRepository.save(imagencard.get()); //QUITAR POR ALS DUDAS

                imagencardEduPreviewDTO.setImgcardedupurodb(listByte);
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
            return new ResponseEntity<ImagencardEduPreviewDTO>(imagencardEduPreviewDTO, HttpStatus.OK);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en experiencia controller ... "+ex);
        }

    }

    // al crear nuevas card ------------------------------------
    @PostMapping("/creareducacioncard")
    public ResponseEntity<?> crearcard(
            @RequestParam("titulocard") String titulocard,
            @RequestParam("subtitulocard") String subtitulocard,
            @RequestParam("thumbnailPreviewts") String thumbnailPreviewts,
            @RequestParam("titulofechacard") String titulofechacard,
            @RequestParam("dateinicio") String dateinicio,
            @RequestParam("datefinal") String datefinal

            ) throws IOException { //@RequestBody EducacioncardDto educacioncardDto    @RequestBody Map<String, String> nuevaeducacioncard



        /*

         */

            Educacioncard educacioncardnew = new Educacioncard();

            //fechas
            //SimpleDateFormat formdate = new SimpleDateFormat("dd/MM/yyyy");
            //String inicialdate = formdate.format(educacioncardDto.getStart());

            //var datestring = educacionService.convertDateToString(educacioncardDto.getStart());

            //System.out.println(datestring);

            try {
                LocalDate fechainiciodata = LocalDate.parse(dateinicio);
                LocalDate fechafinaldata = LocalDate.parse(datefinal);

                ImagencardEduPreviewDTO imagencardEduPreviewDTO = new ImagencardEduPreviewDTO();
                educacioncardnew.setTitulocard(titulocard);
                educacioncardnew.setSubtitulocard(subtitulocard);
                educacioncardnew.setThumbnail(thumbnailPreviewts.getBytes(StandardCharsets.UTF_8));
                educacioncardnew.setTitulodate(titulofechacard);
                educacioncardnew.setStart(fechainiciodata);
                educacioncardnew.setEnd(fechafinaldata);

                logger.info("educacion dto "+titulofechacard+" este es titulo fecha card ");

                iEducacioncardRepository.save(educacioncardnew);
                return new ResponseEntity<>(educacioncardnew, HttpStatus.OK);


            }catch(Exception ex){
                return ResponseEntity.badRequest().body("ERROR en educacion titulo controller ... "+ex.getMessage());
            }


    }


    // inputs independientes -----------------------------------

    // traer info de cards
    @GetMapping("/getcardlist")
    public ResponseEntity<?> getCardlist() throws IOException {

        List<EducacioncardDto> cardlistsDTO = new ArrayList<>();

        try {

            var findallTest = iEducacioncardRepository.findAll().stream().collect(Collectors.toList());

            if(findallTest.isEmpty()){
                return ResponseEntity.ok().body("EDUCACION CARD ESTA VACIO");
            }

            AtomicInteger cont = new AtomicInteger();
            findallTest.stream().forEach(elemento -> {
                //logger.info(Arrays.toString(elemento.getThumbnail()));
                cardlistsDTO.add(
                  /*new EducacioncardDto("d", "d", "d", "d", LocalDate.parse("s"), LocalDate.parse("s"))); */
                  new EducacioncardDto(
                          Math.toIntExact(elemento.getId()),
                          elemento.getTitulocard(),
                          elemento.getSubtitulocard(),
                          new String(elemento.getThumbnail(), StandardCharsets.UTF_8),
                          elemento.getTitulodate(),
                          elemento.getStart(),
                          elemento.getEnd()));

                //new String(elemento.getThumbnail(), StandardCharsets.UTF_8))
                cont.getAndIncrement();

            });

            System.out.println("Cuantos elementos hay en la lista " + cardlistsDTO.size());

            //System.out.println("Enhanced for-each loop: i = " + cardlistsDTO.get(0));
            return ResponseEntity.ok().body(cardlistsDTO);//);


        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR al traer las cardlistr ... "+ex);
        }
    }

    //TODO: Preview de thumbnail solo retornara base64
    @PostMapping("/getThumbnailcardPreview")
    public ResponseEntity<?> getThumbnailcardPreview(
            @RequestPart("thumbnailfilePreview") MultipartFile thumbnailfilePreview)
            throws IOException {  //   @RequestParam("id") Integer id,   QUITAR POR ALS DUDAS ID

        //logger.info("imagencardpreview.getBytes(): "+imagencardpreview.getBytes());

        ImagencardPreviewDatabaseDTO imagencardPreviewDatabaseDTO = new ImagencardPreviewDatabaseDTO();
        try{

            //var imagencard = iExperienciacardRepository.findById(Long.valueOf(String.valueOf(id)));  //QUITAR POR ALS DUDAS


            //convertir archivo a array para guardar en BASE DE DATOS
            byte[] imgcardpreview_db = educacionService.compressBytes(thumbnailfilePreview.getBytes());
            //imagencardPreviewDatabaseDTO.setImagencardPreviewdb(Base64.getEncoder().encode(thumbnailfilePreview.getBytes()));



            //convertir a imagen para MOSTRAR DISPLAY en angular
            byte[] imgcardpreview_ts = educacionService.decompressBytes(imgcardpreview_db);

            //imagencard.get().setImagencard(imgcardpreview_db);  //QUITAR POR ALS DUDAS


            if(imgcardpreview_db.length > 0 || imgcardpreview_ts.length > 0){

                Experienciacard experienciacard = new Experienciacard(); //ORIGINAL

                //experienciacard.setPortadatitulo(imagen.getOriginalFilename());
                imagencardPreviewDatabaseDTO.setImagencardPreviewdb(imgcardpreview_db);
                imagencardPreviewDatabaseDTO.setImagecardPreviewts(imgcardpreview_ts);

                //List<Experienciacard> imagencardlist = new ArrayList<>();
                //Set<byte[]> img = iExperienciacardRepository.findByImagencard();
                //var imagescardlist = iExperienciacardRepository.findByImagencard();

                //imagencardPreviewDatabaseDTO.setImgcardpurodb(imagescardlist);
                //iExperienciacardRepository.save(imagencard.get()); //QUITAR POR ALS DUDAS
                //imagencardPreviewDatabaseDTO.setImgcardpurodb(listByte);



            }

            return new ResponseEntity<ImagencardPreviewDatabaseDTO>(imagencardPreviewDatabaseDTO, HttpStatus.OK);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en experiencia controller ... "+ex);
        }

    }

    //imagenes de la card
    @PostMapping("/actualizarthumbnail")
    public ResponseEntity<?> getImgcardPreview(
            @RequestParam("id") Integer id,
            @RequestPart("imagencardpreview") MultipartFile imagencardpreview)
            throws IOException {  //   @RequestParam("id") Integer id,   QUITAR POR ALS DUDAS ID

        logger.info("imagencardpreview.getBytes().toString(): "+imagencardpreview.getBytes().toString());

        logger.info("encode stream: "+ Base64.getEncoder().encodeToString(imagencardpreview.getBytes()));

        var base64encoder = Base64.getEncoder().encodeToString(imagencardpreview.getBytes());

        //ImagencardPreviewDatabaseDTO imagencardPreviewDatabaseDTO = new ImagencardPreviewDatabaseDTO();

        try{

            var imagencard = iEducacioncardRepository.findById(Long.valueOf(id));  //QUITAR POR ALS DUDAS

            //convertir archivo a array para guardar en BASE DE DATOS
            byte[] imgcardpreview_db = educacionService.compressBytes(imagencardpreview.getBytes());

            logger.info("Base64.getEncoder().encodeToString(imgcardpreview_db): "+Base64.getEncoder().encodeToString(imgcardpreview_db));

            //convertir a imagen para MOSTRAR DISPLAY en angular
            byte[] imgcardpreview_ts = educacionService.decompressBytes(imgcardpreview_db);

            logger.info("imgcardpreview_ts: "+imgcardpreview_ts);

            //imagencard.get().setImagencard(Base64.getDecoder().decode(imagencardpreview.getBytes()));  //QUITAR POR ALS DUDAS
            imagencard.get().setThumbnail(Base64.getEncoder().encode(imagencardpreview.getBytes()));
            //imagencard.get().setImagencard(Base64.getEncoder().encodeToString(imagencardpreview.getBytes()));


            if(imgcardpreview_db.length > 0 || imgcardpreview_ts.length > 0){

                Experienciacard experienciacard = new Experienciacard(); //ORIGINAL


                iEducacioncardRepository.save(imagencard.get()); //QUITAR POR ALS DUDAS

            }

            return new ResponseEntity<Educacioncard>(imagencard.get(), HttpStatus.OK);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en experiencia controller ... "+ex);
        }

    }

    @PostMapping("/titulocardupdate")
    //public String updatetitulocard(
    public ResponseEntity<?> updatetitulocard(
            @RequestParam("id") Integer id,
            @RequestParam("titulocardupdate") String titulocardupdate)
            throws IOException {

        try{
            if(titulocardupdate.isEmpty()){
                //return new ResponseEntity<>("error: titulo y subtitulo vacios", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Optional<Educacioncard> educacionsDatabase = iEducacioncardRepository.findById(Long.valueOf(id));
            var textosDatabase = educacionsDatabase.get().getTitulocard();

            if(textosDatabase.isEmpty()){
                educacionsDatabase.get().setTitulocard("titulo de educacion");
                //return new ResponseEntity<>("sobremi DB estan vacios", HttpStatus.NOT_MODIFIED);
            }
            educacionsDatabase.get().setTitulocard(titulocardupdate);

            var guardarresultado = iEducacioncardRepository.save(educacionsDatabase.get());

            //logger.info("que nos trae  el parametro titulo:... " + guardarresultado.getTitulocard());

            return new ResponseEntity<>(guardarresultado.getTitulocard(), HttpStatus.OK);

            //return guardarresultado.getTitulocard();
            //return ResponseEntity.accepted().body(guardarresultado.getTitulocard());

        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en sobremi titulo controller ... "+ex);
            //return ex.getMessage();
        }
    }


    @PostMapping("/subtitulocardupdate")
    public ResponseEntity<?> subtitulocardupdate(
            @RequestParam("id") Integer id,
            @RequestParam("subtitulocardupdate") String subtitulocardupdate)
            throws IOException {
        try{
            if(subtitulocardupdate.isEmpty()){
                return new ResponseEntity<>("error: titulo y subtitulo vacios", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Optional<Educacioncard> educacionsDatabase = iEducacioncardRepository.findById(Long.valueOf(id));

            var textosDatabase = educacionsDatabase.get().getSubtitulocard();

            if(textosDatabase.isEmpty()){
                educacionsDatabase.get().setSubtitulocard("titulo de educacion");
                return new ResponseEntity<>("sobremi DB estan vacios", HttpStatus.NOT_MODIFIED);
            }

            educacionsDatabase.get().setSubtitulocard(subtitulocardupdate);

            var guardarresultado = iEducacioncardRepository.save(educacionsDatabase.get());

            educacionsDatabase.get().setSubtitulocard(subtitulocardupdate);
            //logger.info("que nos trae  el parametro subtitulo:... " + subtitulo);

            return new ResponseEntity<>(guardarresultado, HttpStatus.OK);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en sobremi subtitulocardupdate controller ... "+ex);
        }
    }

    @PostMapping("/updatetitulofechacard")
    public ResponseEntity<?> updatetitulofechacard(
            @RequestParam("id") Integer id,
            @RequestParam("titulofechacard") String titulofechacard)
            throws IOException {

        logger.info("el ID es " + id + " y el ttulofecha card es: " + titulofechacard);


        try{
            if(titulofechacard.isEmpty()){
                logger.info("asfsf asfsf p´kef a´f k a faf sfasfwefe wefewwe f:... " + titulofechacard);
                return new ResponseEntity<>("error: titulo y subtitulo vacios", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Optional<Educacioncard> educacionsDatabase = iEducacioncardRepository.findById(Long.valueOf(id));
            var textosDatabase = educacionsDatabase.get().getTitulodate();

            /*
            if(textosDatabase.isEmpty()){
                educacionsDatabase.get().setSubtitulocard("titulo de educacion");
                return new ResponseEntity<>("sobremi DB estan vacios", HttpStatus.NOT_MODIFIED);
            }
             */

            educacionsDatabase.get().setTitulodate(titulofechacard);
            var guardarresultado = iEducacioncardRepository.save(educacionsDatabase.get());
            //logger.info("que nos trae  el parametro subtitulo:... " + subtitulo);

            return new ResponseEntity<>(educacionsDatabase, HttpStatus.OK);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en Educacion subtitulocardupdate controller ... "+ex);
        }
    }

    //fecha inicio y finalizacion card
    @PostMapping("/updatefechascard")
    public ResponseEntity<?> updatefechascard(
            @RequestParam("id") Integer id,
            @RequestParam("fechainicio") String fechainicio,
            @RequestParam("fechafinal") String fechafinal)
            throws IOException {
        try{

            Optional<Educacioncard> educacionsDatabase = iEducacioncardRepository.findById(Long.valueOf(id));

            var textosDatabase = educacionsDatabase.get().getTitulodate();

            if(textosDatabase.isEmpty()){
                educacionsDatabase.get().setSubtitulocard("titulo de educacion");
                return new ResponseEntity<>("sobremi DB estan vacios", HttpStatus.NOT_MODIFIED);
            }
            LocalDate fechainiciodata = LocalDate.parse(fechainicio);
            LocalDate fechafinaldata = LocalDate.parse(fechafinal);

            educacionsDatabase.get().setStart(fechainiciodata);
            educacionsDatabase.get().setEnd(fechafinaldata);

            var guardarresultado = iEducacioncardRepository.save(educacionsDatabase.get());
            //logger.info("que nos trae  el parametro subtitulo:... " + subtitulo);

            return new ResponseEntity<>(guardarresultado, HttpStatus.OK);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body("ERROR en sobremi subtitulocardupdate controller ... "+ex);
        }
    }

    //FECHAS CARD




}