//package ro.utcn.sd.cata.stackoverflow.repository.jpa;
//
//import lombok.RequiredArgsConstructor;
//import ro.utcn.sd.cata.stackoverflow.entity.User;
//import ro.utcn.sd.cata.stackoverflow.repository.UserRepository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import java.util.List;
//import java.util.Optional;
//
//@RequiredArgsConstructor
//public class HibernateUserRepository implements UserRepository {
//    private final EntityManager entityManager;
//
//    @Override
//    public User save(User user) {
//        if (user.getId() == null) {
//            entityManager.persist(user);
//            return user;
//        } else {
//            return entityManager.merge(user);
//        }
//    }
//
//    @Override
//    public List<User> findAll() {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> query = builder.createQuery(User.class);
//        query.select(query.from(User.class));
//        return entityManager.createQuery(query).getResultList();
//    }
//
//    @Override
//    public void remove(User user) {
//        entityManager.remove(user);
//    }
//
//    @Override
//    public Optional<User> findById(Integer id) {
//        return Optional.ofNullable(entityManager.find(User.class,id));
//    }
//
//    @Override
//    public Optional<User> findByUsernameAndPassword(String username, String password) {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> query = builder.createQuery(User.class);
//        query.select(query.from(User.class));
//        // query.where(builder.equal(root.get()))
//        return Optional.empty();
//    }
//}
