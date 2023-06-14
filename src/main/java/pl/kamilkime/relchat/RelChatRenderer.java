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

import io.papermc.paper.chat.ChatRenderer;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RelChatRenderer implements ChatRenderer {

    private final ChatRenderer eventRenderer;
    private final FormatCorrection formatCorrection;

    public RelChatRenderer(final ChatRenderer eventRenderer, final FormatCorrection formatCorrection) {
        this.eventRenderer = eventRenderer;
        this.formatCorrection = formatCorrection;
    }

    @Override
    public @NotNull Component render(
        @NotNull final Player source,
        @NotNull final Component sourceDisplayName,
        @NotNull final Component message,
        @NotNull final Audience viewer
    ) {
        final Component baseMessage = this.eventRenderer.render(source, sourceDisplayName, message, viewer);
        if (!(viewer instanceof Player)) {
            return baseMessage;
        }

        final TextReplacementConfig replacementConfig = this.buildReplacementConfig((Player) viewer, source);
        return baseMessage.replaceText(replacementConfig);
    }

    private TextReplacementConfig buildReplacementConfig(final Player viewer, final Player target) {
        return TextReplacementConfig.builder()
            .match(PlaceholderAPI.getRelationalPlaceholderPattern())
            .replacement((match, ignored) -> {
                final String placeholderValue = PlaceholderAPI.setRelationalPlaceholders(viewer, target, match.group());
                return this.formatCorrection.apply(placeholderValue);
            }).build();
    }
}
