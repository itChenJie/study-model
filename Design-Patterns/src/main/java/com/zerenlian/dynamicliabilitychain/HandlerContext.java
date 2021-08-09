package com.zerenlian.dynamicliabilitychain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Description 用于传递链相关的控制信息
 * @Author ChenWenJie
 * @Data 2020/12/7 5:56 下午
 **/
@Component
@Scope("prototype")
public class HandlerContext {
    HandlerContext prev;
    HandlerContext next;
    Handler handler;

    private Object object;

    /**
     * 接收任务
     * @param object
     */
    public void fireTaskReceived(Object object){
        invokeTaskReceived(next(),object);
    }

    /**
     * 处理接收到任务的事件
     * @param ctx
     * @param object
     */
    static void invokeTaskReceived(HandlerContext ctx,Object object){
        if (ctx != null){
            try {
                ctx.handler().receiveTask(ctx, object);
            } catch (Throwable e) {
                ctx.handler().exceptionCaught(ctx, e);
            }
        }
    }

    /**
     * 任务过滤
     * @param object
     */
    public void fireTaskFiltered(Object object){
        invokeTaskFiltered(next(),object);
    }

    /**
     * 处理任务过滤事件
     */
    static void invokeTaskFiltered(HandlerContext ctx,Object object){
        if ( ctx != null){
            try {
                ctx.handler().filterTask(ctx,object);
            }catch (Throwable e){
                ctx.handler().exceptionCaught(ctx,e);
            }
        }
    }

    /**
     * 消防任务执行
     * @param object
     */
    public void fireTaskExecuted(Object object){
        invokeTaskExecuted(next(),object);
    }

    static void invokeTaskExecuted(HandlerContext ctx,Object object){
        if (ctx != null){
             try {
                ctx.handler().executeTask(ctx,object);
             }catch (Exception e){
                ctx.handler().exceptionCaught(ctx,e);
             }
        }
    }

    public void fireAfterCompletion(HandlerContext ctx){
        invokeAfterCompletion(next());
    }

    /**
     * 完成后调用
     * @param ctx
     */
    static void invokeAfterCompletion(HandlerContext ctx){
        if (ctx != null){
            ctx.handler().afterCompletion(ctx);
        }
    }

    private HandlerContext next() {
        return next;
    }

    private Handler handler() {
        return handler;
    }
}
