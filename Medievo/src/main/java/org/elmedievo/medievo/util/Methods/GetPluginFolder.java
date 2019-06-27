package org.elmedievo.medievo.util.Methods;

import org.elmedievo.medievo.Medievo;
import org.jetbrains.annotations.Contract;

import java.io.File;

public class GetPluginFolder {
    @Contract(pure = true)
    public static File getMedievoFolder() {
        return Medievo.instance.getDataFolder();
    }
}
