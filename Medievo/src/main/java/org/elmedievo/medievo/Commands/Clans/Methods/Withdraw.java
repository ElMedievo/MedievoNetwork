package org.elmedievo.medievo.Commands.Clans.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

import static org.elmedievo.medievo.Commands.Clans.Mercantilism.Valuables.CURRENCY_NAME_PLURAL;
import static org.elmedievo.medievo.Commands.Clans.Mercantilism.Valuables.CURRENCY_SYMBOL;
import static org.elmedievo.medievo.Commands.Clans.Mercantilism.Valuables.valueInMarket;
import static org.elmedievo.medievoapi.Database.Getters.ClanMaterials.getClanMaterialAmount;
import static org.elmedievo.medievoapi.Database.Getters.ClanMembersToList.getClanMembers;
import static org.elmedievo.medievoapi.Database.Getters.PlayerClan.getPlayerClan;
import static org.elmedievo.medievoapi.Database.Setters.AlfonsosToClan.addAlfonsosToClan;
import static org.elmedievo.medievoapi.Database.Setters.GoldToClan.addGoldToClan;
import static org.elmedievo.medievoapi.Util.Generic.Messages.*;
import static org.elmedievo.medievoapi.Util.Methods.Utility.PlayerIsOnline.playerIsOnline;

public class Withdraw {

    private static boolean amountIsValid(String amountInput) {
        try {
            Integer.parseInt(amountInput);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private static boolean materialIsValid(String materialInput) {
        try {
            Material material = Material.getMaterial(materialInput);
            return material != null;
        } catch (Exception exception) {
            return false;
        }
    }

    @SuppressWarnings("deprecation")
    public static void withdrawGoldFromClan(Player player, String materialInput, String amountInput) {
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        decimalFormat.setRoundingMode(RoundingMode.UP);
        String playerClan = getPlayerClan(player.getUniqueId());
        if (materialIsValid(materialInput)) {
            if (amountIsValid(amountInput)) {
                int amount = Integer.parseInt(amountInput);
                if (amount > 0) {
                    Material material = Material.getMaterial(materialInput);

                    double alfonsos = valueInMarket(material, false);
                    if (!Objects.requireNonNull(playerClan).equals("none")) {
                        int materialAmount = getClanMaterialAmount(playerClan, material.toString().toLowerCase());
                        if (materialAmount == 0) {
                            player.sendMessage(NOT_ENOUGH_GOLD);
                            return;
                        } else if (amount <= materialAmount) {
                            addAlfonsosToClan(playerClan, alfonsos * amount);
                            addGoldToClan(playerClan, material.toString().toLowerCase(), -amount);
                            List<String> clanMembers = getClanMembers(playerClan);
                            ItemStack gold = new ItemStack(material, amount);
                            player.getInventory().addItem(gold);
                            Objects.requireNonNull(clanMembers).forEach(member -> {
                                if (playerIsOnline(member)) {
                                    Player member_player = Bukkit.getServer().getPlayer(member);
                                    member_player.sendMessage(player.getDisplayName() + ChatColor.AQUA + " » " + WITHDRAW_COMPLETE + ChatColor.AQUA + " » " + ChatColor.WHITE + ChatColor.UNDERLINE + ChatColor.ITALIC + CURRENCY_SYMBOL + decimalFormat.format(alfonsos * amount) + " " + CURRENCY_NAME_PLURAL);
                                }
                            });
                        } else {
                            player.sendMessage(NOT_ENOUGH_GOLD);
                        }
                    } else {
                        player.sendMessage(NOT_IN_A_CLAN);
                    }
                } else {
                    player.sendMessage(INVALID_AMOUNT);
                }
            } else {
                player.sendMessage(INVALID_AMOUNT);
            }
        } else {
            player.sendMessage(INVALID_MATERIAL_TYPE);
        }
    }
}
