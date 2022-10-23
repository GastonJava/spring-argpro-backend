package com.argpro.argentinaprograma.models.DTOs.educacion;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

public class EducacioncardDto {

    private int id;

    private String titulocard;

    private String subtitulocard;

    private String thumbnailString;

    private byte[] thumbnail;

    private String titulodate;

    //dates
    // @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate start;

    private String startStringdate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate end;

    private String endStringdate;

    /*
    public EducacioncardDto(String titulocard, String subtitulocard, String thumbnailString) {
    }

     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumbnailString() {
        return thumbnailString;
    }

    public void setThumbnailString(String thumbnailString) {
        this.thumbnailString = thumbnailString;
    }

    public String getStartStringdate() {
        return startStringdate;
    }

    public void setStartStringdate(String startStringdate) {
        this.startStringdate = startStringdate;
    }

    public String getEndStringdate() {
        return endStringdate;
    }

    public void setEndStringdate(String endStringdate) {
        this.endStringdate = endStringdate;
    }

    public String getTitulocard() {
        return titulocard;
    }

    public void setTitulocard(String titulocard) {
        this.titulocard = titulocard;
    }

    public String getSubtitulocard() {
        return subtitulocard;
    }

    public void setSubtitulocard(String subtitulocard) {
        this.subtitulocard = subtitulocard;
    }


    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitulodate() {
        return titulodate;
    }

    public void setTitulodate(String titulodate) {
        this.titulodate = titulodate;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public EducacioncardDto(int id, String titulocard, String subtitulocard, String thumbnailString, String titulodate, LocalDate start, LocalDate end) {
        this.id = id;
        this.titulocard = titulocard;
        this.subtitulocard = subtitulocard;
        this.thumbnailString = thumbnailString;
        this.titulodate = titulodate;
        this.start = start;
        this.end = end;

    }
}