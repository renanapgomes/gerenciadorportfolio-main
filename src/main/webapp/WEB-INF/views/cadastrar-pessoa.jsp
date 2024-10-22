<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="./base.jsp" %>
</head>
<body>

    <div class="container mt-3">

        <h1>Cadastrar Pessoa</h1>
        <form:form method="POST" action="/manager/pessoas/salvar" >

            <c:if test="${resultado.equals('sucesso')}" >
                <div id="resultado" name="resultado" display="block" class="alert alert-success alert-dismissible fade show" role="alert">
                    ${mensagem}
                </div>
            </c:if>

            <div class="row">
                <div class="col-4">
                    <div class="form-group">
                        <label for="nome">Nome Pessoa</label>
                        <input type="text" class="form-control" id="nomePessoa" name="nome" placeholder="Informe o nome da Pessoa" required>
                    </div>
                </div>

                <div class="col-4">
                    <div class="form-group">
                        <label for="dataNascimento">Data de Nascimento</label>
                        <input type="date" class="form-control" id="dataNascimento" name="dataNascimento">
                    </div>
                </div>

                <div class="col-4">
                    <div class="form-group">
                        <label for="cpf">CPF</label>
                        <input type="text" class="form-control" id="cpf" name="cpf" placeholder="Informe o CPF" maxlength="14">
                    </div>
                </div>

                <div class="col-4">
                    <div class="form-group">
                        <label for="funcionario">Funcionário?</label>
                        <select class="form-control" id="funcionario" name="funcionario">
                            <option value="true">Sim</option>
                            <option value="false">Não</option>
                        </select>
                    </div>
                </div>

                <div class="col-4">
                    <div class="form-group">
                        <label for="gerente">É Gerente?</label>
                        <select class="form-control" id="gerente" name="gerente">
                            <option value="true">Sim</option>
                            <option value="false">Não</option>
                        </select>
                    </div>
                </div>
            </div>

            <a href="${pageContext.request.contextPath}/pessoas/listar" class="btn btn-warning"> Voltar </a>
            <input type="submit" value="Salvar" class="btn btn-primary">
        </form:form>
    </div>

</body>
</html>
