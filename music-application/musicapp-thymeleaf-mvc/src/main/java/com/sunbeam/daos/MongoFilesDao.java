package com.sunbeam.daos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sunbeam.entities.MongoFile;

public interface MongoFilesDao extends MongoRepository<MongoFile, String> {

}
