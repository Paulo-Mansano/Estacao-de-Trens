import java.util.ArrayList;
import java.util.List;

public class GaragemVeiculos  {
    private List<Veiculo> veiculosDisponiveis;

    public GaragemVeiculos() {
        this.veiculosDisponiveis = new ArrayList<>();
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculosDisponiveis.add(veiculo);
    }

    public void removerVeiculo(Veiculo veiculo) {
        veiculosDisponiveis.remove(veiculo);
    }

    public List<Veiculo> listarVeiculosDisponiveis() {
        List<Veiculo> veiculosLivres = new ArrayList<>();
        for (Veiculo veiculo : veiculosDisponiveis) {
            // Verifica se o veículo não está engatado em nenhum trem
            if (veiculo.getTrem() == null) {
                veiculosLivres.add(veiculo);
            }
        }
        return veiculosLivres;
    }

    public List<Veiculo> listarLocomotivasDisponiveis() {
        List<Veiculo> veiculosLivres = new ArrayList<>();
        for (Veiculo veiculo : veiculosDisponiveis) {
            // Verifica se o veículo não está engatado em nenhum trem
            if (veiculo.getTrem() == null && veiculo instanceof Locomotiva) {
                veiculosLivres.add(veiculo);
            }
        }
        return veiculosLivres;
    }

    public List<Veiculo> listarVagoesDisponiveis() {
        List<Veiculo> veiculosLivres = new ArrayList<>();
        for (Veiculo veiculo : veiculosDisponiveis) {
            // Verifica se o veículo não está engatado em nenhum trem
            if (veiculo.getTrem() == null && veiculo instanceof Vagao) {
                veiculosLivres.add(veiculo);
            }
        }
        return veiculosLivres;
    }

    public Veiculo getVeiculoPorId(int idVeiculo) {
        for (Veiculo veiculo : veiculosDisponiveis) {
            if (veiculo.getId() == idVeiculo) {
                return veiculo;
            }
        }
        return null;
    }

    public List<Veiculo> removerLocomotivasDisponiveis() {
        List<Veiculo> locomotivasRemovidas = new ArrayList<>();
        for (Veiculo veiculo : veiculosDisponiveis) {
            if (veiculo.getTrem() == null && veiculo instanceof Locomotiva) {
                locomotivasRemovidas.add(veiculo);
            }
        }
        veiculosDisponiveis.removeAll(locomotivasRemovidas);
        return locomotivasRemovidas;
    }
  

  
    
}
