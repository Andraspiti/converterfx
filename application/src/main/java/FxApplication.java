import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * JavaFX runnable, the Application where the entire GUI is created
 */
public class FxApplication extends Application {

    private final ClassTemplates templates = new ClassTemplates();

    /**
     * @param args A String array containing the external program and its commands to be run under a new process
     */
    public static void startProcess(String[] args) throws Exception {
        ProcessBuilder build = new ProcessBuilder(args);
        System.out.println(build.command());
        build.redirectError(ProcessBuilder.Redirect.INHERIT);
        try {
            Process process  = build.start();

            BufferedInputStream bis = new BufferedInputStream(process.getInputStream());
            StringBuffer sb = new StringBuffer();
            int c;
            while((c=bis.read()) != -1) {
                sb.append(c);
            }
            bis.close();
            process.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Contains the GUI and its elements
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();

        Button button2 = new Button("Call jars");
        button2.setTranslateY(25);

        button2.setOnAction( e -> {
            try {
                startProcess(new String[]{"cmd.exe", "/C", "mvn", "install", "-pl", "iFogSim2", "-am"});
                startProcess(new String[]{"cmd.exe", "/C", "java", "-javaagent:\"jars\\aspectj\\aspectjweaver-1.9.7.jar\"",
                        "-classpath", "iFogSim2\\target\\classes", FileHandler.uploadedFileName});
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        Button button3 = new Button("Run the Generated Dissect-Cf file");
        button3.setOnAction(e -> {
            try {
                startProcess(new String[]{"cmd.exe", "/C", "mvn", "install", "-pl", "dissect-cf-fog-application", "-am"});
                // https://github.com/andrasmarkus/dissect-cf/tree/master/dissect-cf-application
                /* startProcess(new String[]{"cmd.exe", "/C", "java", "-cp", "dissect-cf\\dissect-cf-application\\target\\dissect-cf-fog-application-1.0.0-SNAPSHOT.jar",
                        "-Dloader.main=hu.u_szeged.inf.fog.simulator.application.demo.VmTask",
                        " org.springframework.boot.loader.PropertiesLauncher"});
                 */
                startProcess(new String[]{"cmd.exe", "/C", "java", "-jar", "dissect-cf\\dissect-cf-application\\target\\dissect-cf-fog-application-1.0.0-SNAPSHOT.jar",
                        "hu.u_szeged.inf.fog.simulator.application.demo.VmTaskGenerated"});
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }
        });

        Button button6 = new Button("Create dissect file");
        button6.setTranslateY(100);
        button6.setOnAction(e -> {
            try {

                System.out.println("Cloudlet created " + FileHandler.timesCreated("output/Cloudlet") + " time(s).");
                System.out.println("Datacenter created " + FileHandler.timesCreated("output/Datacenter") + " time(s).");
                System.out.println("Host created " + FileHandler.timesCreated("output/Host") + " time(s).");
                System.out.println("Vm created " + FileHandler.timesCreated("output/Vm") + " time(s).");

                ArrayList<String> vmArguments = FileHandler.mostRecentInArray("output/Vm");
                ArrayList<String> cloudletArguments = FileHandler.mostRecentInArray("output/Cloudlet");
                ArrayList<String> hostArguments = FileHandler.mostRecentInArray("output/Host");

                templates.multiplePhysicalMachine( templates.repairHostArguments());

                templates.repairHostArguments();
                templates.createDissectFile(17179869184L,1000000);

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });


        Parent primaryFxml = FXMLLoader.load(getClass().getResource("primary.fxml"));

        final FileChooser fileChooser = new FileChooser();
        final Button fileOpenButton = new Button("Upload input file");

        Button uploadAndRun = new Button("Upload iFogSim scenario");

        uploadAndRun.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file.exists()){
                FileHandler.openFile(file);
            }
            try {
                startProcess(new String[]{"cmd.exe", "/C", "mvn", "install", "-pl", "iFogSim2", "-am"});
                startProcess(new String[]{"cmd.exe", "/C", "java", "-javaagent:\"jars\\aspectj\\aspectjweaver-1.9.7.jar\"",
                        "-classpath", "iFogSim2\\target\\classes", FileHandler.uploadedFileName});

                System.out.println("Cloudlet created " + FileHandler.timesCreated("output/Cloudlet") + " time(s).");
                System.out.println("Datacenter created " + FileHandler.timesCreated("output/Datacenter") + " time(s).");
                System.out.println("Host created " + FileHandler.timesCreated("output/Host") + " time(s).");
                System.out.println("Vm created " + FileHandler.timesCreated("output/Vm") + " time(s).");


                ArrayList<String> vmArguments = FileHandler.mostRecentInArray("output/Vm");
                ArrayList<String> cloudletArguments = FileHandler.mostRecentInArray("output/Cloudlet");
                ArrayList<String> hostArguments = FileHandler.mostRecentInArray("output/Host");

                templates.createDissectFile(17179869184L,1000000);

            } catch (Exception exception) {
                exception.printStackTrace();
            }

        });

        Menu menu = new Menu("Links to the simulators");
        MenuItem mi1 = new MenuItem("DISSECT-CF-Fog");
        MenuItem mi2 = new MenuItem("iFogSim");
        MenuBar mb = new MenuBar();

        URI ifogsimLink = new URI("https://github.com/Cloudslab/iFogSim");
        URI dissectLink = new URI("https://github.com/andrasmarkus/dissect-cf");


        mi1.setOnAction(e ->{
            try {
                Desktop.getDesktop().browse(dissectLink);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        mi2.setOnAction(e ->{
            try {
                Desktop.getDesktop().browse(ifogsimLink);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        menu.getItems().addAll(mi1, mi2);
        mb.getMenus().add(menu);

        VBox linkVbox = new VBox(mb);
        VBox buttonsVbox = new VBox();
        buttonsVbox.getChildren().addAll(uploadAndRun, button3);
        buttonsVbox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(linkVbox, buttonsVbox);

        // picture from https://www.deviantart.com/annamae22/art/Smoke-Cloud-Fog-Texture-Stock-Photo-0112-cc3-704991390
        Image image = new Image("smoke_cloud_fog.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        Background background = new Background(backgroundImage);
        root.setBackground(background);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Converter");
        primaryStage.show();
    }
}
