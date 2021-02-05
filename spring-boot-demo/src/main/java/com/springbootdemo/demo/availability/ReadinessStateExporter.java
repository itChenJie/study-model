package com.springbootdemo.demo.availability;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Description 随时检索当前的就绪状态
 *
 * 就绪状态，指的是应用程序是否已准备好接受并处理客户端请求。出于任何原因，如果应用程序尚未准备好处理服务请求，则应将其声明为繁忙，
 * 直到能够正常响应请求为止。如果“Readiness”状态尚未就绪，则不应将流量路由到该实例。
 *
 * 例如，在Kubernetes中，如果就绪探针失败，则 Endpoints 控制器将从与Endpoints中删除Pod的IP地址。设置就绪状态为“Failure”。
 * 如果容器未提供就绪探针，则默认状态为“Success”。
 *
 * Spring Boot在ReadinessState的帮助下引入了就绪状态。可以设置的值有：
 * ACCEPTING_TRAFFIC：应用程序准备好接收流量。这是默认值。
 * REFUSING_TRAFFIC：应用程序拒绝接收流量。
 * @Author ChenWenJie
 * @Data 2021/2/2 下午3:14
 **/
@Component
public class ReadinessStateExporter {

    /**
     * 获取程序的当前状态
     * @param event
     */
    @EventListener
    public void onStateChange(AvailabilityChangeEvent<ReadinessState> event){
        switch (event.getState()){
            case ACCEPTING_TRAFFIC:
                break;
            case REFUSING_TRAFFIC:
                break;
        }
    }
}
