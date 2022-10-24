package platform;

import org.springframework.beans.factory.annotation.Autowired;
import platform.businesslayer.CodeRecord;
import platform.businesslayer.CodeRecordService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CodeRecordStore {/*

    private List<CodeRecord> codeStore = new ArrayList<>();
    public CodeRecordStore() {}

    public CodeRecordStore(String initMethod) {
        if (Objects.equals(initMethod, "DB")) {
            this.codeStore = codeRecordService.findAll();
        }
    }

    public int addCodeRecord(CodeRecord codeRecord) {
        if(this.codeStore.add(codeRecord))
            return codeStore.size();
        return -1;
    }

    public CodeRecord retrieveNth(Integer n) {
        return codeStore.get(n - 1);
    }

    public List<CodeRecord> getCodeStore() {
        return codeStore;
    }

    public List<CodeRecord> retrieveLatest() {
        List<CodeRecord> resultset = new ArrayList<>();
        int size = codeStore.size();
        int limit = Math.min(size, 10);
        for (int i = 1; i <= limit; i++) {
            resultset.add( codeStore.get(size-i));
        }
        return resultset;
    }*/
}
