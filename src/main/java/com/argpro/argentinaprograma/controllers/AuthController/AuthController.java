package com.argpro.argentinaprograma.controllers.AuthController;

import com.argpro.argentinaprograma.models.DTOs.AdmincodigoDTO;
import com.argpro.argentinaprograma.models.DTOs.LoginDTO;
import com.argpro.argentinaprograma.models.DTOs.LoginuserdataDTO;
import com.argpro.argentinaprograma.models.DTOs.RegistroDTO;
import com.argpro.argentinaprograma.models.GestionUsuarioModel.RolModel;
import com.argpro.argentinaprograma.models.GestionUsuarioModel.UsuarioModel;
import com.argpro.argentinaprograma.config.seguridad.JWTAuthResonseDTO;
import com.argpro.argentinaprograma.config.seguridad.JwtTokenProvider;
import com.argpro.argentinaprograma.repositories.AdminDTORepository.IAdmindtoRepository;
import com.argpro.argentinaprograma.repositories.GestionUsuarioRepository.IRolRepository;
import com.argpro.argentinaprograma.repositories.GestionUsuarioRepository.IUsuarioRepository;
import com.argpro.argentinaprograma.services.GestionUsuarioServices.UsuarioService;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 4800) //cambiar cualquier cosa: 3600
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    RolModel varroles = new RolModel();

    LoginuserdataDTO usuarioModel = new LoginuserdataDTO();

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    //AdmincodigoDTO admincodigo = new AdmincodigoDTO();

    //Boolean esAdmin = false;

    //variable que guardara el valor de codigo admin enviado por el body
    //AdmincodigoDTO obtenercodigoadminDTO = new AdmincodigoDTO();

    @Autowired
    private IAdmindtoRepository iAdmindtoRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    private IRolRepository iRolRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/iniciarSesion")
    public ResponseEntity<JWTAuthResonseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getNombreOrEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        JWTAuthResonseDTO jwtAuthResonseDTO = new JWTAuthResonseDTO("", "", "", this.usuarioModel);

        if(authentication.isAuthenticated()){

            //List<RolModel> roles = new ArrayList<RolModel>();
            //usuario.setIsadmin()


            // traer el ISADMIN de la base de datos buscando usuario por su NOMBRE
            var usuario = iUsuarioRepository.findByNombre(loginDTO.getNombreOrEmail());
            this.usuarioModel.setNombre(usuario.getNombre());
            this.usuarioModel.setEmail(usuario.getEmail());

            //intentamos pasarlo en descompress antes de enviarlo al observable de angular
            byte[] base64 = usuarioService.decompressBytes(usuario.getImagenbyte());

            //this.usuarioModel.setImagen(usuario.getImagenbyte());
            this.usuarioModel.setImagen(base64);

            //logger.info("que nos trae ISADMIN:... "+usuario.getIsadmin());
            if(usuario.getIsadmin() == 1){
              jwtAuthResonseDTO.setRole("ADMIN");
            }else {
              jwtAuthResonseDTO.setRole("USUARIO");
             }
            //roles = iRolRepository.findByRolnombre().;

            //logger.info("que nos trae SET ROLE:... "+jwtAuthResonseDTO.getRole());

        }

        //obtenemos el token del jwtTokenProvider
        String token = jwtTokenProvider.generarToken(authentication);

        //logger.info("que nos trae TOKEN:... "+token);

        return ResponseEntity.ok(new JWTAuthResonseDTO(token, "", jwtAuthResonseDTO.getRole(), usuarioModel));
    }


    @PostMapping("/registrar") //, consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO) throws Exception{

        //logger.info("que nos trae REGISTRO DTO:... "+ Arrays.toString(registroDTO.getImagenbyte()));
        //SI NO ENVIAMOS EL CODIGO ADMN ESTE SERA 0 (USUARIO COMUN)
        /*
        if(this.admincodigo.getIsAdmin() != 1){
            this.admincodigo.setIsAdmin(0);
        }

         */


        if(iUsuarioRepository.existsByNombre(registroDTO.getNombre())) {
            return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        if(iUsuarioRepository.existsByEmail(registroDTO.getEmail())) {
            return new ResponseEntity<>("Ese email de usuario ya existe",HttpStatus.BAD_REQUEST);
        }

        try{

            UsuarioModel usuario = new UsuarioModel();
            usuario.setNombre(registroDTO.getNombre());
            //usuario.setApellido(registroDTO.getApellido());
            usuario.setEmail(registroDTO.getEmail());
            usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
            usuario.setIsadmin(registroDTO.getIsadmin());
            usuario.setImagenbyte(registroDTO.getImagenbyte());
            usuario.setTituloimg(registroDTO.getTituloimg());

            if(registroDTO.getIsadmin() == 1){
                this.varroles = iRolRepository.findByRolnombre("ROLE_ADMIN");
            }

            if(registroDTO.getIsadmin() == 0){
                this.varroles = iRolRepository.findByRolnombre("ROLE_USER");
            }
            //this.varroles = iRolRepository.findByRolnombre("ROLE_USER");

            /*
            // si el codigo de admin   es correcto true else false
            boolean esAdmin = iUsuarioRepository.codAdmin(registroDTO.getIsadmin());

            if(esAdmin){ // 1: ES UN ADMIN .... 0: ES UN USUARIO COMUN
            //RolModel roles = iRolRepository.findByRolnombre("ROLE_USER");
            this.varroles = iRolRepository.findByRolnombre("ROLE_ADMIN");
            }else if(registroDTO.getIsadmin() == 0){
            this.varroles = iRolRepository.findByRolnombre("ROLE_USER");
            }

            */


             usuario.setRoles(Collections.singletonList(this.varroles)); //Collections.singletonlist / List.of(roles)

             iUsuarioRepository.save(usuario);
             return new ResponseEntity<>("Usuario registrado exitosamente",HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Usuario no se pudo registrar ",HttpStatus.BAD_REQUEST);
        }

    }

    //GETTER PARA ENVIAR EL TRUE O FALSE DEL CODIGO ADMIN
    //@PostMapping("/something")
    @PostMapping("/codigoadmin")
    public ResponseEntity<?> postcodigoadmin(@RequestBody AdmincodigoDTO adminCodigoDTO) {
        //@RequestBody AdmincodigoDTO adminCodigoDTO
        //@RequestParam("parametro") Integer parametro
        //this.obtenercodigoadminDTO = adminCodigoDTO;

        try{
            //System.out.println("adminCodigoDTO: "+adminCodigoDTO);
            //Boolean dato = iAdmindtoRepository.Codigoadmin(adminCodigoDTO.getCodigoadmin());
            //Boolean dato = iAdmindtoRepository.Codigoadmin(parametro); //adminCodigoDTO.getCodigoadmin()
            if(adminCodigoDTO.getCodigoadmin() == 555){
                //return ResponseEntity.ok([true]);
                //esAdmin = true;
                //this.admincodigo.setIsAdmin(1);

                return new ResponseEntity<>(true, HttpStatus.OK);
            }

            //this.admincodigo.setIsAdmin(0);
            /*
            if(parametro == 555){ //parametro == 555
                return ResponseEntity.ok(parametro);
            }
             */
            if(adminCodigoDTO.getCodigoadmin() != 555){
                //esAdmin = false;
                //return ResponseEntity.ok(false);
                //return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
            }

            //return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
            //return ResponseEntity.ok("El Codigo Invalido: "+adminCodigoDTO.getCodigoadmin()); //"dato incorrecto: "+dato
            return new ResponseEntity<>(false, HttpStatus.OK);
            //return ResponseEntity.ok(false);
        }catch(Exception ex){
            return new ResponseEntity<>("no se porque el error... "+ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    /*
    @PostMapping("/codigoadmin")
    public ResponseEntity<?> postcodigoadmin(@RequestBody AdminCodigoDTO adminCodigoDTO) {
        boolean esAdmin = iUsuarioRepository.codAdmin(adminCodigoDTO.getCodadmin());
        if(esAdmin){
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        }else {
            return new ResponseEntity<Boolean>(false,HttpStatus.UNAUTHORIZED);
        }
    }
     */
}