package net.marvk.fs.vatsim.map.commons.motd;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@JsonDeserialize(builder = Range.RangeBuilder.class)
@Value
@Builder
public class Range<T> {
    T from;
    T to;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RangeBuilder<T> {
        Range<T> build() {
            if (from == null && to == null) {
                throw new IllegalArgumentException("Either to or from must not be null");
            }
            return new Range<>(from, to);
        }
    }
}
