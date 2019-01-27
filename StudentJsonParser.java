package com.apple.amp.ae.aggregate.management.services;

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
//        StringBuilder jsonString = new StringBuilder();
        StringBuilder jsonString = new StringBuilder("{\"report\":[{\"enrollment\":\"rit2011001\",\"name\":\"Julia\",\"subject\":[{\"code\":\"DSA\",\"grade\":\"A\"}]},{\"enrollment\":\"rit2011020\",\"name\":\"Samantha\",\"subject\":[{\"code\":\"COM\",\"grade\":\"B\"},{\"code\":\"DSA\",\"grade\":\"A\"}]}]}");


//        while (input.hasNext()) {
//            jsonString.append(input.nextLine());
//        }

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

//    public static void main(String args[]) throws Exception {
//        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
//        Scanner input = new Scanner(System.in);
//        StringBuilder jsonString = new StringBuilder();
//                //new StringBuilder("{\"reports\":[{\"enrollment\":\"sdfsd\",\"name\":\"asdf\",\"subjects\":[{\"code\":\"ab\",\"grade\":\"a\"},{\"code\":\"bc\",\"grade\":\"b\"}]},{\"enrollment\":\"sdfsd1\",\"name\":\"asdf1\",\"subjects\":[{\"code\":\"ab\",\"grade\":\"a\"},{\"code\":\"bc\",\"grade\":\"b\"}]}]}");
//
//
//        // None of the JSON Parsers work, try to parse manually.
//        while (input.hasNext()) {
//            jsonString.append(input.nextLine());
//        }
//
//
//        Report report = parseReportCards(jsonString.toString());
//
//        List<ReportCard> reportCards = convertRport(report);
//        Collections.sort(reportCards);
//        reportCards.stream().forEach(reportCard -> System.out.println(reportCard));
//    }
//
//    private static List<ReportCard> convertRport(Report report) {
//
//        List<ReportCard> reportCards = new ArrayList<>();
//
//        for (JsonCard jsonCard : report.getReports()) {
//            for (JsonSubject jsonSubject : jsonCard.getSubjects()) {
//                ReportCard reportCard = new ReportCard(jsonSubject.getCode(), jsonSubject.getGrade(), jsonCard.getEnrollment(), jsonCard.getName());
//                reportCards.add(reportCard);
//            }
//
//        }
//        return reportCards;
//    }
//
//    private static Report parseReportCards(String s) {
//        Gson gson = new GsonBuilder().create();
//        return gson.fromJson(s, Report.class);
//    }
//
//    private static class Report {
//        // Make these private and add getters.
//        List<JsonCard> reports;
//
//        public List<JsonCard> getReports() {
//            return reports;
//        }
//
//        public void setReports(List<JsonCard> reports) {
//            this.reports = reports;
//        }
//    }
//
//    private static class JsonCard {
//        String enrollment;
//        String name;
//        List<JsonSubject> subjects;
//
//        public String getEnrollment() {
//            return enrollment;
//        }
//
//        public void setEnrollment(String enrollment) {
//            this.enrollment = enrollment;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public List<JsonSubject> getSubjects() {
//            return subjects;
//        }
//
//        public void setSubjects(List<JsonSubject> subjects) {
//            this.subjects = subjects;
//        }
//    }
//
//
//    private static class JsonSubject {
//        String code;
//        char grade;
//
//        public String getCode() {
//            return code;
//        }
//
//        public void setCode(String code) {
//            this.code = code;
//        }
//
//        public char getGrade() {
//            return grade;
//        }
//
//        public void setGrade(char grade) {
//            this.grade = grade;
//        }
//    }
//
//    // AutoValue would have been easy.
//    private static class ReportCard implements Comparable<ReportCard> {
//        private final String code;
//        private final char grade;
//        private final String enrollment;
//        private final String name;
//
//        public ReportCard(String code, char grade, String enrollment, String name) {
//            this.code = code;
//            this.grade = grade;
//            this.enrollment = enrollment;
//            this.name = name;
//        }
//
//        public String getCode() {
//            return code;
//        }
//
//        public char getGrade() {
//            return grade;
//        }
//
//        public String getEnrollmentId() {
//            return enrollment;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        @Override
//        public int compareTo(@NotNull ReportCard o) {
//
//            if (getCode().compareTo(o.getCode()) != 0) {
//                return getCode().compareTo(o.getCode());
//            }
//            if (Character.compare(getGrade(), o.getGrade()) != 0) {
//                return Character.compare(getGrade(), o.getGrade());
//            }
//            return getEnrollmentId().compareTo(o.getEnrollmentId());
//        }
//
//        @Override
//        public String toString() {
//            return MoreObjects.toStringHelper(this)
//                    .add("code", code)
//                    .add("grade", grade)
//                    .add("enrollment", enrollment)
//                    .add("name", name)
//                    .toString();
//        }
//    }

//}