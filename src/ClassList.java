import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassList {
    List<ClassInfo> data = new ArrayList<>();
    List<String> className = new ArrayList<>();
    List<String> classNum = new ArrayList<>();
    List<Integer> gradeList = new ArrayList<>();
    List<Integer> mClass = new ArrayList<>();
    List<Integer> sClass = new ArrayList<>();

    float[][] time = new float[18][6];
    float[][] timeAlpa = new float[1][18];
    float[][] timeNum = new float[1][18];
    int startClass = 9;
    int timeCount = 0;
    int dayCount;
    int[][] timeCheck = new int[100][9];
    int[][] dayCheck = new int[400][7];
    int dataCount = 0;

    public ClassList() {
        for (int i = 0; i < timeAlpa[0].length; i++) {
            if (i % 2 == 0) {
                timeAlpa[0][i] = (i / 2) + 1;
            } else {
                timeAlpa[0][i] = (i / 2) + 1;
            }
        }

        for (int i = 0; i < timeNum[0].length; i++) {
            timeNum[0][i] = startClass;
            startClass += 0.5;
        }

        // 파일 읽기
        try (
            BufferedReader br = new BufferedReader(new FileReader("2024_sub.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int timeShort = 0;
                String[] cuttingLine = line.split(",");
                for (String m : cuttingLine) {
                    for (int k = 0; k < timeAlpa[0].length; k++) {
                        int result = m.indexOf((int) timeAlpa[0][k]);
                        if (result != -1) {
                            timeCheck[timeCount][timeShort] = (int) timeNum[0][k];
                            timeShort++;
                        }
                    }
                }

                dayCount = 0;
                for (int k = 0; k < 6; k++) {
                    int dayResult = cuttingLine[35].indexOf(k);
                    if (dayResult != -1) {
                        for (int m = 0; m < dayCount + 1; m++) {
                            if (dayCheck[dataCount][m] == k) {
                                continue;
                            } else {
                                dayCheck[dataCount][dayCount] = k;
                                dayCount++;
                            }
                        }
                    }
                }

                ClassInfo classInfo = new ClassInfo();
                classInfo.grade = Integer.parseInt(cuttingLine[3]);
                classInfo.gradePoint = Integer.parseInt(cuttingLine[23]);
                classInfo.classBeginF = Float.parseFloat(cuttingLine[1]);
                classInfo.classEndF = Float.parseFloat(cuttingLine[3]);
                classInfo.classBeginS = Float.parseFloat(cuttingLine[4]);
                classInfo.classEndS = Float.parseFloat(cuttingLine[5]);
                classInfo.classDateF = Float.parseFloat(cuttingLine[6]);
                classInfo.classDateS = Integer.parseInt(cuttingLine[7]);
                classInfo.kind = Integer.parseInt(cuttingLine[8]);
                classInfo.max = Integer.parseInt(cuttingLine[9]);
                classInfo.now = Integer.parseInt(cuttingLine[10]);

                data.add(classInfo);
                className.add(cuttingLine[15]);
                classNum.add(cuttingLine[11]);
                dataCount++;
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

}
