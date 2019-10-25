package io.github.vertex101.taboo.utils;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.ban.Ban;
import org.spongepowered.api.util.ban.BanTypes;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class BanUtils {
    public static Ban onBan(GameProfile profile, CommandSource src, Instant instant, Integer days, Text reason) {
        Ban bb = null;
        if(days > 0) {
            bb = Ban.builder()
                        .type(BanTypes.PROFILE)
                        .profile(profile)
                        .source(src)
                        .startDate(instant)
                        .expirationDate(Instant.now().plus(days, ChronoUnit.DAYS))
                        .reason(Text.of(reason))
                        .build();
        } else {
            bb = Ban.builder()
                    .type(BanTypes.PROFILE)
                    .profile(profile)
                    .source(src)
                    .startDate(instant)
                    .reason(Text.of(reason))
                    .build();
        }
        return bb;
    }
}
