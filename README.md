# Sudoku
## Verkefni 8 - Viðmótsforritun
### Um Verkefnið
Við ákváðum að smíða forrit sem býr til Sudoku þraut fyrir notenda. Við höfum allir mikinn áhuga á Sudoku og lá því valið fyrir hendi. 

<p>&nbsp;</p>

### Lýsing
* Notandi keyrir forrit og velur erfiðleikastig.
* Forritið býr til nýja Sudoku þraut eftir erfiðleikastigi og tekur tíma.
* Ef notandi svarar rangt þrisvar sinnum hefur notandi tapað leiknum og tími stöðvast.
* Ef notandi klárar þrautina birtist gluggi sem tilkynnir honum að hann hafi klárað leik.

<p>&nbsp;</p>


### Virkni
Forritið býr til fullklárað púsl og dregur síðan reiti slembið í burt með því að nota Collections.shuffle skipunina.

Skipunin dregur 20 tölur burt fyrir "Auðveldan" leik, 35 fyrir "Miðlungs", 40 fyrir "Erfitt" og 50 fyrir "Ómögulegt".

Forritið býr til afrit af fullkláraða spilinu í byrjun, áður en reitirnir eru teknir í burtu. 

Fullkláraða spilið er svo borið saman við kláraða reiti sem notandinn skilar inn og ákvarðar svo forritið hvort hann fái villu eða rétt. 

Leiknum er svo lokið þegar notandi skilar inn jafn mörgum reitum og teknir voru af borðinu í byrjun.

<p>&nbsp;</p>

### Prófanir
Við tókum allir virkan þátt í að prófa...

<p>&nbsp;</p>

### Keyrsla
Hér eru leiðbeiningar að því hvernig hægt er að keyra og byggja forrit.
* Einfaldast er að afrita URL undir "code" í Repositary og opna verkefnið inn í IntelliJ IDEA, VSCode eða einhverju sambærilegu.
* Svo næst þarf að passa að Maven er virkt og að þú hefur útgáfu af java sem að styður verkefnið.
* Keyrið forritið, hægt er að keyra forritið í gegn um Terminal eða þróunartól eins og IntelliJ þar sem að notandi ýtir á Maven merkið við hlið kóðans og finnur "javafx:run" sem hann smellir á.
* Ef notandi kýs að opna forritið í gegnum Terminal þarf hann að vísa í rót verkefnisins með skipununni: "cd /dæmi/um/leið/að/Sudoku".
* Næst skal hann slá inn "mvn compile" og svo "mvn exec:java -Dexec.mainClass="src.main.java.hi.vidmot.SudokuApplication"".


