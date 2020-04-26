package com.springmongodb.mongodb.service;

import com.springmongodb.mongodb.entity.UserAttachmentRel;

import java.util.List;

/**
 * @Author ChenWenJie
 * @Classname UserAttachmentRelService
 * Describe:
 * @Date 2020/2/24 16:50
 */
public interface UserAttachmentRelService {

    UserAttachmentRel save(UserAttachmentRel userAttachmentRel);

    UserAttachmentRel findOne(String id);

    UserAttachmentRel findOneMongoTemplate(String id);

    List<UserAttachmentRel> findByFileNameLike(String fileName);
}
