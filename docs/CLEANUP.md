# Repair and cleanup

- Applied the MP3 patch to the four active desktop/backend files in their correct packages.
- New era audio accepts your uploaded ambience.mp3 and retains WAV-only eras. Stone Age and farming routes are preserved.
- Removed the unused org.example.adamschart package, Hello/FXML template resources and controller, and unused HistoryDiorama class. Removed the unused FXML module/dependency.
- Removed local hunter-camp-background.png, hunter-individual-poses.png, hunter-fire-poses.png, camp.wav and unused nature.wav. Stone Age images now load through HTTP, so the local copies are redundant.
- Removed archived repository/IDE ZIPs, local IDE configuration, and redundant scene JSON snapshots. Build output is not shipped.
- Kept setup SQL under database for repeatable deployment. No live database or bucket was modified.
- Kept local resources and TSV data still referenced by the timeline and other scenes. Shared illustration sheets cannot be deleted just because some cells are now animated from cloud assets.
- Kept project wrappers and Windows/Linux launch scripts. Start the desktop with org.example.historyinaglimpse.Launcher.

Validation: desktop Maven package succeeded; Spring Boot 4.1.1 backend tests passed (15 tests). Native JavaFX cloud-loading checks use a local mock server with the real scene assets because live Supabase credentials are unavailable. Audible playback and Windows narration require checking on the user's computer.

Native result: all seven cloud scenes loaded their expected layers through HTTP (Stone Age 5, farming 6, Sumerian 3, Old Kingdom 4, Indus 3, Akkadian 3, Middle Kingdom 3); play/pause worked; retained illustration resources, all 93 entries, splash and timeline navigation passed.
