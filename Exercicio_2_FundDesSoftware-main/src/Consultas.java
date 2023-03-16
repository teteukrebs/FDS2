import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Consultas {
    private TempoRepository repositoryTempo;
    private Predicate<RegistroDoTempo> consulta;

    public Consultas(TempoRepository repositoryTempo) {
        this.repositoryTempo = repositoryTempo;
        LocalDateTime hoje = LocalDateTime.now();
        this.consulta = r -> (r.getDia() == hoje.getDayOfMonth() && r.getMes() == hoje.getMonthValue()
                && r.getAno() == hoje.getYear());
    }

    public boolean existeData() {
        return false;
    }

    public List<String> datasEmQueChoveuMaisDe(double milimetros) {

        return repositoryTempo.getAll()
                .stream()
                .filter(r -> r.getPrecipitacaoMaxima() > milimetros)
                .map(r -> r.getDia() + "/" + r.getMes() + "/" + r.getAno())
                .toList();
    }

    public String diaQueMaisChoveuNoAno(int ano) {
        RegistroDoTempo registro = repositoryTempo.getAll()
                .stream()
                .filter(reg -> reg.getAno() == ano)
                .max(Comparator.comparing(RegistroDoTempo::getPrecipitacaoMaxima))
                .orElseThrow(IllegalArgumentException::new);
        String resp = registro.getDia() + "/" + registro.getMes() + "/" + registro.getAno() + ", "
                + registro.getPrecipitacaoMaxima();
        return resp;
    }

    public List<Data> diasEmQue() {
        return repositoryTempo.getAll()
                .stream()
                .filter(r -> consulta.test(r))
                .map(r -> new Data(r.getDia(), r.getMes(), r.getAno()))
                .toList();
    }

    public void alteraConsultaPadrao(Predicate<RegistroDoTempo> consulta) {
        this.consulta = consulta;
    }
}
