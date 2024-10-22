<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="./base.jsp"%>
</head>
<body>

	<div class="container mt-3">

		<h1>Cadastrar Membro</h1>
		<form:form method="POST" action="/manager/membros/salvar" modelAttribute="membroRequest">

                 <c:if test="${resultado.equals('sucesso')}">
                    <div id="resultado" name="resultado"  class="alert alert-success alert-dismissible fade show" role="alert">
                       ${mensagem}
                    </div>
                 </c:if>
                <c:if test="${resultado.equals('error')}">
                <div id="resultado" name="resultado"  class="alert alert-danger alert-dismissible fade show" role="alert">
                               ${mensagem}
                </c:if>

			<div class="row">
				<div class="col-4">
					<div class="form-group">
						<label for="nome">Nome Membro</label>
						<input type="text" class="form-control" id="nomeMembro" name="nome" placeholder="Informe o nome do Membro">
					</div>
					<div class="form-group">
                    	<label for="atribuicao">Atribuição</label>
                    	<input type="text" class="form-control" id="atribuicaoMembro" name="atribuicao" placeholder="Informe a atribuição do membro">
                    </div>
                    <div class="form-group">
                            <label for="projeto">Projetos</label>
                            <form:select path="idProjetoSelecionado" class="form-control" id="projeto" name="projeto">
                                  <c:forEach items="${projetos}" var="projetoAux">
                                    <form:option value="${projetoAux.id}">${projetoAux.nome}</form:option>
                                  </c:forEach>
                            </form:select>

                    </div>
				</div>
			</div>

			<a href="${pageContext.request.contextPath}/membros/listar"
				class="btn btn-warning"> Voltar </a>
			<input type="submit" value="Salvar" class="btn btn-primary">
		</form:form>
	</div>

</body>
</html>