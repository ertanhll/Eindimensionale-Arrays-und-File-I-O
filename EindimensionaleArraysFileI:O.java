/*
    Aufgabe 2) Eindimensionale Arrays und File I/O
*/

import java.awt.*;

public class Aufgabe2 {

    private static String[] readFileData(String fileName, int lineStart, int lineEnd) {
        In fileReader = new In(fileName);
        String[] output = new String[lineEnd - lineStart + 1];

        for (int i = lineStart; i > 1; i--) {
            fileReader.readLine();
        }

        for (int i = 0; i <= lineEnd - lineStart; i++) {
            output[i] = fileReader.readLine();
            //System.out.println(output[i]);
        }

        fileReader.close();
        return output; //Zeile kann geändert oder entfernt werden.
    }

    private static void extractData(String[] dataArray, int[] resultArray, int numColumn, int entriesPerYear) {

        int monthSum = 0;
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < entriesPerYear; j++) {
                String[] columnArr = dataArray[i * entriesPerYear + j].split(";");
                monthSum += Integer.parseInt(columnArr[numColumn]); //(-1)
            }
            resultArray[i] = monthSum;
            monthSum = 0;

        }
    }


    private static void drawChart(int[] frostDays, int[] iceDays, int[] summerDays, int[] heatDays) {
        int width = 1230;
        int height = 500;
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(-height / 2, height / 2);


        if (frostDays == null || iceDays == null || summerDays == null || heatDays == null) {
            System.out.println("Ein Array ist leer.");
        }
        int barWidth = 20;
        int year = 78;
        Font font = new Font("Times", Font.PLAIN, 10);


        for (int i = 1; i <= 40; i++) {

            StdDraw.setPenRadius(0.005);
            StdDraw.setPenColor(Color.red);
            StdDraw.rectangle(((20 + barWidth / 2) * i), summerDays[i - 1], barWidth / 2, summerDays[i - 1]);
            StdDraw.filledRectangle(((20 + barWidth / 2) * i), heatDays[i - 1], barWidth / 4, heatDays[i - 1]);

            StdDraw.setPenColor(Color.blue);
            StdDraw.rectangle(((20 + barWidth / 2) * i), -frostDays[i - 1], barWidth / 2, frostDays[i - 1]);
            StdDraw.filledRectangle(((20 + barWidth / 2) * i), -iceDays[i - 1], barWidth / 4, iceDays[i - 1]);

            StdDraw.setPenColor(Color.black);
            StdDraw.setFont(font);

            year++;
            if (year == 100) {
                year = 0;
                StdDraw.text((20 + barWidth / 2) * i, -height / 2 + 20, "0" + year);
            } else if (year < 10) {
                StdDraw.text((20 + barWidth / 2) * i, -height / 2 + 20, "0" + year);
            } else
                StdDraw.text((20 + barWidth / 2) * i, -height / 2 + 20, "" + year);

        }
        StdDraw.setPenColor(Color.green);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(8, 0, width - 8, 0);

        for (int i = 1; i <= 4; i++) {
            StdDraw.setPenColor(Color.black);
            StdDraw.setFont(font);
            StdDraw.text(8, 50 * i, "" + 25 * i);
            StdDraw.text(width - 8, 50 * i, "" + 25 * i);
            StdDraw.text(8, -50 * i, "" + 25 * i);
            StdDraw.text(width - 8, -50 * i, "" + 25 * i);
        }
    }

    public static void main(String[] args) {
        int[] frostDays = new int[40];
        int[] iceDays = new int[40];
        int[] summerDays = new int[40];
        int[] heatDays = new int[40];

        extractData(readFileData("weather_data.csv", 290, 769), frostDays, 9, 12);
        extractData(readFileData("weather_data.csv", 290, 769), iceDays, 10, 12);
        extractData(readFileData("weather_data.csv", 290, 769), summerDays, 11, 12);
        extractData(readFileData("weather_data.csv", 290, 769), heatDays, 12, 12);

        drawChart(frostDays, iceDays, summerDays, heatDays);


    }
}


