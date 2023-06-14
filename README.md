# RelChat

[PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) has relational placeholders that return a different value for
each recipient. However, many chat formatting plugins only support simple, non-relational placeholder replacement. RelChat fixes this — you
can still use your favourite chat plugin, but relational placeholders will now be replaced correctly.

----------------------------------------------------

## :desktop_computer: Requirements

- [Paper](https://papermc.io/) or one of its forks (for MC 1.16 or newer)
- [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/)
- Any chat formatting plugin (tested with [LPC](https://www.spigotmc.org/resources/lpc-chat-formatter-1-7-10-1-19.68965/) and
  [EssentialsXChat](https://www.spigotmc.org/resources/essentialsx.9089/))

----------------------------------------------------

## :gear: Configuration

In most cases no configuration is required. However, sometimes returned placeholder values may contain legacy color characters — section
(§) or ampersand (&). In this case placeholders may not look right in your chat messages.

To fix this — try changing the `correctionMode` setting in RelChat's `config.yml` file. You may need to try both legacy options if you are
not sure what the placeholder returns. But after some trial and error - messages should start to look correct.

```yaml
# Correction mode, used for format correction of the returned placeholder value
# If e.g. RGB colors in the chat format are not correct — try changing the mode to one of the legacy ones
# Available modes:
#   LEGACY_AMPERSAND — corrects & characters in the returned text
#   LEGACY_SECTION — corrects § characters in the returned text
#   NONE — no correction, creates a component from raw text
correctionMode: "NONE"
```

## :memo: Issues and suggestions

Any issues, suggestions or other questions should be asked [here](https://github.com/Kamilkime-Plugins/RelChat/issues). If you do not have
a Github account you may contact me on Discord — `@kamilkime`.

## :unlock: License

RelChat is distributed under the [Apache License 2.0](https://choosealicense.com/licenses/apache-2.0/).
