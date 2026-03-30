package splewsh.hidehotbar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Path;

public class HideHotbarConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("hidehotbar.json");

    public boolean hideInFirstPerson = false;
    public boolean hideInThirdPersonBack = true;
    public boolean hideInThirdPersonFront = true;

    private static HideHotbarConfig instance;

    public static HideHotbarConfig get() {
        if (instance == null) load();
        return instance;
    }

    public static void load() {
        if (CONFIG_PATH.toFile().exists()) {
            try (Reader reader = new FileReader(CONFIG_PATH.toFile())) {
                instance = GSON.fromJson(reader, HideHotbarConfig.class);
            } catch (IOException e) {
                instance = new HideHotbarConfig();
            }
        } else {
            instance = new HideHotbarConfig();
            save();
        }
    }

    public static void save() {
        try (Writer writer = new FileWriter(CONFIG_PATH.toFile())) {
            GSON.toJson(instance, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}