package com.argpro.argentinaprograma.models.Wrappers;

public class SMImagenWrapper {

    private long sobremiId;

    private String contentType;

    private String base64;

    public long getSobremiId() {
        return sobremiId;
    }

    public void setSobremiId(long sobremiId) {
        this.sobremiId = sobremiId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    @Override
    public String toString() {
        return "SMImagenWrapper{" +
                "sobremiId=" + sobremiId +
                ", contentType='" + contentType + '\'' +
                ", base64='" + base64 + '\'' +
                '}';
    }
}
