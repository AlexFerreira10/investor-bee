package com.investor.bee.model.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.Filter;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public DataUserDto save(CreateUserDto user) {
        User newUser = userRepository.save(new User(user));
        return new DataUserDto(newUser.getId(), newUser.getName(), newUser.getEmail(), newUser.isActive());

    }

    public Page<DataUserDto> read (Pageable pageable) {
        return userRepository.findAll(pageable).map(
                x -> new DataUserDto(x.getId(), x.getName(), x.getEmail(), x.isActive())
        );
    }

    @Transactional
    public UpdateUserDto update(UpdateUserDto user) {
        verifyIdExists(user.id());

        User userToUpdate = userRepository.getReferenceById(user.id());

        userToUpdate.updateData(user);

        return new UpdateUserDto(userToUpdate);
    }

    @Transactional
    public void delete(Long id) {
        verifyIdExists(id);
        User user = userRepository.getReferenceById(id);

        userRepository.delete(user);
    }

    @Transactional
    public PageImpl<DataUserDto> findUsers(boolean includeDeleted) {
        // Gerenciador de entidade, faz a persistência
        Session session = entityManager.unwrap(Session.class);

        Filter filter = session.enableFilter("deletedUserFilter");
        filter.setParameter("isDeleted", !includeDeleted);

        // Cria e executa a consulta HQL para recuperar todos os usuários.
        List<DataUserDto> userList = session.createQuery("from User", User.class).getResultList()
                .stream().map(
                        x -> new DataUserDto(x.getId(), x.getName(), x.getEmail(), x.isActive())).toList();

        session.disableFilter("deletedUserFilter");

        return new PageImpl<>(userList);
    }

    public DataUserDto findById(Long id) {
        verifyIdExists(id);
        User user = userRepository.getReferenceById(id);
        return new DataUserDto(user.getId(), user.getName(), user.getEmail(), user.isActive());
    }

    private void verifyIdExists(Long id) {
        if(!userRepository.existsById(id)) {
            throw new RuntimeException("User with id " + id + " does not exist");
        }
    }
}