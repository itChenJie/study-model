//package com.redis.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.core.RedisOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.SessionCallback;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Description
// * @Author ChenWenJie
// * @Data 2021/1/28 下午5:25
// **/
//public class Test {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    public void AA(Long activityId,String couponCode){
//        try {
//            // 01、添加redis分布式锁（锁的时间），避免集群环境重复领取
//            if (getLock(getRedisLockKey(activityId, couponCode), 4500, TimeUnit.MILLISECONDS)) {
//                // 02、从redis中判断是否还有库存。
//                if (isCheckInventory()) {
//                    if (queryActivityCouponNumIsZero(activityId, couponCode)) {
//                        return MemberReceiveCouponStatusEnum.COUPON_NUM_ZERO;
//                    }
//                }
//
//                // TODO 以后可能会进行领取限制的双重检查
//
//                // 03、【核心操作】领取实际操作，扣减营销活动对应的优惠券的库存，并新增用户优惠记录。
//                if (updateActivityCouponInventory(activityId, couponId)) {
//                    addMemberCoupon(condition);
//                    return MemberReceiveCouponStatusEnum.RECEIVE_COUPON_SUCCESS;
//                }
//
//                // 04、扣减库存失败后处理。
//                receiveFailOperator(condition, loggerFormat);
//            }
//
//            // 07、未获取到锁，返回领取失败
//            return MemberReceiveCouponStatusEnum.REPETITION_REQUEST;
//
//        } catch (GuoquanMarketingException e) {
//            throw e;
//        } catch (Exception e) {
//            log.error("【领取优惠券操作】---营销活动编码：{}，券号：{}，用户id：{}，异常信息：{}", activityCode, couponCode, memberId, e);
//            GQWarningUtils.warn("receive-coupon-error", Lists.newArrayList(condition.getActivityId().toString(), couponCode));
//            throw new GuoquanMarketingException(GuoquanMarketingExceptionEnum.RECEIVE_COUPON_ERROR);
//        } finally {
//            releaseLock(getRedisLockKey(activityId, couponCode));
//        }
//
//    }
//
//    /**
//     * 获得锁
//     */
//    @SuppressWarnings(value = "all")
//    protected boolean getLock(String lockId, long duration, TimeUnit timeUnit) {
//        SessionCallback<Boolean> sessionCallback = new SessionCallback<Boolean>() {
//            List<Object> exec = null;
//            @Override
//            public Boolean execute(RedisOperations operations) throws DataAccessException {
//                operations.multi();
//                redisTemplate.opsForValue().setIfAbsent(lockId, CommonConstant.RECEIVE_REDIS_LOCK);
//                redisTemplate.expire(lockId, duration, timeUnit);
//                exec = operations.exec();
//                if(exec.size() > 0) {
//                    return (Boolean) exec.get(0);
//                }
//                return false;
//            }
//
//        };
//        Boolean execute = redisTemplate.execute(sessionCallback);
//        if (Objects.isNull(execute)) {
//            return false;
//        }
//        return execute;
//    }
//
//
//    private String getRedisLockKey(Long activityId,String couponCode){
//        return activityId+"_"+couponCode;
//    }
//    /**
//     * 释放锁
//     * @param lockId
//     */
//    private void releaseLock(String lockId) {
//        redisTemplate.delete(lockId);
//    }
//}
