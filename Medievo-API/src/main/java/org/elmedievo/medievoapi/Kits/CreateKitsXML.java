package org.elmedievo.medievoapi.Kits;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.elmedievo.medievoapi.Util.Methods.Utility.GetPluginFolder.getAPIFolder;
import static org.elmedievo.medievoapi.Util.Generic.Messages.KITS_FILE_FOUND;
import static org.elmedievo.medievoapi.Util.Generic.Messages.KITS_FILE_NOT_FOUND;
import static org.elmedievo.medievoapi.Util.Generic.Messages.KITS_CANNOT_CREATE;
import static org.elmedievo.medievoapi.Util.Methods.Console.consoleAlert;

public class CreateKitsXML {

    public static void createKitsXMLFile() {
        try {
            if (!new File(getAPIFolder() + "/kits.xml").exists()) {
                consoleAlert(KITS_FILE_NOT_FOUND);
                generateKitsFile();
            } else {
                consoleAlert(KITS_FILE_FOUND);
            }
        } catch (IOException exception) {
            consoleAlert(KITS_CANNOT_CREATE);
            exception.printStackTrace();
        }
    }

    private static void generateKitsFile() throws IOException {
        Document kitsXMLDocument = new Document();

        Element kits = new Element("kits");
        kitsXMLDocument.setRootElement(kits);

        Element starterKit = new Element("kit");
        starterKit.setAttribute("name", "Starter");

        Element objects = new Element("object");
        objects.setAttribute("id", "minecraft:bread");
        objects.setAttribute("amount", "5");

        starterKit.addContent(objects);

        kits.addContent(starterKit);

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(kitsXMLDocument, new FileOutputStream(getAPIFolder() + "/kits.xml"));

    }
}
