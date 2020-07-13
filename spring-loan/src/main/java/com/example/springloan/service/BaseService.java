package com.example.springloan.service;

import com.example.springloan.dao.domain.BaseEntity;
import com.example.springloan.dao.repository.BaseRepository;
import javassist.NotFoundException;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class BaseService<R extends BaseRepository<E>, E extends BaseEntity> {

    private final R repository;

    public BaseService(R repository) {
        this.repository = repository;
    }

    public E create(E input) {
        return repository.save(input);
    }

    public E get(Long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Not Found !"));
    }

    public List<E> getAll() {
        return repository.findAll();
    }

    public abstract E update(E updating) throws NotFoundException;

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
