package net.marvk.fs.vatsim.map.commons.motd;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@JsonDeserialize(builder = Button.ButtonBuilder.class)
@Value
@Builder
public class Button {
    @NonNull
    String label;
    @NonNull
    String url;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ButtonBuilder {
    }
}
