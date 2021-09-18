import socket

HOST = ''                 # Symbolic name meaning all available interfaces
PORT = 8080              # Arbitrary non-privileged port
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((HOST, PORT))
s.listen(1)


while(1):
    conn, addr = s.accept()
    print(addr)
    try:
        while(1):
            try:
                data = conn.recv(4096)
                if not data:
                    conn.close()
                    break
                print(data)
            except ConnectionResetError:
                break
            except KeyboardInterrupt:
                import sys
                sys.exit(0)

    except:
        break
