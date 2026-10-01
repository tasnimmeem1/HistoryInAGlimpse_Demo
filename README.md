# History in a Glimpse

Native JavaFX history demo. Open this folder as a Maven project in IntelliJ,
select JDK 17 or newer, reload Maven and run org.example.historyinaglimpse.Launcher.
Alternatively run mvnw.cmd javafx:run on Windows.

The horizontally scrollable chart contains 62 entries in history and archaeology.
The variable time scale is marked on the chart. Biblical genealogy and mythology
are excluded. The splash screen credits Sebastian C. Adams and his Synchronological Chart
of Universal History as the visual inspiration. Event dates, descriptions and references are included in the story screens.

## Illustrated character animation

Click Stone Age life to open the hunter-gatherer family animation. The mother and
child raise food to their mouths, lower their hands and share food. The standing
girl changes her gaze and arm position. Each person has an individual ImageView and an independent pose transition.
The father reaches toward the roast, the mother eats berries, the child reaches
for food and eats, and the girl turns her head and adjusts her arms. The fire
has its own faster flame sequence. The fixed background fills the animation
pane. This is an illustrated pose animation, not a realistic generated video. The camp has its own birds-and-fire ambience, without
footsteps or crowd chatter. The Narration toggle button remains off by default.

The Phoenician seafaring story also has a layered illustrated animation: a merchant
walks along the quay and a separate boat sails and rocks on the water.

Animations open first and start automatically. Era map is the second tab;
selecting it pauses playback. Returning resumes it. Playback starts automatically; returning to the chart stops it. The Illustration tab and manual Play/Pause controls have been removed.
The simple geometric figure scenes from the previous version have been removed.
Other stories currently retain their still artwork; they have not yet received
this illustrated character treatment. Stone tools, publishing still life and
text-only date plates remain silent.

## Maps and sound

All entries have sourced regional geographic context maps. Persia and the long
Roman-period entry also have explicitly dated territorial snapshots. Other maps
are geographic locators, not historical territorial reconstructions. See MAPS.md
for source dates, licenses and limitations. Sound design is documented in SOUNDS.md.
Windows descriptive narration uses the installed System.Speech voice through
PowerShell. Ambient loops use Java Sound. No API key, Python, HTML or web app is
needed by the application.

## Verification

Delivered Java source compiled against JavaFX 21.0.6 with JDK 17. Headless JavaFX
checks verify animation opens first, starts automatically, changes rendered pixels,
pauses for the map and resumes when returning. Independent-family preview frames were
captured from the running JavaFX application. Audible device output and Windows
speech still require a local test. A separate Independent_Family_Preview.mp4 accompanies this project download.

## Inspiration

https://www.davidrumsey.com/blog/2012/3/28/timeline-maps

Individual playback was verified in a running headless JavaFX application:
pausing only the father left the child moving. All five actors have distinct
cycle durations and phase offsets. Map navigation pauses all five and resumes
them on return. The app remains JavaFX; no Spring Boot backend was added.

The app opens on the splash screen. Select Explore history (or press Enter) to
open the chart. The window title and chart toolbar use History in a Glimpse.
The main application class is HistoryInAGlimpseApplication; Launcher still works.

Project name, Maven artifact, Java package and module are now HistoryInAGlimpse
(org.example.historyinaglimpse). The Narration button toggles optional speech;
only Animation/Scene and Era map tabs are shown.
