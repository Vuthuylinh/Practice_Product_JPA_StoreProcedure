package linhVu.repository;

import java.util.List;

public interface GeneralRepository<E> {
    List<E> findAll();
//    void save(E e);

    void add(E e);
    E findById(Long id);
    void update(E e);
    void delete(Long id);
    List<E> findByName(String name);
}
