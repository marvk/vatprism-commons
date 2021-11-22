package net.marvk.fs.vatsim.map.commons.motd;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class HoursRangeDeserializer extends StdDeserializer<Range<Double>> {
    protected HoursRangeDeserializer() {
        super(Range.class);
    }

    @Override
    public Range<Double> deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException, JacksonException {
        final TreeNode treeNode = jsonParser.readValueAsTree();

        final TreeNode fromNode = treeNode.get("from");
        final TreeNode toNode = treeNode.get("to");

        return new Range<>(parseDouble(fromNode), parseDouble(toNode));
    }

    private static Double parseDouble(final TreeNode fromNode) {
        if (fromNode != null) {
            return Double.parseDouble(fromNode.toString());
        } else {
            return null;
        }
    }
}