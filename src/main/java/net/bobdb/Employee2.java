package net.bobdb;

public class Employee2 {
    String name;
    String mail;
    int age;
    String gender;


    public Employee2(String name, String mail, int age, String gender) {
        this.name = name;
        this.mail = mail;
        this.age = age;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee2{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
