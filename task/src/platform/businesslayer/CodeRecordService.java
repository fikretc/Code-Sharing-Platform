package platform.businesslayer;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import platform.persistence.CodeRecordRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CodeRecordService {

    private final CodeRecordRepository codeRecordRepository;

    @Autowired
    public CodeRecordService(CodeRecordRepository codeRecordRepository) {
        this.codeRecordRepository = codeRecordRepository;
    }

    public Codej findCodeRecordById(Integer id) {
        return new Codej(codeRecordRepository.findCodeRecordById(id));
    }

    public CodeRecord save(CodeRecord toSave) {
        if (toSave.getDate() == null) {
            toSave.setDate(LocalDateTime.now());
        }
        return codeRecordRepository.save(toSave);
    }

    public List<Codej> retrieveLatest() {
        List<CodeRecord> allCodes = findAll();
        List<Codej> resultSet = new ArrayList<>();
        int size = allCodes.size();
        int limit = Math.min(size, 10);
        for (int i = 1; i <= limit; i++) {
            resultSet.add(new Codej(allCodes.get(size-i)));
        }
        return resultSet;
    }

    public List<CodeRecord> findAll() {
        return codeRecordRepository.findAll();
    }

}