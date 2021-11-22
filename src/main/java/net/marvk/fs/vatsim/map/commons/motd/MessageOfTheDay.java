
package net.marvk.fs.vatsim.map.commons.motd;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.zafarkhaja.semver.Version;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@JsonDeserialize(builder = MessageOfTheDay.MessageOfTheDayBuilderInternal.class)
@JsonView()
@Value
@Builder(builderClassName = "MessageOfTheDayBuilder", toBuilder = true)
public class MessageOfTheDay {
    @NonNull
    String title;
    String file;
    String content;
    @Builder.Default
    boolean enabled = true;
    @Builder.Default
    boolean skippable = true;
    @Builder.Default
    boolean dismissable = true;
    @Builder.Default
    boolean recurring = true;
    @Builder.Default
    boolean production = true;
    @NonNull
    Level level;
    @Builder.Default
    List<Button> buttons = List.of();
    Range<LocalDate> dateRange;
    LocalDate date;
    Range<Version> versionRange;
    Version version;
    @JsonDeserialize(using = HoursRangeDeserializer.class)
    Range<Double> hoursRange;

    public boolean matchesHours(final Double hours) {
        Objects.requireNonNull(hours);

        return hoursRange.contains(hours);
    }

    public boolean matchesVersion(final Version version) {
        Objects.requireNonNull(version);

        if (this.version != null) {
            return this.version.equals(version);
        }

        if (this.versionRange != null) {
            return this.versionRange.contains(version);
        }

        return true;
    }

    public boolean matchesDate(final LocalDate localDate) {
        Objects.requireNonNull(localDate);

        if (this.date != null) {
            return this.date.equals(localDate);
        }

        if (this.dateRange != null) {
            return this.dateRange.contains(localDate);
        }

        return true;
    }

    public static MessageOfTheDayBuilder builder() {
        return new MessageOfTheDayBuilderInternal();
    }

    public static class MessageOfTheDayBuilder {
    }

    @JsonPOJOBuilder(withPrefix = "")
    static class MessageOfTheDayBuilderInternal extends MessageOfTheDayBuilder {
        MessageOfTheDayBuilderInternal() {
            super();
        }

        @Override
        public MessageOfTheDay build() {
            final MessageOfTheDay motd = super.build();
            if (motd.versionRange != null && motd.version != null) {
                throw new IllegalArgumentException("Either versionRange or version must be null");
            }

            if (motd.dateRange != null && motd.date != null) {
                throw new IllegalArgumentException("Either dateRange or date must be null");
            }

            if (motd.file != null && motd.content != null) {
                throw new IllegalArgumentException("Either dateRange or date must be null");
            }

            if (motd.file == null && motd.content == null) {
                throw new IllegalArgumentException("Either dateRange or date must not be null");
            }
            return motd;
        }
    }

    public MessageOfTheDay withDereferencedContent(final String content) {
        if (this.content != null || this.file == null) {
            throw new IllegalArgumentException("MOTD is already dereferenced");
        }
        return toBuilder().file(null).content(content).build();
    }
}
