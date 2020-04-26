package cn.itcast.jd.service;

import cn.itcast.jd.pojo.Item;

import java.util.List;

/**
 * @Author ChenWenJie
 * @Classname service
 * Describe:
 * @Date 2020/4/19 10:02
 */
public interface ItemService {

    public void save(Item item);

    public List<Item> findAll(Item item);
}
