import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentJsonParser {

    public static void main(String args[]) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner input = new Scanner(System.in);
        StringBuilder jsonString = new StringBuilder();

        while (input.hasNext()) {
            jsonString.append(input.nextLine());
        }

        StudentJsonParser studentJsonParser = new StudentJsonParser();
        System.out.println(studentJsonParser.generateSortedReport(jsonString.toString()));
    }

    public String generateSortedReport(String jsonString) {

        Report report = parseReport(jsonString);
        List<ReportCard> reportCards = convertReport(report);
        Collections.sort(reportCards);
        return reportCards.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }

    private List<ReportCard> convertReport(Report report) {

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

    private Report parseReport(String jsonString) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonString, Report.class);
    }

    private class Report {
        private List<StudentEnrollment> report;

        public List<StudentEnrollment> getReport() {
            return report;
        }

        public void setReport(List<StudentEnrollment> report) {
            this.report = report;
        }
    }

    private class StudentEnrollment {
        private String enrollment;
        private String name;
        private List<Subject> subject;

        private String getEnrollment() {
            return enrollment;
        }

        private void setEnrollment(String enrollment) {
            this.enrollment = enrollment;
        }

        private String getName() {
            return name;
        }

        private void setName(String name) {
            this.name = name;
        }

        private List<Subject> getSubject() {
            return subject;
        }

        private void setSubject(List<Subject> subject) {
            this.subject = subject;
        }
    }

    private class Subject {
        private String code;
        private char grade;

        private String getCode() {
            return code;
        }

        private void setCode(String code) {
            this.code = code;
        }

        private char getGrade() {
            return grade;
        }

        private void setGrade(char grade) {
            this.grade = grade;
        }
    }

    // AutoValue would have been easy.
    private class ReportCard implements Comparable<ReportCard> {
        private final String code;
        private final Character grade;
        private final String enrollment;
        private final String name;

        public ReportCard(String code, Character grade, String enrollment, String name) {
            this.code = code;
            this.grade = grade;
            this.enrollment = enrollment;
            this.name = name;
        }

        private String getCode() {
            return code;
        }

        private Character getGrade() {
            return grade;
        }

        private String getEnrollmentId() {
            return enrollment;
        }

        private String getName() {
            return name;
        }

        @Override
        public int compareTo(ReportCard reportCard) {

            if (!getCode().equals(reportCard.getCode())) {
                return getCode().compareTo(reportCard.getCode());
            }
            if (!getGrade().equals(reportCard.getGrade())) {
                return Character.compare(getGrade(), reportCard.getGrade());
            }
            return getEnrollmentId().compareTo(reportCard.getEnrollmentId());
        }

        @Override
        public String toString() {
            return code + " " + grade + " " + enrollment + " " + name;
        }
    }
}