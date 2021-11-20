package net.marvk.fs.vatsim.map.commons.motd;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.zafarkhaja.semver.Version;

import java.io.IOException;
import java.io.Serial;

class VersionSerializer extends StdSerializer<Version> {
    @Serial
    private static final long serialVersionUID = 7891423672504871112L;

    VersionSerializer() {
        super(Version.class);
    }

    @Override
    public void serialize(final Version version, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(version.toString());
    }
}
