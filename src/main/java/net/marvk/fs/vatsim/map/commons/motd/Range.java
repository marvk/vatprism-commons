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

    public Range(final T from, final T to) {
        if (from == null && to == null) {
            throw new IllegalArgumentException("Either to or from must not be null");
        }
        this.from = from;
        this.to = to;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class RangeBuilder<T> {
    }

    /**
     * If the elements of this range are not Comparable, a {@link java.lang.ClassCastException} will be thrown.
     */
    public boolean contains(final T t) {
        if (from == null && to != null) {
            return isFirstGreaterThanOrEqualToSecond(to, t);
        }

        if (to == null && from != null) {
            return isFirstLessThanOrEqualToSecond(from, t);
        }

        return isFirstGreaterThanOrEqualToSecond(to, t) && isFirstLessThanOrEqualToSecond(from, t);
    }

    /**
     * If the elements of this range are not Comparable, a {@link java.lang.ClassCastException} will be thrown.
     */
    public boolean containsExclusive(final T t) {
        if (from == null && to != null) {
            return isFirstGreaterThanSecond(to, t);
        }

        if (to == null && from != null) {
            return isFirstLessThanSecond(from, t);
        }

        return isFirstGreaterThanSecond(to, t) && isFirstLessThanSecond(from, t);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <E> int compare(final E first, final E second) {
        return ((Comparable) first).compareTo(second);
    }

    private static <T> boolean isFirstLessThanSecond(final T first, final T second) {
        return compare(first, second) < 0;
    }

    private static <T> boolean isFirstLessThanOrEqualToSecond(final T first, final T second) {
        return compare(first, second) <= 0;
    }

    private static <T> boolean isFirstEqualToSecond(final T first, final T second) {
        return compare(first, second) == 0;
    }

    private static <T> boolean isFirstGreaterThanOrEqualToSecond(final T first, final T second) {
        return compare(first, second) >= 0;
    }

    private static <T> boolean isFirstGreaterThanSecond(final T first, final T second) {
        return compare(first, second) > 0;
    }
}
