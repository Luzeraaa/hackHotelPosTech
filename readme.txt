CRUD default
MS -> accommodation
	/criarLocalidade
	/criarPredio
	/criarQuarto
	/obterQuartosDisponiveis
		and dataInicio >=
		and dataInicio <=
		and quantidadePessoas = :quantidade
		and localidade = :localida

CRUD default
MS -> optionalServices
	/criarServico
	/criarItem

CRUD default
MS -> user
	/criarUsuario
	/solicitarReserva
	/solicitarServico
	/solicitarItem	

CRUD default	
MS -> roomManagement 
	/enviarEmailConfirmacao
	/exibirTotaisHospedagem
	
0. Criar usuario admin
1. Cadastrar localidades
2. Cadastrar Predios
3. Cadastrar quartos
4. Gerente cria serviços
5. Gerente cria itens
6. Usuario verifica quartos disponiveis (data inicio, data fim, quantidade pessoas e localidade)
7. Se usuario não tiver cadastro realiza passo A, senão pula para o passo B
	A. Usuario se cadastra
	B. Usuario solicita item e/ou servico
8. Usuario solicita reserva
9. Exibir detalhes da reserva
10. Sistema envia e-mail
