package org.example.historyinaglimpse;
import java.util.List;
final class EventCatalog {
    private static final List<HistoricalEvent> BASE_EVENTS = List.of(
        new HistoricalEvent("Unification of Egypt", -3100, 1, "Around 3100 BCE, Egypt was unified into a single political entity. This marks the beginning of the Early Dynastic Period. The Narmer Palette records imagery associated with this transformation. The event starts the chart's sequence of Egyptian kingdoms.", "https://egymonuments.gov.eg/en/historical-periods/early-dynastic-period"),
        new HistoricalEvent("Alexander enters Persepolis", -330, 2, "In 330 BCE, Alexander reached Persepolis during his conquest of the Achaemenid Persian Empire. The palace complex was burned. This event marks the transition from Achaemenid rule to the world shaped by Alexander's campaigns and their successors.", "https://www.iranicaonline.org/articles/alexander-the-great-356-23-bc/"),
        new HistoricalEvent("Qin unifies China", -221, 4, "In 221 BCE, Qin conquered the remaining rival states and established China's first imperial dynasty. Although Qin rule lasted only about fifteen years, its centralized model influenced later Chinese history. The chart places this event before the Han dynasty.", "https://asia.si.edu/education/educator-resources/teaching-china-with-the-smithsonian/explore-by-dynasty/qin-dynasty/"),
        new HistoricalEvent("Beginning of Imperial Rome", -27, 3, "The imperial period of Rome is conventionally dated from 27 BCE. Augustus became the first Roman emperor. This begins the chart's western imperial Roman band, which extends to 476 CE. The eastern empire continued beyond that date.", "https://education.nationalgeographic.org/resource/imperial-rome/"),
        new HistoricalEvent("End of the Western Roman Empire", 476, 3, "The year 476 CE conventionally marks the end of the Western Roman Empire. It does not mark the end of the eastern empire. The chart therefore ends the western Roman band while keeping the eastern Roman chronology visible.", "https://education.nationalgeographic.org/resource/imperial-rome/"),
        new HistoricalEvent("The Hijrah", 622, 5, "In 622 CE, Prophet Muhammad and his followers migrated from Mecca to Medina. This migration, known as the Hijrah, became the epoch of the Islamic calendar. The event provides an important chronological marker in the chart's Islamic-world lane.", "https://en.wikisource.org/wiki/1911_Encyclop%C3%A6dia_Britannica/Hejira"),
        new HistoricalEvent("Genghis Khan proclaimed", 1206, 2, "In 1206, an assembly of Mongol chiefs elected Genghis Khan as supreme ruler of the Mongolian tribes. The unification provided the foundation for the expansion of the Mongol Empire across Asia and into Europe.", "https://www.iranicaonline.org/articles/iran-ii2-islamic-period-page-3/"),
        new HistoricalEvent("Fall of Constantinople", 1453, 3, "In 1453, Ottoman forces captured Constantinople. The conquest ended the Byzantine, or Eastern Roman, Empire and changed the city's place in the Ottoman world. On the chart, the eastern Roman band ends at this event.", "https://academic.oup.com/book/62441"),
        new HistoricalEvent("Beginning of Mughal rule", 1526, 6, "The Mughal dynasty began ruling in India in 1526. Its courts became renowned for wealth, culture and the arts. The chart's Mughal band extends to 1858 using the period given by The Metropolitan Museum of Art.", "https://www.metmuseum.org/exhibitions/listings/2001/jeweled-arts-of-mughal-india"),
        new HistoricalEvent("Declaration of Independence", 1776, 7, "On July 4, 1776, the Continental Congress adopted the Declaration of Independence. The document explained the colonies' decision to separate from British rule. Adoption on July 4 and the later signing of the engrossed copy were different steps.", "https://www.archives.gov/milestone-documents/declaration-of-independence"),
        new HistoricalEvent("French Revolution begins", 1789, 7, "In 1789, the French Revolution began. Unrest challenged the monarchy and the existing political order. Thomas Jefferson, then in Paris, witnessed the early events. The chart places this turning point beside the other late eighteenth-century Atlantic events.", "https://www.archives.gov/exhibits/eyewitness/assets/html/1.intro.html")
    );
    static final List<HistoricalEvent> EVENTS = loadEvents();
    private static List<HistoricalEvent> loadEvents() {
        var events = new java.util.ArrayList<>(BASE_EVENTS);
        try (var input = EventCatalog.class.getResourceAsStream("/additional-events.tsv")) {
            if (input == null) throw new IllegalStateException("Missing civilization entries");
            var reader = new java.io.BufferedReader(new java.io.InputStreamReader(input, java.nio.charset.StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] f = line.split("\t", -1);
                if (f.length != 7) throw new IllegalStateException("Invalid civilization entry");
                events.add(new HistoricalEvent(f[0], Integer.parseInt(f[1]), Integer.parseInt(f[2]),
                    f[4], f[5], f[3], "prehistory-vignettes.png", Integer.parseInt(f[6])));
            }
        } catch (java.io.IOException ex) { throw new IllegalStateException("Cannot load civilizations", ex); }
        try (var input = EventCatalog.class.getResourceAsStream("/expanded-events.tsv")) {
            if (input == null) throw new IllegalStateException("Missing expanded history entries");
            var reader = new java.io.BufferedReader(new java.io.InputStreamReader(input, java.nio.charset.StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] f = line.split("\t", -1);
                if (f.length != 8) throw new IllegalStateException("Invalid history entry");
                events.add(new HistoricalEvent(f[0], Integer.parseInt(f[1]), Integer.parseInt(f[2]),
                    f[4], f[5], f[3], f[6], Integer.parseInt(f[7])));
            }
        } catch (java.io.IOException ex) { throw new IllegalStateException("Cannot load history", ex); }
        return List.copyOf(events);
    }
    private EventCatalog() {}
}
