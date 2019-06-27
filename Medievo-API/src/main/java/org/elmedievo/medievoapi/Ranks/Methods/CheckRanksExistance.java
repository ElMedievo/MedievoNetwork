package org.elmedievo.medievoapi.Ranks.Methods;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.elmedievo.medievoapi.Util.Methods.Utility.GetPluginFolder.getAPIFolder;

public class CheckRanksExistance {
    public static boolean rankExists(String rank) {
        try {
            if (rankWasFoundInXML(rank)) {
                return true;
            }
        } catch (JDOMException | IOException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public static List<String> getRanksList() {
        try {
            return lookUpRanks();
        } catch (JDOMException | IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private static boolean rankWasFoundInXML(String inputRank) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        Document readRanksXML = builder.build(new File(getAPIFolder() + "/ranks.xml"));
        Element root = readRanksXML.getRootElement();

        for (Element rankInstance : root.getChildren("rank")) {
            String rankInstanceName = rankInstance.getAttributeValue("name");
            if (rankInstanceName.equals(inputRank)) {
                return true;
            }
        }
        return false;
    }

    private static List<String> lookUpRanks() throws JDOMException, IOException {
        List<String> ranksList = new ArrayList<>();
        SAXBuilder builder = new SAXBuilder();
        Document readRanksXML = builder.build(new File(getAPIFolder() + "/ranks.xml"));
        Element root = readRanksXML.getRootElement();

        for (Element rankInstance : root.getChildren("rank")) {
            String rankInstanceName = rankInstance.getAttributeValue("name");
            ranksList.add(rankInstanceName);
        }
        return ranksList;
    }
}
