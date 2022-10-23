package com.argpro.argentinaprograma.models.DTOs;

import java.util.List;

public class ImagencardPreviewDatabaseDTO {

    private byte[] imagecardPreviewts;
    private byte[] imagencardPreviewdb;
    private List<byte[]> imgcardpurodb;

    private String imagencardString;

    public String getImagencardString() {
        return imagencardString;
    }

    public void setImagencardString(String imagencardString) {
        this.imagencardString = imagencardString;
    }

    public List<byte[]> getImgcardpurodb() {
        return imgcardpurodb;
    }

    public void setImgcardpurodb(List<byte[]> imgcardpurodb) {
        this.imgcardpurodb = imgcardpurodb;
    }

    private List<String> titulocardtest;

    public List<String> getTitulocardtest() {
        return titulocardtest;
    }

    public void setTitulocardtest(List<String> titulocardtest) {
        this.titulocardtest = titulocardtest;
    }


    public byte[] getImagecardPreviewts() {
        return imagecardPreviewts;
    }

    public void setImagecardPreviewts(byte[] imagecardPreviewts) {
        this.imagecardPreviewts = imagecardPreviewts;
    }

    public byte[] getImagencardPreviewdb() {
        return imagencardPreviewdb;
    }

    public void setImagencardPreviewdb(byte[] imagencardPreviewdb) {
        this.imagencardPreviewdb = imagencardPreviewdb;
    }




}
