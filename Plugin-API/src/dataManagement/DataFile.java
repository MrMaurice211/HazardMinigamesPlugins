package dataManagement;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class DataFile {
    //Fields
    private final File file;
    private final FileConfiguration config;

    //Constructor
    /**
     * Creates a new dataFile to store variables when the server shuts off. This file is placed in the plugin's
     * respective data folder.
     *
     * @param filePath Path to the file while in the plugin data folder.
     * @param plugin The plugin itself. This can be accessed by doing Main.getPlugin() outside of main, and
     *               this.getPlugin() inside of main.
     */
    public DataFile(String filePath, Plugin plugin) {
        this.file = new File(plugin.getDataFolder(), filePath);
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Creates a new dataFile to store variables when the server shuts off. This constructor is only recommended for
     * testing purposes.
     *
     * @param filePath path to the file
     */
    public DataFile(String filePath) {
        this.file = new File(filePath);
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    //Methods
    /**
     * Sets a variable to the dataFile that can be altered and deleted at will.
     *  @param path the path in the dataFile that the variable is going to occupy
     * @param o the variable that is wanted to be added
     * @param save save the file right after adding the variable? (not recommended if adding multiple variables at the
     */
    public void set(String path, Object o, boolean save) {
        config.set(path, o);
        if (save) saveData();
    }

    /**
     * Sets a variable to the dataFile that can be altered and deleted at will. This method does not save after adding
     * the variable.
     *
     * @param path the path in the dataFile that the variable is going to occupy
     * @param variable the variable that is wanted to be added
     */
    public void set(String path, Object variable) {
        set(path, variable, false);
    }

    /**
     * Deletes a the variable in the path provided.
     *
     * @param path path to the variable
     * @param save save the file right after deleting the variable? (not recommended for rapid deletion)
     */
    public void remove(String path, boolean save) {
        config.set(path, null);
        if (save) saveData();
    }

    /**
     * Deletes a variable in the path provided. Does not save after doing so.
     *
     * @param path path to the variable
     */
    public void remove(String path) {
        remove(path, false);
    }

    /**
     * Gets a variable in a specified path of the dataFile.
     *
     * @param path path to the variable in the DataFolder
     */
    public Object get(String path) {
        return config.get(path);
    }

    /**
     * Saves the current changes to the dataFile
     */
    public void saveData() {
        try {
            getConfig().save(getFile());
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    /**
     * Deletes the dataFile
     */
    public void delete() {
        file.delete();
    }

    //Getters
    public File getFile() {return file;}
    public FileConfiguration getConfig() {return config;}
}

