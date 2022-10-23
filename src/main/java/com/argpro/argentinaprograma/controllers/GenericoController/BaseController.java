package com.argpro.argentinaprograma.controllers.GenericoController;

import java.io.Serializable;


import com.argpro.argentinaprograma.models.config.Base;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



public interface BaseController<T extends Base, ID extends Serializable> {
    
    //los datos
    public ResponseEntity<?> getAll();

    //por ID
    public ResponseEntity<?> getById(@PathVariable ID id);

    // Crear / Guardad
    public ResponseEntity<?> save(@RequestBody T model);

    //traer respuesta
   // public ResponseEntity<?> traerRespuesta();

    //obtener usuario por prioridad
    //public ArrayList<T> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad);

    //public ResponseEntity<?> findByName(@RequestParam("nombre") String nombre);

    //update
    public ResponseEntity<?> update(@PathVariable ID id, @RequestBody T model);

    //eliminar
    public ResponseEntity<?> delete(@PathVariable ID id);
}
