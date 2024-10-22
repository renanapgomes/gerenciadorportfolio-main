<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <%@ include file="./base.jsp" %>
    <script>
        function editarProjeto(event) {
            event.preventDefault();
            const id = document.getElementById("idProjeto").value; // ID do projeto
            const nome = document.getElementById("nomeProjeto").value;
            const descricao = document.getElementById("descricao").value;
            const dataInicio = document.getElementById("dataInicio").value;
            const dataFim = document.getElementById("dataFim").value;
            const dataPrevisaoFim = document.getElementById("dataPrevisaoFim").value;
            const orcamento = document.getElementById("orcamento").value;
            const status = document.getElementById("status").value;
            const classificacaoRisco = document.getElementById("classificacaoRisco").value;
            const gerenteId = document.getElementById("gerente_id").value;

            fetch(`/manager/projetos/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    id: id,
                    nome: nome,
                    descricao: descricao,
                    dataInicio: dataInicio,
                    dataFim: dataFim,
                    dataPrevisaoFim: dataPrevisaoFim,
                    orcamento: orcamento,
                    status: status,
                    classificacaoRisco: classificacaoRisco,
                    gerenteId: gerenteId,
                }),
            })
            .then(response => {
                if (response.ok) {
                    window.location.href = "/manager/projetos/listar"; // Redireciona após sucesso
                } else {
                    alert("Erro ao atualizar projeto.");
                }
            });
        }
    </script>
</head>

<body>

    <div class="container mt-3">
        <h1>Editar Projeto</h1>
        <form onsubmit="editarProjeto(event)">
            <input type="hidden" id="idProjeto" name="id" value="${projeto.id}"/>

            <div class="row">
                <div class="col-md-4 mb-3">
                    <div class="form-group">
                        <label for="nomeProjeto">Nome Projeto</label>
                        <input type="text" class="form-control" id="nomeProjeto" name="nome"
                               value="${projeto.nome}" placeholder="Informe o nome do projeto" required>
                    </div>
                </div>
                <div class="col-md-6 mb-3">
                    <div class="form-group">
                        <label for="descricao">Descrição</label>
                        <input type="text" class="form-control" id="descricao" name="descricao"
                               value="${projeto.descricao}" placeholder="Descrição do projeto">
                    </div>
                </div>
                <div class="col-md-2 mb-3">
                    <div class="form-group">
                        <label for="gerente_id">Pessoa Responsável</label>
                        <select name="pessoas" id="gerente_id" class="form-control">
                            <c:forEach items="${pessoas}" var="proximo">
                                <option value="${proximo.idPessoa}"
                                        ${proximo.idPessoa == projeto.gerente.idPessoa ? 'selected' : ''}>
                                    ${proximo.nome}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-2 mb-3">
                    <div class="form-group">
                        <label for="dataInicio">Data Inicio</label>
                        <input type="date" class="form-control" id="dataInicio" name="dataInicio"
                               value="${projeto.dataInicio}">
                    </div>
                </div>

                <div class="col-md-2 mb-3">
                    <div class="form-group">
                        <label for="dataFim">Data Final</label>
                        <input type="date" class="form-control" id="dataFim" name="dataFim"
                               value="${projeto.dataFim}">
                    </div>
                </div>
                <div class="col-md-2 mb-3">
                    <div class="form-group">
                        <label for="dataPrevisaoFim">Previsão de Término</label>
                        <input type="date" class="form-control" id="dataPrevisaoFim" name="dataPrevisaoFim"
                               value="${projeto.dataPrevisaoFim}">
                    </div>
                </div>
                <div class="col-md-2 mb-3">
                    <div class="form-group">
                        <label for="orcamento">Orçamento</label>
                        <input type="text" class="form-control" id="orcamento" name="orcamento"
                               value="${projeto.orcamento}">
                    </div>
                </div>
                <div class="col-md-2 mb-3">
                    <div class="form-group">
                        <label for="status">Status do Projeto</label>
                        <select name="status" id="status" class="form-control">
                            <option value="EM_ANALISE" ${projeto.status == 'EM_ANALISE' ? 'selected' : ''}>EM_ANALISE</option>
                            <option value="ANALISE_REALIZADA" ${projeto.status == 'ANALISE_REALIZADA' ? 'selected' : ''}>ANALISE_REALIZADA</option>
                            <option value="ANALISE_APROVADA" ${projeto.status == 'ANALISE_APROVADA' ? 'selected' : ''}>ANALISE_APROVADA</option>
                            <option value="INICIADO" ${projeto.status == 'INICIADO' ? 'selected' : ''}>INICIADO</option>
                            <option value="PLANEJADO" ${projeto.status == 'PLANEJADO' ? 'selected' : ''}>PLANEJADO</option>
                            <option value="EM_ANDAMENTO" ${projeto.status == 'EM_ANDAMENTO' ? 'selected' : ''}>EM_ANDAMENTO</option>
                            <option value="ENCERRADO" ${projeto.status == 'ENCERRADO' ? 'selected' : ''}>ENCERRADO</option>
                            <option value="CANCELADO" ${projeto.status == 'CANCELADO' ? 'selected' : ''}>CANCELADO</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-2 mb-3">
                    <div class="form-group">
                        <label for="classificacaoRisco">Classificação de Risco</label>
                        <select name="classificacaoRisco" id="classificacaoRisco" class="form-control">
                            <option value="BAIXO_RISCO" ${projeto.classificacaoRisco == 'BAIXO_RISCO' ? 'selected' : ''}>BAIXO_RISCO</option>
                            <option value="MEDIO_RISCO" ${projeto.classificacaoRisco == 'MEDIO_RISCO' ? 'selected' : ''}>MEDIO_RISCO</option>
                            <option value="ALTO_RISCO" ${projeto.classificacaoRisco == 'ALTO_RISCO' ? 'selected' : ''}>ALTO_RISCO</option>
                        </select>
                    </div>
                </div>
            </div>

            <a href="${pageContext.request.contextPath}/projetos/listar" class="btn btn-warning"> Voltar </a>
            <input type="submit" value="Salvar" class="btn btn-primary">
        </form>
    </div>

</body>
</html>
