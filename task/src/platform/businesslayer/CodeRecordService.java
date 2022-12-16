package platform.businesslayer;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import platform.persistence.CodeRecordRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CodeRecordService {

    private final CodeRecordRepository codeRecordRepository;

    @Autowired
    public CodeRecordService(CodeRecordRepository codeRecordRepository) {
        this.codeRecordRepository = codeRecordRepository;
    }

    public CodeRecord findCodeRecordById(UUID id) {
        CodeRecord codeRecord = codeRecordRepository.findCodeRecordByCodeId(id);
        if( codeRecord != null) {
            if(( codeRecord.getViews() != 0) || (codeRecord.getTime() != 0)) {
                return processRestrictions(codeRecord);
            }
        }
        return codeRecord;
    }

    private CodeRecord processRestrictions(CodeRecord codeRecord) {
        //Check time restriction
        if(codeRecord.getTime() > 0) {
            if ( codeRecord.getDate()
                    .plus(codeRecord.getTime(), ChronoUnit.SECONDS)
                    .isBefore(LocalDateTime.now())) {
                codeRecordRepository.delete(codeRecord);
                return null;
            }
        }
        //Check views restriction
        if(codeRecord.getViews() != 0) {
            codeRecord.setViews(codeRecord.getViews()-1);
            codeRecord.setViewsRestriction(true);
            if (codeRecord.getViews() == 0) {
                codeRecordRepository.delete(codeRecord);
            } else {
                codeRecordRepository.save(codeRecord);
            }
        }
        return codeRecord;
    }

    public CodeRecord save(CodeRecord toSave) {
        if (toSave.getDate() == null) {
            toSave.setDate(LocalDateTime.now());
        }
        return codeRecordRepository.save(toSave);
    }

    public List<Coder> retrieveLatest() {
        List<CodeRecord> resultSet = new ArrayList<>(codeRecordRepository.findAll());
        resultSet.sort((codeRecord1, codeRecord2) ->
                codeRecord2.getDate().compareTo(codeRecord1.getDate()));
        return resultSet.stream()
                .filter(codeRecord -> ((codeRecord.getTime() == 0) && (codeRecord.getViews() == 0)))
                .limit(10)
                .map(codeRecord -> new Coder(codeRecord))
                .collect(Collectors.toList());
    }


}