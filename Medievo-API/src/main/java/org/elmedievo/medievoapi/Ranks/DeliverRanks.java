package org.elmedievo.medievoapi.Ranks;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.elmedievo.medievoapi.MedievoAPI;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.getRanksYML;
import static org.elmedievo.medievoapi.Util.Methods.Utility.GetPluginFolder.getAPIFolder;

public class DeliverRanks {
    public static void deliverRanks(Player player) {
        try {
            getRanksForDelivery(player);
        } catch (JDOMException | IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void getRanksForDelivery(Player player) throws JDOMException, IOException {
        String player_name = player.getName();
        String player_uuid = player.getUniqueId().toString();

        List<String> ranksList = getRanksYML().getStringList("players." + player_uuid + ".ranks");
        String[] flairsByPriorityArray = new String[100000];
        StringBuilder builtFlair = new StringBuilder();

        SAXBuilder builder = new SAXBuilder();
        Document readRanksXML = builder.build(new File(getAPIFolder() + "/ranks.xml"));
        Element root = readRanksXML.getRootElement();

        for (Element rankInXML : root.getChildren("rank")) {
            String rankInXMLName = rankInXML.getAttributeValue("name");
            String uncoloredFlair = rankInXML.getAttributeValue("flair");
            int priority = Integer.parseInt(rankInXML.getAttributeValue("priority"));
            if (ranksList.contains(rankInXMLName)) {
                flairsByPriorityArray[priority] = uncoloredFlair;
                for (Element permissions : rankInXML.getChildren()) {
                    String permission = permissions.getText();
                    player.addAttachment(MedievoAPI.instance, permission, true);
                }
            }
        }

        for (String flairInstance : flairsByPriorityArray) {
            if (flairInstance != null) {
                String coloredFlair = (ChatColor.translateAlternateColorCodes ('$', flairInstance));
                builtFlair.append(coloredFlair);
            }
        }

        String finalFlair = builtFlair.toString();
        player.setDisplayName(finalFlair + player_name);
        player.setPlayerListName(finalFlair + player_name);
    }
}
