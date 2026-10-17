# History in a Glimpse

Group 2 capstone: a JavaFX desktop history explorer with an interactive timeline,
era maps, animated daily-life scenes, optional spoken descriptions, and separate
ambient sound for each scene. The Java Spring Boot backend retrieves metadata
and private media from Supabase.

## Start here for group members

Use the `member1-timeline-maps-animation` branch for the current working version.

```bash
git clone --branch member1-timeline-maps-animation https://github.com/tasnimmeem1/HistoryInAGlimpse_Demo.git
cd HistoryInAGlimpse_Demo
```

1. Install **JDK 21 or newer** and open the root `pom.xml` in IntelliJ IDEA.
   Use the same JDK for the nested backend module. The desktop targets Java 17;
   the backend targets Java 21.
2. Add `animation-backend/pom.xml` as a Maven project if IntelliJ does not import
   it automatically. Reload Maven so JavaFX and Spring dependencies download.
3. Ask the project owner privately for backend access. In the **backend run
   configuration's Environment variables**, set `SUPABASE_SECRET_KEY` to the
   server-side project key. The Supabase URL is configured for the existing
   shared project; `SUPABASE_URL` can override it when needed.
4. Run `AnimationBackendApplication` in `animation-backend` first. It listens on
   `http://127.0.0.1:8080` by default.
5. Run `HistoryInAGlimpseApplication` from the desktop project, or run Maven's
   `javafx:run` goal from the root project. Enter the timeline and select an era.

The key belongs in the backend environment only. Do not paste it into Java,
properties files, the desktop run configuration, README, or GitHub. A local
`.env` file is **not automatically loaded** by this project. Use IntelliJ's
Environment variables field or your terminal environment.

For the existing shared Supabase project, **do not create another bucket or run
all SQL scripts again**. The shared data and media are already in the cloud.
Members can work on the timeline/UI while backend access is being arranged;
cloud scenes and sounds need a running configured backend.

### Terminal alternative (Windows PowerShell)

With `SUPABASE_SECRET_KEY` already set privately in that terminal's environment:

```powershell
# Terminal 1, from the repository root
.\mvnw.cmd -f animation-backend/pom.xml spring-boot:run

# Terminal 2, from the repository root
.\mvnw.cmd javafx:run
```

On macOS/Linux, use `./mvnw` instead. The backend secret is required only in
Terminal 1. To use a separately hosted backend later, set the desktop JVM option
`-Dhistory.animation.backend=https://your-backend-host` in its run configuration.

## Current work

- Interactive history timeline with era ribbons, compact cards, dates and maps.
- Stone Age and early farming scenes loaded from private Supabase media.
- Cloud era catalog covering the subsequent civilization batches.
- **Mauryan Empire five-pose test**: visible walking and hammering actions,
  turning couriers, fixed worker lower body, and a separate fixed foreground
  workbench for layered depth. Existing three-pose scenes remain supported.
- Optional description voice and independent per-era ambient audio.

Only the Mauryan scene has been changed to the new five-pose approach so far.
Other uploaded scenes remain available for later animation improvements.

## Project layout and collaboration

| Location | Purpose |
| --- | --- |
| `src/main/java/org/example/historyinaglimpse` | JavaFX UI, timeline, maps, scene playback |
| `src/main/resources/*.tsv` | History entries, timeline layout and era settings |
| `src/main/resources/chart.css` | Desktop styling |
| `animation-backend` | Spring Boot REST API and private cloud access |
| `database` | Documented database changes; not a startup requirement |
| `docs` | History entry guide, dates, map and sound notes |

The timeline/maps/animation work is maintained on the Member 1 branch. Other
members should create their own branches from this current branch and commit
their own work. Use pull requests to integrate changes. History content can be
extended through `EventCatalog` and the TSV files described in `docs`.
Keep new group features in their own classes/packages where practical so the
timeline and animation renderer remain easy to review.

## Cloud media and API

The shared database table is `public.animation_scenes`. It stores metadata and
object paths; image/audio bytes are in the **private** `animation-assets` bucket.
Each era has a separate folder. Its sound is usually `ambiance.mp3`, so the same
basename can represent different recordings in different era folders.

For Mauryan Empire, the current visual objects are:

```text
mauryan-empire/five-background.png
mauryan-empire/five-actors.png
mauryan-empire/five-foreground.png
```

Maps and sounds keep their existing configured paths. Do not rename an uploaded
object without updating the matching database path. New scene uploads and
animation-source artwork stay outside IntelliJ and GitHub. Some compact shared
thumbnail sheets, map data and fallback assets remain in resources because the
app still uses them for other history entries.

Useful checks with the backend running:

```text
http://localhost:8080/api/era-scenes/mauryan-empire
http://localhost:8080/api/era-scenes/mauryan-empire/background
http://localhost:8080/api/era-scenes/mauryan-empire/characters
http://localhost:8080/api/era-scenes/mauryan-empire/foreground
http://localhost:8080/api/era-scenes/mauryan-empire/map
http://localhost:8080/api/era-scenes/mauryan-empire/audio
```

Scene metadata should show `atlasColumns: 5` and `sourceImage: false` for the
Mauryan test. An image/audio error usually needs the precise configured path and
backend error checked. If an initial scene load fails, use Retry and inspect the
JavaFX console's `Historical scene ... failed:` line. No SQL reset is needed for
a temporary loading failure. Restart JavaFX after changing cloud assets to clear
its in-memory cache.

## Validation

The assembled desktop source is compiled as a Java module. Backend service
tests use mocked Supabase responses and include private foreground assets,
same-folder path validation, and separate MP3/WAV handling. The Mauryan renderer
was exercised against real included artwork through a local HTTP fixture:
all five poses were reached, travel direction changed, play/pause propagated,
and the bench and worker lower-body position stayed fixed. Live scene data
continues to depend on the shared Supabase uploads.

For normal project checks:

```powershell
.\mvnw.cmd test
.\mvnw.cmd -f animation-backend/pom.xml test
```

The backend context test uses a mock key and does not require the real secret.
The standalone `DesktopSmokeCheck` exercises the JavaFX UI separately.
