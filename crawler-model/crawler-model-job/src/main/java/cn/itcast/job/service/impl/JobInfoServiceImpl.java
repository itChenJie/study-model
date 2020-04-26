package cn.itcast.job.service.impl;

import cn.itcast.job.dao.JobInfoDao;
import cn.itcast.job.pojo.JobInfo;
import cn.itcast.job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ChenWenJie
 * @Classname JobInfoServiceImpl
 * Describe:
 * @Date 2020/4/19 22:06
 */
@Service
public class JobInfoServiceImpl implements JobInfoService {
    @Autowired
    private JobInfoDao jobInfoDao;

    @Override
    public void save(JobInfo jobInfo) {
        JobInfo param = new JobInfo();
        param.setUrl(jobInfo.getUrl());
        param.setTime(jobInfo.getTime());
        List<JobInfo> all = findAll(param);
        if (all.size()==0) {
            jobInfoDao.saveAndFlush(jobInfo);
        }


    }

    @Override
    public List<JobInfo> findAll(JobInfo jobInfo) {
        Example example = Example.of(jobInfo);
        List<JobInfo> list = jobInfoDao.findAll(example);
        return list;
    }
}
