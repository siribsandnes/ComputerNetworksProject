# Report - Plant Monitor

This application is the soloution to a school project in IDATA2304

## Abstract

Taking care of plants requires a lot of attention, and a good memory, or at least it can require those things if you opt to do it the old fashioned way.
The main problem is that many owners forget to water plants, or don't know when to water a plant, and end up with dead dried up plants. This project
offers a soloution to that exact problem. The moisture sensors monitor the moisture levels in the soil of the plants, and transfer the data to the desktop
application, which then displays these values and lets the user know if the plant needs watering or not. Possible future development plans would be to
create a mobile application version with notifications.

## Introduction

Our application is made for monitoring thе moisturе lеvеl in thе soil of your plants at homе. From еxpеriеncе wе know that remembering to keep plants
watеrеd and alivе is no еasy task in a busy schеdulе. Our solution for this is an app that will keep track of the moisture level, so you can see whеn your
plants arе in nееd of watеr, all in one place, instead of having to check each plant manually. You placе a moisturе sеnsor in thе soil of your plants, and
thе sеnsors connected the Raspberry PI will transmit data to an MQTT server, and thе desktop application is a subscriber to the sensors topic in the MQTT,
so the app will receive the data published to the MQTT server every time it updates. In the application you can keep track of your plants moisture level,
and be able to see if they need water.

The target audience of this system is plant owners, whether it be in private homes or bigger companies. We made a simple desktop application with basic
design that is easy to use and understand. 

Our group chose to create an IoT system for plant monitoring, utilizing moisture sensors. In this report we start by giving a description of the theory
and technology used. Next we offer a description of the work process during the project in the methodology section. Our end results are expressed in the
result section. The last section of the project report contains reflections and thoughts on improvements and future work we could implement to better our
system.


## Theory and technology

The project makes use of a lot of different network protocols for transferring the data from the sensor node to the application node.
It uses MQTT protocol, which is a communication protocol  that contains features which are targeted at internet of things (IoT) solutions, it is built with
client-server architecture, and allows messages to be transmitted in both directions, from client to server, and from server to client. 
In MQTT one has the ability to both subscribe and publish to a topic. If you are subscribed to a topic, you will get notified when something is published
to that topic, because the MQTT server sends notifications to everyone subscribed, no matter who publishes it. There is no direct connection between the
publishers and subscribers, so the publisher knows nothing about the subscriber, and vice versa, the topics are what connects the publisher and the
subscriber through the server. The protocol typically runs on TCP/IP.

TCP is a connection oriented transport layer protocol which ensures reliable transfer of data packets.
(https://www.dummies.com/article/technology/information-technology/networking/general-networking/network-basics-tcpip-protocol-suite-185407/ ) TCP ensures
reliable transfer of data by first establishing a connection to the recipient and then by having mechanisms to solve possible errors such as packet loss or packet corruption. To transfer packets from one network device to another TCP uses the IP protocol, which is a network layer protocol. The IP protocol
makes use of the IP addresses, which are logical unique addresses that identify devices connected to the internet. IP addresses are converted to MAC
addresses by another protocol called ARP.  The MAC address is a unique physical address each machine has, and is used on the link layer to transfer data between network entities, for example from a computer to a server. 

Different programming languages have been utilized in this project. Java and Python, two object oriented languages, were used for programming the sensor
node and the application node. Both Java and Python are high-level programming languages. The main differences between the two are the fact that Java is a
compiled language and Python is an interpreted language. Python also supports programming paradigms such as structured and functional programming.  

Another programming language used in the project is Sql. Sql is a standard language used for storing, manipulating and retrieving data from a database.(https://www.w3schools.com/sql/) Sql is something the group members learned in another course, IDATA2303 Data modeling and databases, and wanted to
implement in this project as it enables functionality such as persistent data. 

To visualize the data received from the sensors, we have created a desktop application using JavaFX which is a Java library that is used to develop
Desktop applications as well as Rich Internet Applications (RIA). (https://www.javatpoint.com/javafx-tutorial). 

The sensors used in the project are Grow moisture sensors. These sensors use a pulse frequency modulation (PFM), where they transmit the moisture level by
sending out a pattern (square waves) on-off electrical pulses that have varying frequency. Lower frequency tells us that the soil is dry, and high
frequency tells us that the soil is wet. Our application interprets the frequency as hertz (Hz) in a number between 0 and 30, where 0 is the wettest, and
30 is the driest. The sensors are connected to a raspberry pi, which is a single board computer.


# Methodology

The first five weeks of working on the project we scheduled group meetings twice a week, where the first meeting was spent planning and delegating the workload for the upcoming sprint and creating issues in github. The second meeting was spent working on the issues and tasks set in the first meeting.
The last few weeks the group communicated mostly online to delegate tasks, which resulted in a less structured and organized issueboard.

Our group organized a user test with another group of students in the computer networks course. The test consisted of placing the sensors in soil that had different moisture levels, one pot had very wet soil, one had very dry soil, and the last one was somewhere in between the two. From this test they were able to confirm that the data transmitted from the sensors to the application was correct. The feedback we received was positive, and they were able to see that the moisture levels in the soil correlated with the moisture levels displayed in the application. They also provided us with feedback on the user experience of the applications, and confirmed that the application was easy to understand and interact with.

Some best-practises that were followed are naming conventions of variables and classes, code commenting and the use of indentations. This was all done to make sure that the code is easy to read and understand. 


# Results

The result is easier explained if we divide it in two, a sensor node and a visualization node, which works together as a whole. 

### Sensor Node: 
The sensor node consists of three moisture sensors connected to a Raspberry PI. The Raspberry PI runs a python program consisting of two files, a Moisture Sensor Class and a connect function. 

The Moisture Sensor class is responsible for setting up the right configuration to access the signal transmitted from the sensors through the pins and evaluate this signal. The sensors transmit electrical signals from 0 to 3,3 volt to the raspberry. The signal is a square wave pattern which has to be interpreted to make sense to the user.

The connect function is responsible for connecting the program to the right MQTT broker, and publishing data on the defined topics. It uses a library known as Paho made by Eclipse which is a MQTT implementation. 

### Visualization node: 
This is the desktop application where the data sent from the sensors are visualized. It is a program with many different responsibilities and functionalities: The program connects to a MQTT server to access the data, creates and displays the GUI with all its functionalities and connects to a locally run database. 

The program code is split into three packages based on the responsibility of the classes in the packages, a data package, a logic package and a ui package. 

The data package contains classes responsible for “storing data”, such as the Plant class which stores info about the different Plants created by the user in the application. 

There are two different classes responsible for logic:

1) A class responsible for connecting to the MQTT client, subscribing to the correct topic and defining what to do next when data has been received. 
2) A class responsible for connecting to the local database defining different methods for accessing and manipulating data in the database.

The UI package contains the classes responsible for the GUI. Two of the classes simply create GUI elements using java, while the third is the controller which is connected to the FXML which defines the user interface. 

The controller defines the possible actions for when a user interacts with the application. What happens when the user performs an action, i.e clicks a button.  It works as a mediator between a user and application.

The application is run from the MainApp, which is the class responsible for loading the FXML file and setting the stage.

In addition to the sensor node and the visualization node there is also a mySQL database stored locally on Siris computer which is run using dBeaver. This is the database the application connects to and saves the plants in. 


# Discussion

The solution and code written on the sensor node is working in the way we intended. Sensor data is successfully sent to the MQTT broker, this provides us with reassurance that the TCP connection is successful. The visualization node also ended up operating successfully, and we know that the numbers on the sensor side is matched up to the numbers we received on the visualization side, in the app. We connected the Raspberry Pi to a screen, so we were able to look at the data transmitted from the sensors to the Raspberry Pi, while simultaneously watching the data transmitted from the MQTT server to our application. This made it possible to confirm that the data is correct.

The overall user experience the application provides, also ended up functioning well, and through testing ourselves and conducting a user test, this was confirmed. A negative we encountered in our application is that we have not implemented a solution that enables the application to send out notifications. For the time being, you can only view the moisture levels when you have the application running on the computer. To rectify this inconvenience we could have integrated a function for sending SMS notifications, reminding users to check on their plants, if the moisture levels are not in the target area.


# Conclusion and future work

In conclusion we were able to create a project that fulfills the requirements. Our solution consists of a sensor node which publishes data to a MQTT broker and an application node which subscribes to the data from the MQTT and displays it in an application. We have also done some extra work such as using physical sensors and a raspberry pi, and incorporating a database with the application, as well as actually creating an application to display the data, not only using the terminal.

If we were to further develop this app we would firstly create a mobile application instead of a desktop application. That would make the application more accessible and user-friendly and is more appropriate. We would also implement push notifications and reminders to the user, so that they don't have to remember to check the app. 

We would also like to implement more functionality in the application such as enabling the user to see the history, how was the plant doing last week? And graphs and summaries such as average moisture level, and so on. 
Last but not least we would like to add more sensors, such as light and temperature. 


# References

Here you provide sources of information. In a written report you typically
include list of references in the end and have only links to those in the text,
such as [1], [2], [3]. In markdown (as this document will be) you can include
most of the links directly in the text. Here in this section you should list the
sources of information you have used - books, articles, Wikipedia articles,
other online articles. For each of them you should specify at least the title,
the author. If available: web link and year when this was published.

Note: YouTube videos are not a good source for information... Some of them are
very good, but in general YouTube is a large trash bin, where some things turn
out to be "edible".
