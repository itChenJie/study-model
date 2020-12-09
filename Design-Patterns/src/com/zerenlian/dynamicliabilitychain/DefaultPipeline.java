package com.zerenlian.dynamicliabilitychain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Description 默认管道实现
 * @Author ChenWenJie
 * @Data 2020/12/8 1:53 下午
 **/
@Component("pipeline")
@Scope("prototype")
public class DefaultPipeline implements Pipeline, ApplicationContextAware, InitializingBean {
    /**
     * 创建一个默认的handler，将其注入到首尾两个节点的HandlerContext中，其作用只是将链往下传递
     */
    private static final Handler DEFAULT_HANDLER = new Handler() {};

    /**
     * 将ApplicationContext注入进来的主要原因在于，HandlerContext是prototype类型的，因而需要
     * 通过ApplicationContext.getBean()方法来获取其实例
     */
    private ApplicationContext applicationContext;

    /**
     * 创建一个头结点和尾节点，这两个节点内部没有做任何处理，只是默认的将每一层级的链往下传递，
     * 这里头结点和尾节点的主要作用就是用于标志整个链的首尾，所有的业务节点都在这两个节点中间
     */
    private HandlerContext head;
    private HandlerContext tail;

    /**
     * 用于业务调用的request对象，其内部封装了业务数据
     */
    private Object request;
    /**
     * 用于执行任务的task对象
     */
    private Object task;

    /**
     * 最初始的业务数据需要通过构造函数传入，这是驱动整个pipeline所需要的数据，
     * @param request
     */
    public DefaultPipeline(Object request){
        this.request = request;
    }

    /**
     *  这里我们可以看到，每一层级的调用都是通过HandlerContext.invokeXXX(head)的方式进行的，
     *  也就是说我们每一层级链的入口都是从头结点开始的
     * @return
     */
    @Override
    public Pipeline fireTaskReceived() {
        HandlerContext.invokeTaskReceived(head,request);
        return this;
    }

    /**
     * 触发任务过滤的链调用
     * @return
     */
    @Override
    public Pipeline fireTaskFiltered() {
        HandlerContext.invokeTaskFiltered(head,task);
        return this;
    }

    /**
     * 触发任务执行的链执行
     * @return
     */
    @Override
    public Pipeline fireTaskExecuted() {
        HandlerContext.invokeTaskExecuted(head,task);
        return this;
    }

    /**
     * 触发最终完成的链的执行
     * @return
     */
    @Override
    public Pipeline fireAfterCompletion() {
        HandlerContext.invokeAfterCompletion(head);
        return this;
    }

    /**
     * 用于往Pipeline中添加节点的方法，TODO 优化
     * @param handler
     */
    void addHandler(Handler handler){
        HandlerContext handlerContext = newContext(handler);
        tail.prev.next = handlerContext;
        handlerContext.prev = tail.prev;
        handlerContext.next = tail;
        tail.prev = handlerContext;
    }

    /**
     * 使用默认的Handler初始化一个HandlerContext
     * @param handler
     * @return
     */
    private HandlerContext newContext(Handler handler) {
        HandlerContext context = this.applicationContext.getBean(HandlerContext.class);
        context.handler = handler;
        return context;
    }

    /**
     * 这里通过实现InitializingBean接口来达到初始化Pipeline的目的，可以看到，这里初始的时候
     * 我们通过ApplicationContext实例化了两个HandlerContext对象，然后将head.next指向tail节点，
     * 将tail.prev指向head节点。也就是说，初始时，整个链只有头结点和尾节点。
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        head = newContext(DEFAULT_HANDLER);
        tail = newContext(DEFAULT_HANDLER);
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
    }
}
