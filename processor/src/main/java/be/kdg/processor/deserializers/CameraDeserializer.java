package be.kdg.processor.deserializers;

import be.kdg.processor.model.Camera;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CameraDeserializer extends StdDeserializer<Camera> {

    public CameraDeserializer() {
        this(null);
    }

    public CameraDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Camera deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        int cameraId = (Integer) node.get("cameraId").numberValue();
        double latitude = (Double) node.get("location").get("lat").numberValue();
        double longitude = (Double) node.get("location").get("long").numberValue();
        int euroNorm = (Integer) node.get("euroNorm").numberValue();
        return new Camera(cameraId, latitude, longitude, euroNorm);
    }
}
