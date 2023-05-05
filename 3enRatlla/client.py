import socket 
import pickle 

from tres_en_ratlla import TresEnRatlla                      # importem el joc

HOST = '127.0.0.1'                                           # adreça IP de localhost
PORT = 12783                                                 # port

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)        # Tipus direcció IP: AF_INET = IPv4; protocol socket: SOCK_STREAM = TCP
s.connect((HOST, PORT))                                      # connexió amb el host:
print(f"\nConectat a {s.getsockname()}!")

jugadorO = TresEnRatlla("O")

revenja = True

while revenja == True:
    print(f"\n\n TRES - EN - RATLLA ")

    jugadorO.dibuixarTaulell()

    print(f"\nEsperant a que el contrincant mogui fitxa...")
    llistaSimbolsX = s.recv(1024)
    llistaSimbolsX = pickle.loads(llistaSimbolsX)                       #NO ES EL MATEIX QUE LLISTASIMBOLS
    jugadorO.actualitzaLlista(llistaSimbolsX)


    while jugadorO.guanyat("O") == False and jugadorO.guanyat("X") == False and jugadorO.empatat() == False:
      
        print(f"\n     El teu torn!!")
        jugadorO.dibuixarTaulell()
        coordJugador = input(f"Col.loca la teva fitxa: ")
        jugadorO.colocarFitxa(coordJugador)

        jugadorO.dibuixarTaulell()

        # pickle the symbol list and send it 
        simbolO = pickle.dumps(jugadorO.llistaSimbols)       #pickle.dumps(o) s'utilitza per convertir o en una sequencia de bytes per poderla enviar.
        s.send(simbolO)

        if jugadorO.guanyat("O") == True or jugadorO.empatat() == True:
            break

        print(f"\nEsperant a que el contrincant mogui fitxa...")
        simbolX = s.recv(1024)
        simbolX = pickle.loads(simbolX)                     #pickle.loads(o) agafa una sequencia de bytes i la converteix a un objecte 
        jugadorO.actualitzaLlista(simbolX)

    if jugadorO.guanyat("O") == True:
        print(f"VICTORIA!")
    elif jugadorO.empatat() == True:
        print(f"És un empat!!")
    else:
        print(f"Ooh! has perdut!")

    print(f"\nEsperant si el contrincant vol revenja...")           #esperem si el host vol revenja
    resposta_host = s.recv(1024)
    resposta_host = pickle.loads(resposta_host)
    resposta_client = "N"

                                                                    #si el host diu si, sens pregunta
    if resposta_host == "S":
        print(f"\nEl contrincant vol la revenja!")
        resposta_client = input("Revenja? (S/N): ")
        resposta_client = resposta_client.capitalize()
        temp_client_resp = resposta_client

        resposta_client = pickle.dumps(resposta_client)
        s.send(resposta_client)

        if temp_client_resp == "S":
            jugadorO.reiniciar()

        else:
            revenja = False

    else:
        print(f"\nEl contrincant no vol revenja.")
        revenja = False

spacer = input(f"\nGracies per jugar!!\nPrem ENTER per surtir...\n")

s.close()
