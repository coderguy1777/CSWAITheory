/*
   A Program that does Gaussian Naive bayes for a given set of data with the goal of doing
   Gaussian Naive Bayes to find the Probability of the point itself being in the dataset.
   By @Jordan Hill
   Period for doing AI Theory: F Period.
 */

//All the Imports I used for the project of doing Gaussian Naive Baye Heuristics.

import java.util.*;
import java.io.*;

public class GaussianNaiveBayes {
    //All the Static Arrays I used for Storing Points and User input for the program to use later on.
    private static ArrayList<ArrayList<Double>> dataset = new ArrayList<>();
    private static ArrayList<ArrayList<Double>> YCoordinates = new ArrayList<>();
    private static ArrayList<ArrayList<Double>> XCoordinatess = new ArrayList<>();

    //All the Static Integers and Doubles I used for my Program when making it.
    private static int trueclassnumber;
    private static Double Coordinate1; //Value for Storing X Coordinates.
    public static Double Coordinate2; //Value for Storing Y Coordinates.
    private static double xvalue1; //Value for the User input for the X Points to be used to calculate the final probability.
    private static double yvalue2; //Value for the User input for the Y Points to be used to calculate the final probability.
    public static double SumofX; //Used to find the sum of all the X Coordinates, mainly to be used for finding the mean value of the X Coordinates.
    private static double MeanofXCoordinates; //Value that stores the Mean of the X Coordinates once the SumofX is divided by 4 to find the mean of the X Coordinates.
    private static double VarianceofXCoordinates; //Value that stores the Variance of the X Coordinates, which is simply standard deviation^2 in this case.
    public static double SumofY;//Used to find the sum of all the Y Coordinates, mainly to be used for finding the mean value of the Y Coordinates.
    private static double MeanofYCoordinates;//Value that stores the Mean of the Y Coordinates once the SumofY is divided by 4 to find the mean of the Y Coordinates.
    private static double VarianceofYCoordinates; //Value that stores the Variance of the Y Coordinates, which is simply Standard Deviation^2
    public static double MeanofEntireTrainingDataSet;//Value that stores the combined Mean of the dataset, that being MeanofX + MeanofY to find the final mean of the dataset.
    private static double VarianceforBothXandYCoordinates; //Value that stores the Combined Variance of the X and Y Coordinates for the training dataset.
    private static double FinalPartforX; //Stores the Final value for all X Values of the X Coordinates of the Dataset, and for use for the final combining of the class formula.
    private static double FinalPartforY; //Stores the Final value for all Y Values of the Y Coordinates of the Dataset, and for use for the final combining of the class formula.
    private static double FinalPartofFormula1;
    private static double FinalPartofFormula2;
    private static int secondcvalue;

    public static void main(String[] args) {
        UserInput();
    }

    private static void DataSetReadingConfirmation() {
        String a = "Data has been read";
        System.out.println(a);
    }

    private static void DataReader() {
        try {
            Scanner scan = new Scanner(new BufferedReader(new FileReader("data")));
            dataset = new ArrayList<>();
            XCoordinatess = new ArrayList<>();
            while (scan.hasNext()) {
                trueclassnumber = scan.nextInt();
                if (dataset.size() < trueclassnumber + 1) {
                    dataset.add(new ArrayList<>());
                }

                Coordinate1 = scan.nextDouble();
                Coordinate2 = scan.nextDouble();
                dataset.get(trueclassnumber).add(Coordinate1);
                dataset.get(trueclassnumber).add(Coordinate2);

                int i = 0;
                for(int x = i; i < dataset.size(); x = i + 1) {
                    XCoordinatess.add(new ArrayList<>());
                    XCoordinatess.get(trueclassnumber).add(Coordinate1);
                    break;
                }
                int o = 0;
                for(int y = o; y < dataset.size(); y = o + 1) {
                    YCoordinates.add(new ArrayList<>());
                    YCoordinates.get(trueclassnumber).add(Coordinate2);
                    break;
                }

                while (!scan.hasNext()) {
                    DataSetReadingConfirmation();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void UserInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Enter X:");
        xvalue1 = scan.nextDouble();
        System.out.println("You entered:" + " " + xvalue1 + "for x, now please enter Y.");
        yvalue2 = scan.nextDouble();
        System.out.println("You entered:" + " " + xvalue1 + "," + yvalue2 + " " + "for both points for the Gaussian Naive bayes to Solve for, please confirm this is correct, by typing Y or N.");
        String Input = scan.next();
        if (Input.equals("Y")) {
            System.out.println("The Program will now calculate the probability that your points exist, please wait while this is done.");
            DataReader();
        } else if (Input.equals("N")) {
            System.out.println("The Program will now end.");
            boolean endinput = true;
            while (endinput) {
                break;
            }
        } else if (Input.equals("y")) {
            System.out.println("The Program will now calculate the probability of your points.");
            DataReader();
        } else if (Input.equals("n")) {
            boolean endinput = true;
            while (endinput) {
                break;
            }
        }
    }

    private static void MeanMethod() {
        SumofX = 0;
        SumofY = 0;
    }

    private static void VarianceMethod() {
        int i = 0;
        int j = 0;
        int k = 0;
    }

    private static void FormulaMethod() {
        int i = 0;
        int j = 0;
        int k = 0;
    }

    private static void ClassProbabilityFinalMethod() {

    }
}