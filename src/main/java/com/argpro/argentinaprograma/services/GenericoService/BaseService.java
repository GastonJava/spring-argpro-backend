package com.argpro.argentinaprograma.services.GenericoService;

import com.argpro.argentinaprograma.models.config.Base;

import java.io.Serializable;
import java.util.List;


public interface BaseService<T extends Base, ID extends Serializable> {
    /* 
    public abstract List<T> findAll() throws Exception;
    public abstract T findById(ID id) throws Exception;
    public abstract T save(T entity) throws Exception;
    public abstract T update(ID id, T entity) throws Exception;
    public abstract boolean delete(ID id) throws Exception;

    */

    public List<T> findAll() throws Exception;
    public T findById(ID id) throws Exception;
    public T save(T entity) throws Exception;
    public T update(ID id, T entity) throws Exception;
    //public T traerRespuesta() throws Exception;
    //public ArrayList<T> obtenerPorPrioridad(Integer integer) throws Exception;
    public boolean delete(ID id) throws Exception;

    //ULTIMOS CAMBIOS BORRAR SI NO FUNCIONA
    public byte[] compressBytes(byte[] base64);
    public byte[] decompressBytes(byte[] base64);

    // find by name global
    //public T findByName(String name) throws Exception;

    // al crear usuario comience con un ROL agregado
    //public List<T> rolInicialByName(String name) throws Exception; 
}
