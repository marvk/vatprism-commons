package net.marvk.fs.vatsim.map.commons.version;

import lombok.Value;

@Value
public class VersionResponse {
    VersionInformation stable;
    VersionInformation experimental;
}