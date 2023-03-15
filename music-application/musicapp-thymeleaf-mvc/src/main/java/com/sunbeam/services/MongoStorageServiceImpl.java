package com.sunbeam.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sunbeam.daos.MongoFilesDao;
import com.sunbeam.entities.MongoFile;

@Primary
@Component
public class MongoStorageServiceImpl implements StorageService {
	@Autowired
	private MongoFilesDao filesDao;
	
	@Override
	public List<String> loadAll() {
		return 	filesDao.findAll()
				.stream()
				.map(file -> file.getId())
				.collect(Collectors.toList());
	}

	@Override
	public String store(MultipartFile file) {
		try {
			Binary binFile = new Binary(BsonBinarySubType.BINARY, file.getBytes());
			MongoFile mongoFile = new MongoFile(null, binFile);
			mongoFile = filesDao.insert(mongoFile);
			return mongoFile.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Resource load(String fileId) {		
		Optional<MongoFile> mongoFile = filesDao.findById(fileId);
		if(!mongoFile.isPresent())
			return null;
		return new ByteArrayResource(mongoFile.get().getFile().getData());
	}

	@Override
	public void delete(String fileId) {
		filesDao.deleteById(fileId);
	}

}
