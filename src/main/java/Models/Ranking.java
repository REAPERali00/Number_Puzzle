package Models;

public class Ranking {
    public String name;
    public String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Ranking(String name, String time) {
        this.name = name;
        this.time = time;
    }

}
