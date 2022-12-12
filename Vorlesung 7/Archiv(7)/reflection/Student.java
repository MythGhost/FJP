package reflection;

public class Student {
    static private int count;
    public int[] points = new int[]{1, 2, 3, 4, 5};

    public String vorName;
    protected String nachName;
    private Address adresse;
    private int semester;
    long matrikelNummer;

    Student(String vorName, String nachName, Address adresse, int semester, long matrikelNummer) {
        super();
        this.vorName = vorName;
        this.nachName = nachName;
        this.adresse = adresse;
        this.semester = semester;
        this.matrikelNummer = matrikelNummer;
    }

    @Override
    public String toString() {
        return "Student{" +
                "vorName='" + vorName + '\'' +
                ", nachName='" + nachName + '\'' +
                ", adresse=" + adresse +
                ", semester=" + semester +
                ", matrikelNummer=" + matrikelNummer +
                '}';
    }

    public static void main(String[] args) {
        // complete example in FieldInspector
        Object o = new Student("Claire", "Werk",
                new Address("Lohengrinstr.", 11, 72729, "Neustadt"), 7, 83939L);
        System.out.println(o.getClass().getName());
        System.out.println(o);
    }
}
