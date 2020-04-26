package com.springmongodb.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @Author ChenWenJie
 * @Classname UserAttachmentRel
 * Describe:
 * @Date 2020/2/24 16:47
 */
@Data
@Document(collection = "user")

public class UserAttachmentRel implements Serializable {
    @Id
    private String id;

    private String userId;

    private String fileName;


}
