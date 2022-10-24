package platform.businesslayer;

import java.time.LocalDateTime;

public class Codej {
    private String code;
    private LocalDateTime date;

    public Codej() {;}
    public Codej(CodeRecord codeRecord) {
        this.code = codeRecord.getCode();
        this.date =codeRecord.getDate();
    }
    public String getDate() {
        return CodeRecord.getFormatDateTime(this.date);
    }
    public String getCode() {
        return this.code;
    }
}
