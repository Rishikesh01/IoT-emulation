# Task
 - You are required to :
1. Create a server which accepts socket connections from clients and which parses the
   data.

2. Scrape the data from the given url, check if the data is fresh and different from your
   previously recorded data and then emulate an IoT device by establishing a socket
   connection with the server and sending it the data packet and then closing the socket
   connection. If you find any fresher data on the url, you are required to constantly
   send the server the data and repeat this process, thus emulating a real time IoT
   device.

3. Both the server and client processes (emulated IoT device) should be fault-tolerant,
   save any errors to a log and restart in case of any fault.

4. After parsing the data at the server, save this data along with the time when this data
   was received, to a database best suited for storing data of this type (so that you can
   also do real time analytics on this gathered data).

5. Create real time alerts for this IoT device and battery in case the battery goes below
   20 % or the battery starts discharging (the value of current becomes negative) or the
   pack voltage shoots across 100 mV. Then, show these alerts and the data received
   from the device in realtime on a webpage.

6. Create a webpage from where you can issue commands to the IoT device to turn it
   off remotely.

# Things to do to achieve the objectives

- use MQTT protocol to communicate messages between iot devices and servers.
- data will be periodically scrapped for every 2000 millisecond and will be sent to server.
- both server and iot will be dockerized and will be set to restart on failure. if needed, we will be adding HAPROXY to perform load balancing of MQTT broker
- data will be parsed and saved in InfluxDB for real-time analytics performs.
- task 5 and 6 are trivial problems.