package app.planet.domain.model.channel;
import java.util.regex.Pattern;

import static java.util.Objects.nonNull;

public record ChannelInfo(
        String channelType,
        String channelName
){
    public static final String Name_REGEX = "^.{2,12}$";
    public boolean isValid() {
        return isChannelTypeValid();
    }
    private boolean isChannelTypeValid() {
        return nonNull(this.channelType)&&Pattern.matches(Name_REGEX,channelName);
    }
}
