package org.elmedievo.medievoapi.Util.Methods.Utility;

import org.elmedievo.medievoapi.MedievoAPI;

import java.io.File;

public class GetPluginFolder {
    public static File getAPIFolder() {
        return MedievoAPI.instance.getDataFolder();
    }
}
