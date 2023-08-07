package com.bookSharingPlatform.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CustomRepositoryHandler<T> {

    @Autowired
    private YourEntityRepository yourEntityRepository ;

    public void saveEntity(T entity) {
        yourEntityRepository.save(entity);
    }
}
