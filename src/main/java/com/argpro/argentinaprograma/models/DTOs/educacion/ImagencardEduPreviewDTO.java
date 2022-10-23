package com.argpro.argentinaprograma.models.DTOs.educacion;

import java.util.List;

public class ImagencardEduPreviewDTO {

    private byte[] imagecardeduPreviewts;
    private byte[] imagencardeduPreviewdb;
    private List<byte[]> imgcardedupurodb;

    private String imagencardeduString;


    // getters and setters

    public byte[] getImagecardeduPreviewts() {
        return imagecardeduPreviewts;
    }

    public void setImagecardeduPreviewts(byte[] imagecardeduPreviewts) {
        this.imagecardeduPreviewts = imagecardeduPreviewts;
    }

    public byte[] getImagencardeduPreviewdb() {
        return imagencardeduPreviewdb;
    }

    public void setImagencardeduPreviewdb(byte[] imagencardeduPreviewdb) {
        this.imagencardeduPreviewdb = imagencardeduPreviewdb;
    }

    public List<byte[]> getImgcardedupurodb() {
        return imgcardedupurodb;
    }

    public void setImgcardedupurodb(List<byte[]> imgcardedupurodb) {
        this.imgcardedupurodb = imgcardedupurodb;
    }

    public String getImagencardeduString() {
        return imagencardeduString;
    }

    public void setImagencardeduString(String imagencardeduString) {
        this.imagencardeduString = imagencardeduString;
    }
}
