package cn.itcast.jd.dao;

import cn.itcast.jd.pojo.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author ChenWenJie
 * @Classname ItemDao
 * Describe:
 * @Date 2020/4/19 10:01
 */
public interface ItemDao extends JpaRepository<Item,Long> {
}
