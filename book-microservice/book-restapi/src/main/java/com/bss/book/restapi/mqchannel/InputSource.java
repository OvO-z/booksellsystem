package com.bss.book.restapi.mqchannel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.MessageChannel;

/**
 * Created by QAQ on 2019/5/27
 */
public interface InputSource {
    String ORDERCHANNEL = "orderchannel";
    String REPLYCHANNEL = "replychannel";
    String CANCEL = "cancel";

    @Input("orderchannel")
    SubscribableChannel orderInput();

    @Input("cancel")
    SubscribableChannel cancel();

    @Output("replychannel")
    MessageChannel replyOutput();

}
