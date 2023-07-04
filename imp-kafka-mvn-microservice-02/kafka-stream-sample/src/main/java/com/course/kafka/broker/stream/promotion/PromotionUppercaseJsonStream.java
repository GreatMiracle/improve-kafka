package com.course.kafka.broker.stream.promotion;

import com.course.kafka.message.PromotionMessage;
import com.course.kafka.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

@Configuration
@Slf4j
public class PromotionUppercaseJsonStream {

    public KStream<String, String> kstreamPromotionUppercaseJsonStream(StreamsBuilder builder){
        var stringSerde=Serdes.String();
        var sourceStream = builder.stream("t-commodity-promotion", Consumed.with(stringSerde, stringSerde));

        var uppercaseStream = sourceStream.mapValues(this::uppercasePromotionCode);

        uppercaseStream.to("t-commodity-promotion-uppercase");

        sourceStream.print(Printed.<String, String>toSysOut().withLabel("JSON original stream"));
        uppercaseStream.print(Printed.<String, String>toSysOut().withLabel("JSON uppercase stream"));

        return sourceStream;
    }

    public String uppercasePromotionCode(String message) {
        try {
            var original = JsonUtils.convertJsonToObject(message, PromotionMessage.class);
            var converted = new PromotionMessage(original.getPromotionCode().toUpperCase());

            return JsonUtils.convertObjectToJson(converted);
        } catch (Exception e) {
            return "";
        }
    }
}
