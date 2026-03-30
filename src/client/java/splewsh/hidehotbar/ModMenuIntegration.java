package splewsh.hidehotbar;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.network.chat.Component;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            HideHotbarConfig config = HideHotbarConfig.get();

            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Component.literal("Hide Hotbar Settings"))
                    .setSavingRunnable(HideHotbarConfig::save);

            ConfigCategory general = builder.getOrCreateCategory(Component.literal("Options"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            general.addEntry(entryBuilder
                    .startBooleanToggle(Component.literal("Hide in First Person"), config.hideInFirstPerson)
                    .setDefaultValue(false)
                    .setSaveConsumer(val -> config.hideInFirstPerson = val)
                    .build());

            general.addEntry(entryBuilder
                    .startBooleanToggle(Component.literal("Hide in Third Person (Back)"), config.hideInThirdPersonBack)
                    .setDefaultValue(true)
                    .setSaveConsumer(val -> config.hideInThirdPersonBack = val)
                    .build());

            general.addEntry(entryBuilder
                    .startBooleanToggle(Component.literal("Hide in Third Person (Front)"), config.hideInThirdPersonFront)
                    .setDefaultValue(true)
                    .setSaveConsumer(val -> config.hideInThirdPersonFront = val)
                    .build());

            return builder.build();
        };
    }
}