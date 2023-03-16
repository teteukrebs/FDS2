public class App {
    public static void main(String[] args) {
        Consultas consultas = new Consultas(new TempoRepository());
        System.out.println("Dia em que mais choveu no ano de 1980: ");
        System.out.println(consultas.diaQueMaisChoveuNoAno(1980));
        System.out.println("Datas em que choveu mais de 90 milimetros");
        consultas.datasEmQueChoveuMaisDe(90)
            .forEach(System.out::println);
    }
}
