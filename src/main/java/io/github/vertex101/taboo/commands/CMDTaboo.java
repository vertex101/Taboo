package io.github.vertex101.taboo.commands;

import io.github.vertex101.taboo.Taboo;
import io.github.vertex101.taboo.utils.BanUtils;
import io.github.vertex101.taboo.utils.InvUtils;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.*;
import org.spongepowered.api.item.inventory.property.InventoryDimension;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.item.inventory.property.SlotPos;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.service.ban.BanService;
import org.spongepowered.api.service.user.UserStorageService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.ban.Ban;
import org.spongepowered.api.util.ban.BanTypes;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class CMDTaboo implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if(!(src instanceof Player)) {
            return CommandResult.empty();
        }
        Player s = args.<Player>getOne(Text.of("player")).orElse(null);
        GameProfile profile = s.getProfile();
        User ss = Sponge.getServiceManager().provide(UserStorageService.class).get().get(profile).get();
        Instant instant = Instant.now();
        BanService bs = Sponge.getServiceManager().provide(BanService.class).get();
        Player player = (Player)src;

        Inventory inv = Inventory.builder()
                .property(InventoryTitle.PROPERTY_NAME, new InventoryTitle(Text.of(TextColors.DARK_PURPLE, "Taboo")))
                .property(InventoryDimension.PROPERTY_NAME, new InventoryDimension(9,1))
                .listener(ClickInventoryEvent.class, event -> {
                    ItemStackSnapshot iss = event.getCursorTransaction().getDefault();
                    event.setCancelled(true);
                    if(iss.getType().equals(ItemTypes.FEATHER)) {
                        bs.addBan(BanUtils.onBan(profile, src, instant, 7, Text.of("You been ban for fly hacking")));
                        if (s != null && ss.getPlayer().isPresent()) {
                            if (profile != null) {
                                ss.getPlayer().get().kick(Text.of("You been ban for fly hacking"));
                            }
                        }
                    } else if(iss.getType().equals(ItemTypes.REDSTONE)) {
                        bs.addBan(BanUtils.onBan(profile, src, instant, 7, Text.of("You been ban for speed hacking")));
                        if (s != null && ss.getPlayer().isPresent()) {
                            if (profile != null) {
                                ss.getPlayer().get().kick(Text.of("You been ban for speed hacking"));
                            }
                        }
                    } else if(iss.getType().equals(ItemTypes.DIAMOND)) {
                        bs.addBan(BanUtils.onBan(profile, src, instant, 14, Text.of("You been ban for xray hacking")));
                        if (s != null && ss.getPlayer().isPresent()) {
                            if (profile != null) {
                                ss.getPlayer().get().kick(Text.of("You been ban for xray hacking"));
                            }
                        }
                    } else if(iss.getType().equals(ItemTypes.BEDROCK)) {
                        bs.addBan(BanUtils.onBan(profile, src, instant, 0, Text.of("You been PREM BANNNNN")));
                        if (s != null && s.getPlayer().isPresent()) {
                            if (profile != null) {
                                ss.getPlayer().get().kick(Text.of("You been PREM BANNNNN"));
                            }
                        }
                    } else if(iss.getType().equals(ItemTypes.SKULL)) {
                        if (s != null && s.getPlayer().isPresent()) {
                            if (profile != null) {
                                player.setLocation(player.getLocation().setPosition(s.getPosition()));
                            }
                        }
                    }
                })
                 .build(Taboo.plugin);

        Slot headSlot = inv.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(0,0)));
        Slot flySlot = inv.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(3,0)));
        Slot speedSlot = inv.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(4,0)));
        Slot xraySlot = inv.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(5,0)));
        Slot permSlot = inv.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(8,0)));

        headSlot.set(InvUtils.playerHead(s));
        flySlot.set(InvUtils.fly);
        speedSlot.set(InvUtils.speed);
        xraySlot.set(InvUtils.xray);
        permSlot.set(InvUtils.perm);

        player.openInventory(inv);
        return CommandResult.success();
    }

    public static CommandSpec base() {
        return CommandSpec.builder()
                .permission("taboo.base.ban")
                .arguments(GenericArguments.onlyOne(GenericArguments.player(Text.of("player"))))
                .executor(new CMDTaboo())
                .build();
    }
}
