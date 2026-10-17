Adding history

Keep one era record, one ribbon and one image card per period. Put era names, date ranges and duration beside the ribbon. Use separate event records for battles, rulers, inventions and other turning points. Link those events from the corresponding era.

History descriptions and source links are in src/main/resources/expanded-events.tsv. Each line has eight fields separated by tabs: title, starting year (negative for BCE), lane, displayed date, description, source URL, illustration sheet, illustration cell. Preserve existing record order because the chart uses record indices. Do not insert a line in the middle.

A new record also needs an era-settings.tsv map entry. A new era needs a PATH ribbon, TEXT labels and a HOTSPOT card in chart-layout.tsv. Related event IDs are listed in era-related-events.tsv. New pictures in this version show physical geography with place markers; they do not claim to reconstruct territorial borders.
