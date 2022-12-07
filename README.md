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

Here you can write about the way you have worked. You don't need to write much
about how you organized sprints, this documentation will be handled separately.
Again - think about the next engineer-reader. What does the engineer need to
know about the way you worked? Did you do some user tests? Experiments? How did
you measure, evaluate? Any best-practices you followed? What must the reader
understand to be able to interpret the results properly?

# Results

Here you describe the results you have obtained. Some considerations:

* Have a top-down approach. Start with a short introduction, then go more into
  details. For example, a good way to start the section could be with a picture
  showing the overall architecture of the solution and a short text describing
  it. After that you can go into more details on each component of the system.
* Describe the structure you got, the general principles of how it works.
* You could also include some screenshots showing the system. How could the
  reader get an impression of the result without running the system?
* No need to include code in the report, all the code is in the repository.

# Discussion

Here you can reflect on the result. What is working well? What is not working
well and why?

# Conclusion and future work

Here you summarize the work shortly, the status. Also, here you identify the
potential work in the future. Note: think in general - how could this work be
continued (by your group or by others)?

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
