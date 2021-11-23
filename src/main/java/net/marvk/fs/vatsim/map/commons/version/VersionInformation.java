package net.marvk.fs.vatsim.map.commons.version;

import lombok.Value;

@Value
public class VersionInformation {
    public static final VersionInformation NONE = new VersionInformation("0.0.0", "", "", "");

    String version;
    String windowsUrl;
    String macosUrl;
    String body;
}
