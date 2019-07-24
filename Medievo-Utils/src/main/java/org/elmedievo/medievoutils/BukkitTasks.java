package org.elmedievo.medievoutils;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

class BukkitTasks implements Listener {
    @SuppressWarnings("deprecation")
    static void setGameParams() {
        /* TODO: Make this configurable */
        Bukkit.getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.LEATHER), Material.ROTTEN_FLESH));
        /* Medieval gold foundry mechanics */
        addFurnaceRecipe(getGold(5), Material.GOLDEN_HELMET);
        addFurnaceRecipe(getGold(8), Material.GOLDEN_CHESTPLATE);
        addFurnaceRecipe(getGold(7), Material.GOLDEN_LEGGINGS);
        addFurnaceRecipe(getGold(5), Material.GOLDEN_BOOTS);
        addFurnaceRecipe(getGold(2), Material.GOLDEN_SWORD);
        addFurnaceRecipe(getGold(3), Material.GOLDEN_PICKAXE);
        addFurnaceRecipe(getGold(1), Material.GOLDEN_SHOVEL);
        addFurnaceRecipe(getGold(2), Material.GOLDEN_HOE);
        addFurnaceRecipe(getGold(20), Material.GOLDEN_HORSE_ARMOR);
        addFurnaceRecipe(getGold(8), Material.GOLDEN_APPLE);
        addFurnaceRecipe(getGold(8), Material.GOLDEN_CARROT);
        /* Medieval gold foundry mechanics */

        new BukkitRunnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorlds().get(0); // World 0 returns overworld much better instead of using getWorld('world')
                world.setDifficulty(Difficulty.HARD);
                this.cancel();
            }
        }.runTaskTimer(MedievoUtils.instance, 0L, 0L);
    }

    private static void addFurnaceRecipe(ItemStack result, Material source){
        Bukkit.getServer().addRecipe(new FurnaceRecipe(new NamespacedKey(MedievoUtils.instance, "key_" + result.toString()), result, source, 0.3f, 200));
    }

    private static ItemStack getGold(int amount){
        return new ItemStack(Material.GOLD_INGOT, amount);
    }


}