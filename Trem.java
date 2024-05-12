import java.util.ArrayList;
import java.util.List;

public class Trem extends Veiculo {
    private List<Locomotiva> locomotivas;
    private List<Vagao> vagoes;

    public Trem(int id) {
        super(id);
        if (id < 0) {
            throw new IllegalArgumentException("O identificador não pode ser negativo");
        }
        this.locomotivas = new ArrayList<>();
        this.vagoes = new ArrayList<>();
    }

    public void adicionarLocomotiva(Locomotiva locomotiva) {
        locomotivas.add(locomotiva);
    }

    public void removerLocomotiva(Locomotiva locomotiva) {
        locomotivas.remove(locomotiva);
    }

    public void adicionarVagao(Vagao vagao) {
        if (vagao.getTrem() != null && vagao.getTrem() != this) {
            throw new IllegalArgumentException("Este vagão já está engatado em outro trem!");
        }
        if (!vagoes.contains(vagao)) {
            vagoes.add(vagao);
        }
    }
    

    public void removerVagao(Vagao vagao) {
        vagoes.remove(vagao);
    }

    public double calcularCapacidade() {
        int numLocomotivas = locomotivas.size();
        double capacidadeTotal = 0;

        for (Locomotiva locomotiva : locomotivas) {
            capacidadeTotal += locomotiva.getPesoMax();
        }

        if (numLocomotivas > 1) {
            capacidadeTotal *= Math.pow(0.9, numLocomotivas - 1);
        }

        return capacidadeTotal;
    }

    public boolean podeInserirLocomotiva(Locomotiva locomotiva) {
        double pesoTotal = 0;
    
        // Calcula o peso total das locomotivas atualmente no trem
        for (Locomotiva loc : locomotivas) {
            pesoTotal += loc.getPesoMax();
        }
    
        // Verifica se a capacidade total do trem (incluindo a nova locomotiva) excede o peso máximo do trem
        if (pesoTotal + locomotiva.getPesoMax() > calcularCapacidade()) {
            return false;
        }
    
        return true;
    }
    
    public List<Locomotiva> getLocomotivasNoTrem(Trem trem) {
        List<Locomotiva> locomotivasNoTrem = new ArrayList<>();
        for (Locomotiva locomotiva : locomotivas) {
            if (locomotiva.getTrem() != null && locomotiva.getTrem().equals(trem)) {
                locomotivasNoTrem.add(locomotiva);
            }
        }
        return locomotivasNoTrem;
    }

    public List<Vagao> getVagoesNoTrem(Trem trem) {
        List<Vagao> vagoesNoTrem = new ArrayList<>();
        for (Vagao vagao : vagoes) {
            if (vagao.getTrem() != null && vagao.getTrem().equals(trem)) {
                vagoesNoTrem.add(vagao);
            }
        }
        return vagoesNoTrem;
    }

    public List<Vagao> getVagoes() {
        return new ArrayList<>(vagoes);
    }

    @Override
    public String getTipoVeiculo() {
        return "Trem";
    }
}
