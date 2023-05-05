"""
Tic Tac Toe
"""

class TresEnRatlla():

    def __init__(self, simbolJugador):                  #inicialitzem llista de simbols
        self.llistaSimbols = []
 
        for i in range(9):                          #definim la llista amb espais buits
            self.llistaSimbols.append(" ") 

        self.simbolJugador = simbolJugador          # initialitcem el simbol del jugador

    def reiniciar(self):                              #reinicia la llista de simbols
        for i in range(9):
            self.llistaSimbols[i] = " "

    def dibuixarTaulell(self):                           # dibuixa les capçaleres de les columnes
        print("\n       A   B   C\n")
        
   
        fila1 = "   1   " + self.llistaSimbols[0]
        fila1 += " ║ " + self.llistaSimbols[1]
        fila1 += " ║ " + self.llistaSimbols[2]
        print(fila1)

        print("      ═══╬═══╬═══")                          # divisor primera fila

        fila2 = "   2   " + self.llistaSimbols[3]
        fila2 += " ║ " + self.llistaSimbols[4]
        fila2 += " ║ " + self.llistaSimbols[5]
        print(fila2)

        print("      ═══╬═══╬═══")                          #divisor segona fila

        # display third and last row 
        fila3 = "   3   " + self.llistaSimbols[6]
        fila3 += " ║ " + self.llistaSimbols[7]
        fila3 += " ║ " + self.llistaSimbols[8]
        print(fila3, "\n")


    def colocarFitxa(self, coordTaulell):                                        # passa de "1A" a "A1"
        if coordTaulell[0].isdigit():                                           #si el primer es digit, ho girem
            coordTaulell = coordTaulell[1] + coordTaulell[0]

        # divides the coordinate 
        col = coordTaulell[0].capitalize()                                    #la columna es LA MAJUSCULA 
        fila = coordTaulell[1]                                                #la fila es el num.

        numTaulell = 0                                                      #aquesta part pasa: A1 =0; B1=1; C1=2

        if fila == "1":                                                     #grid_index = numTaulell
            if col == "A":
                numTaulell = 0
            elif col == "B":
                numTaulell = 1
            elif col == "C":
                numTaulell = 2
        elif fila == "2":
            if col == "A":
                numTaulell = 3
            elif col == "B":
                numTaulell = 4
            elif col == "C":
                numTaulell = 5
        elif fila == "3":
            if col == "A":
                numTaulell = 6
            elif col == "B":
                numTaulell = 7
            elif col == "C":
                numTaulell = 8

        if self.llistaSimbols[numTaulell] == " ":
            self.llistaSimbols[numTaulell] = self.simbolJugador             #coloca la fitxa del jugador q crida la funcio

    def actualitzaLlista(self, novaLlistaSimbols):                            #actualitza la llista dels simbols
        for i in range(9):
            self.llistaSimbols[i] = novaLlistaSimbols[i]


    def guanyat(self, simbolJugador):                       #funció que retorna true si guanyes                           
        g = []                                            
        for i in range(9):                                  #append afegeix self.llistaSImbols[i] al final de la llista g.
            g.append(self.llistaSimbols[i])                 # sera el nostre taulell

        sym = simbolJugador

        if g[0] == sym and g[1] == sym and g[2] == sym:                     #fila de dalt
            return True

        elif g[3] == sym and g[4] == sym and g[5] == sym:                   #fila del mig
            return True
        
        elif g[6] == sym and g[7] == sym and g[8] == sym:                   #fila de sota
            return True                     

        elif g[0] == sym and g[3] == sym and g[6] == sym:
            return True 

        elif g[1] == sym and g[4] == sym and g[7] == sym:                    #columna del mig
            return True 

        elif g[2] == sym and g[5] == sym and g[8] == sym:                   #columna dreta
            return True

        elif g[2] == sym and g[4] == sym and g[6] == sym:                  #diagonal
            return True 

        elif g[0] == sym and g[4] == sym and g[8] == sym:                  #diagonal
            return True 

        return False                                                                         #si no es dona cap combinació torna false

    def empatat(self):                                                                       # aquesta funció ens diu si hi ha empat                           
        espaisBuits = 0                                             
        for i in range(9):                                          
                if self.llistaSimbols[i] == " ":
                    espaisBuits += 1

        if self.guanyat(self.simbolJugador) == False and espaisBuits == 0:      # true = està tot ple = empat
            return True
        else:
            return False                                                        # false = encara hi ha espais buits
