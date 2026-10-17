-- Run this in the Supabase SQL editor after uploading the two PNG files.
-- Existing RLS and private bucket settings stay in place.
INSERT INTO public.animation_scenes
(id, title, bucket_name, background_path, characters_path, fire_path, settings)
VALUES ('early-farming', 'Early farming in the Levant', 'animation-assets',
'early-farming/farming-background.png', 'early-farming/farming-actors.png', '',
$scene${
  "canvasWidth": 1000,
  "canvasHeight": 1000,
  "atlasWidth": 887,
  "atlasHeight": 1774,
  "atlasColumns": 3,
  "audio_path": "early-farming/farming-ambience.mp3",
  "actors": [
    {
      "name": "hoe-farmer",
      "rowTop": 0,
      "rowEnd": 336,
      "x": 45,
      "y": 425,
      "height": 350,
      "cycleSeconds": 9.8,
      "phaseSeconds": 0,
      "swayDegrees": 0.18
    },
    {
      "name": "harvester",
      "rowTop": 336,
      "rowEnd": 672,
      "x": 330,
      "y": 580,
      "height": 400,
      "cycleSeconds": 11.4,
      "phaseSeconds": 1.5,
      "swayDegrees": 0.22
    },
    {
      "name": "basket-carrier",
      "rowTop": 672,
      "rowEnd": 1073,
      "x": 725,
      "y": 420,
      "height": 310,
      "cycleSeconds": 14.2,
      "phaseSeconds": 2.7,
      "swayDegrees": 0.16
    },
    {
      "name": "grain-child",
      "rowTop": 1073,
      "rowEnd": 1291,
      "x": 620,
      "y": 590,
      "height": 155,
      "cycleSeconds": 8.7,
      "phaseSeconds": 3.1,
      "swayDegrees": 0.12
    },
    {
      "name": "goat",
      "rowTop": 1291,
      "rowEnd": 1550,
      "x": 680,
      "y": 730,
      "height": 220,
      "cycleSeconds": 12.8,
      "phaseSeconds": 1.9,
      "swayDegrees": 0.1
    },
    {
      "name": "kid",
      "rowTop": 1550,
      "rowEnd": 1774,
      "x": 810,
      "y": 815,
      "height": 140,
      "cycleSeconds": 10.6,
      "phaseSeconds": 4.3,
      "swayDegrees": 0.12
    }
  ]
}$scene$::jsonb)
ON CONFLICT (id) DO UPDATE SET
 title = EXCLUDED.title, bucket_name = EXCLUDED.bucket_name,
 background_path = EXCLUDED.background_path, characters_path = EXCLUDED.characters_path,
 fire_path = EXCLUDED.fire_path, settings = EXCLUDED.settings;
