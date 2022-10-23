package com.argpro.argentinaprograma.controllers.GenericoController;

import com.argpro.argentinaprograma.controllers.GenericoController.BaseController;
import com.argpro.argentinaprograma.models.config.Base;
import com.argpro.argentinaprograma.services.GenericoService.BaseServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


public class BaseControllerImplem<T extends Base, S extends BaseServiceImple<T, Long>>
    implements BaseController<T, Long> {

    @Autowired
    S service;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente luego.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente luego. \"}");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody T model) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.save(model));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente luego. \"}");
        }
    }

    //rol por defecto
    /*
    @GetMapping("/query")
    public ResponseEntity<?> findByName(@RequestParam("name") String name) {

        try {
         return ResponseEntity.status(HttpStatus.OK).body(service.findByName(name));
        } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente luego. \"}");
        }
    }
    */

    // probamos el response entity para personificar https
    /*
     * @GetMapping("/pruebarespuesta")
     * ResponseEntity<String> traerRespuesta(){
     * return new ResponseEntity<>("Esto es un mensaje Entity", HttpStatus.OK);
     * }
     */

    // http://localhost:8080/usuario/query?prioridad=5
    /*
     * @GetMapping("/query")
     * public ArrayList<UsuarioModel>
     * obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
     * return this.usuarioService.obtenerPorPrioridad(prioridad);
     * }
     */

    // http://localhost:8080//api/v1/usuarios/5
    /*
     * @DeleteMapping(path = "/{id}")
     * public ResponseEntity<String> eliminarPorId(@PathVariable("id") Long id) {
     * try {
     * return ResponseEntity.status(HttpStatus.OK).body(service.delete(id) + "");
     * } catch (Exception e) {
     * return ResponseEntity.status(HttpStatus.NOT_FOUND).
     * body("{\"error\":\"Error. No se pudo eliminar. \"}" + e);
     * }
     * }
     */

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody T model) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(id, model));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error. No se pudo eliminar. \"}" + e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error. No se pudo eliminar. \"}" + e);
        }
    }

}
