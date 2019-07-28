package org.elmedievo.medievoapi.Kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;

import static org.elmedievo.medievoapi.Util.Methods.Utility.GetPluginFolder.getAPIFolder;

public class DeliverKit {

    public static void giveKit(Player player, String kit) {
        try {
            deliverKit(player, kit);
        } catch (JDOMException | IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void deliverKit(Player player, String kit) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        Document readKitsXML = builder.build(new File(getAPIFolder() + "/kits.xml"));
        Element root = readKitsXML.getRootElement();

        for (Element kits : root.getChildren("kit")) {
            String kitName = kits.getAttributeValue("name");
            if (kitName.equalsIgnoreCase(kit)) {
                for (Element object : kits.getChildren()) {
                    String id = object.getAttributeValue("id").toUpperCase();           // Bukkit IDs Materials
                    String amount = object.getAttributeValue("amount");
                    player.getInventory().addItem(new ItemStack(Material.valueOf(id), Integer.valueOf(amount)));
                }
            }
        }
    }
}
