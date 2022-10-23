package com.argpro.argentinaprograma.models.SeccionModel.sobremi;

import com.argpro.argentinaprograma.models.config.Base;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;


// SOBREMI IMAGEN MODEL
public class SMImagenModel extends Base {

    @ManyToOne
    private SobremiModel sobremiModel;

    @Lob
    private String base64;

    private String contentType;

    public SobremiModel getSobremiModel() {
        return sobremiModel;
    }

    public void setSobremiModel(SobremiModel sobremiModel) {
        this.sobremiModel = sobremiModel;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
