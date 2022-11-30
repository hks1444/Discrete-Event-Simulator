# Discrete-Event-Simulator
This is a discrete event simulator that simulates flight events. ATC(Air Traffic Controller) controls flight operations in a specific airport that has its own unique code. ACC(Area Control Center) coordinates ATCs and a flight begins at an ACC and it ends at that ACC. ACCs also have its own code. ATCs are stored in a hashtable of an ACC. ATCs are placed in the hashtable according to their codes. Linear probing is used to solve collisions. There are 21 operations to be completed for each flight. Each ACC and ATC can only process one operation at a given time. The operation is pulled from the Ready Queue(this is a priority queue of flight operations). In waiting operations, there is no need for processing event so flights wait in the waiting queue until their time is up. In the end, each ACC code is printed with its elapsed time and its own ATCs' codes concatenated with ATCs' hashtable index.
<br />Events:
<br />ACC - Running: ACC initial processing.
<br />ACC - Waiting: Relaying flight information to ACC.
<br />ACC - Running: Control transfer from ACC to departure ATC.
<br />ATC Departure - Running: Departure ATC initial processing.
<br />ATC Departure - Waiting: Boarding wait.
<br />ATC Departure - Running: Taxi information processing.
<br />ATC Departure - Waiting: Taxi wait.
<br />ATC Departure - Running: Takeoff clearance processing.
<br />ATC Departure - Waiting: Takeoff wait.
<br />ATC Departure - Running: Control transfer from departure ATC to ACC.
<br />ACC - Running: Flight path processing.
<br />ACC - Waiting: Flight wait.
<br />ACC - Running: Control transfer from ACC to arrival ATC.
<br />ATC Arrival - Running: ATC initial processing and landing clearance processing.
<br />ATC Arrival - Waiting: Landing wait.
<br />ATC Arrival - Running: Taxi information processing.
<br />ATC Arrival - Waiting: Taxi wait.
<br />ATC Arrival - Running: Gate and ground crew information processing.
<br />ATC Arrival - Waiting: Disembarkation wait.
<br />ATC Arrival - Running: Control transfer from arrival ATC to ACC.
<br />ACC - Running: ACC wrap-up.
