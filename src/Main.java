import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John", "Anna", "Lea", "Steve");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        getUnderage(persons);
        getRecruit(persons);
        getWorkingPersons(persons);
    }

    public static void getUnderage(Collection<Person> persons) {
        long stream = persons.stream()
                .filter(x -> x.getAge() < 18).count();
        System.out.println(stream);
    }

    public static void getRecruit(Collection<Person> persons) {
        List<String> collect = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(x -> x.getAge() < 28)
                .filter(x -> x.getAge() > 17)
                .map(Person -> Person.getFamily())
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    public static void getWorkingPersons(Collection<Person> persons) {
        List<String> collect = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(x -> x.getAge() < 66)
                .filter(x -> x.getAge() > 17)
                .sorted(Comparator.comparing(Person -> Person.getFamily()))
                .map(Person -> Person.getFamily())
                .collect(Collectors.toList());
        System.out.println(collect);

        List<String> collect2 = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getSex() == Sex.WOMAN)
                .filter(x -> x.getAge() < 61)
                .filter(x -> x.getAge() > 17)
                .sorted(Comparator.comparing(Person -> Person.getFamily()))
                .map(Person -> Person.getFamily())
                .collect(Collectors.toList());
        System.out.println(collect2);
    }
}