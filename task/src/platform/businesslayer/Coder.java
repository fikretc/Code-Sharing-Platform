package platform.businesslayer;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
@JsonPropertyOrder({"code", "date", "time", "views"})
public class Coder {
    CodeRecord codeRecord;

    Coder(){}

    public Coder(CodeRecord codeRecord) {
        this.codeRecord = codeRecord;
    }

    public String getCode() {
        return this.codeRecord.getCode();
    }

    public String getDate() {
        return CodeRecord.getFormatDateTime(this.codeRecord.getDate());
    }

    public int getViews() {
        return this.codeRecord.getViews();
    }

    public int getTime() {
        return this.codeRecord.remainingTime();
    }
}
