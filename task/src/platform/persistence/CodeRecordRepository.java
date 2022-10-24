package platform.persistence;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.businesslayer.CodeRecord;

import java.util.List;

@Repository
public interface CodeRecordRepository extends CrudRepository<CodeRecord, Long> {
    CodeRecord findCodeRecordById(Integer id);
    List<CodeRecord> findAll();
    CodeRecord save(CodeRecord toSave);
}