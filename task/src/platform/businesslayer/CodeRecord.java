package platform.businesslayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table
public class CodeRecord {
    @Id
    @JsonIgnore
    @Column
    @GeneratedValue //(strategy = GenerationType.IDENTITY)
    private UUID codeId;
    @Column
    @NotNull
    private String code;
    @Column
    private LocalDateTime date;

    @Column
    private int time;

    @Column
    private int views;


    private static final String DATE_FORMATTER= "yyyy/MM/dd HH:mm:ss";
    private boolean viewsRestriction = false;

    public CodeRecord() {
        this.date = LocalDateTime.now();
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

    public void setCodeId(UUID codeId) {
        this.codeId = codeId;
    }

    public UUID getCodeId() {
        return this.codeId;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return this.date; //.withNano(0)
    }

    public void setDate(LocalDateTime localDateTime) {
        this.date = localDateTime;//.withNano(0)
    }

    public String getDateOfCode() {
        return getFormatDateTime(this.date);
    }

    public static String getFormatDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return localDateTime.format(formatter);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void fixRestrictionConstraints() {
        int timeConstraint = Math.max(0,this.getTime());
        this.setTime(timeConstraint);
        int viewsConstraint = Math.max(0,this.getViews());
        this.setViews(viewsConstraint);

    }

    public int getRemainingTime() {
        LocalDateTime endTime = this.getDate()
                .plus(this.getTime(), ChronoUnit.SECONDS);
        Duration duration = Duration.between(LocalDateTime.now(), endTime);
        int seconds = (int) (duration.toSeconds() < 0? 0 : duration.toSeconds());
        return seconds;
    }

    public void setViewsRestriction(boolean b) {
        this.viewsRestriction = b;
    }

    public boolean isViewsRestriction() {
        return viewsRestriction;
    }
}
