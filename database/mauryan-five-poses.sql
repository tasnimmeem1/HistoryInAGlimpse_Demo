-- Changes only the Mauryan test scene. Existing map/audio paths are preserved.
BEGIN;
INSERT INTO public.animation_scenes (id,title,bucket_name,background_path,characters_path,fire_path,settings)
VALUES ('mauryan-empire','Mauryan Empire','animation-assets','mauryan-empire/five-background.png','mauryan-empire/five-actors.png','',
$five${
  "canvasWidth": 1536,
  "canvasHeight": 1024,
  "atlasWidth": 1619,
  "atlasHeight": 971,
  "atlasColumns": 5,
  "sourceImage": false,
  "foreground_path": "mauryan-empire/five-foreground.png",
  "foreground": {
    "x": 1000,
    "y": 650,
    "width": 420,
    "height": 280
  },
  "actors": [
    {
      "name": "distant-courier",
      "rowTop": 0,
      "rowEnd": 460,
      "x": 650,
      "y": 430,
      "height": 220,
      "cycleSeconds": 14,
      "poseCycleSeconds": 1.05,
      "phaseSeconds": 0.45,
      "travelX": 170,
      "bobY": 0,
      "swayDegrees": 0,
      "animatePoses": true,
      "faceTravel": true
    },
    {
      "name": "courier",
      "rowTop": 0,
      "rowEnd": 460,
      "x": 150,
      "y": 520,
      "height": 360,
      "cycleSeconds": 18,
      "poseCycleSeconds": 0.95,
      "phaseSeconds": 0,
      "travelX": 480,
      "bobY": 0,
      "swayDegrees": 0,
      "animatePoses": true,
      "faceTravel": true
    },
    {
      "name": "metalworker",
      "rowTop": 460,
      "rowEnd": 971,
      "x": 1130,
      "y": 465,
      "height": 400,
      "cycleSeconds": 2.1,
      "poseCycleSeconds": 2.1,
      "phaseSeconds": 0,
      "travelX": 0,
      "bobY": 0,
      "swayDegrees": 0,
      "animatePoses": true,
      "faceTravel": false,
      "motionRegions": [
        {
          "x": 0,
          "y": 0,
          "width": 1,
          "height": 0.65
        }
      ]
    }
  ],
  "map_path": "mauryan-empire/map.png",
  "audio_path": "mauryan-empire/ambiance.mp3"
}$five$::jsonb)
ON CONFLICT (id) DO UPDATE SET background_path=EXCLUDED.background_path,
characters_path=EXCLUDED.characters_path,
settings=(COALESCE(animation_scenes.settings,'{}'::jsonb)-ARRAY['sourceMotion','water','atmosphere'])
 || (EXCLUDED.settings-'map_path'-'audio_path');
COMMIT;
