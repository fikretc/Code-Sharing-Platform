package platform.persistence;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.businesslayer.CodeRecord;

import java.util.List;
import java.util.UUID;

@Repository
public interface CodeRecordRepository extends CrudRepository<CodeRecord, UUID> {
    CodeRecord findCodeRecordByCodeId(UUID id);
    List<CodeRecord> findAll();
    CodeRecord save(CodeRecord toSave);

}