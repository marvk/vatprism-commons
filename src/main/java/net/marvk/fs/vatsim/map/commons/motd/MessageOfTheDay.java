
package net.marvk.fs.vatsim.map.commons.motd;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.zafarkhaja.semver.Version;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@JsonDeserialize(builder = MessageOfTheDay.MessageOfTheDayBuilderInternal.class)
@JsonView()
@Value
@Builder(builderClassName = "MessageOfTheDayBuilder")
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
    @NonNull
    Level level;
    @Builder.Default
    List<Button> buttons = List.of();
    Double minHours;
    Double maxHours;
    Range<LocalDate> dateRange;
    LocalDate date;
    Range<Version> versionRange;
    Version version;

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
}
