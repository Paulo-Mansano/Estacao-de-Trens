import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Cria a instância da garagem de veiculos
        GaragemVeiculos garagemveiculo = new GaragemVeiculos();

        // Cria locomotivas de teste
        Locomotiva locomotiva1 = new Locomotiva(1, 100, 5);
        Locomotiva locomotiva2 = new Locomotiva(2, 120, 6);
        Locomotiva locomotiva3 = new Locomotiva(3, 90, 4);

        // Adiciona as locomotivas à garagem de locomotivas
        garagemveiculo.adicionarVeiculo(locomotiva1);
        garagemveiculo.adicionarVeiculo(locomotiva2);
        garagemveiculo.adicionarVeiculo(locomotiva3);

        // Cria vagões de teste
        Vagao vagao1 = new Vagao(101, 50);
        Vagao vagao2 = new Vagao(102, 45);
        Vagao vagao3 = new Vagao(103, 60);
        Vagao vagao4 = new Vagao(104, 55);

        // Adiciona os vagões à garagem de vagões
        garagemveiculo.adicionarVeiculo(vagao1);
        garagemveiculo.adicionarVeiculo(vagao2);
        garagemveiculo.adicionarVeiculo(vagao3);
        garagemveiculo.adicionarVeiculo(vagao4);

        // Cria trens de teste
        Trem trem1 = new Trem(1);

        // Engata locomotivas e vagões nos trens
        locomotiva1.engatarEmTrem(trem1);
        //locomotiva2.engatarEmTrem(trem1);

        // Adicionar composições ao pátio de composições
        PatioTrens patioTrens = new PatioTrens();
        patioTrens.adicionarTrem(trem1);

        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1- Criar um trem");
            System.out.println("2- Editar um trem");
            System.out.println("3- Listar todos os trens criados");
            System.out.println("4- Desfazer um trem");
            System.out.println("5- Fim");

            System.out.printf("Opcao: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Informe o identificador do trem: ");
                    int identificadorTrem = scanner.nextInt();

                    // Crie uma nova composição para representar o trem
                    Trem novoTrem = new Trem(identificadorTrem);

                    System.out.print("Informe o identificador da primeira locomotiva: ");
                    int idLocPrimeira = scanner.nextInt();

                    // Verifique se a locomotiva está disponível na garagem
                    Veiculo primeiraLocomotiva = garagemveiculo.listarLocomotivasDisponiveis()
                            .stream()
                            .filter(veiculo -> veiculo instanceof Locomotiva && veiculo.getId() == idLocPrimeira)
                            .findFirst()
                            .orElse(null);

                    Locomotiva locomotivaParaInserir = (Locomotiva) primeiraLocomotiva;

                    if (primeiraLocomotiva != null) {
                        // Engate a primeira locomotiva na composição
                        locomotivaParaInserir.engatarEmTrem(novoTrem);

                        // Adicione a locomotiva à composição
                        novoTrem.adicionarLocomotiva(locomotivaParaInserir);

                        System.out.print("Informe o identificador do vagão: ");
                        int idVagao = scanner.nextInt();

                        // Verifique se o vagão está disponível na garagem
                        Veiculo vagao = garagemveiculo.getVeiculoPorId(idVagao);

                        if (vagao != null && vagao instanceof Vagao) {
                            Vagao vagaoParaInserir = (Vagao) vagao;

                            // Verifique a compatibilidade do vagão com o trem, se necessário

                            // Engate o vagão na composição do trem
                            vagaoParaInserir.engatarEmTrem(novoTrem);

                            // Adicione o vagão à composição do trem
                            novoTrem.adicionarVagao(vagaoParaInserir);

                        }

                        patioTrens.adicionarTrem(novoTrem);

                        System.out.println("Trem criado com sucesso!");
                    } else {
                        System.out.println("Locomotiva não encontrada ou não disponível na garagem.");

                        System.out.println("Vagão não encontrado ou não disponível na garagem.");
                    }
                    break;

                case 2: // Editar um trem
                    System.out.print("Informe o identificador do trem a ser editado: ");
                    int identificadorTremEditar = scanner.nextInt();

                    // Encontre a composição correspondente ao identificador informado
                    Trem tremParaEditar = patioTrens.getTremPorIdentificador(identificadorTremEditar);

                    if (tremParaEditar != null) {
                        boolean continuarEdicao = true;

                        while (continuarEdicao) {
                            System.out.println("Escolha uma opção:");
                            System.out.println("1- Inserir uma locomotiva");
                            System.out.println("2- Inserir um vagão");
                            System.out.println("3- Remover o último elemento do trem");
                            System.out.println("4- Listar locomotivas livres");
                            System.out.println("5- Listar vagões livres");
                            System.out.println("6- Encerrar a edição do trem");

                            int opcaoEdicao = scanner.nextInt();

                            switch (opcaoEdicao) {
                                case 1:
                                    // Inserir uma locomotiva
                                    System.out.print("Informe o identificador da locomotiva a ser inserida: ");
                                    int idLocomotivaInserir = scanner.nextInt();

                                    // Encontre a locomotiva correspondente na garagem de veiculos
                                    
                                    Locomotiva locomotivaParaInserir2 = garagemveiculo.listarLocomotivasDisponiveis()
                                            .stream()
                                            .filter(veiculo -> veiculo instanceof Locomotiva
                                                    && veiculo.getId() == idLocomotivaInserir)
                                            .map(locomotiva -> (Locomotiva) locomotiva)
                                            .findFirst()
                                            .orElse(null);

                                            if (locomotivaParaInserir2 == null){
                                                System.out.println("Não é possível inserir esta locomotiva na composição.");
                                            } else{
                                                System.out.println("Digite o id do trem que receberá a locomotiva");
                                                int idTremInserir = scanner.nextInt();
                                                Trem tremaReceber = patioTrens.listarTrens()
                                                        .stream()
                                                        .filter(Trem -> Trem.getId() == idTremInserir)
                                                        .findFirst()
                                                        .orElse(null);
                                                locomotivaParaInserir2.engatarEmTrem(tremaReceber);
                                                tremParaEditar.adicionarLocomotiva(locomotivaParaInserir2);
                                                System.out.println("Locomotiva inserida com sucesso.");
                                            }
                                    break;

                                case 2:
                                // Inserir um vagao
                                System.out.print("Informe o identificador do vagao a ser inserido: ");
                                int idVagaoInserir = scanner.nextInt();

                                // Encontre o vagao correspondente na garagem de veiculos
                                
                                Vagao VagaoParaInserir2 = garagemveiculo.listarVagoesDisponiveis()
                                        .stream()
                                        .filter(veiculo -> veiculo instanceof Vagao
                                                && veiculo.getId() == idVagaoInserir)
                                        .map(vagao -> (Vagao)  vagao)
                                        .findFirst()
                                        .orElse(null);

                                if (VagaoParaInserir2 == null) {
                                    System.out.println("Vagao não encontrado ou não disponível na garagem.");
                                }

                                if (VagaoParaInserir2 != null) {
                                    
                                    System.out.println("Digite o id do trem que receberá o vagao");
                                    int idTremInserir = scanner.nextInt();
                                    Trem tremaReceber = patioTrens.listarTrens()
                                            .stream()
                                            .filter(Trem -> Trem.getId() == idTremInserir)
                                            .findFirst()
                                            .orElse(null);
                                    VagaoParaInserir2.engatarEmTrem(tremaReceber);
                                    tremParaEditar.adicionarVagao(VagaoParaInserir2);

                                     } else {
                                    System.out.println("Não é possível inserir este vagao na composição.");
                                 }
                                System.out.println("Vagao inserido com sucesso.");
                                break;
                                

                                case 3: // Remover o último elemento do trem
                                    List<Locomotiva> locomotivasTrem = tremParaEditar
                                            .getLocomotivasNoTrem(tremParaEditar);
                                    List<Vagao> vagoesTrem = tremParaEditar.getVagoes();

                                    if (!locomotivasTrem.isEmpty()) {
                                        tremParaEditar
                                                .removerLocomotiva(locomotivasTrem.get(locomotivasTrem.size() - 1));

                                        System.out.println("Última locomotiva removida do trem.");

                                    } else if (!vagoesTrem.isEmpty()) {
                                        tremParaEditar.removerVagao(vagoesTrem.get(vagoesTrem.size() - 1));

                                        System.out.println("Último vagão removido do trem.");

                                    } else {
                                        System.out.println("Não há elementos para remover no trem.");

                                    }

                                    break;

                                case 4: // Listar locomotivas livres
                                    List<Locomotiva> locomotivasLivres = new ArrayList<>();
                                    List<Trem> composicoesCriadas = patioTrens.listarTrens();


                                    for (Veiculo veiculo : garagemveiculo.listarLocomotivasDisponiveis()) {
                                        if (veiculo instanceof Locomotiva) {
                                            locomotivasLivres.add((Locomotiva) veiculo);
                                        }
                                    }

                                    if (locomotivasLivres.isEmpty()) {
                                        System.out.println("Não há locomotivas livres na garagem.");

                                    } else {
                                        System.out.println("Locomotivas livres na garagem:");

                                        for (Locomotiva loc : locomotivasLivres) {
                                            System.out.println(loc);
                                        }
                                    }
                                    
                                  
                                    break;
                                case 5: // Listar vagões livres
                                    List<Vagao> vagoesLivres = new ArrayList<>();

                                    for (Veiculo veiculo : garagemveiculo.listarVagoesDisponiveis()) {
                                        if (veiculo instanceof Vagao) {
                                            vagoesLivres.add((Vagao) veiculo);
                                        }
                                    }

                                    if (vagoesLivres.isEmpty()) {
                                        System.out.println("Não há vagões livres na garagem.");

                                    } else {
                                        System.out.println("Vagões livres na garagem:");

                                        for (Vagao vagao : vagoesLivres) {
                                            System.out.println(vagao);

                                        }
                                    }
                                    break;

                                case 6: // Encerrar a edição do trem
                                    continuarEdicao = false;
                                    break;

                                default:
                                    System.out.println("Opção inválida. Tente novamente.");
                            }
                        }
                    } else {
                        System.out.println("Trem não encontrado.");

                    }
                    break;

                case 3: // Listar todas as composições (trens) criadas
                    List<Trem> composicoesCriadas = patioTrens.listarTrens();

                    if (composicoesCriadas.isEmpty()) {
                        System.out.println("Nenhuma composição foi criada até o momento.");
                    } else {
                        System.out.println("Composições criadas:");
                        for (Trem trem : composicoesCriadas) {
                            System.out.println(trem);

                        }

                    }
                    break;

                case 4: // Desfazer um trem (remover uma composição)
                    System.out.print("Informe o identificador da composição que deseja desfazer: ");
                    int idComposicaoDesfazer = scanner.nextInt();

                    Trem composicaoParaDesfazer = patioTrens.getTremPorIdentificador(idComposicaoDesfazer);

                    if (composicaoParaDesfazer != null) {
                        // Remova a composição do pátio de composições
                        patioTrens.removerTrem(composicaoParaDesfazer);

                        // Libere as locomotivas e vagões da composição
                        for (Locomotiva locomotiva : composicaoParaDesfazer
                                .getLocomotivasNoTrem(composicaoParaDesfazer)) {
                            locomotiva.desengatar();
                        }
                        for (Vagao vagao : composicaoParaDesfazer.getVagoesNoTrem(composicaoParaDesfazer)) {
                            vagao.desengatar();
                        }

                        System.out.println("Composição desfeita com sucesso.");
                    } else {
                        System.out.println("Composição não encontrada.");
                    }
                    break;

                case 5:
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);

        scanner.close();
    }
}
