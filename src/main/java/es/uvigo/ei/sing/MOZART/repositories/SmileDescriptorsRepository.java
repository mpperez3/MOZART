package es.uvigo.ei.sing.MOZART.repositories;

import es.uvigo.ei.sing.MOZART.entities.SmileDescriptorsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SmileDescriptorsRepository extends CrudRepository<SmileDescriptorsEntity, Integer> {


    @Transactional(readOnly = true)
    public Optional<SmileDescriptorsEntity> findByHash(String hash);
}
