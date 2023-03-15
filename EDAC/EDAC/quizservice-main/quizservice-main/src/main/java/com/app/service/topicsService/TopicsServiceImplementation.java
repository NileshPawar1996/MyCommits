//CREATED BY RAJVARDHAN SHINDE
package com.app.service.topicsService;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.TopicsDao;
import com.app.pojos.Topic;

@Service
@Transactional
public class TopicsServiceImplementation implements TopicsService {
	@Autowired
	private TopicsDao topicsDao;

	@Override
	public List<Topic> getAllTopicsByModule(Long moduleId) {
		return topicsDao.findByModuleId(moduleId);
	}

	@Override
	public void addTopic(Topic topic) {
		topicsDao.save(topic);
	}

	@Override
	public void editTopic(Topic topic) {
		if (topicsDao.existsById(topic.getId())) {
			topicsDao.save(topic);
		} else
			throw new ResourceNotFoundException("Topic with id = " + topic.getId() + " does not exist");

	}

	@Override
	public void deleteTopic(Long id) {
		if(topicsDao.existsById(id)) {
			topicsDao.deleteById(id);			
		}else {
			throw new ResourceNotFoundException("Topic with id = " + id + " does not exist");
		}

	}

}
