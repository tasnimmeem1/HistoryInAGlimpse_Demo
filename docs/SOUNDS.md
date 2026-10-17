# Scene sound design

The packaged PCM WAV loops are original generated sound designs for this demo.
Market, harbor and crowd loops combine overlapping FLite voices (awb, rms, slt),
varied pitch/rate, footsteps and distant crowd texture. The harbor adds water and
gull-like calls. Nature uses birds and wind; the optional garden preset adds
childlike synthesized laughter. Workshop, council, horse-travel and quiet presets
use matching generated textures. These are stylized soundscapes, not authentic
recordings or reconstructions of ancient languages. English chatter is used for
this prototype. No external audio service or runtime FFmpeg install is needed.

All loops: 20 seconds, mono, 22050 Hz, 16-bit PCM. Runtime playback uses Java Sound.
The descriptive Windows voice remains optional and separate from scene ambience.

## Silence rule
Text-only date plates, the stone-tools still life and the publishing still life
have no ambient playback. Scene sounds is disabled for them. Optional descriptive
narration remains selectable. Quiet/garden presets are unused by current entries.

## Hunter-gatherer camp

The former synthesized camp.wav has been removed. Stone Age playback now uses
`animation-assets/stone-age/stone-age-birds.mp3` in private Supabase Storage,
served by the backend and played with JavaFX MediaPlayer. Other scenes still use
bundled Java Sound WAV clips.

Recording title: add the title of the MP3 you downloaded.
Creator: add the creator name.
Source URL: add the exact recording page.
License: add the license shown on that recording page.
