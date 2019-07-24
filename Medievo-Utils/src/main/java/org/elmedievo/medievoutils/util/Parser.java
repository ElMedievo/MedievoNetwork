package org.elmedievo.medievoutils.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Objects;

public class Parser {

    public static Location parseLocation(ConfigurationSection section){
        World world = parseWorld(Objects.requireNonNull(section.getString("world")));
        double x = section.getDouble("x");
        double y = section.getDouble("y");
        double z = section.getDouble("z");
        float yaw = Float.parseFloat(Objects.requireNonNull(section.getString("yaw")));
        float pitch = Float.parseFloat(Objects.requireNonNull(section.getString("pitch")));
        return new Location(world, x, y, z, yaw, pitch);
    }

    private static World parseWorld(String string){
        switch (string){
            case "nether":
                return Bukkit.getWorlds().get(1);
            case "end":
                return Bukkit.getWorlds().get(2);
            case "overworld":
            default:
                return Bukkit.getWorlds().get(0);
        }
    }

}
