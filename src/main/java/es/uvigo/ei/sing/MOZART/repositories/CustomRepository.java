package es.uvigo.ei.sing.MOZART.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable>
        extends CrudRepository<T, ID> {
    void refresh(T t);
//    <S extends T> S save(S entity);
//    <S extends T> List<S> saveAll(Iterable<S> entities);
}