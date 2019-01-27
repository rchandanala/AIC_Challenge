import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String args[]) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner input = new Scanner(System.in);
        StringBuilder jsonString = new StringBuilder();

        while (input.hasNext()) {
            jsonString.append(input.nextLine());
        }

        Report report = parseReport(jsonString.toString());
        List<ReportCard> reportCards = convertReport(report);
        Collections.sort(reportCards);
        reportCards.stream().forEach(reportCard -> System.out.println(reportCard));
    }

    private static List<ReportCard> convertReport(Report report) {

        return report.getReport().stream()
                .map(studentEnrollment -> {
                    return studentEnrollment.getSubject().stream()
                            .filter(subject -> subject.grade != 'F')
                            .map(subject -> new ReportCard(subject.code, subject.grade, studentEnrollment.enrollment, studentEnrollment.getName()))
                            .collect(Collectors.toList());
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static Report parseReport(String jsonString) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonString, Report.class);
    }

    private static class Report {
        private List<StudentEnrollment> report;

        public List<StudentEnrollment> getReport() {
            return report;
        }

        public void setReport(List<StudentEnrollment> report) {
            this.report = report;
        }
    }

    private static class StudentEnrollment {
        private String enrollment;
        private String name;
        private List<Subject> subject;

        public String getEnrollment() {
            return enrollment;
        }

        public void setEnrollment(String enrollment) {
            this.enrollment = enrollment;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Subject> getSubject() {
            return subject;
        }

        public void setSubject(List<Subject> subject) {
            this.subject = subject;
        }
    }

    private static class Subject {
        private String code;
        private char grade;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public char getGrade() {
            return grade;
        }

        public void setGrade(char grade) {
            this.grade = grade;
        }
    }

    private static class ReportCard implements Comparable<ReportCard> {
        private final String code;
        private final char grade;
        private final String enrollment;
        private final String name;

        public ReportCard(String code, char grade, String enrollment, String name) {
            this.code = code;
            this.grade = grade;
            this.enrollment = enrollment;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public char getGrade() {
            return grade;
        }

        public String getEnrollmentId() {
            return enrollment;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(ReportCard o) {

            if (getCode().compareTo(o.getCode()) != 0) {
                return getCode().compareTo(o.getCode());
            }
            if (Character.compare(getGrade(), o.getGrade()) != 0) {
                return Character.compare(getGrade(), o.getGrade());
            }
            return getEnrollmentId().compareTo(o.getEnrollmentId());
        }

        @Override
        public String toString() {
            return code + " " + grade + " " + enrollment + " " + name;
        }
    }
}