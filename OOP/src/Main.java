//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*Human human = new Human("Montana", "Antonio", 25);
        System.out.println(human);
        Student student = new Student("Pinkman", "Jessie", 22, "Chemistry", "WW_220", 95, 96);
        System.out.println(student);
        Teacher teacher = new Teacher("White", "Walter", 50, "Chemistry", 25);
        System.out.println(teacher);*/

        Human[] group = new Human[]
                {
                        new Human("Montana", "Antonio", 25),
                        new Student("Pinkman", "Jessie", 22, "Chemistry", "WW_220", 95, 96),
                        new Teacher("White", "Walter", 50, "Chemistry", 25)
                };
        for (Human h : group) {
            System.out.println(h);
        }
    }
}