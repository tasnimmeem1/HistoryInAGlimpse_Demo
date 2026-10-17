-- Upload the five same-named folders to the ROOT of animation-assets first.

-- Keep the bucket private. Existing Stone Age and farming rows are untouched.

BEGIN;

INSERT INTO public.animation_scenes (id,title,bucket_name,background_path,characters_path,fire_path,settings)
VALUES ('sumerian-urban', 'Sumerian urban period', 'animation-assets', 'sumerian-urban/background.png', 'sumerian-urban/actors.png', '',
$scene${
  "canvasWidth": 1000,
  "canvasHeight": 667,
  "atlasWidth": 1536,
  "atlasHeight": 1024,
  "atlasColumns": 3,
  "audio_path": "sumerian-urban/ambience.wav",
  "actors": [
    {
      "name": "dock-worker",
      "x": 820,
      "y": 325,
      "height": 65,
      "cycleSeconds": 13,
      "phaseSeconds": 0,
      "travelX": 0,
      "bobY": 0,
      "swayDegrees": 0.12,
      "animatePoses": true,
      "rowTop": 668,
      "rowEnd": 1024
    },
    {
      "name": "reed-cargo",
      "x": 630,
      "y": 450,
      "height": 120,
      "cycleSeconds": 34,
      "phaseSeconds": 4,
      "travelX": -110,
      "bobY": 2,
      "swayDegrees": 0.5,
      "animatePoses": false,
      "rowTop": 425,
      "rowEnd": 668
    },
    {
      "name": "ceremonial-boat",
      "x": 45,
      "y": 305,
      "height": 300,
      "cycleSeconds": 42,
      "phaseSeconds": 0,
      "travelX": 160,
      "bobY": 3,
      "swayDegrees": 0.45,
      "animatePoses": false,
      "rowTop": 0,
      "rowEnd": 425
    }
  ],
  "water": {
    "x": 0,
    "y": 420,
    "width": 1000,
    "height": 247
  }
}$scene$::jsonb)
ON CONFLICT (id) DO UPDATE SET title=EXCLUDED.title,bucket_name=EXCLUDED.bucket_name,background_path=EXCLUDED.background_path,characters_path=EXCLUDED.characters_path,fire_path=EXCLUDED.fire_path,settings=EXCLUDED.settings;

INSERT INTO public.animation_scenes (id,title,bucket_name,background_path,characters_path,fire_path,settings)
VALUES ('egypt-old-kingdom', 'Egypt: Old Kingdom', 'animation-assets', 'egypt-old-kingdom/background.png', 'egypt-old-kingdom/actors.png', '',
$scene${
  "canvasWidth": 1000,
  "canvasHeight": 667,
  "atlasWidth": 1536,
  "atlasHeight": 1024,
  "atlasColumns": 3,
  "audio_path": "egypt-old-kingdom/ambience.wav",
  "actors": [
    {
      "name": "mason",
      "x": 745,
      "y": 435,
      "height": 170,
      "cycleSeconds": 11,
      "phaseSeconds": 2,
      "travelX": 0,
      "bobY": 0,
      "swayDegrees": 0.12,
      "animatePoses": true,
      "rowTop": 360,
      "rowEnd": 745
    },
    {
      "name": "sledge",
      "x": 385,
      "y": 515,
      "height": 115,
      "cycleSeconds": 32,
      "phaseSeconds": 0,
      "travelX": -35,
      "bobY": 0,
      "swayDegrees": 0,
      "animatePoses": false,
      "rowTop": 745,
      "rowEnd": 1024
    },
    {
      "name": "rope-worker",
      "x": 125,
      "y": 480,
      "height": 150,
      "cycleSeconds": 32,
      "phaseSeconds": 0,
      "travelX": -35,
      "bobY": 0,
      "swayDegrees": 0.1,
      "animatePoses": true,
      "rowTop": 0,
      "rowEnd": 360
    },
    {
      "name": "second-rope-worker",
      "x": 70,
      "y": 430,
      "height": 115,
      "cycleSeconds": 32,
      "phaseSeconds": 0,
      "travelX": -35,
      "bobY": 0,
      "swayDegrees": 0.08,
      "animatePoses": true,
      "rowTop": 0,
      "rowEnd": 360
    }
  ],
  "map_path": "egypt-old-kingdom/map.png"
}$scene$::jsonb)
ON CONFLICT (id) DO UPDATE SET title=EXCLUDED.title,bucket_name=EXCLUDED.bucket_name,background_path=EXCLUDED.background_path,characters_path=EXCLUDED.characters_path,fire_path=EXCLUDED.fire_path,settings=EXCLUDED.settings;

INSERT INTO public.animation_scenes (id,title,bucket_name,background_path,characters_path,fire_path,settings)
VALUES ('indus-valley', 'Indus Valley civilization', 'animation-assets', 'indus-valley/background.png', 'indus-valley/actors.png', '',
$scene${
  "canvasWidth": 1000,
  "canvasHeight": 1000,
  "atlasWidth": 1536,
  "atlasHeight": 1024,
  "atlasColumns": 3,
  "audio_path": "indus-valley/ambience.wav",
  "actors": [
    {
      "name": "basket-carrier",
      "x": 550,
      "y": 330,
      "height": 130,
      "cycleSeconds": 16,
      "phaseSeconds": 2,
      "travelX": 15,
      "bobY": 0,
      "swayDegrees": 0.12,
      "animatePoses": true,
      "rowTop": 350,
      "rowEnd": 706
    },
    {
      "name": "pot-worker",
      "x": 335,
      "y": 535,
      "height": 290,
      "cycleSeconds": 14,
      "phaseSeconds": 0,
      "travelX": 0,
      "bobY": 0,
      "swayDegrees": 0.12,
      "animatePoses": true,
      "rowTop": 0,
      "rowEnd": 350
    },
    {
      "name": "oxen",
      "x": 610,
      "y": 450,
      "height": 125,
      "cycleSeconds": 18,
      "phaseSeconds": 3,
      "travelX": 0,
      "bobY": 0,
      "swayDegrees": 0.06,
      "animatePoses": true,
      "rowTop": 706,
      "rowEnd": 1024
    }
  ],
  "map_path": "indus-valley/map.png"
}$scene$::jsonb)
ON CONFLICT (id) DO UPDATE SET title=EXCLUDED.title,bucket_name=EXCLUDED.bucket_name,background_path=EXCLUDED.background_path,characters_path=EXCLUDED.characters_path,fire_path=EXCLUDED.fire_path,settings=EXCLUDED.settings;

INSERT INTO public.animation_scenes (id,title,bucket_name,background_path,characters_path,fire_path,settings)
VALUES ('akkadian-empire', 'Akkadian Empire', 'animation-assets', 'akkadian-empire/background.png', 'akkadian-empire/actors.png', '',
$scene${
  "canvasWidth": 1000,
  "canvasHeight": 667,
  "atlasWidth": 1536,
  "atlasHeight": 1024,
  "atlasColumns": 3,
  "audio_path": "akkadian-empire/ambience.wav",
  "actors": [
    {
      "name": "porter",
      "x": 150,
      "y": 350,
      "height": 115,
      "cycleSeconds": 18,
      "phaseSeconds": 1,
      "travelX": 35,
      "bobY": 0,
      "swayDegrees": 0.12,
      "animatePoses": true,
      "rowTop": 330,
      "rowEnd": 706
    },
    {
      "name": "scribe",
      "x": 680,
      "y": 385,
      "height": 80,
      "cycleSeconds": 12,
      "phaseSeconds": 2,
      "travelX": 0,
      "bobY": 0,
      "swayDegrees": 0.12,
      "animatePoses": true,
      "rowTop": 706,
      "rowEnd": 1024
    },
    {
      "name": "cargo-boat",
      "x": 260,
      "y": 480,
      "height": 150,
      "cycleSeconds": 44,
      "phaseSeconds": 0,
      "travelX": 150,
      "bobY": 2.5,
      "swayDegrees": 0.4,
      "animatePoses": false,
      "rowTop": 0,
      "rowEnd": 330
    }
  ],
  "map_path": "akkadian-empire/map.png",
  "water": {
    "x": 0,
    "y": 505,
    "width": 770,
    "height": 162
  }
}$scene$::jsonb)
ON CONFLICT (id) DO UPDATE SET title=EXCLUDED.title,bucket_name=EXCLUDED.bucket_name,background_path=EXCLUDED.background_path,characters_path=EXCLUDED.characters_path,fire_path=EXCLUDED.fire_path,settings=EXCLUDED.settings;

INSERT INTO public.animation_scenes (id,title,bucket_name,background_path,characters_path,fire_path,settings)
VALUES ('egypt-middle-kingdom', 'Egypt: Middle Kingdom', 'animation-assets', 'egypt-middle-kingdom/background.png', 'egypt-middle-kingdom/actors.png', '',
$scene${
  "canvasWidth": 1000,
  "canvasHeight": 667,
  "atlasWidth": 1536,
  "atlasHeight": 1024,
  "atlasColumns": 3,
  "audio_path": "egypt-middle-kingdom/ambience.wav",
  "actors": [
    {
      "name": "basket-worker",
      "x": 230,
      "y": 465,
      "height": 140,
      "cycleSeconds": 20,
      "phaseSeconds": 0,
      "travelX": 55,
      "bobY": 0,
      "swayDegrees": 0.12,
      "animatePoses": true,
      "rowTop": 0,
      "rowEnd": 347
    },
    {
      "name": "mason",
      "x": 650,
      "y": 485,
      "height": 120,
      "cycleSeconds": 12,
      "phaseSeconds": 2,
      "travelX": 0,
      "bobY": 0,
      "swayDegrees": 0.12,
      "animatePoses": true,
      "rowTop": 347,
      "rowEnd": 641
    },
    {
      "name": "administrator",
      "x": 470,
      "y": 435,
      "height": 165,
      "cycleSeconds": 17,
      "phaseSeconds": 3,
      "travelX": 0,
      "bobY": 0,
      "swayDegrees": 0.12,
      "animatePoses": true,
      "rowTop": 641,
      "rowEnd": 1024
    }
  ],
  "map_path": "egypt-middle-kingdom/map.png"
}$scene$::jsonb)
ON CONFLICT (id) DO UPDATE SET title=EXCLUDED.title,bucket_name=EXCLUDED.bucket_name,background_path=EXCLUDED.background_path,characters_path=EXCLUDED.characters_path,fire_path=EXCLUDED.fire_path,settings=EXCLUDED.settings;

COMMIT;
