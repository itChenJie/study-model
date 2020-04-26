package com.springmongodb.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ChenWenJie
 * @Classname User
 * Describe:
 * @Date 2020/3/18 17:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String id;

    private String userId;

    private String fileName;
}
