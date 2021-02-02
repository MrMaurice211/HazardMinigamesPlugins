package dataManagment;

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
     * Adds a variable to the dataFile that can be altered and deleted at will.
     *
     * @param variable the variable that is wanted to be added
     * @param path the path in the dataFile that the variable is going to occupy
     * @param save save the file right after adding the variable? (not recommended if adding multiple variables at the
     *             same time)
     */
    public void add(Object variable, String path, boolean save) {
        config.set(path, variable);
        if (save) saveData();
    }

    /**
     * Adds a variable to the dataFile that can be altered and deleted at will. This method does not save after adding
     * the variable.
     *
     * @param variable the variable that is wanted to be added
     * @param path the path in the dataFile that the variable is going to occupy
     */
    public void add(Object variable, String path) {
        add(variable, path, false);
    }

    /**
     * Deletes a the variable in the path provided.
     *
     * @param path path to the variable
     * @param save save the file right after deleting the variable? (not recommended for rapid deletion)
     */
    public void delete(String path, boolean save) {
        config.set(path, null);
        if (save) saveData();
    }

    /**
     * Deletes a variable in the path provided. Does not save after doing so.
     *
     * @param path path to the variable
     */
    public void delete(String path) {
        delete(path, false);
    }

    /**
     * Sets a variable in a specified path of the dataFile.
     *
     * @param path path to the variable in the DataFolder
     * @param save save the file afterwards? (not recommended for rapid changes)
     * @param <Type> type of the variable you are trying to reach
     */
    public <Type> void get(Type type, String path, boolean save) {
        config.get(path, type);
        if (save) saveData();
    }

    /**
     * Sets a variable in a specified path of the dataFile. Does not save afterwards.
     *
     * @param path path to the variable in the DataFolder
     * @param <Type> type of the variable you are trying to reach
     */
    public <Type> void get(Type type, String path) {
        get(type, path, false);
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

    //Getters
    public File getFile() {return file;}
    public FileConfiguration getConfig() {return config;}
}

