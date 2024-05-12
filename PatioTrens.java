import java.util.ArrayList;
import java.util.List;

public class PatioTrens {
    private List<Trem> trens;
    private GaragemVeiculos garagemVeiculos;

    public PatioTrens() {
        this.trens = new ArrayList<>();
        this.garagemVeiculos = new GaragemVeiculos();
    }

    public void adicionarTrem(Trem trem) {
        trens.add(trem);
    }

    public void removerTrem(Trem trem) {
        trens.remove(trem);
    }

    public Trem getTremPorIdentificador(int identificador) {
        for (Trem trem : trens) {
            if (trem.getId() == identificador) {
                return trem;
            }
        }
        return null;
    }

    public List<Trem> listarTrens() {
        return new ArrayList<>(trens);
    }

    public GaragemVeiculos getGaragemVeiculos() {
        return garagemVeiculos;
    }
}
