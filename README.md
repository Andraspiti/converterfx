The project contains the two simulators:

https://github.com/andrasmarkus/dissect-cf

https://github.com/Cloudslab/iFogSim

Currently only works under windows

Installation setup guide

In order to the program to be able to run the following criteria must be met: 
 - Being  able to run Maven from cmd
 - JavaFX installed, refer to https://openjfx.io/openjfx-docs/#introduction

After extracting the code,  

run Maven install: `mvn install`

Run Main.java under application/src/main/java with using IDEA of choice, or the following command:

`java --module-path PATH_TO_JAVAFX/lib --add-modules javafx.controls,javafx.fxml -classpath application\target\classes Main`

where PATH_TO_JAVAFX is the installation folder of the JavaFX 

Clicking the "Upload iFogSim scenario" launches a file selector, with this you must select a valid, runnable iFogSim scenario.
The scenario must be runnable under org.fog.test.perfeval under iFogSim

