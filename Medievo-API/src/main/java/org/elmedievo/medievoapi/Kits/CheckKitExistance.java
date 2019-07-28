package org.elmedievo.medievoapi.Kits;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import static org.elmedievo.medievoapi.Util.Methods.Utility.GetPluginFolder.getAPIFolder;

public class CheckKitExistance {

    public static boolean kitExists(String kit) {
        try {
            if (kitWasFoundInXML(kit)) {
                return true;
            }
        } catch (JDOMException | IOException exception) {
            exception.getStackTrace();
        }
        return false;
    }

    public static List<String> getKitsList() {
        try {
            return lookUpKits();
        } catch (JDOMException | IOException exception) {
            exception.getStackTrace();
        }
        return null;
    }

    private static boolean kitWasFoundInXML(String inputKit) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        Document readKitsXML = builder.build(new File(getAPIFolder() + "/kits.xml"));
        Element root = readKitsXML.getRootElement();

        for (Element kitInstance : root.getChildren("kit")) {
            String kitInstanceName = kitInstance.getAttributeValue("name");
            if (kitInstanceName.equalsIgnoreCase(inputKit)) {
                return true;
            }
        }
        return false;
    }

    private static List<String> lookUpKits() throws JDOMException, IOException {
        List<String> kitsList = new ArrayList<>();
        SAXBuilder builder = new SAXBuilder();
        Document readKitsXML = builder.build(new File(getAPIFolder() + "/kits.xml"));
        Element root = readKitsXML.getRootElement();

        for (Element kitInstance : root.getChildren("kit")) {
            String kitInstanceName = kitInstance.getAttributeValue("name");
            kitsList.add(kitInstanceName);
        }
        return kitsList;
    }
}
