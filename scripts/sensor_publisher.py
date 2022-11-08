import time
import paho.mqtt.client as mqtt
from moisturesensor import MoistureSensor, MOISTURE_1_PIN, MOISTURE_2_PIN, MOISTURE_3_PIN

def on_connect(client, userdata, flags, rc):
    print("Connected with result code " + str(rc))

sensor1 = MoistureSensor(MOISTURE_1_PIN, "Tomat")
sensor2 = MoistureSensor(MOISTURE_2_PIN, "Salat")
sensor3 = MoistureSensor(MOISTURE_3_PIN, "Agurk")

mqttclient = mqtt.Client()
mqttclient.on_connect = on_connect
mqttclient.connect("localhost", 1883, 60)

while (True):
    if (sensor1.value_changed):
        message = "New value: " + str(sensor1.moisture) + " for sensor " + sensor1.sensor_name
        print(message)
        mqttclient.publish('mqtt/sensortopic', payload = message, qos = 0, retain = False)
    if (sensor2.value_changed):
        message = "New value: " + str(sensor2.moisture) + " for sensor " + sensor2.sensor_name
        print(message)
        mqttclient.publish('mqtt/sensortopic', payload = message, qos = 0, retain = False)
    if (sensor3.value_changed):
        message = "New value: " + str(sensor3.moisture) + " for sensor " + sensor3.sensor_name
        print(message)
        mqttclient.publish('mqtt/sensortopic', payload = message, qos = 0, retain = False)