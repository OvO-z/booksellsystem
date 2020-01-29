package com.bss.order.restapi.mqchannel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by QAQ on 2019/5/26
 */
public interface OutputSource {
    String ORDERCHANNEL = "orderchannel";
    String CANCEL = "cancel";

    @Output("orderchannel")
    MessageChannel ordersOutput();

    @Output("cancel")
    MessageChannel cancel();
}
