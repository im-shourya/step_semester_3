package encapsulation.assigment_problems;

public class AccessChecker {
    private static final String[] MODIFIERS = {"private", "default", "protected", "public"};

    /*
     * Java visibility rules for a field, by where the access happens:
     * - private   : only inside the same class.
     * - default   : same class or any class in the same package.
     * - protected : everything default allows, plus a subclass in another package, but only
     *               through a reference whose compile-time type is that subclass (own type),
     *               never through a reference declared as the parent type.
     * - public    : everywhere.
     */
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        boolean allowed;
        switch (fieldModifier) {
            case "private":
                allowed = accessorContext.equals("SAME_CLASS");
                break;
            case "default":
                allowed = isSameClassOrPackage(accessorContext);
                break;
            case "protected":
                allowed = isSameClassOrPackage(accessorContext)
                        || accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE");
                break;
            case "public":
                allowed = true;
                break;
            default:
                throw new IllegalArgumentException("Unknown modifier: " + fieldModifier);
        }
        validateContext(accessorContext);
        return allowed ? "ALLOWED" : "DENIED";
    }

    private static boolean isSameClassOrPackage(String accessorContext) {
        return accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE");
    }

    private static void validateContext(String accessorContext) {
        switch (accessorContext) {
            case "SAME_CLASS":
            case "SAME_PACKAGE":
            case "DIFFERENT_PACKAGE":
            case "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE":
            case "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE":
                return;
            default:
                throw new IllegalArgumentException("Unknown accessor context: " + accessorContext);
        }
    }

    public static String summarizeByModifier(String[][] attempts) {
        int[] allowed = new int[MODIFIERS.length];
        int[] denied = new int[MODIFIERS.length];

        for (String[] attempt : attempts) {
            int index = indexOfModifier(attempt[0]);
            if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED")) {
                allowed[index]++;
            } else {
                denied[index]++;
            }
        }

        StringBuilder summary = new StringBuilder();
        for (int i = 0; i < MODIFIERS.length; i++) {
            if (i > 0) {
                summary.append(" | ");
            }
            summary.append(MODIFIERS[i]).append(": ")
                    .append(allowed[i]).append(" allowed / ")
                    .append(denied[i]).append(" denied");
        }
        return summary.toString();
    }

    private static int indexOfModifier(String modifier) {
        for (int i = 0; i < MODIFIERS.length; i++) {
            if (MODIFIERS[i].equals(modifier)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Unknown modifier: " + modifier);
    }

    // "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE" -> "Subclass Different Package Own Type"
    public static String describeContext(String accessorContext) {
        if (accessorContext == null || accessorContext.trim().isEmpty()) {
            return "";
        }
        String[] words = accessorContext.trim().split("_+");
        StringBuilder sentence = new StringBuilder();
        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            if (sentence.length() > 0) {
                sentence.append(' ');
            }
            sentence.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1).toLowerCase());
        }
        return sentence.toString();
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 1 ---");
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeByModifier(attempts));

        System.out.println("--- Problem 2 ---");
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
        System.out.println(describeContext("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
    }
}
