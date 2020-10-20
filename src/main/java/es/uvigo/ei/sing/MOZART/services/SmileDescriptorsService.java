package es.uvigo.ei.sing.MOZART.services;

import es.uvigo.ei.sing.MOZART.entities.SmileDescriptorsEntity;
import es.uvigo.ei.sing.MOZART.repositories.SmileDescriptorsRepository;
import es.uvigo.ei.sing.MOZART.utils.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class SmileDescriptorsService {

    private final SmileDescriptorsRepository SmileDescriptorsRepository;

    @Autowired
    public SmileDescriptorsService(SmileDescriptorsRepository SmileDescriptorsRepository) {
        this.SmileDescriptorsRepository = SmileDescriptorsRepository;
    }

    public SmileDescriptorsEntity save(SmileDescriptorsEntity SmileDescriptorsEntity) {
        return SmileDescriptorsRepository.save(SmileDescriptorsEntity);
    }

    @Cacheable(value = "findByLabel", key = "{#root.methodName,#a0}")
    @Transactional(readOnly = true)
    public Optional<SmileDescriptorsEntity> hasAny(String smile) {
        String hash = smile;
        try {
            hash = Functions.makeSHA1Hash(smile);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return SmileDescriptorsRepository.findByHash(hash);
    }

}
