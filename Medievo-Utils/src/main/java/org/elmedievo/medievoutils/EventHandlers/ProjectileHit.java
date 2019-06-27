package org.elmedievo.medievoutils.EventHandlers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.elmedievo.medievoutils.MedievoUtils;

public class ProjectileHit implements Listener {

    private final MedievoUtils plugin;

    private ProjectileHit(MedievoUtils instance) {
        plugin = instance;
    }

    private void setAreaOnFire(World world, Location origin, int radius) {
        double X = origin.getBlockX();
        double Y = origin.getBlockY() + 1;
        double Z = origin.getBlockZ();

        double minX = X - radius;
        double minZ = Z - radius;

        double maxX = X + radius;
        double maxZ = Z + radius;

        while (minX < maxX) {
            if (minX == maxX) {
                minZ++;
                minX = minX - radius;
            }
            Block block = world.getBlockAt(new Location(world, minX, Y, minZ));
            block.setType(Material.FIRE);
            minX++;
        }
    }

    @EventHandler
    public void onFlameArrowImpact(ProjectileHitEvent event) {
        if (event.getHitBlock() != null) {
            Location impact = event.getHitBlock().getLocation();
            World world = event.getEntity().getWorld();
            if (event.getEntity().getFireTicks() > 0) {

            }
        }
    }
}
