package com.zerenlian.service;

import com.zerenlian.dynamicliabilitychain.Handler;
import com.zerenlian.dynamicliabilitychain.HandlerContext;
import org.springframework.stereotype.Component;

@Component
public class DurationHandler implements Handler {

  @Override
  public void filterTask(HandlerContext ctx, Object task) {
    System.out.println("时效性检验");
    ctx.fireTaskFiltered(task);
  }
}