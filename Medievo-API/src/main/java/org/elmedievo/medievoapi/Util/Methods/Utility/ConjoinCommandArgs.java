package org.elmedievo.medievoapi.Util.Methods.Utility;

public class ConjoinCommandArgs {
    public static String buildMessageFromCommandArgs(String[] args, int startAtArgument) {
        StringBuilder builder = new StringBuilder();
        for (int i = startAtArgument; i < args.length; i++) {
            builder.append(args[i]).append(" ");
        }
        return builder.toString();
    }
}
