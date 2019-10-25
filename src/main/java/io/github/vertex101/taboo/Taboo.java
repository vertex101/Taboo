package io.github.vertex101.taboo;

import com.google.inject.Inject;
import io.github.vertex101.taboo.commands.CMDTaboo;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

@Plugin(id = "taboo", name = "Taboo", version = "1.0.0", description = "A tempban plugin", authors = "Vertex101")
public class Taboo {

    @Inject
    private Logger logger;
    @Inject
    private PluginContainer container;
    private static Taboo instance;
    public static Taboo plugin;
    public static Logger getLogger() { return instance.logger; }
    public static PluginContainer getContainer() { return instance.container; }

    @Listener
    public void onPreInit(GamePreInitializationEvent e) {
        plugin = this;
        Sponge.getCommandManager().register(this, CMDTaboo.base(), "taboo");
    }

    @Listener
    public void onInit(GameInitializationEvent e) {
    }

    @Listener
    public void onPostInit(GamePostInitializationEvent e) {
    }
}
