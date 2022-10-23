package com.argpro.argentinaprograma.services.GenericoService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.transaction.Transactional;

import com.argpro.argentinaprograma.models.config.Base;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;



public abstract class BaseServiceImple<T extends Base, ID extends Serializable> implements BaseService<T, ID> {

    @Autowired
    protected BaseRepository<T, ID> baseRepository;

    public BaseServiceImple(@Lazy BaseRepository<T, ID> baseRepository){
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
    public List<T> findAll() throws Exception {
        try{
            List<T> model = baseRepository.findAll();
            return model;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public T findById(ID id) throws Exception {
         try{
            Optional<T> modelOpt = baseRepository.findById(id);
            return modelOpt.get();
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public T save(T modelSave) throws Exception {
        try{
            modelSave = baseRepository.saveAndFlush(modelSave);
            return modelSave;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public T update(ID id, T entity) throws Exception {
        try{
            Optional<T> modelOpt = baseRepository.findById(id);
            T model = modelOpt.get();
            return model; 
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        try{
            if(baseRepository.existsById((ID) id)){
                baseRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //IMAGENES DE IMPLEMENTACION DE SERVICIO
    // - DE IMAGEN A ARRAY GUARDAR EN DATABASE
    @Override
    @Transactional
    public byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray());

        return outputStream.toByteArray();
    }

    //  DE ARRAY A IMAGEN LEER DE DATABASE
    @Override
    @Transactional
    public byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ignored) {

        }
        return outputStream.toByteArray();
    }

    //ROL POR DEFECTO
    /*
    @Override
    @Transactional
    public T findByName(String nombre) throws Exception {
        try {
            Optional<T> modelOpt = baseRepository.findByNombre(nombre);
            return modelOpt.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    */
    //obtener por prioridad
    /*
    public ArrayList<T> obtenerPorPrioridad(Integer prioridad){
        return baseRepository.findByPrioridad(prioridad);
    }
    */
}

