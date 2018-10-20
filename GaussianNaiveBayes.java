package com.company;
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
    private static Double[] XandYArray;
    private static double[] XCoordinates;
    private static double[] YCoordinatess;
    private static ArrayList<Double> dataset = new ArrayList<Double>();
    private static ArrayList<Double> XCoordinatess = new ArrayList<Double>();
    private static ArrayList<Double> XandYPointStorage = new ArrayList<Double>();
    private static ArrayList<Double> YCoordinates = new ArrayList<Double>();

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
    private static double FinalPartofFormula1; //Does the Combining of the X user input with X to find the probability of the X Coordinate given by the user to be in the dataset.
    private static double FinalPartofFormula2; //Does the Combining of the Y user input with Y to find the probability of the Y Coordinate given by the user to be in the dataset.


    /*
      @param: the first parameter of this method is to trigger the user input of the program.
      @param: the second parameter of this method is to use the user input method and ask for X and Y inputs.
      @return value: the return value of this method is using the user input methods and storing those values
      into an ArrayList and then Array once that next method is triggered.
     */
    public static void main(String[] args) {
        int i = 0;
        int x = 0;
        UserInput();
    }

    /*
      @param: The First Parameter of this method is using the declared Arraylist of Doubles, and scans into the data file giiven
      , which is data.txt in this case.
      @param: The Second Parameter of this method is scanning the .txt file given, which contains all the data points that
      the program uses to calculate the probabilitys using the Gaussian Naive Bayes formula.
      @return: the return value of this method is the X and Y point values stored within in the proper arrays that are needed
      for them to work and perform the proper function for the final formula for Gaussian Naive Bayes.
     */
    private static void XandYStorerforAllClasses(ArrayList<Double>dataset) {
        for(int i = 0; i < dataset.size(); i++) {
            XCoordinates[i] = XCoordinatess.get(i);
            YCoordinatess[i] = YCoordinates.get(i);
        }

    }

    /*
    @param: The First Parameter of this method is declaring the scanner, which is then used to scan the file, which
    will contain the datapoints that you need for making this method work at all in the first place.
    @param: The Second Parameter of this method is scanning the file itself, and dynammically generating Arraylists,
    which will be storing all the classes of the file, which are integers, into an array list, along with there respective coordinates
    that are with each class.
    @return: The Return value of this method is the data from the .txt file, which is then stored into the Arraylist which
    contains all the datapoints for the data that is used throughout this program for the method to work at all in the
    first place and do Gaussian Naive Bayes correctly and print them out in the right way for the correct probability of
    the point being in the file.
     */
    private static ArrayList<ArrayList<Double>> FileReader() {
        try {
            Scanner scan = new Scanner(new BufferedReader(new FileReader("data.txt")));
            ArrayList<ArrayList<Double>> dataset = new ArrayList<>();

            while (scan.hasNext()) {
                trueclassnumber = scan.nextInt();
                if (dataset.size() < trueclassnumber + 1) {
                    dataset.add(new ArrayList<Double>());
                }
                Coordinate1 = scan.nextDouble();
                Coordinate2 = scan.nextDouble();

                XCoordinatess.add(trueclassnumber, Coordinate1);
                YCoordinates.add(trueclassnumber, Coordinate2);

                while (!scan.hasNext()) {
                    break;
                }
            }
            return dataset;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    @param: The First Parameter of this method is that it takes the user input of X for finding the probability, and then asks
    for the Y User input
    @param: The Second Parameter of this method is taking both user inputs, and takes those and puts it into an
    arraylist, which the program can then take and use anywhere else in the program, once the values are then put into an array,
    which is later on in the program.
    @return: The Return value of this method is the values for the X and Y user input being stored in the Arraylist, that
    being the Array list XandYPointStorage<>();
     */
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
        } else if (Input.equals("N")) {
            System.out.println("The Program will now end.");
            boolean endinput = true;
            while (endinput) {
                break;
            }
        } else if (Input.equals("y")) {
            System.out.println("The Program will now calculate the probability of your points.");
            FileReader();
        } else if (Input.equals("n")) {
            boolean endinput = true;
            while (endinput) {
                break;
            }
        }
        return null;
    }

    /*
    @param: The First Parameter of this method is the make of a new Array for the use of the X and Y Point Storage,
    with those points being from the user input given, and then added to the array list for the storing of the user input
    values.
    @param: The Second Parameter of this method is running a for loop, mainly to add the indicies for each user input
    value into the Array, assigned XandYArray[0] and XandYArray[1]
    @return: The Return Value of this method is the User input values being stored in the XandYArray for use later on in the
    program for computing the final probability of the point being in the dataset in this case for this program.
     */

    /*
    @param:
    @param:
    @return:
     */
    private static void XCoordinateArray() {
        XCoordinates = new double[XCoordinatess.size()];
        for (int i = 0; i < XCoordinatess.size(); i++) {
            XCoordinates[i] = XCoordinatess.get(i);
        }
    }

    /*
    @param: The First parameter of this method is taking the size of the Arraylist that is storing the Y Coordinates, and
    and makes it into a new array for the use within making a Y Coordinate Array.
    @param:
    @return: The Return value of this method is all the Y Values from the Arraylist that is storing the Y Coordinates into
     */
    private static void YCoordinateArray() {
        YCoordinatess = new double[YCoordinates.size()];
        for (int i = 0; i < YCoordinates.size(); i++) {
            YCoordinatess[i] = YCoordinates.get(i);
        }
    }

    /*
    @param: The First parameter of this method is being called by the userinput method, and does
    all the functions for getting parts of the final formula ready to be used for this program.
    @param: The Second Parameter of this method is running all the methods called in it for the formula parts.
    @return: Return value is the compilation of the methods in the final formula method, which prints out the
    final probability of the class for each set of class points given.
     */
    private static void BackFunctions() {
        XandYStorerforAllClasses(dataset);
        XCoordinateArray();
        YCoordinateArray();
        SumofXFramework();
        SumofYFramework();
        MeanofXCoordinatesFramework();
        MeanofYCoordinatesFramework();
        FinalFormula();
    }

    /*
    @param:
    @param:
    @return:
     */
    private static double SumofXFramework() {
        SumofX = 0;
        for (int i = 0; i < XCoordinates.length; i++) {
            SumofX += XCoordinates[i];
        }
        return SumofX;
    }

    /*
    @param:
    @param:
    @return:
     */
    private static double SumofYFramework() {
        SumofY = 0;
        for (int i = 0; i < YCoordinatess.length; i++) {
            SumofY += YCoordinatess[i];
        }
        return SumofY;
    }

    /*
     @param: The First Parameter of this method is taking the SumofY then dividing that by 4.
     @param: The Second Parameter of this method is taking the divided SumofY divided by 4 and multiplying that by 1.
     @return: The Return Value of this Method is adding the SumofY Divided by 4 and Multiplying that by one, and then
     assigning that to the static double of MeanofYCoordinates, allowing it to be used throughout the program.
    */
    private static void MeanofXCoordinatesFramework() {
        int i = 1;
        MeanofXCoordinates = SumofX / XCoordinatess.size() * i;
    }

    /*
     @param: The First Parameter of this method is taking the SumofY then dividing that by 4.
     @param: The Second Parameter of this method is taking the divided SumofY divided by 4 and multiplying that by 1.
     @return: The Return Value of this Method is adding the SumofY Divided by 4 and Multiplying that by one, and then
     assigning that to the static double of MeanofYCoordinates, allowing it to be used throughout the program.
    */
    private static void MeanofYCoordinatesFramework() {
        int i = 1;
        MeanofYCoordinates = SumofY / YCoordinates.size() * i;
    }

    /*
    @param: The First Parameter of this Method is taking the Mean of the X Coordinates, and taking that value to be
    assigned to a double, average.
    @param: When that is done, a forEach loop occurs, in which a double assigned before the loop started, takes each value of the Class, and does Variance Parts with each value
    and when that is done, all the values are taken and added up for the Variance of the Class, which in this case is class 0.
    @return: The Return Value of this Method is the final value of the Variance for Class 0, which will be used later in the formula for the final probability.
     */
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

    /*
    @param: The First Parameter of this method is same as the last method, and takes the Mean for the Y values of the class given, and assigns that
    to the value of a double given.
    @param: The Second Parameter of this Method is doing a forEach loop, and the Same thing as the last forEach loop in the previous method.
    @return: The Return value of this method is the variance for the class 1 probability.
     */
    private static double VarianceFrameWorkForYCoordinates(ArrayList<Double> YCoordinates) {
        double variance2 = 0;
        VarianceofYCoordinates = 0;
        double meanformethod2 = MeanofYCoordinates;
        for (double pointvalue : YCoordinates) {
            variance2 = Math.pow((pointvalue * 1.0 - meanformethod2), 2);
            VarianceofYCoordinates += variance2;
        }
        VarianceofYCoordinates = VarianceofYCoordinates / (YCoordinates.size() - 1);
        return VarianceofYCoordinates;
    }

    /*
    @param: What this method does is combining each part of the formula and prepares that for the final formula printout
    of the formula for doing Gaussian Naive Bayes and finding the Probability of a point being int that dataset at all.
     */
    private static void FinalFormula() {
        //doubles used for assigning the Variance of X From its method, to the Double Variance 1, and for Y, which is double variance2 in this case
        double Variance1 = VarianceFrameworkForXCoordinates(XCoordinatess);
        double Variance2 = VarianceFrameWorkForYCoordinates(YCoordinates);
        //What this does is it takes the static double declared earlier on, and then combines Variance1 and 2 together to get total class probability
        VarianceforBothXandYCoordinates = Variance1 + Variance2;
        //Stores the Mean of X and Y into these doubles here.
        double sigmax = MeanofXCoordinates;
        double sigmay = MeanofYCoordinates;
        //Combines the mean of the entire training dataset for use later on in the final formual.
        MeanofEntireTrainingDataSet = sigmax + sigmay;
        double firstvalue = 1 / Math.sqrt(2 * 3.14 * Variance1);
        double secondvalue = 1 / Math.sqrt(2 * 3.14 * Variance2);
        //Solves for the exponent of the Formula, for it to be used later to combine the fractional part of the formula with the exponent.
        double exponentvalue1 = 1.0 * (Math.pow(xvalue1 - MeanofXCoordinates, 2) / 2 * Variance1);
        double exponentvalue2 = 1.0 * (Math.pow(yvalue2 - MeanofYCoordinates, 2) / 2 * Variance2);
        //Multiplys the Fractional Part of the Exponent
        double multiplyingvalue1 = firstvalue * exponentvalue1;
        double multiplyingvalue2 = secondvalue * exponentvalue2;
        //Since the Exponent I couldn't figure out on how to do it properly, these static doubles I assigned earlier in the program multiply
        //it by itself, so the exponent value can be computed into the formula correctly.
        FinalPartforX = multiplyingvalue1 * multiplyingvalue1;
        FinalPartforY = multiplyingvalue2 * multiplyingvalue2;
        //Combining of Formula for the final probability print out in the last method.
        FinalPartofFormula1 = trueclassnumber/ FinalPartforX / FinalPartforY; //Probability for X values being in Dataset.
        FinalPartofFormula2 = yvalue2 / FinalPartforX / FinalPartforY; //Probability for Y values being in Dataset.
        Probabilityprintout();
    }

    /*
    @param: The First Parameter of this method is calling the method above, FinalFormula(), and calls it to be used it to be run
    in the program so all values of it are computed correctly.
    @param: The Second Parameter of this method is taking each class, and doing the formula for each of the points in those classes
    and stores it for use within the final probability of the point being in the .txt file that was given to solve the formula for the
    probability of the point in this case.
    @return: The Return value of this method is the probability of the class value being in the mehtod, with it printing out the probability
    of the point tested for it be for either being in the X Values, or the Y Values, and prints the probability or each Class Value, and
    the Probability of the points being either in the X or Y of the .txt file that is given with classes, data, and returns the final probability via
    System.out.println(), and after that the program itself ends with the final probability of the point values having been calculated.
     */
    private static void Probabilityprintout() {
        System.out.println("Class" + " " + trueclassnumber + " " + "Probability:" + " " + FinalPartofFormula1);
    }
}