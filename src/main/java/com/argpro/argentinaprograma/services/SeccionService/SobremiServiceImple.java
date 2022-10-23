package com.argpro.argentinaprograma.services.SeccionService;

import com.argpro.argentinaprograma.models.SeccionModel.sobremi.SobremiModel;
import com.argpro.argentinaprograma.repositories.GenericoRepository.BaseRepository;
import com.argpro.argentinaprograma.services.GenericoService.BaseServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class SobremiServiceImple extends BaseServiceImple<SobremiModel, Long> implements SobremiService {



    //@Autowired
    //ICrudimagenRepository iCrudimagenRepository;


    public SobremiServiceImple(BaseRepository<SobremiModel, Long> baseRepository) {
        super(baseRepository);
    }

    /*
    @Override
    public void subirImagen(byte[] file) throws IOException {



    }

     */

    // - DE IMAGEN A ARRAY GUARDAR EN DATABASE
    /*
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
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    //  DE ARRAY A IMAGEN LEER DE DATABASE
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

     */

     /*
    @Override
    public ResponseEntity.BodyBuilder subirImagen(file) throws IOException {

        SobremiModel sobremiModel = new SobremiModel();
        //smImagenModel.setSobremiModel(iCrudimagenRepository.findById(smImagenWrapper.getSobremiId()).get());
        //smImagenModel.setSobremiModel(sobremiRepository.findById(smImagenWrapper.getSobremiId()).get());
        //sobremiModel.setContentType(smImagenWrapper.getContentType());
        sobremiModel.setPortada(compressBytes(file.getBytes()));

        return iCrudimagenRepository.save(sobremiModel);
    }

     */



}
