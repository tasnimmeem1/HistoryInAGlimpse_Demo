-- Run only if these shared rows still reference older WAV filenames.
-- Changes only audio_path; each folder needs its own ambiance.mp3.
UPDATE public.animation_scenes
SET settings = jsonb_set(COALESCE(settings, '{}'::jsonb), '{audio_path}',
    to_jsonb(id || '/ambiance.mp3'), true)
WHERE id IN ('sumerian-urban', 'egypt-old-kingdom', 'indus-valley',
             'akkadian-empire', 'egypt-middle-kingdom')
RETURNING id, settings->>'audio_path' AS audio_path;
