/*
        A Program that does Gaussian Naive bayes for a given set of data with the goal of doing
        Gaussian Naive Bayes to find the Probability of the point itself being in the dataset.
        By @Jordan Hill
        Period for doing AI Theory: F Period.
        */

//All the Imports I used for the project of doing Gaussian Naive Baye Heuristics.

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class GaussianNaiveBayes {
    //All the Static Arrays I used for Storing Points and User input for the program to use later on.
    private static ArrayList<ArrayList<Double>> dataset = new ArrayList<>();
    private static ArrayList<ArrayList<Double>> YCoordinates = new ArrayList<>();
    private static ArrayList<ArrayList<Double>> XCoordinatess = new ArrayList<>();
    private static ArrayList<Integer> ClassValues = new ArrayList<>();
    private static Integer[] ClassArray;

    //All the Static Integers and Doubles I used for my Program when making it.
    private static int trueclassnumber; //Prints Class Value.
    private static Double Coordinate1; //Value for Storing X Coordinates.
    public static Double Coordinate2; //Value for Storing Y Coordinates.
    public static Double meanforx;
    public static Double meanfory;
    private static double VarianceX;
    private static double VarianceY;
    private static double xvalue1; //Value for the User input for the X Points to be used to calculate the final probability.
    private static double yvalue2; //Value for the User input for the Y Points to be used to calculate the final probability.
    private static Double BottomHalfofFormulaforY;
    private static Double BottomHalfofFormulaforX;
    private static Double ExponentofFormulaforX;
    private static Double FinalformulaX;
    private static Double ClassXProbability;
    private static Double Finalformulay;
    private static Double ClassYProbabilityY;
    private static Double ExponentofFormulaY;
    private static double Cvalue;
    private static double XCvalue;
    private static double YCvalue;
    private static double ClassProbability;
    private static double sumofx;
    private static double sumofy;


    public static void main(String[] args) {
        UserInput();
    }

    private static void DataSetReadingConfirmation() {
        String a = "Data has been read";
        System.out.println(a);
    }

    private static ArrayList<ArrayList<Double>> DataReader() {
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
                ClassValues.add(trueclassnumber);


                int i = 0;
                if (i < dataset.size()) {
                    XCoordinatess.add(new ArrayList<>());
                    XCoordinatess.get(trueclassnumber).add(Coordinate1);
                    MeanforX();
                }

                int o = 0;
                if (o < dataset.size()) {
                    YCoordinates.add(new ArrayList<>());
                    YCoordinates.get(trueclassnumber).add(Coordinate2);
                    MeanforY();
                }

                Backfunctions();

                if (!scan.hasNext()) {
                    DataSetReadingConfirmation();

                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    private static void Backfunctions() {
        ClassValueArray();
        VarianceforY(YCoordinates);
        VarianceforX(XCoordinatess);
        SumofX();
        SumofY();
        MeanforX();
        MeanforY();
        FormulaforX();
        FormulaforY();
        ClassCombination();

    }

    private static void MeanforX() {
        meanforx = sumofx / XCoordinatess.size();
    }


    private static void SumofX() {
        sumofx = 0;
        for (int i = 0; i < XCoordinatess.size(); i++) {
            for (int ii = 0; ii < XCoordinatess.get(i).size(); ii++) {
                sumofx += XCoordinatess.get(i).get(ii);
            }
        }

    }

    private static void SumofY() {
        sumofy = 0;
        for (int i = 0; i < YCoordinates.size(); i++) {
            for (int ii = 0; ii < YCoordinates.get(i).size(); ii++) {
                sumofy += YCoordinates.get(i).get(ii);
            }
        }
    }

    private static void MeanforY() {
        meanfory = sumofy / YCoordinates.size();
    }

    private static double VarianceforX(ArrayList<ArrayList<Double>> XCoordinatess) {
        double variance = 0;
        VarianceX = 0;
        for (int i = 0; i < XCoordinatess.size(); i++) {
            for (int ii = 0; ii < XCoordinatess.get(i).size(); ii++) {
                double pointvalue = XCoordinatess.get(i).get(ii);
                double meanforxx = meanforx;
                variance = Math.pow((pointvalue * 1.0 - meanforxx), 2);
                VarianceX += variance;
            }
        }
        VarianceX = VarianceX / (XCoordinatess.size() - 1);
        return VarianceX;
    }

    private static double VarianceforY(ArrayList<ArrayList<Double>> YCoordinates) {
        double variance = 0;
        VarianceY = 0;
        for (int i = 0; i < YCoordinates.size(); i++) {
            for (int ii = 0; ii < YCoordinates.get(i).size(); ii++) {
                double pointvalue = YCoordinates.get(i).get(ii);
                double meanforyy = meanfory;
                variance = Math.pow((pointvalue * 1.0 - meanforyy), 2);
                VarianceY += variance;
            }
        }
        VarianceY = VarianceY / (YCoordinates.size() - 1);
        return VarianceY;
    }

    private static void ClassValueArray() {
        ClassArray = new Integer[ClassValues.size()];
        for (int i = 0; i < ClassValues.size(); i++) {
            ClassArray[i] = ClassValues.get(i);
        }
    }

    private static void FormulaforX() {
        BottomHalfofFormulaforX = 1 / Math.sqrt(2 * 3.14 * meanforx);
        ExponentofFormulaforX = Math.pow(xvalue1 - VarianceX, 2) / 2 * VarianceX;
        FinalformulaX = BottomHalfofFormulaforX * ExponentofFormulaforX;
    }

    private static void FormulaforY() {
        BottomHalfofFormulaforY = 1 / Math.sqrt(2 * 3.14 * meanfory);
        ExponentofFormulaY = Math.pow(yvalue2 - VarianceY, 2) / 2 * VarianceY;
        Finalformulay = BottomHalfofFormulaforY * ExponentofFormulaY;
    }

    private static void FinalCombination() {
        Cvalue = 2;
        XCvalue = FinalformulaX / meanforx / VarianceX;
        YCvalue = Finalformulay / meanfory / VarianceY;
        ClassProbability = Cvalue / XCvalue / YCvalue;
    }

    private static void ClassCombination() {
        FinalCombination();
        for (int i = 0; i < ClassArray.length; i++) {
            double xx = ClassArray[i];
            System.out.println("Class" + " " + xx + " " + "Probability:" + " " + Cvalue/XCvalue/YCvalue);
        }
    }
}