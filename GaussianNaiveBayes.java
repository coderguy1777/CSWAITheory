/*
A Program that does Gaussian Naive bayes for a given set of data with the goal of doing
Gaussian Naive Bayes to find the Probability of the point itself being in the dataset.
By @Jordan Hill
Period for doing AI Theory: F Period.
*/

//All the Imports I used for the project of doing Gaussian Naive Baye Heuristics.

import java.util.Collections;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class GaussianNaiveBayes {
    //All the Static Arrays I used for Storing Points and User input for the program to use later on.
    private static ArrayList<ArrayList<Double>> dataset = new ArrayList<>(); //Stores all the data points from the .txt file, data.
    private static ArrayList<Double> sumforx = new ArrayList<>(); //Stores all the sums for the X Points of each Class Value.
    private static ArrayList<Double> sumfory = new ArrayList<>(); //Stores all the sums for the Y Points of each Class Value.
    private static ArrayList<Double> meanx = new ArrayList<>(); //Stores all the means for the X Points of each Class Value.
    private static ArrayList<Double> meany = new ArrayList<>(); //Stores all the means for the Y Points of each Class Value.
    private static ArrayList<Double> variancevaluesx = new ArrayList<>(); //Stores all the variance values for the X Points of each Class Value.
    private static ArrayList<Double> variancevaluesy = new ArrayList<>(); //Stores all the variance values for the X Points of each Class Value.
    private static ArrayList<Double> bottomhalfforx = new ArrayList<>(); //Stores the fractional value of the gaussian naive bayes formula for x.
    private static ArrayList<Double> Exponentvalueforx = new ArrayList<>(); //Stores the exponent value of the gaussian naive bayes formula for x.
    private static ArrayList<Double> bottomhalffory = new ArrayList<>(); //Stores the fractional value of the gaussian naive bayes formula for y.
    private static ArrayList<Double> Exponentvaluefory = new ArrayList<>(); //Stores the exponent value of the gaussian naive bayes formula for y.
    private static ArrayList<Double> classxformula = new ArrayList<>(); //Stores the combined formula for x, that being the gaussian naive bayes formula.
    private static ArrayList<Double> classyformula = new ArrayList<>(); //Stores the combined formula for y, that being the gaussian naive bayes formula.
    private static ArrayList<Double> cvaluex = new ArrayList<>(); //Stores all the P(X|C) for each of the combined formulas for each Class Value.
    private static ArrayList<Double> cvaluey = new ArrayList<>(); //Stores all the P(Y|C) for each of the combined formulas for each Class Value.
    private static ArrayList<Double> finalcvalues = new ArrayList<>(); //Stores the combined C Values after the formula: P(C)/P(X|C)/P(Y|C) is done.


    //All the Static Integers and Doubles I used for my Program when making it.
    private static int trueclassnumber; //Prints Class Value.
    public static Double Coordinate1; //Value for Storing X Coordinates.
    public static Double Coordinate2; //Value for Storing Y Coordinates.
    public static Double meanforx; //Value used for storing the mean of x for each point set before being put into the MeanX ArrayList.
    public static Double meanfory; //Value used for storing the mean of y for each point set before being put into the MeanY ArrayList.
    private static double xvalue1; //Value for the User input for the X Points to be used to calculate the final probability.
    private static double yvalue2; //Value for the User input for the Y Points to be used to calculate the final probability.
    private static double sumofx; //Value used for storing the sum of x for each point set before being put into the SumforX ArrayList.
    private static double sumofy; //Value used for storing the mean of x for each point set before being put into the SynArrayList.


    /*
    @param: starts the program for the Gaussian Naive Bayes.
    @return: When called, the main method triggers the user input method for training datapoints that are used in the naive bayes and the probability calculations.
     */
    public static void main(String[] args) {
        int holder = 0; //Can delete, I just put this there to stop intellij from putting this method all into one line like it did, this variable does not contribute to the program at all what soever.
        UserInput(); // Triggers the method for user input of training data points.
    }

    /*
    @param: sorts each set of X Coordinates for each class value into the set of training data points.
    @param: sorts each set of Y Coordinates for each class value into the set of training data points.
    @return: returns the sorted set of training data points for the program to use with the Gaussian Naive Bayes formula.
     */

    private static ArrayList<ArrayList<Double>> DataReader() {
        try {
            Scanner scan = new Scanner(new BufferedReader(new FileReader("data"))); //Scans the training data file, note, u may need to change to data.txt if a file exception occurs.
            dataset = new ArrayList<>(); //Makes a new ArrayList for the Dataset.

            //Loop that scans the file and sorts the Data Points based on x and y point values for each unique class value.
            while (scan.hasNext()) {
                trueclassnumber = scan.nextInt(); //Scans for the class value.
                if (dataset.size() < trueclassnumber + 1) {
                    dataset.add(new ArrayList<>()); //Generates a new dynamic ArrayList for each class of points and there new value, and does this with each new class value scanned.
                }
                Coordinate1 = scan.nextDouble(); //Sorting variable for X Coordinates.
                Coordinate2 = scan.nextDouble(); //Sorting variable for Y Coordinates.
                dataset.get(trueclassnumber).add(Coordinate1); //Adds the X Coordinate to the training dataset with the class value for the proper class.
                dataset.get(trueclassnumber).add(Coordinate2); //Adds the Y Coordinate to the training dataset with the class value for the proper class.
            }

             /*
             Nested for loop that sums up all the class points values for each class of X Points for each unique class value, such
             as class 0, class 1, etc.
             */
            for (int ix = 0; ix < dataset.size(); ix++) {
                sumofx = 0;
                for(int ixx = 0; ixx < dataset.get(ix).size(); ixx = ixx + 2) {
                    sumofx = sumofx + dataset.get(ix).get(ixx); //Sums up the two datapoints in X for each unique class value.
                }
                sumforx.add(sumofx); //Adds it to the sumforx ArrayList.
            }

             /*
             Nested for loop that sums up all the class points values for each class of Y Points for each unique class value, such
             as class 0, class 1, etc.
             */
            for(int iy = 0; iy < dataset.size(); iy++) {
                sumofy = 0;
                for(int iyy = 1; iyy < dataset.get(iy).size(); iyy = iyy + 2) {
                    sumofy = sumofy + dataset.get(iy).get(iyy); //Sums up the two datapoints in X for each unique class value.
                }
                sumfory.add(sumofy); //Adds it to the sumforx ArrayList.
            }

            /*
            Finds the mean for the X Coordinate Sums for each unique Class Value.
             */
            for(int sumx = 0; sumx < sumforx.size(); sumx++) {
                meanforx = sumforx.get(sumx) / 2; //Finds the mean for each sum of x points for each unique class value.
                meanx.add(meanforx);
            }

            /*
            Finds the mean for the Y Coordinate Sums for each unique Class Value.
             */
            for(int sumy = 0; sumy < sumfory.size(); sumy++) {
                meanfory = sumfory.get(sumy) / 2; //Finds the mean for each sum of y points for each unique class value.
                meany.add(meanfory);
            }

            /*
            Finds the variance for the X Coordinate Means for each unique Class Value.
             */
            for(int varx = 0; varx < dataset.size(); varx++) {
                double variancex = 0;
                double meanvaluex = meanx.get(varx); //Gets the mean for x
                for(int varxx = 0; varxx < dataset.get(varx).size(); varxx = varxx + 2) {
                    double pointvaluex = dataset.get(varx).get(varxx);
                    variancex = Math.pow((pointvaluex * 1.0 - meanvaluex), 2) / 2; //Finds the variance for each sum of x points for each unique class value.
                }
                variancevaluesx.add(variancex);
            }

            /*
            Finds the variance for the Y Coordinate Means for each unique Class Value.
             */
            for(int vary = 0; vary < dataset.size(); vary++) {
                double variancey = 0;
                double meanvaluey = meany.get(vary); //Gets the mean for y
                for(int varyy = 1; varyy < dataset.get(vary).size(); varyy = varyy + 2) {
                    double pointvaluey = dataset.get(vary).get(varyy);
                    variancey = Math.pow((pointvaluey * 1.0 - meanvaluey), 2) / 2; //Finds the variance for each sum of y points for each unique class value.
                }
                variancevaluesy.add(variancey);
            }

            /*
            Finds the exponent value of the formula that uses the training data point given by the user, and the non exponent value, the
            fractional value as well to be used later on for each unique class value when are they combined together for x points and there
            unique class values that the x points have.
             */
            for(int finalvalues = 0; finalvalues < dataset.size(); finalvalues++) {
                double testingvaluex = xvalue1;
                double meanxpart = meanx.get(finalvalues);
                double variancexvalue = variancevaluesx.get(finalvalues);
                double bottomhalfofformulax = 0;
                double exponentformulavalue = 0;
                bottomhalfofformulax = 1 / Math.sqrt(2 * Math.PI * meanxpart);
                exponentformulavalue = Math.pow(testingvaluex - variancexvalue, 2) / 2 * variancexvalue * -1;
                bottomhalfforx.add(bottomhalfofformulax);
                Exponentvalueforx.add(exponentformulavalue);
            }

            /*
            Finds the exponent value of the formula that uses the training data point given by the user, and the non exponent value, the
            fractional value as well to be used later on for each unique class value when are they combined together for y points and there
            unique class values that the y points have.
             */
            for(int finalvaluesy = 0; finalvaluesy < dataset.size(); finalvaluesy++) {
                double testvaluey = yvalue2;
                double meanypart = meany.get(finalvaluesy);
                double varianceyvalue = variancevaluesy.get(finalvaluesy);
                double BottomHalfOftheFormulaY = 0;
                double ExponentvalueforY = 0;
                BottomHalfOftheFormulaY = 1 / Math.sqrt(2 * Math.PI * meanypart);
                ExponentvalueforY = Math.pow(testvaluey - varianceyvalue, 2) / 2 * varianceyvalue * -1;
                bottomhalffory.add(BottomHalfOftheFormulaY);
                Exponentvaluefory.add(ExponentvalueforY);
            }

            /*
            Using the Exponent value and fractional value found, they are all combined here, to be used to eventually
            find the P(X|C) in the combination of x and y for each class, and here the formula is solved so that P(X|C) can
            be found using the combined formula for class x.
             */
            for(int combinedx = 0; combinedx < dataset.size(); combinedx++) {
                double exponentx = Exponentvalueforx.get(combinedx);
                double fractionalvaluex = bottomhalfforx.get(combinedx);
                double totalclassformulax = 0;
                totalclassformulax = Math.pow(fractionalvaluex, exponentx);
                classxformula.add(totalclassformulax);
            }

            /*
            Using the Exponent value and fractional value found, they are all combined here, to be used to eventually
            find the P(Y|C) in the combination of x and y for each class, and here the formula is solved so that P(Y|C) can
            be found using the combined formula for class y.
             */
            for(int combinedy = 0; combinedy < dataset.size(); combinedy++) {
                double exponenty = Exponentvaluefory.get(combinedy);
                double fractionalvaluey = bottomhalffory.get(combinedy);
                double totalclassformulay = 0;
                totalclassformulay = Math.pow(fractionalvaluey, exponenty);
                classyformula.add(totalclassformulay);
            }

            /*
            In this for loop, the value P(X|C) is found for each class values, using the combined formulas for
            each unique set of datapoints, and uses that combined formula to find P(X|C).
             */
            for(int classcombinationx = 0; classcombinationx < dataset.size(); classcombinationx++) {
                double meanxvalue = meanx.get(classcombinationx);
                double variancexvalue = variancevaluesx.get(classcombinationx);
                double xtrainingpoint = xvalue1;
                double xcvalue = 0;
                xcvalue = xtrainingpoint / meanxvalue / variancexvalue;
                cvaluex.add(xcvalue);
            }

            /*
            In this for loop, the value P(Y|C) is found for each class values, using the combined formulas for
            each unique set of datapoints, and uses that combined formula to find P(Y|C).
             */
            for(int classcombinationy = 0; classcombinationy < dataset.size(); classcombinationy++) {
                double varianceyvalue = variancevaluesy.get(classcombinationy);
                double meanyvalue = meany.get(classcombinationy);
                double filler = 0; // For me mainly, u can delete this, but if it wasn't here, the code above and below would be underlined yellow and say duplicated code, and be super annoying to look at, so feel free to delete this variable, as it is not needed in the program at all to function.
                double ytrainingpoint = yvalue2;
                double ycvalue = 0;
                ycvalue = ytrainingpoint / meanyvalue / varianceyvalue;
                cvaluey.add(ycvalue);
            }

            /*
            In this for loop, the value P(C) is found, to be used in the combining formula, that being P(C)/P(X|C)/P(Y|C) when that formual is done.
             */
            for(int multidimension = 0; multidimension < dataset.size(); multidimension++) {
                double cvalue = 0;
                cvalue = dataset.get(multidimension).size() / 2;
                double cvalueforx = cvaluex.get(multidimension);
                double cvaluefory = cvaluey.get(multidimension);
                double finalcvalue = 0;
                finalcvalue = cvalue / cvalueforx / cvaluefory; // The Formula: P(C)/P(X|C)/P(Y|C) is done here for each class and each unique value of P(X|C) and P(Y|C) is used here as well in this formula for finding the probability of a training data point being present here within this dataset of training datapoints in any of the class values given here in this case.
                finalcvalues.add(finalcvalue);
            }

            /*
            Prints the Probability for either of the training data points given by the user for either x and y, and prints the probability of the points being in the training data set for each unique class value.
             */
            for(int classprobability = 0; classprobability < dataset.size(); classprobability++) {
                double classprob = finalcvalues.get(classprobability); // Assigns a double, Classprob, to print the Class's Probability of containing any of the given training data points given by the user.
                System.out.println("Class " + classprobability +":" + " " + classprob); // Prints the Class's Probability of containing any of the given training data points given by the user.
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    @param: First Parameter of this method is asking for an X training data point.
    @param: Second Parameter of this method is asking for a Y training data point.
    @return: Return value of this method is those points used with the DataReader() method,
    and are used in the final GaussianNaiveBayes formula for calculating point probability.
     */
    private static void UserInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Enter X:"); // Asks for an X training data point.
        xvalue1 = scan.nextDouble();
        System.out.println("You entered:" + " " + xvalue1 + "for x, now please enter Y."); //Asks for a Y training data point.
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
}