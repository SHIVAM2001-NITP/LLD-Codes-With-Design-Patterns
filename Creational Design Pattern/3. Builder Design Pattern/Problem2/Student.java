import java.util.List;
    public class Student {
        int rollNumber;//Mandatory
        int age;//Optional
        String name;//optional
        String fatherName;//optional
        String motherName;//optional
        List<String> subjects;//optional

        public Student(int rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }
        public Student(int rollNumber, String FathersName) {//SAme COnstructor type argument and too many Constructor
            this.rollNumber = rollNumber;
            this.fatherName = FathersName;
        }

        public Student(int rollNumber, int age, String name, String fatherName, String motherName, List<String> subjects) {
            this.rollNumber = rollNumber;
            this.age = age;
            this.name = name;
            this.fatherName = fatherName;
            this.motherName = motherName;
            this.subjects = subjects;
        }
    }
