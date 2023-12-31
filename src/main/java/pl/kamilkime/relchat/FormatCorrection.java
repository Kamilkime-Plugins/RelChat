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

import java.util.function.Function;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public enum FormatCorrection {

    LEGACY_AMPERSAND(text -> LegacyComponentSerializer.legacyAmpersand().deserialize(text)),
    LEGACY_SECTION(text -> LegacyComponentSerializer.legacySection().deserialize(text)),
    NONE(Component::text);

    private final Function<String, Component> correction;

    FormatCorrection(final Function<String, Component> corrector) {
        this.correction = corrector;
    }

    public Component apply(final String text) {
        return this.correction.apply(text);
    }
}
