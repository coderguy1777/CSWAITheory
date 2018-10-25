/*
   A Program that does Gaussian Naive bayes for a given set of data with the goal of doing
   Gaussian Naive Bayes to find the Probability of the point itself being in the dataset.
   By @Jordan Hill
   Period for doing AI Theory: F Period.
 */

//All the Imports I used for the project of doing Gaussian Naive Baye Heuristics.
import java.util.*;
import java.io.*;

public class GaussianNaiveBayesNew {
    //All the Static Arrays I used for Storing Points and User input for the program to use later on.
    private static ArrayList<ArrayList<Double>> dataset = new ArrayList<>();
    private static Double[] XandYArray;
    private static double[] XCoordinates;
    private static double[] YCoordinatess;
    private static ArrayList<Double>XCoordinatess = new ArrayList<Double>();
    private static ArrayList<Object> ClassXCoordinates = new ArrayList<Object>();
    private static ArrayList<Double> XandYPointStorage = new ArrayList<Double>();
    private static ArrayList<Double> YCoordinates = new ArrayList<Double>();
    private static ArrayList<Integer>ClassValues = new ArrayList<Integer>();
    private static ArrayList<Integer>ClassValues2 = new ArrayList<Integer>();
    private static double[]ClassValuesArray;

    //All the Static Integers and Doubles I used for my Program when making it.
    private static int trueclassnumber;
    private static double Coordinate1; //Value for Storing X Coordinates.
    private static double Coordinate2; //Value for Storing Y Coordinates.
    private static double xvalue1; //Value for the User input for the X Points to be used to calculate the final probability.
    private static double yvalue2; //Value for the User input for the Y Points to be used to calculate the final probability.
    private static double SumofX; //Used to find the sum of all the X Coordinates, mainly to be used for finding the mean value of the X Coordinates.
    private static double MeanofXCoordinates; //Value that stores the Mean of the X Coordinates once the SumofX is divided by 4 to find the mean of the X Coordinates.
    private static double VarianceofXCoordinates; //Value that stores the Variance of the X Coordinates, which is simply standard deviation^2 in this case.
    private static double SumofY;//Used to find the sum of all the Y Coordinates, mainly to be used for finding the mean value of the Y Coordinates.
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
        int i = 0;
        int x = 0;
        UserInput();
    }

    private static void DataSetReadingConfirmation() {
        String a = "Data has been read";
        System.out.println(a);
    }

    private static ArrayList<ArrayList<Double>> FileReader() {
        try {
            Scanner scan = new Scanner(new BufferedReader(new FileReader("data")));
            dataset = new ArrayList<>();
            while (scan.hasNext()) {
                trueclassnumber = scan.nextInt();
                if (dataset.size() < trueclassnumber + 1 ) {
                    dataset.add(new ArrayList<Double>());
                }
                if(dataset.size() < Coordinate1) {
                    dataset.add(new ArrayList<Double>());
                }
                Coordinate1 = scan.nextDouble();
                Coordinate2 = scan.nextDouble();

                dataset.get(trueclassnumber).add(Coordinate1);

                XCoordinatess.add(Coordinate1);
                YCoordinates.add(Coordinate2);
                ClassValues.add(trueclassnumber);
                System.out.println(dataset.get(0));
                System.out.println(dataset.get(0));
                while (!scan.hasNext()) {
                    DataSetReadingConfirmation();
                    break;
                }
            }
            return dataset;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<ArrayList<Integer>> UserInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Enter X:");
        xvalue1 = scan.nextDouble();
        System.out.println("You entered:" + " " + xvalue1 + "for x, now please enter Y.");
        yvalue2 = scan.nextDouble();
        System.out.println("You entered:" + " " + xvalue1 + "," + yvalue2 + " " + "for both points for the Gaussian Naive bayes to Solve for, please confirm this is correct, by typing Y or N.");
        String Input = scan.next();
        if (Input.equals("Y")) {
            System.out.println("The Program will now calculate the probability that your points exist, please wait while this is done.");
            FileReader();
            BackFunctions();
            ClassValueloop();
        } else if (Input.equals("N")) {
            System.out.println("The Program will now end.");
            boolean endinput = true;
            while (endinput) {
                break;
            }
        } else if (Input.equals("y")) {
            System.out.println("The Program will now calculate the probability of your points.");
            FileReader();
            BackFunctions();
        } else if (Input.equals("n")) {
            boolean endinput = true;
            while (endinput) {
                break;
            }
        }
        return null;
    }

    private static void ClassValueloop() {
        for (int xx = 0; xx < ClassValues.size(); xx++) {
            if (xx < trueclassnumber + 1) {
                ClassValues2.add(xx);
            }
        }
    }

    private static void XandYPointStorageArray() {
        XandYArray = new Double[XandYPointStorage.size()];
        for (int i = 0; i < XandYPointStorage.size(); i++) {
            XandYArray[i] = XandYPointStorage.get(i);
        }

    }

    private static void ClassValuesArray() {
        ClassValuesArray = new double[ClassValues2.size()];
        for(int i = 0; i < ClassValues2.size(); i++) {
            ClassValuesArray[i] = ClassValues2.get(i);
        }
    }


    private static void XCoordinateArray() {
        XCoordinates = new double[XCoordinatess.size()];
        for (int i = 0; i < XCoordinatess.size(); i++) {
            XCoordinates[i] = XCoordinatess.get(i);
        }
    }

    private static void YCoordinateArray() {
        YCoordinatess = new double[YCoordinates.size()];
        for (Double i = YCoordinates.get(0); i < YCoordinates.size(); i++) {
            int iiii = 0;
        }
    }

    private static void BackFunctions() {
        ClassValueloop();
        ClassValuesArray();

        XandYPointStorageArray();
        XCoordinateArray();
        YCoordinateArray();
        SumofXFramework();
        SumofYFramework();
        MeanofXCoordinatesFramework();
        MeanofYCoordinatesFramework();
        Probabilityprintout();
    }

    private static double SumofXFramework() {
        SumofX = 0;
        for (int i = 0; i < XCoordinates.length; i++) {
            SumofX += XCoordinates[i];
        }
        return SumofX;
    }

    private static double SumofYFramework() {
        SumofY = 0;
        for (int i = 0; i < dataset.size(); i++) {
            SumofY += YCoordinatess[i];
        }
        return SumofY;
    }

    private static void MeanofXCoordinatesFramework() {
        int i = 1;
        MeanofXCoordinates = SumofX / XCoordinatess.size();
    }

    private static void MeanofYCoordinatesFramework() {
        int i = 1;
        MeanofYCoordinates = SumofY / YCoordinates.size();
    }

    private static double VarianceFrameworkForXCoordinates(ArrayList<Double> XCoordinatess) {
        double variance = 0;
        VarianceofXCoordinates = 0;
        double meanformethod1 = MeanofXCoordinates;
        for (double pointvalue1 : XCoordinatess) {
            variance = Math.pow((pointvalue1 * 1.0 - meanformethod1), 2);
            VarianceofXCoordinates += variance;
        }
        VarianceofXCoordinates = VarianceofXCoordinates / (XCoordinatess.size() - 1);
        return VarianceofXCoordinates;
    }

    private static double VarianceFrameWorkForYCoordinates(ArrayList<Double> YCoordinates) {
        double variance2 ;
        VarianceofYCoordinates = 0;
        double meanformethod2 = MeanofYCoordinates;
        for (double pointvalue : YCoordinates) {
            variance2 = Math.pow((pointvalue * 1.0 - meanformethod2), 2);
            VarianceofYCoordinates += variance2;
        }
        VarianceofYCoordinates = VarianceofYCoordinates / (YCoordinates.size() - 1);
        return VarianceofYCoordinates;
    }

    private static void FinalFormula() {
        double Variance1 = VarianceFrameworkForXCoordinates(XCoordinatess);
        double Variance2 = VarianceFrameWorkForYCoordinates(YCoordinates);
        VarianceforBothXandYCoordinates = Variance1 + Variance2;
        double sigmax = MeanofXCoordinates;
        double sigmay = MeanofYCoordinates;
        MeanofEntireTrainingDataSet = sigmax + sigmay;
        double firstvalue = 1 / Math.sqrt(2 * 3.14 * Variance1);
        double secondvalue = 1 / Math.sqrt(2 * 3.14 * Variance2);
        double exponentvalue1 = 1.0 * (Math.pow(xvalue1 - MeanofXCoordinates, 2) / 2 * Variance1);
        double exponentvalue2 = 1.0 * (Math.pow(yvalue2 - MeanofYCoordinates, 2) / 2 * Variance2);
        double multiplyingvalue1 = firstvalue * exponentvalue1;
        double multiplyingvalue2 = secondvalue * exponentvalue2;
        FinalPartforX = multiplyingvalue1 * multiplyingvalue1;
        FinalPartforY = multiplyingvalue2 * multiplyingvalue2;
        int cvalue = XCoordinatess.size() + YCoordinates.size();
        secondcvalue = cvalue/ClassValues.size();
        FinalPartofFormula1 = secondcvalue / FinalPartforX / FinalPartforY;
        FinalPartofFormula2 = yvalue2 / FinalPartforX / FinalPartforY;
    }

    private static void Probabilityprintout() {
        FinalFormula();
        for(int i = 0; i < ClassValues2.size(); i++) {
            double xx = ClassValuesArray[i];
            System.out.println("Class" + " " + xx + " " + "Probability:" + " " + FinalPartofFormula1);
            System.out.println(ClassValues2.get(i));
            System.out.println(dataset.get(i));
        }
    }
}