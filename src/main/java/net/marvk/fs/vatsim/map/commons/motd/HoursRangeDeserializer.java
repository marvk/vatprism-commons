package net.marvk.fs.vatsim.map.commons.motd;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.NullNode;

import java.io.IOException;
import java.io.Serial;

public class HoursRangeDeserializer extends StdDeserializer<Range<Double>> {
    @Serial
    private static final long serialVersionUID = -645078820266909642L;

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

    private static Double parseDouble(final TreeNode node) {
        if (node == null || node instanceof NullNode) {
            return null;
        } else {
            return Double.parseDouble(node.toString());
        }
    }
}
