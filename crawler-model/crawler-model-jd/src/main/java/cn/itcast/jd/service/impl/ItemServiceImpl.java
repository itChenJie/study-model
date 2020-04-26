package cn.itcast.jd.service.impl;

import cn.itcast.jd.dao.ItemDao;
import cn.itcast.jd.pojo.Item;
import cn.itcast.jd.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author ChenWenJie
 * @Classname ItemServiceImpl
 * Describe:
 * @Date 2020/4/19 10:05
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;

    @Override
    @Transactional
    public void save(Item item) {
        itemDao.save(item);
    }

    @Override
    public List<Item> findAll(Item item) {
        Example<Item> example = Example.of(item);
        List<Item> itemList = itemDao.findAll(example);
        return itemList;
    }
}
