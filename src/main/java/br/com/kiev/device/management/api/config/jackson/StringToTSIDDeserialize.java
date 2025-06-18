package br.com.kiev.device.management.api.config.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.hypersistence.tsid.TSID;

import java.io.IOException;

public class StringToTSIDDeserialize extends JsonDeserializer<TSID> {

    @Override
    public TSID deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        return TSID.from(jsonParser.getText());
    }
}
