package com.maxcar.base.impl;

import com.maxcar.base.service.TopicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("topicService")
public class TopicServiceImpl implements TopicService {

    @Value("${kafka.consumer.topic006}")
    public String consumerTopic6;
    @Value("${kafka.consumer.topic007}")
    public String consumerTopic7;
    @Value("${kafka.consumer.topic008}")
    public String consumerTopic8;
    /*@Value("${kafka.consumer.topic009}")*/
    public String consumerTopic9;
    @Value("${kafka.consumer.topic010}")
    public String consumerTopic10;
    /*@Value("${kafka.consumer.topic011}")*/
    public String consumerTopic11;
    /*@Value("${kafka.consumer.topic012}")*/
    public String consumerTopic12;
    /*@Value("${kafka.consumer.topic013}")*/
    public String consumerTopic13;
    /*@Value("${kafka.consumer.topic014}")*/
    public String consumerTopic14;

    @Override
    public String getTopic(String marketId) {
        String topic = "";
        switch (marketId) {
            case "006":
                topic = consumerTopic6;
                break;
            case "007":
                topic = consumerTopic7;
                break;
            case "008":
                topic = consumerTopic8;
                break;
            case "009":
                topic = consumerTopic8;
                break;
            case "010":
                topic = consumerTopic10;
                break;
            case "011":
                topic = consumerTopic11;
                break;
            case "012":
                topic = consumerTopic12;
                break;
            case "013":
                topic = consumerTopic13;
                break;
            case "014":
                topic = consumerTopic14;
                break;
            default:
                topic = consumerTopic7;
                break;
        }
        return topic;
    }
}
