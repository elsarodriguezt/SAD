import socket 
import pickle 

from tres_en_ratlla import TresEnRatlla                               # importem el joc

HOST = '127.0.0.1'                                                              # adreça IP de localhost
PORT = 12783                                                        # port   

#servidor
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)        #Tipus direcció IP: AF_INET = IPv4; protocol socket: SOCK_STREAM = TCP
s.bind((HOST, PORT))                                            
s.listen(5)
                                            
socket_client, adreça_client = s.accept()                           #acceptem la conexió amb el client
print(f"\nConectat a {adreça_client}!")

# set up the game
jugadorX = TresEnRatlla("X")

revenja = True                                              #aquesta variable ens permet fer revancha

while revenja == True:

    print(f"\n\n TRES - EN - RATLLA ")

    while jugadorX.guanyat("X") == False and jugadorX.guanyat("O") == False and jugadorX.empatat() == False:            #el joc funciona mentres no hi hagi ni guanyador ni empat

        print(f"\n     El teu torn!!")
        jugadorX.dibuixarTaulell()
        coordJugador = input(f"Col.loca la teva fitxa: ")                   #coordJugador es la coordenada on volem colocar la fitxa
        jugadorX.colocarFitxa(coordJugador)

        jugadorX.dibuixarTaulell()                                          #dibuixem el taulell actualitzat

        simbolX = pickle.dumps(jugadorX.llistaSimbols)                      # pickle the symbol list and send it 

        socket_client.send(simbolX)

        if jugadorX.guanyat("X") == True or jugadorX.empatat() == True:         #si aquest moviment ha provocat empat o victora, surtim del bucle
            break

        print(f"\nEsperant a que el contrincant mogui fitxa...")
        simbolO = socket_client.recv(1024)
        simbolO = pickle.loads(simbolO)
        jugadorX.actualitzaLlista(simbolO)


    if jugadorX.guanyat("X") == True:                                      #missatges depenent del final del joc
        print(f"VICTORIA!")
    elif jugadorX.empatat() == True:
        print(f"És un empat!!")
    else:
        print(f"Ooh! has perdut!")


    resposta_host = input(f"\nRevenja? (S/N): ")                                #preguntem per una revenja
    resposta_host = resposta_host.capitalize()
    temp_host_resp = resposta_host
    resposta_client = ""

   
    resposta_host = pickle.dumps(resposta_host)                         # pickle response and send it to the client 
    socket_client.send(resposta_host)

    if temp_host_resp == "N":                                               #si  no es vol revenja, s'acaba el joc
        revenja = False

    else:                                                                   #si el host vol revenja, preguntem al client
        # receive client's response 
        print(f"Esperant la resposta de l'oponent...")
        resposta_client = socket_client.recv(1024)
        resposta_client = pickle.loads(resposta_client)
 
        if resposta_client == "N":                                          #si el client no la vol, acabem
            print(f"\nEl contrincant no vol revenja...")
            revenja = False

        else:
            jugadorX.reiniciar()                                                #si tots volen, reiniciem

spacer = input(f"\nGràcies per jugar!!\nPrem ENTER per surtir...\n")

socket_client.close()
