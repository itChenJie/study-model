package cn.itcast.job.service;

import cn.itcast.job.pojo.JobInfo;

import java.util.List;

/**
 * @Author ChenWenJie
 * @Classname JobInfoService
 * Describe:
 * @Date 2020/4/19 22:05
 */
public interface JobInfoService {

    public void save(JobInfo jobInfo);

    public List<JobInfo> findAll(JobInfo jobInfo);
}
