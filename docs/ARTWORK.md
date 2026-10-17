# Artwork

historical-vignettes.png uses a 4-column, 3-row atlas of antique engraving-style
historical scenes. prehistory-vignettes.png uses a 3-column, 2-row atlas. Both were
generated with the built-in image tool. They are artistic interpretations.

Prehistory prompt: six equally sized scenes in a 3-column, 2-row grid, colored
nineteenth-century engravings on parchment: a modestly clothed hunter-gatherer
family near a cave/rock shelter; flint hand axe, scrapers and blades; Neolithic
farmers in the Levant; Sumerian city; planned Indus brick town with drains;
Classic Maya city. No text, no dinosaurs, no modern objects. The approved garden
plate supplied only the antique illustration style.

The chart itself is drawn using native JavaFX shapes, text, paths and image
viewports from chart-layout.tsv. No generated diagram controls the dates.

Civilization-vignettes.png was generated using the built-in image tool. Prompt:
Twelve equal 4-by-3 cells in an antique hand-colored engraving style: Phoenician
merchants, Neo-Assyrian officials, Neo-Babylonian city, Achaemenid terraces, Greek
market, Shang foundry, Zhou town, Aksum trade, Gupta craftspeople, Mauryan
administration, medieval cloth market, Renaissance secular portrait workshop.
No captions. Contextual artistic reconstructions, not documentary images.
The sheet is consumed through JavaFX viewports; no image editing scripts are
required by the application.

Three layered Phoenician assets were generated with the built-in image tool:
- phoenician-harbor-background.png: fixed antique engraved Levantine harbor, empty
  water and empty stone quay, no people or boats, matching the Phoenician vignette.
- phoenician-merchant-walk.png: eight sequential right-facing walking poses of the
  same cream-tunic merchant with purple cloth, sandals and amphora; four columns
  and two rows; stable proportions and baseline; transparent background.
- phoenician-boat.png: isolated right-facing wooden Phoenician vessel with cream
  square sail and amphorae, antique engraving style, transparent background.
JavaFX changes the merchant viewport and the position of each foreground layer.
The generated PNGs are copied unchanged into the application resources.
