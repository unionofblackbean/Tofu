package asia.ubb.tofu.framework.services;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ConfigService {

    private final JavaPlugin plugin;
    private final List<Runnable> reloadConfigCallbacks = new ArrayList<>();

    public ConfigService(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void createConfig(boolean override) {
        plugin.saveResource("config.yml", override);
    }

    public void createConfig() {
        createConfig(false);
    }

    public void registerReloadConfigCallback(Runnable callback) {
        reloadConfigCallbacks.add(callback);
    }

    public void reloadConfig(boolean runCallbacks) {
        plugin.reloadConfig();

        if (runCallbacks) {
            for (Runnable callback : reloadConfigCallbacks) {
                callback.run();
            }
        }
    }

    public void reloadConfig() {
        reloadConfig(true);
    }

    public void saveConfig() {
        plugin.saveConfig();
    }

    public void saveDefaultConfig() {
        plugin.saveDefaultConfig();
    }

    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }

}
