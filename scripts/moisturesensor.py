import time
import RPi.GPIO as GPIO

MOISTURE_1_PIN = 2
MOISTURE_2_PIN = 3
MOISTURE_3_PIN = 4

# Enkel klasse for å håndtere en fuktighetssensor
class MoistureSensor(object):

    def __init__(self, pin, name):
        self._gpio_pin = pin
        self._sensor_name = name
        GPIO.setwarnings(False)
        GPIO.setmode(GPIO.BCM)
        GPIO.setup(self._gpio_pin, GPIO.IN)
        self._new_data = False
        self._value_changed = False
        self._counter = 0
        self._prev_value = 0.0
        self._curr_value = 0.0
        self._time_last_reading = time.time()
        GPIO.add_event_detect(self._gpio_pin, GPIO.RISING, callback=self._event_handler, bouncetime=30)

    def _event_handler(self, pin):
        self._counter += 1
        if(self._time_elapsed >= 1.0):
            self._prev_value = self._curr_value
            self._curr_value = round(self._counter / self._time_elapsed, 1)
            self._counter = 0
            self._time_last_reading = time.time()
            self._new_data = True
            self._value_changed = self._prev_value != self._curr_value

    @property
    def _time_elapsed(self):
        return time.time() - self._time_last_reading

    @property
    def new_data(self):
        return self._new_data

    @property
    def value_changed(self):
        return self._new_data and self._value_changed

    @property
    def moisture(self):
        self._new_data = False
        self._value_changed = False
        return self._curr_value

    @property
    def sensor_name(self):
        return self._sensor_name

# Slutt MoistureSensor klasse