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
import static org.elmedievo.medievoapi.Database.Getters.ClanMembersToList.getClanMembers;
import static org.elmedievo.medievoapi.Database.Getters.PlayerClan.getPlayerClan;
import static org.elmedievo.medievoapi.Database.Setters.AlfonsosToClan.addAlfonsosToClan;
import static org.elmedievo.medievoapi.Database.Setters.GoldToClan.addGoldToClan;
import static org.elmedievo.medievoapi.Util.Generic.Messages.CANNOT_DEPOSIT;
import static org.elmedievo.medievoapi.Util.Generic.Messages.DEPOSIT_SUCCESS;
import static org.elmedievo.medievoapi.Util.Generic.Messages.NOT_IN_A_CLAN;
import static org.elmedievo.medievoapi.Util.Methods.Utility.PlayerIsOnline.playerIsOnline;

public class Deposit {
    @SuppressWarnings("deprecation")
    public static void depositGoldIntoClan(Player player) {
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        decimalFormat.setRoundingMode(RoundingMode.UP);
        String playerClan = getPlayerClan(player.getUniqueId());
        if (!Objects.requireNonNull(playerClan).equalsIgnoreCase("none")) {
            Material materialInHand = player.getInventory().getItemInMainHand().getType();
            String material = materialInHand.toString().toLowerCase();
            int materialAmount = player.getInventory().getItemInMainHand().getAmount();
            float alfonsos = valueInMarket(materialInHand, true) * materialAmount;
            if (alfonsos == 0) {
                player.sendMessage(CANNOT_DEPOSIT);
            } else {
                player.setItemInHand(new ItemStack(Material.AIR));
                addAlfonsosToClan(playerClan, alfonsos);
                addGoldToClan(playerClan, material, materialAmount);
                String clan = getPlayerClan(player.getUniqueId());
                List<String> clanMembers = getClanMembers(clan);
                Objects.requireNonNull(clanMembers).forEach(member -> {
                    if (playerIsOnline(member)) {
                        Player member_player = Bukkit.getServer().getPlayer(member);
                        member_player.sendMessage(player.getDisplayName()  + ChatColor.AQUA + " » " + DEPOSIT_SUCCESS + ChatColor.AQUA + " » " + ChatColor.WHITE + ChatColor.UNDERLINE + ChatColor.ITALIC + CURRENCY_SYMBOL + decimalFormat.format(alfonsos) + " " + CURRENCY_NAME_PLURAL);
                    }
                });
            }
        } else {
            player.sendMessage(NOT_IN_A_CLAN);
        }
    }
}
