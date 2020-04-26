package com.springmongodb.mongodb.repository;

import com.springmongodb.mongodb.entity.UserAttachmentRel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Author ChenWenJie
 * @Classname UserAttachmentRelRepository
 * Describe:
 * @Date 2020/2/24 16:49
 */
public interface UserAttachmentRelRepository extends MongoRepository<UserAttachmentRel,String> {

    List<UserAttachmentRel> findByFileNameLike(String fileName);
}
