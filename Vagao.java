import java.util.ArrayList;

public class Vagao extends Veiculo{
    private int capacidadeCarga;
    private ArrayList<Locomotiva>locomotiva;


    //Vagão sendo criado sem trem definido, livre.

    public Vagao(int id, int capacidadeCarga){
        super(id);
        if (id < 0 || capacidadeCarga <= 0){
            throw new IllegalArgumentException("Id não pode ser negativo e a capacidade máxima deve ser maior que 0");
        }
        this.capacidadeCarga = capacidadeCarga;
    }

    public void engatarEmTrem(Trem trem) {
        if (this.trem != null) {
            throw new IllegalArgumentException("Este vagão já está engatado em um trem!");
        }
    
        // Verifica se há pelo menos uma locomotiva no trem
        if (trem.getLocomotivasNoTrem(trem).isEmpty()) {
            System.out.println("Não é possível adicionar vagão. Não há locomotivas no trem.");
            return; // Encerra o método aqui, pois não há locomotivas para engatar este vagão
        }
    
        this.trem = trem; 
        trem.adicionarVagao(this);
    }
    
    

    public void desengatar(){
        if (this.trem != null) {
            this.trem.removerVagao(this);
            this.trem = null;
        } else {
            System.out.println("Esta locomotiva não está atualmente associada a um trem.");
        }
        System.out.println("Vagao removido com sucesso no trem.");
    }

    public int getCapacidadeCarga() {
        return capacidadeCarga;
    }

    @Override
    public String toString() {
        return "Vagao: id=" + getId() + ", trem=" + (getTrem() != null ? getTrem().getId() : "Nenhum") + ", capacidadeCarga=" + capacidadeCarga;
    }

    @Override
    public String getTipoVeiculo() {
        return "Vagao";
    }
}
