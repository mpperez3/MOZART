package es.uvigo.ei.sing.MOZART.repositories;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements CustomRepository<T, ID> {

    private final EntityManager entityManager;
    private final JpaEntityInformation entityInformation;

    public CustomRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
    }

    @Override
    @Transactional
    public void refresh(T t) {
        entityManager.refresh(t);
    }

//
//    @Override
//    @Transactional
//    public <S extends T> S save(S entity) {
//
//        System.out.println("entity = " + entity);
//        System.out.println("entity = " + this.entityInformation.getJavaType());
//
//        if (entity instanceof EntityType) {
//            System.out.println("entity = " + entity);
////            this.saveEntity((EntityType) entity);
//            return null;
//        } else {
//            return super.save(entity);
//        }
//    }
//
//
//    public <S extends T> List<S> saveAll(Iterable<S> entities) {
//        Assert.notNull(entities, "The given Iterable of entities not be null!");
//        List<S> result = new ArrayList<S>();
//
//        for (S entity : entities) {
//
//            System.out.println("entity = " + entity);
//
//            if (entity instanceof EntityType) {
//
////            this.saveEntity((EntityType) entity);
//            }
//            result.add(super.save(entity));
//        }
//
//        return result;
//    }


//    @Override
//    @Transactional
//    public void refresh(T t) {
//        entityManager.refresh(t);
//    }


}