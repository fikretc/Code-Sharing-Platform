package platform.businesslayer;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import platform.persistence.CodeRecordRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeRecordService {

    private final CodeRecordRepository codeRecordRepository;

    @Autowired
    public CodeRecordService(CodeRecordRepository codeRecordRepository) {
        this.codeRecordRepository = codeRecordRepository;
    }

    public CodeRecord findCodeRecordById(Integer id) {
        return codeRecordRepository.findCodeRecordByCodeId(id);
    }

    public CodeRecord save(CodeRecord toSave) {
        if (toSave.getDate() == null) {
            toSave.setDate(LocalDateTime.now());
        }
        return codeRecordRepository.save(toSave);
    }

    public List<CodeRecord> retrieveLatest() {
        List<CodeRecord> resultSet = new ArrayList<>(codeRecordRepository.findAll());
        resultSet.sort((codeRecord1, codeRecord2) ->
                codeRecord2.getDate().compareTo(codeRecord1.getDate()));
        return resultSet.stream()
                .limit(10)
                .collect(Collectors.toList());
    }


}