# Map sources and accuracy

All 62 entries open a map. The previous six hand-drawn continent polygons have
been replaced with Natural Earth land geometry at 1:50 million. The application
uses a regional Mercator view sized around each entry's places. Markers identify
approximate places or regional context, never political boundaries. Modern
coastlines are an orientation aid, not a reconstruction of ancient coastlines.
The underlying data is public domain. Made with Natural Earth.
https://www.naturalearthdata.com/about/terms-of-use/
Data: https://github.com/nvkelso/natural-earth-vector/blob/master/geojson/ne_50m_land.geojson
Conversion: outer land rings converted to coordinate TSV; inland lakes omitted.

Two historical territorial maps are bundled as additional dated snapshots:

* Achaemenid period: approximately 500 BCE. Anton Gutsunaev and Uirauna,
  CC BY-SA 3.0. The original SVG was rendered to a 1100-pixel PNG, without
  changing its historical content. The distributed PNG remains CC BY-SA 3.0.
  https://commons.wikimedia.org/wiki/File:Achaemenid_Empire_En.svg
  https://creativecommons.org/licenses/by-sa/3.0/
* Roman period: 117 CE, the united empire at its maximum extent. ArdadN,
  public domain. SVG rendered to a 1100-pixel PNG without historical edits.
  https://commons.wikimedia.org/wiki/File:RomanEmpire_117.svg

These are published reconstructions, not proof of exact frontiers. Their dates
are shown separately from the selected event/period. The 117 CE map is attached
only to the long Roman-period entry, not Augustus in 27 BCE or the end in 476 CE.
Its caption explicitly distinguishes the united empire from its later western
administration. The Persian snapshot is attached to the Achaemenid period,
not Alexander's arrival at Persepolis in 330 BCE.

Other entries currently have geographic context maps, not reconstructed
territorial maps. No modern country borders or invented ancient borders are
shown. A source-based territorial map for every civilization remains future work.
Generic Stone Age markers are continental regions, not excavation sites.

Specific context corrections:
* Phoenician map marks Tyre, Sidon, Byblos and Arwad, not a single country or
  later colonies wrongly placed at 1000 BCE.
  https://www.metmuseum.org/essays/the-phoenicians-1500-300-b-c
* Mughal founding locates Panipat, Delhi and Agra, not later maximal territory.
  https://panipat.gov.in/first-battle/
* Early Ottoman setting locates northwestern Anatolia; Constantinople is not
  presented as Ottoman in 1299. Bursa is contextual, not a 1299 Ottoman capital.
  https://islamansiklopedisi.org.tr/sogut
* Parthian/Sasanian context no longer uses Persepolis as their common center.
  https://www.metmuseum.org/essays/ctesiphon

Map coordinates are approximate. The broad-region markers in era-settings.tsv
are not intended to establish precise archaeological site positions.
