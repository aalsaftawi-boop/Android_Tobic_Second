package revesion_recyclerView;

public class Student {
    private String name;
    private int id;
    private double gba;


    public Student(String name, int id, double gba) {
        this.name = name;
        this.id = id;
        this.gba = gba;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGba() {
        return gba;
    }

    public void setGba(double gba) {
        this.gba = gba;
    }
}
