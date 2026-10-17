package org.example.historyinaglimpse;

/** Stable scene IDs; existing catalog indices and timeline links remain unchanged. */
final class CloudEraCatalog {
    static String sceneId(HistoricalEvent event) {
        return switch (event.title()) {
            case "Sumerian urban period", "Sumerian cities" -> "sumerian-urban";
            case "Egypt: Old Kingdom" -> "egypt-old-kingdom";
            case "Mature Indus period", "Indus Valley civilization" -> "indus-valley";
            case "Akkadian Empire" -> "akkadian-empire";
            case "Egypt: Middle Kingdom" -> "egypt-middle-kingdom";
            case "Hittite kingdom and empire" -> "hittite-kingdom";
            case "Shang bronze technology" -> "shang-dynasty";
            case "Egypt: New Kingdom" -> "egypt-new-kingdom";
            case "Phoenician seafaring" -> "phoenician-network";
            case "Zhou dynasty" -> "zhou-dynasty";
            case "Neo-Assyrian cities" -> "neo-assyrian-empire";
            case "Archaic Greek communities" -> "archaic-greece";
            case "Neo-Babylonian period" -> "neo-babylonian-period";
            case "Achaemenid Persian Empire" -> "achaemenid-persia";
            case "Classical Greece" -> "classical-greece";
            case "Mauryan Empire" -> "mauryan-empire";
            case "Parthian Empire" -> "parthian-empire";
            case "Qin dynasty" -> "qin-dynasty";
            case "Han dynasty" -> "han-dynasty";
            case "Western Imperial Rome" -> "western-imperial-rome";
            case "Aksum and Red Sea trade" -> "aksumite-empire";
            case "Sasanian Empire" -> "sasanian-empire";
            case "Classic Maya period" -> "classic-maya";
            case "Eastern Roman Empire" -> "eastern-roman-empire";
            case "Ghana / Wagadu Empire" -> "ghana-wagadu";
            case "Gupta-era craftsmanship" -> "gupta-empire";
            default -> "";
        };
    }
    static String sumerianHistory() {
        return "Sumerian cities developed in southern Mesopotamia. Farming, canals, craft production and river trade supported urban communities, while institutions kept records of goods and labor.\n\n"
            + "Early writing, c. 3200 BCE: records from late-fourth-millennium Mesopotamia began with signs used for administration. Cuneiform developed over time and later recorded several languages.\n\n"
            + "Later Babylonian history, c. 1750 BCE: Hammurabi, king of Babylon, had legal judgments inscribed on a stone monument. This belongs to a later period and was not a law code of the third-millennium Sumerian cities.";
    }
    private CloudEraCatalog() {}
}
