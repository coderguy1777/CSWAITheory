/*
   A Program that does Gaussian Naive bayes for a given set of data w
 */
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class GaussianNaiveBayes {
    //All the Static Arrays I used for Storing Points and User input for the program to use later on.
    private static Double[] XandYArray;
    private static double[] XCoordinates;
    private static Double[] YCoordinatess;
    private static ArrayList<Double> Coordinates = new ArrayList<Double>();
    private static ArrayList<Double> dataset = new ArrayList<Double>();
    private static ArrayList<Double> XCoordinatess = new ArrayList<Double>();
    private static ArrayList<Double> XandYPointStorage = new ArrayList<Double>();
    private static ArrayList<Double> YCoordinates = new ArrayList<Double>();
    private static ArrayList<Double> FinalValues = new ArrayList<Double>();

    //All the Static Integers and Doubles I used for my Program when making it.
    private static int trueclassnumber;
    private static double Coordinate1;
    private static double Coordinate2;
    private static double xvalue1;
    private static double yvalue2;
    private static double SumofX;
    private static double MeanofXCoordinates;
    private static double VarianceofXCoordinates;
    private static double SumofY;
    private static double MeanofYCoordinates;
    private static double VarianceofYCoordinates;
    public static double MeanofEntireTrainingDataSet;
    private static double VarianceforBothXandYCoordinates;
    private static double fractionaldivider1;
    private static double fractionaldivider2;
    private static double bottomHalfofFormula;
    private static double squarerootingofformula;
    private static double muforx;
    private static double mufory;
    private static double exponent1offormula;
    private static double exponent2offformula;
    private static double FinalPartforX;
    private static double FinalPartforY;


    /*
      @param: the first parameter of this method is to trigger the user input of the program.
      @param: the second parameter of this method is to use the user input method and ask for X and Y inputs.
      @return value: the return value of this method is using the user input methods and storing those values
      into an ArrayList and then Array once that next method is triggered.
     */
    public static void main(String[] args) {
        UserInput();
    }

    /*
      @param: The First Parameter of this method is to check if the dataset has been read properly.
      @param: The Second Parameter of this Method is to then go into its return value.
      @return value: the return value is a confirmation message that will say if the data has been read,
      with that message being "Data Has been Read.", after which the program will continue as normal.
     */
    private static void DataSetReadingConfirmation() {
        String a = "Data has been read";
        System.out.println(a);
    }

    /*
      @param: The First Parameter of this method is using the declared Arraylist of Doubles, and scans into the data file giiven
      , which is data.txt in this case.
      @param: The Second Parameter of this method is scanning the .txt file given, which contains all the data points that
      the program uses to calculate the probabilitys using the Gaussian Naive Bayes formula.
      @return: the return value of this method is
     */

    private static ArrayList<ArrayList<Double>> FileReader() {
        try {
            Scanner scan = new Scanner(new BufferedReader(new FileReader("data")));
            ArrayList<ArrayList<Integer>> Coordinates = new ArrayList<ArrayList<Integer>>();
            ArrayList<ArrayList<Double>> dataset = new ArrayList<ArrayList<Double>>();

            while (scan.hasNext()) {
                trueclassnumber = scan.nextInt();
                if (dataset.size() < trueclassnumber + 1) {
                    dataset.add(new ArrayList<Double>());
                    Coordinates.add(new ArrayList<Integer>());

                }
                Coordinate1 = scan.nextDouble();
                Coordinate2 = scan.nextDouble();

                dataset.get(trueclassnumber).add(Coordinate1);
                dataset.get(trueclassnumber).add(Coordinate2);
                XCoordinatess.add(Coordinate1);
                YCoordinates.add(Coordinate2);
                System.out.println(trueclassnumber + " " + Coordinate1 + " , " + Coordinate2);

                while (!scan.hasNext()) {
                    DataSetReadingConfirmation();
                    break;
                }
                BackFunctions();
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

    private static void XandYPointStorageArray() {
        XandYArray = new Double[XandYPointStorage.size()];
        for (int i = 0; i < XandYPointStorage.size(); i++) {
            XandYArray[i] = XandYPointStorage.get(i);
        }

    }

    private static void XCoordinateArray() {
        XCoordinates = new double[XCoordinatess.size()];
        for (int i = 0; i < XCoordinatess.size(); i++) {
            XCoordinates[i] = XCoordinatess.get(i);
        }
    }

    /*
    @param:
    @param:
    @return:
     */
    private static void YCoordinateArray() {
        YCoordinatess = new Double[YCoordinates.size()];
        for (int i = 0; i < YCoordinates.size(); i++) {
            YCoordinatess[i] = YCoordinates.get(i);
        }
    }

    /*
    @param:
    @param:
    @return:
     */
    private static void BackFunctions() {
        XandYPointStorageArray();
        XCoordinateArray();
        YCoordinateArray();
        SumofXFramework();
        SumofYFramework();
        MeanofXCoordinatesFramework();
        MeanofYCoordinatesFramework();
        MeanValueoftheDataSet();
        VarianceforbothXandY();
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
        MeanofXCoordinates = SumofX / 4 * i;
    }

    /*
     @param: The First Parameter of this method is taking the SumofY then dividing that by 4.
     @param: The Second Parameter of this method is taking the divided SumofY divided by 4 and multiplying that by 1.
     @return: The Return Value of this Method is adding the SumofY Divided by 4 and Multiplying that by one, and then
     assigning that to the static double of MeanofYCoordinates, allowing it to be used throughout the program.
    */
    private static void MeanofYCoordinatesFramework() {
        int i = 1;
        MeanofYCoordinates = SumofY / 4 * i;
    }

    /*
    @param: The First parameter of this method 
    @param:
    @return:
     */
    private static void MeanValueoftheDataSet() {
        MeanofEntireTrainingDataSet = MeanofXCoordinates + MeanofYCoordinates;
    }

    /*
    @param:
    @param:
    @return:
     */
    private static double VarianceFrameworkForXCoordinates(ArrayList<Double> XCoordinatess) {
        double variance = 0;
        VarianceofXCoordinates = 0;
        double average = MeanofXCoordinates;
        for (double num : XCoordinatess) {
            variance = Math.pow((num * 1.0 - average), 2);
            VarianceofXCoordinates += variance;
        }
        VarianceofXCoordinates = VarianceofXCoordinates / (XCoordinatess.size() - 1);
        return VarianceofXCoordinates;
    }

    /*
    @param:
    @param:
    @return:
     */
    private static double VarianceFrameWorkForYCoordinates(ArrayList<Double> YCoordinates) {
        double variance = 0;
        VarianceofYCoordinates = 0;
        double meanformethod = MeanofYCoordinates;
        for (double pointvalue : YCoordinates) {
            variance = Math.pow((pointvalue * 1.0 - meanformethod), 2);
            VarianceofYCoordinates += variance;
        }
        VarianceofYCoordinates = VarianceofYCoordinates / (YCoordinates.size() - 1);
        return VarianceofYCoordinates;
    }

    /*
    @param:
    @param:
    @return:
     */
    private static void VarianceforbothXandY() {
        double Variance1 = VarianceFrameworkForXCoordinates(XCoordinatess);
        double Variance2 = VarianceFrameWorkForYCoordinates(YCoordinates);
        VarianceforBothXandYCoordinates = Variance1 + Variance2;
        double sigmax = MeanofXCoordinates;
        double sigmay = MeanofYCoordinates;
        MeanofEntireTrainingDataSet = sigmax + sigmay;
        double firstvalue = 1 / Math.sqrt(2 * 3.14 * Variance1);
        double secondvalue = 1 / Math.sqrt(2 * 3.14 * Variance2);
        double exponentvalue1 = -1.0 * (Math.pow(xvalue1 - MeanofXCoordinates, 2) / 2 * Variance1);
        double exponentvalue2 = -1.0 * (Math.pow(yvalue2 - MeanofYCoordinates, 2) / 2 * Variance2);
        double multiplyingvalue1 = firstvalue * exponentvalue1;
        double multiplyingvalue2 = secondvalue * exponentvalue2;
        double finalvalue1 = multiplyingvalue1 * multiplyingvalue1;
        double finalvalue2 = multiplyingvalue2 * multiplyingvalue2;
        double finalpartofformula = xvalue1 / finalvalue1 / finalvalue2;
        double finalpartofformula2 = yvalue2 / finalvalue1 / finalvalue2;
        System.out.println(finalpartofformula);
        System.out.println(finalpartofformula2);

    }
}