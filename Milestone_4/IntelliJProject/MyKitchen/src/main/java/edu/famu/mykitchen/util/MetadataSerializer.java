package edu.famu.mykitchen.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.grpc.Metadata;

import java.io.IOException;

public class MetadataSerializer extends JsonSerializer<Metadata> {
    @Override
    public void serialize(Metadata metadata, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        // Add custom serialization logic here if needed
        gen.writeEndObject();
    }


}