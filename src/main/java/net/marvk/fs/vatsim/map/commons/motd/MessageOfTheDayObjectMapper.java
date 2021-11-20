package net.marvk.fs.vatsim.map.commons.motd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.Serial;

public class MessageOfTheDayObjectMapper extends ObjectMapper {
    @Serial
    private static final long serialVersionUID = -7154540251831796787L;

    public MessageOfTheDayObjectMapper() {
        registerModule(new JavaTimeModule());
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        registerModule(new SimpleModule().addSerializer(new VersionSerializer()));
    }
}
