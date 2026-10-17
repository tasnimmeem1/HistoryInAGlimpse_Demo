# Animation database changes

The group uses the existing shared Supabase project. **Cloning and running the
app does not require running these SQL files.** The current cloud rows already
contain scene metadata, map paths and sound paths.

- `mauryan-five-poses.sql`: the applied Mauryan five-pose update. Requires
  `five-background.png`, `five-actors.png`, `five-foreground.png` inside the
  private bucket's `mauryan-empire` folder. It preserves existing map/audio paths.
- `fix-first-five-audio.sql`: the applied filename correction for the original
  five eras. Changes only `settings.audio_path` to each folder's `ambiance.mp3`.

These files document changes for the owner. Do not rerun older seed scripts
while other people are changing animation settings. Media uploads and database
changes are separate operations. A database path must exactly match its object
name in Storage. The bucket remains private.

The shared table is `public.animation_scenes`, with `id`, `title`,
`bucket_name`, `background_path`, `characters_path`, `fire_path`, `settings`
(JSONB), and `created_at`. The server reads it through Supabase REST and retrieves
private PNG/audio objects through Supabase Storage. The desktop receives media
through the Java Spring Boot backend.
