package com.springmongodb.mongodb.service.impl;

import com.springmongodb.mongodb.entity.UserAttachmentRel;
import com.springmongodb.mongodb.repository.UserAttachmentRelRepository;
import com.springmongodb.mongodb.service.UserAttachmentRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author ChenWenJie
 * @Classname UserAttachmentRelServiceImpl
 * Describe:
 * @Date 2020/2/24 16:51
 */
@Service
public class UserAttachmentRelServiceImpl implements UserAttachmentRelService {
    @Autowired
    private UserAttachmentRelRepository userAttachmentRelRepository;
    @Autowired
    private MongoTemplate mongoTemplate=null;
    @Override
    public UserAttachmentRel save(UserAttachmentRel userAttachmentRel) {
        return userAttachmentRelRepository.save(userAttachmentRel);
    }

    @Override
    public UserAttachmentRel findOne(String id) {
        Optional<UserAttachmentRel> userAttachmentRel = userAttachmentRelRepository.findById(id);
        if (!userAttachmentRel.isPresent())
            return new UserAttachmentRel();
        return userAttachmentRel.get();
    }

    @Override
    public UserAttachmentRel findOneMongoTemplate(String id) {
        Criteria criteria=Criteria.where("id").is(id);
        Query query=Query.query(criteria);
        UserAttachmentRel rel=mongoTemplate.findOne(query,UserAttachmentRel.class);
        return rel;
    }

    @Override
    public List<UserAttachmentRel> findByFileNameLike(String fileName) {

        return userAttachmentRelRepository.findByFileNameLike(fileName);
    }


}
