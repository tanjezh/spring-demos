package com.tan.event.listener.basic;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author tanjezh
 * @create 2024-08-12 15:59
 */
public class MsgEvent extends ApplicationEvent {

    @Getter
    private String msg;

    public MsgEvent setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public MsgEvent(Object source) {
        super(source);
    }

    @Override
    public String toString() {
        return "MsgEvent{" + "msg='" + msg + '\'' + '}';
    }
}
