package cn.itcast.job.dao;

import cn.itcast.job.pojo.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author ChenWenJie
 * @Classname JobInfoDao
 * Describe:
 * @Date 2020/4/19 22:04
 */
public interface JobInfoDao extends JpaRepository<JobInfo,Long> {
}
