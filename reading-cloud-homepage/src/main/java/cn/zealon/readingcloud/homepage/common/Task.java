package cn.zealon.readingcloud.homepage.common;

import cn.zealon.readingcloud.homepage.service.AuthTaskService;
import cn.zealon.readingcloud.homepage.service.AuthTasklogService;
import cn.zealon.readingcloud.homepage.service.ReadTaskService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Task {

    @Resource
    private AuthTasklogService authTasklogService;

    @Resource
    private ReadTaskService readTaskService;

    @Resource
    private AuthTaskService authTaskService;

    @Scheduled(cron = "0 0 0 * * ?")//每天的0点都执行一次
    public void authTask(){
        System.out.println("每日更新用户任务表启动...");
        this.authTasklogService.resetTaskLog();
        System.out.println("每日更新用户任务表完成...");
    }
    @Scheduled(cron = "0 0 0 * * ?")//每天的0点都执行一次
    public void readTask(){
        System.out.println("每日更新用户阅读任务启动...");
        this.readTaskService.toReadTask();
        System.out.println("每日更新用户阅读任务完成...");
    }
//    @Scheduled(initialDelay = 2000,fixedDelay = 3000)
    public void myTask2(){
        System.out.println("第二个定时任务");
    }
//    @Scheduled(cron = "*/5 * * * * ?")
    public void myTask3(){
        System.out.println("第三个定时任务");
    }
//    @Scheduled(cron = "* */1 * * * ?")
    public void myTask4(){
        System.out.println("第四个定时任务");
    }


//    0 0 2 1 * ? *                         在每月的1日的凌晨2点调度任务
//0 15 10 ? * MON-FRI           周一到周五每天上午10：15执行作业
//0 15 10 ? 6L 2022-2026       2022-2026年的每个月的最后一个星期五上午10:15执行作
//0 0 10,14,16 * * ?                每天上午10点，下午2点，4点
//0 0/30 9-17 * * ?                 朝九晚五工作时间内每半小时
//0 0 12 ? * WED                    每个星期三中午12点
//0 0 12 * * ?                          每天中午12点触发
//0 15 10 ? * *                        每天上午10:15触发
//0 15 10 * * ?                        每天上午10:15触发
//0 15 10 * * ? *                      每天上午10:15触发
//0 15 10 * * ? 2005               2005年的每天上午10:15触发
//0 * 14 * * ?                           每天下午2点到下午2:59期间的每1分钟触发
//0 0/5 14 * * ?                       每天下午2点到下午2:55期间的每5分钟触发
//0 0/5 14,18 * * ?                  每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
//0 0-5 14 * * ?                       每天下午2点到下午2:05期间的每1分钟触发
//0 10,44 14 ? 3 WED           每年三月的星期三的下午2:10和2:44触发
//0 15 10 ? * MON-FRI          周一至周五的上午10:15触发
//0 15 10 15 * ?                     每月15日上午10:15触发
//0 15 10 L * ?                       每月最后一日的上午10:15触发
//0 15 10 ? * 6L                     每月的最后一个星期五上午10:15触发
//0 15 10 ? * 6L 2022-2025  2022年至2025年的每月的最后一个星期五上午10:15触发
//0 15 10 ? * 6#3                  每月的第三个星期五上午10:15触发
//*/5 * * * * ?                         每隔5秒执行一次
//0 */1 * * * ?                        每隔1分钟执行一次
//0 0 23 * * ?                        每天23点执行一次
//0 0 1 * * ?                          每天凌晨1点执行一次
//0 0 1 1 * ?                         每月1号凌晨1点执行一次
//0 0 23 L * ?                       每月最后一天23点执行一次
//0 0 1 ? * L                         每周星期天凌晨1点实行一次
//0 26,29,33 * * * ?              在26分、29分、33分执行一次
//0 0 0,13,18,21 * * ?           每天的0点、13点、18点、21点都执行一次

}
