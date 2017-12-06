package com.tangshengbo.design.command.example;

import com.google.common.collect.Lists;
import org.apache.commons.lang.time.FastDateFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by Tangshengbo on 2017/9/29.
 */
public class Waiter {

    private List<Command> commands = Lists.newArrayList();

    public void setOrder(Command command) {
        if (command instanceof BakeBeefCommand) {
            System.out.println("服务员:牛肉没有了");
            return;
        }
        commands.add(command);
        Date current = new Date();
        System.out.println(String.format("增加订单：%s 时间: %tF %tT", command.getName(), current, current));
    }

    public void cancelOrder(Command command) {
        if (commands.contains(command)) {
            commands.remove(command);
            String processingTime = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date());
            System.out.println(String.format("取消订单：%s 时间: %s", command.getName(), processingTime));
        }
    }

    public void notifyBarbecuer() {
        commands.forEach(command -> {
            command.executeCommand();
        });
    }
}
