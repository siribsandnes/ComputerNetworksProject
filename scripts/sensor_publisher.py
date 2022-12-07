import time
import paho.mqtt.client as mqtt
from moisturesensor import MoistureSensor, MOISTURE_1_PIN, MOISTURE_2_PIN, MOISTURE_3_PIN

def on_connect(client, userdata, flags, rc):
    print("Connected with result code " + str(rc))

sensor1 = MoistureSensor(MOISTURE_1_PIN, "sensor1")
sensor2 = MoistureSensor(MOISTURE_2_PIN, "sensor2")
sensor3 = MoistureSensor(MOISTURE_3_PIN, "sensor3")

mqttclient = mqtt.Client()
mqttclient.on_connect = on_connect
mqttclient.connect("129.241.152.12", 1883, 60)

while (True):
    if (sensor1.value_changed):
        message = "Moisture: " + str(sensor1.moisture)
        print(message)
        mqttclient.publish('inashouse/house/livingroom/moisture/group14/1', payload = message, qos = 0, retain = False)
    if (sensor2.value_changed):
        message = "Moisture: " + str(sensor2.moisture)
        print(message)
        mqttclient.publish('inashouse/house/livingroom/moisture/group14/2', payload = message, qos = 0, retain = False)
    if (sensor3.value_changed):
        message = "Moisture: " + str(sensor3.moisture)
        print(message)
        mqttclient.publish('inashouse/house/livingroom/moisture/group14/3', payload = message, qos = 0, retain = False)