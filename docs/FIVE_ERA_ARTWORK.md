# Artwork and historical references

Five backgrounds and five transparent actor sheets were created with the built-in image generation tool, using the user's reference images and the existing Indus illustration. These are artistic reconstructions, not photographs or exact archaeological plans. Original generated files were copied without alpha manipulation into Supabase Storage <era>/background.png and actors.png.

Backgrounds were generated from the user references: Sumerian reed-boat harbor, Giza construction scene, original Indus street panel, a river-palace composition adjusted to Akkadian material, and a low Middle Kingdom temple court beneath Theban cliffs.

Actor prompt set: strict three-column, three-row transparent sprite sheet; same subject/camera/size in each row; modest pose changes; isolated objects; no background, no lettering or grid lines. Subjects:
- Sumerian: ceremonial reed boat with canopy and passengers; smaller cargo boat with paddlers; dock worker with basket.
- Old Kingdom: rope-pulling worker; kneeling mason; limestone block on wooden sledge.
- Indus: cream/red draped pot worker; basket carrier; pair of zebu oxen, matching the original panel's painted style.
- Akkadian: simple canopied wooden cargo boat; basket porter; tablet scribe. No blue glazed Babylonian walls.
- Middle Kingdom: basket worker; kneeling mason; scroll-holding administrator in linen.

Actual row boundaries were inspected after generation and are stored in the database setup script. They are deliberately not assumed to divide the image into equal thirds. All frames of a row use a shared alpha content box to reduce crop jitter. Boats and the sledge hold their base pose while their complete layer moves; people use gentle pose crossfades. Backgrounds stay fixed.

## Historical adjustments

The Akkadian reference resembles later Babylonian hanging-garden imagery. The new background retains the river/terrace composition but removes blue glazed animal walls and spectacular waterfalls. The garden-palace is illustrative, not a claim that the Hanging Gardens belonged to the Akkadian Empire.
Akkadian reference: https://www.metmuseum.org/essays/the-akkadian-period-ca-2350-2150-b-c

The supplied Middle Kingdom photo shows Hatshepsut's later New Kingdom temple. The scene instead uses a cautious low colonnaded temple reconstruction inspired by Middle Kingdom Mentuhotep II at Deir el-Bahri. It is not an exact reconstruction of the excavated plan.
- https://egymonuments.gov.eg/en/monuments/hatshepsut-temple/
- https://www.metmuseum.org/art/collection/search/544208
- https://www.metmuseum.org/art/collection/search/545292

Early writing and Hammurabi appear together as a chronological Mesopotamian history section, with the Babylonian material explicitly later:
- https://www.metmuseum.org/essays/the-origins-of-writing
- https://www.louvre.fr/en/the-code-of-hammurabi

## Supplied maps

Map files are byte-for-byte copies of the user attachments; no credits were cropped or edited.
- Old Kingdom: Catette / Wikimedia, September 2021, CC BY 4.0, as credited in image. https://creativecommons.org/licenses/by/4.0/
- Indus: © 2012 Encyclopaedia Britannica, Inc., as credited in image. Supplied reference, not represented as public domain.
- Akkadian: original Google / TerraMetrics imagery credits preserved. Schematic shading and uncertain capital marker remain as supplied.
- Middle Kingdom: Simeon Netchev / World History Encyclopedia, CC BY-NC-ND. https://www.worldhistory.org/image/19956/map-of-the-middle-kingdom-of-egypt-c-2000-bce/ The map also labels later Hyksos control; the application caption explains this chronological overlay.

These supplied map images retain their original rights; they are not covered by any general project-code license. Other source-reference images were used for generation, not added to the runtime bundle.
