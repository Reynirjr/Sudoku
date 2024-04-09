# Sudoku
## Verkefni 8 - Viðmótsforritun
### Lýsing
Notandi keyrir forrit og velur erfiðleikastig. Forritið býr til nýja Sudoku þraut eftir erfiðleikastigi og tekur tíma. Ef notandi svarar rangt þrisvar sinnum hefur notandi tapað leiknum og tími stöðvast. Ef notandi klárar þrautina birtist gluggi sem tilkynnir honum að hann hafi klárað leik. 


### Keyrsla
Hér eru leiðbeiningar að því hvernig hægt er að keyra og byggja forrit.
* Einfaldast er að afrita URL undir "code" í Repositary og opna verkefnið inn í IntelliJ IDEA, VSCode eða einhverju sambærilegu.
* Svo næst þarf að passa að Maven er virkt og að þú hefur útgáfu af java sem að styður verkefnið.
* Keyrið forritið, hægt er að keyra forritið í gegn um Terminal eða þróunartól eins og IntelliJ þar sem að notandi ýtir á Maven merkið við hlið kóðans og finnur "javafx:run" sem hann smellir á.
* Ef notandi kýs að opna forritið í gegnum Terminal þarf hann að vísa í rót verkefnisins með "cd /dæmi/um/leið/að/Sudoku"
* Næst skal hann slá inn "mvn compile" og svo "mvn exec:java -Dexec.mainClass="src.main.java.hi.vidmot.SudokuApplication""


### Notendaprófanir
Notendi skal prófa eftirfarandi atriði:
1. Veldu erfiðleikastig.
2. Sláðu inn rangt svar þrisvar sinnum.
3. Byrjaðu nýjan leik og leystu þrautina á undir 5 mínútum með færri en 3 villur.
4. Leystu þrautina á undir 2 mínútum.
5. Leystu þrautina með því að klára einn kassa í einu.
