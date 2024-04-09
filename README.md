# Sudoku
## Verkefni 8 - Viðmótsforritun
### Lýsing
Notandi keyrir forrit og velur erfiðleikastig. Forritið býr til nýja Sudoku þraut eftir erfiðleikastigi og tekur tíma. Ef notandi svarar rangt þrisvar sinnum hefur notandi tapað leiknum og tími stöðvast. Ef notandi klárar þrautina birtist gluggi sem tilkynnir honum að hann hafi klárað leik. 


### Keyrsla
Hér eru leiðbeiningar að því hvernig hægt er að keyra og byggja forrit.
* Hlaðið niður zip skjali
* Skoðið hvar skjalið er staðsett
* Opnið Terminal
* Breytið vísun í rót verkefnisins(þar sem pom.xml skjalið er staðsett) með "cd path/to/project"
* Sláið inn "mvn compile" í Terminal
* Og svo loks til að keyra forritið sláið inn "mvn exec:java -Dexec.mainClass="packageName.MainClassName"


### Notendaprófanir
Notendi skal prófa eftirfarandi atriði:
1. Veldu erfiðleikastig.
2. Sláðu inn rangt svar þrisvar sinnum.
3. Byrjaðu nýjan leik og leystu þrautina á undir 5 mínútum með færri en 3 villur.
4. Leystu þrautina á undir 2 mínútum.
5. Leystu þrautina með því að klára einn kassa í einu.
