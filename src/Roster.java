import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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
        if(!myRoster.contains(student))
            myRoster.add(student);
        return this;
    }

    public boolean removeRoster(Student student){
        return myRoster.remove(student);
    }

    public boolean removeRoster(String surname){
        return myRoster.remove(lookForRoster(surname));
    }

    public boolean removeRoster(int id){
        return myRoster.remove(lookForRoster(id));
    }

    public Student lookForRoster(String surname){
        AtomicInteger success = new AtomicInteger(-1);
        myRoster.forEach(i -> {
            if(i.getSurname().equals(surname)) {
                success.set(myRoster.indexOf(i));
            }
        });
        return success.get() == -1 ? null : myRoster.get(success.get());
    }

    public Student lookForRoster(int id){
        AtomicInteger success = new AtomicInteger(-1);
        myRoster.forEach(i -> {
            if(i.getId() == id) {
                success.set(myRoster.indexOf(i));
            }
        });
        return success.get() == -1 ? null : myRoster.get(success.get());
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
