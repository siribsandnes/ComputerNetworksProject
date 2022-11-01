For å teste:
1 - Sjekk at mqtt broker går: sudo systemctl status mosquitto
2 - Start mqtt klient: et terminalvindu: mosquitto_sub -h localhost -t "mqtt/sensortopic"
3 - Kjør python script i et annet terminalvindu eller Mu: sensor_publisher.py
