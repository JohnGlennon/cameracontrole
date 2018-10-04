package be.kdg.processor.deserializers;

import be.kdg.processor.model.LicensePlate;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class LicensePlateDeserializer extends StdDeserializer<LicensePlate> {

    public LicensePlateDeserializer() {
        this(null);
    }

    public LicensePlateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LicensePlate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        String plateId = node.get("plateId").asText();
        String nationalNumber = node.get("nationalNumber").asText();
        int euroNorm = (Integer) node.get("euroNumber").numberValue();
        return new LicensePlate(plateId, nationalNumber, euroNorm);
    }
}
