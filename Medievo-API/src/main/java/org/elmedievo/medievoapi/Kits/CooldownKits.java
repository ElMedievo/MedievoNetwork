package org.elmedievo.medievoapi.Kits;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static org.elmedievo.medievoapi.Kits.DeliverKit.giveKit;
import static org.elmedievo.medievoapi.Util.Methods.Utility.GetPluginFolder.getAPIFolder;
import static org.elmedievo.medievoapi.Util.Methods.Utility.upperCaseWords.upperCaseWords;

public class CooldownKits {

    private static HashMap<String, Long> cooldowns = new HashMap<>();

    public static boolean CheckKitsCooldown(Player player, String kit) {
        try {
            if (kitHasCooldown(player, kit)) {
                return true;
            }
        } catch (JDOMException | IOException exception) {
            exception.getStackTrace();
        }
        return false;
    }

    private static boolean kitHasCooldown(Player player, String kit) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        Document readKitsXML = builder.build(new File(getAPIFolder() + "/kits.xml"));
        Element root = readKitsXML.getRootElement();

        for (Element kits : root.getChildren("kit")) {
            String kitName = kits.getAttributeValue("name");
            if (kitName.equalsIgnoreCase(kit)) {
                String CooldownTimeString = kits.getAttributeValue("cooldown");
                int CooldownTime = Integer.valueOf(CooldownTimeString);          // Seconds
                if(cooldowns.containsKey(String.valueOf(player)) && cooldowns.containsKey(kit)) {
                    long secondsLeft = ((cooldowns.get(kit)/1000)+ CooldownTime) - (System.currentTimeMillis()/1000);
                    if (secondsLeft > 0) {                                      // Cooling down
                        long hours = (secondsLeft / 3600);
                        long minutes = ((secondsLeft % 3600) / 60);
                        long seconds = (secondsLeft % 60);
                        if (hours <= 0 && minutes > 0) {
                            String timeString = String.format("%02d min:%02d sec", minutes, seconds);
                            player.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.RED + "You must wait " + ChatColor.YELLOW + timeString + ChatColor.RED + " to get again the " + ChatColor.YELLOW + upperCaseWords(kit) + ChatColor.RED + "kit." );
                        } if (minutes <= 0) {
                            String timeString = String.format("%02d seconds", seconds);
                            player.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.RED + "You must wait " + ChatColor.YELLOW + timeString + ChatColor.RED + " to get again the " + ChatColor.YELLOW + upperCaseWords(kit) + ChatColor.RED + "kit." );
                        } if (hours > 0) {
                            String timeString = String.format("%02d h:%02d min:%02d sec", hours, minutes, seconds);
                            player.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.RED + "You must wait " + ChatColor.YELLOW + timeString + ChatColor.RED + " to get again the " + ChatColor.YELLOW + upperCaseWords(kit) + ChatColor.RED + "kit." );
                        }
                        return true;
                    }
                }
            }
        }

        cooldowns.put(String.valueOf(player), System.currentTimeMillis());
        cooldowns.put(kit, System.currentTimeMillis());
        giveKit(player, kit);
        player.sendMessage(ChatColor.GREEN + "Kit " + ChatColor.BLUE + upperCaseWords(kit) + ChatColor.GREEN + "successfully delivered" );
        return false;
    }
}
