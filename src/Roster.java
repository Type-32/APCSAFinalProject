import java.util.ArrayList;

public class Roster {
    public Roster(){
        myRoster = new ArrayList<>();
    }

    public Roster(ArrayList<Student> students){
        myRoster = students;
    }

    private ArrayList<Student> myRoster = new ArrayList<>();

    public ArrayList<Student> getMyRoster() {
        return myRoster;
    }

    public void setMyRoster(ArrayList<Student> myRoster) {
        this.myRoster = myRoster;
    }

    public Roster addRoster(Student student){
        myRoster.add(student);
        return this;
    }

    public Roster removeRoster(Student student){
        myRoster.remove(student);
        return this;
    }

    public Roster removeRoster(String surname){
        myRoster.forEach(i -> {
            if(i.getSurname().equals(surname)) {
                myRoster.remove(i);
                return;
            }
        });
        return this;
    }

    public Roster removeRoster(int id){
        myRoster.forEach(i -> {
            if(i.getId() == id) {
                myRoster.remove(i);
                return;
            }
        });
        return this;
    }

    public Roster printRoster(String surname){
        myRoster.forEach(i -> {
            if (i.getSurname().equals(surname)){
                System.out.println(i);
                return;
            }
        });
        return this;
    }

    public Roster printRoster(int id){
        myRoster.forEach(i -> {
            if (i.getId() == id){
                System.out.println(i);
                return;
            }
        });
        return this;
    }

    public Roster printAll(){
        myRoster.forEach(System.out::println);
        return this;
    }
}
