package org.elmedievo.medievoapi.Ranks;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.elmedievo.medievoapi.Util.Generic.Messages.RANKS_CANNOT_CREATE;
import static org.elmedievo.medievoapi.Util.Generic.Messages.RANKS_FILE_FOUND;
import static org.elmedievo.medievoapi.Util.Generic.Messages.RANKS_FILE_NOT_FOUND;
import static org.elmedievo.medievoapi.Util.Methods.Console.consoleAlert;
import static org.elmedievo.medievoapi.Util.Methods.Utility.GetPluginFolder.getAPIFolder;

public class CreateRanksXML {

    public static void createRanksXMLFile() {
        try {
            if (!new File(getAPIFolder() + "/ranks.xml").exists()) {
                consoleAlert(RANKS_FILE_NOT_FOUND);
                generateRanksXML();
            } else {
                consoleAlert(RANKS_FILE_FOUND);
            }
        } catch (IOException exception) {
            consoleAlert(RANKS_CANNOT_CREATE);
            exception.printStackTrace();
        }
    }

    private static void generateRanksXML() throws IOException {
        Document ranksXMLDocument = new Document();

        Element ranks = new Element("ranks");
        ranksXMLDocument.setRootElement(ranks);

        Element userRank = new Element("rank");
        userRank.setAttribute("name", "Default");
        userRank.setAttribute("flair", "$e");
        userRank.setAttribute("priority", "1000");

        Element adminRank = new Element("rank");
        Element adminPerms = new Element("permission");
        adminRank.setAttribute("name", "Administrator");
        adminRank.setAttribute("staff", "true");
        adminRank.setAttribute("flair", "$6❖");
        adminRank.setAttribute("priority", "900");
        adminRank.addContent(adminPerms);
        adminPerms.setText("minecraft.command.op");

        ranks.addContent(userRank);
        ranks.addContent(adminRank);

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(ranksXMLDocument, new FileOutputStream(getAPIFolder() + "/ranks.xml"));
    }
}
