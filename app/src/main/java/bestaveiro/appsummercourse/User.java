package bestaveiro.appsummercourse;

import java.io.Serializable;

public class User implements Serializable {
    private String name, password;
    private int participant;

    public User(){

    }

    public User(String name, String password, int participant){
        this.name=name;
        this.password=password;
        this.participant=participant;
    }

    public int getParticipant() {
        return participant;
    }

    /*0:organizer; 1:participant*/
    public void setParticipant(int participant) {
        this.participant = participant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}