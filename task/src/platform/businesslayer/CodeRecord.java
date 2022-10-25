package platform.businesslayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import io.micrometer.core.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table
public class CodeRecord {
    @Id
    @JsonIgnore
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //private UUID codeId;
    private int codeId;
    @Column
    @NotNull
    private String code;
    @Column
    private LocalDateTime date;

    private static final String DATE_FORMATTER= "yyyy/MM/dd HH:mm:ss";

    public CodeRecord() {

    }
    public CodeRecord(String code, LocalDateTime date) {
        this.code = code;
        this.date = date;
    }


    public CodeRecord(String code) {
        this.code = code;
        this.date = LocalDateTime.now();
    }

    public void newCodeAndTime(String code) {
        this.code = code;
        this.date = LocalDateTime.now();
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public int getCodeId() {
        return this.codeId;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return this.date;
        //return this.dateOfCode();
    }

    public void setDate(LocalDateTime localDateTime) {
        this.date = localDateTime;
    }

    public String dateOfCode() {
        return getFormatDateTime(this.date);
    }

    public static String getFormatDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return localDateTime.format(formatter);
    }
}
