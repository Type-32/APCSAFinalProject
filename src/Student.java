public class Student {
    private String name, surname, grade;
    private int age, id;

    public Student(String name, String surname, String grade, int age, int id) {
        this.name = name;
        this.surname = surname;
        this.grade = grade;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s %s, Age %s, Grade %s, ID %s", getName(), getSurname(), getAge(), getGrade(), getId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                id == student.id &&
                name != null ? name.equals(student.name) : student.name != null &&
                surname != null ? surname.equals(student.surname) : student.surname == null &&
                grade.equals(student.grade);
    }
}
