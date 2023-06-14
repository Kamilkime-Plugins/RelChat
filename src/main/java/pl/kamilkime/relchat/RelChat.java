/*
 *  Copyright 2023 Kamil Trysiński
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package pl.kamilkime.relchat;

import io.papermc.paper.event.player.AsyncChatEvent;
import java.util.Locale;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class RelChat extends JavaPlugin implements Listener {

    private FormatCorrection formatCorrection;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        final PluginManager pluginManager = this.getServer().getPluginManager();
        if (!pluginManager.isPluginEnabled("PlaceholderAPI")) {
            this.getLogger().severe("RelChat is useless without PlaceholderAPI, disabling...");
            pluginManager.disablePlugin(this);
            return;
        }

        final String correctionString = this.getConfig().getString("formatCorrection", "NONE");
        this.formatCorrection = FormatCorrection.valueOf(correctionString.toUpperCase(Locale.ROOT));

        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChat(final AsyncChatEvent event) {
        event.renderer(new RelChatRenderer(event.renderer(), this.formatCorrection));
    }
}
