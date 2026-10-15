package parcel_service.util;

public final class InputSanitizer {

    private InputSanitizer() {}

    public static String clean(String input) {
        if (input == null) return null;
        return input
            .replaceAll("(?is)<script.*?>.*?</script>", "")   // elimina scripts completos
            .replaceAll("(?s)<[^>]*>", "")                     // elimina etiquetas HTML
            .replaceAll("[\\p{Cntrl}&&[^\\r\\n\\t]]", "")      // elimina caracteres de control
            .replaceAll("[\"'`;\\\\]", "")                     // elimina caracteres peligrosos
            .replaceAll("\\s+", " ")                           // colapsa espacios
            .trim();
    }
}