package com.argpro.argentinaprograma.models.SeccionModel.educacion;

import com.argpro.argentinaprograma.models.config.Base;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "educacioncard")
public class Educacioncard extends Base {

    @Column(name = "titulocard")
    private String titulocard;

    @Column(name = "subtitulocard")
    private String subtitulocard;

    @Lob
    @Column(name = "thumbnail", columnDefinition = "LONGBLOB")
    private byte[] thumbnail;

    @Column(name = "titulodate")
    private String titulodate;

    //dates
   // @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "inicio")
    private LocalDate start;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "fin")
    private LocalDate end;

    // getters and setters
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



}
