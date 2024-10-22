<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="./base.jsp" %>
    <script>
        function editarPessoa(event) {
            event.preventDefault();
            const id = document.getElementById("idPessoa").value; // ID da pessoa
            const nome = document.getElementById("nomePessoa").value;
            const dataNascimento = document.getElementById("dataNascimento").value;
            const cpf = document.getElementById("cpf").value;
            const funcionario = document.getElementById("funcionario").value;
            const gerente = document.getElementById("gerente").value;

            fetch(`/manager/pessoas/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    idPessoa: id,
                    nome: nome,
                    dataNascimento: dataNascimento,
                    cpf: cpf,
                    funcionario: funcionario,
                    gerente: gerente,
                }),
            })
            .then(response => {
                if (response.ok) {
                    window.location.href = "/manager/pessoas/listar"; // Redireciona após sucesso
                } else {
                    alert("Erro ao atualizar pessoa.");
                }
            });
        }
    </script>
</head>
<body>

    <div class="container mt-3">
        <h1>Editar Pessoa</h1>
        <form onsubmit="editarPessoa(event)">
            <input type="hidden" id="idPessoa" name="id" value="${pessoa.idPessoa}"/>

            <div class="row">
                <div class="col-4">
                    <div class="form-group">
                        <label for="nome">Nome Pessoa</label>
                        <input type="text" class="form-control" id="nomePessoa" name="nome" value="${pessoa.nome}" placeholder="Informe o nome da Pessoa" required>
                    </div>
                </div>

                <div class="col-4">
                    <div class="form-group">
                        <label for="dataNascimento">Data de Nascimento</label>
                        <input type="date" class="form-control" id="dataNascimento" name="dataNascimento" value="${pessoa.dataNascimento}">
                    </div>
                </div>

                <div class="col-4">
                    <div class="form-group">
                        <label for="cpf">CPF</label>
                        <input type="text" class="form-control" id="cpf" name="cpf" value="${pessoa.cpf}" placeholder="Informe o CPF" maxlength="14">
                    </div>
                </div>

                <div class="col-4">
                    <div class="form-group">
                        <label for="funcionario">Funcionário?</label>
                        <select class="form-control" id="funcionario" name="funcionario">
                            <option value="true" ${pessoa.funcionario ? 'selected' : ''}>Sim</option>
                            <option value="false" ${!pessoa.funcionario ? 'selected' : ''}>Não</option>
                        </select>
                    </div>
                </div>

                <div class="col-4">
                    <div class="form-group">
                        <label for="gerente">É Gerente?</label>
                        <select class="form-control" id="gerente" name="gerente">
                            <option value="true" ${pessoa.gerente ? 'selected' : ''}>Sim</option>
                            <option value="false" ${!pessoa.gerente ? 'selected' : ''}>Não</option>
                        </select>
                    </div>
                </div>
            </div>

            <a href="${pageContext.request.contextPath}/pessoas/listar" class="btn btn-warning"> Voltar </a>
            <input type="submit" value="Salvar" class="btn btn-primary">
        </form>
    </div>

</body>
</html>