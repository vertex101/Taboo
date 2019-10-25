package io.github.vertex101.taboo.utils;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.SkullTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.*;

public class InvUtils {
    public static List<Text> flyLore = Arrays.asList(Text.of(TextColors.DARK_RED, "7 day ban"),
            Text.of(TextColors.GOLD, "Click to ban for Fly Hacking"));
    public static List<Text> speedLore = Arrays.asList(Text.of(TextColors.DARK_RED, "7 day ban"),
            Text.of(TextColors.GOLD, "Click to ban for Speed Hacking"));
    public static List<Text> xrayLore = Arrays.asList(Text.of(TextColors.DARK_RED, "14 day ban"),
            Text.of(TextColors.GOLD, "Click to ban for X-Ray Hacking"));

    public static ItemStack fly = ItemStack.builder()
            .itemType(ItemTypes.FEATHER)
            .quantity(1)
            .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Fly Hacking"))
            .add(Keys.ITEM_LORE, flyLore)
            .build();
    public static ItemStack speed = ItemStack.builder()
            .itemType(ItemTypes.REDSTONE)
            .quantity(1)
            .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Speed Hacking"))
            .add(Keys.ITEM_LORE, speedLore)
            .build();
    public static ItemStack xray = ItemStack.builder()
            .itemType(ItemTypes.DIAMOND)
            .quantity(1)
            .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "X-Ray Hacking"))
            .add(Keys.ITEM_LORE, xrayLore)
            .build();

    public static ItemStack perm = ItemStack.builder()
            .itemType(ItemTypes.BEDROCK)
            .quantity(1)
            .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Perm BANNNNNNNNN"))
            .build();

    public static ItemStack playerHead(User user) {
        return ItemStack.builder()
                .itemType(ItemTypes.SKULL)
                .quantity(1)
                .add(Keys.SKULL_TYPE, SkullTypes.PLAYER)
                .add(Keys.REPRESENTED_PLAYER, GameProfile.of(user.getProfile().getUniqueId()))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, user.getName()))
                .build();
    }
}
