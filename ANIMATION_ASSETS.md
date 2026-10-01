# Independent illustrated camp animation

Assets produced with built-in image generation from the earlier family illustration:
* src/main/resources/images/hunter-individual-poses.png
* src/main/resources/images/hunter-fire-poses.png
* src/main/resources/images/hunter-camp-background.png (retained)

Final prompt set:
1. Preserve the same realistic antique engraved faces, hair and modest hide
   clothing. Separate the father, mother, child and girl into individual rows,
   three poses per person. Father rests/reaches/withdraws; mother lowers/raises/eats
   a berry; child reaches/eats; girl changes gaze and adjusts her twigs. Require
   transparent backgrounds, whole bodies, fixed anchors, no scenery/fire or other
   people in each cell, and no simple geometric figures.
2. Isolate the matching campfire, rocks, logs and roast into three horizontally
   arranged transparent frames. Preserve its base layout while flame tongues
   change shape. No people, scenery, labels or background.

The supplied individual atlas has nonuniform row heights. LivingCamp explicitly
uses inspected row boundaries and a consistent alpha-content union across each
person's three poses. Each person has a separate ImageView and its own standalone
Transition, duration and phase. The fire is a fifth independent actor. A lifecycle
Timeline propagates global play/pause/stop; it does not set individual frame times.
The previous combined-family atlas is unused and omitted from this archive.

These are illustrated artistic reconstructions rather than documented portraits.
