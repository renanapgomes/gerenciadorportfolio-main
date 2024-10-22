<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<%@include file="./base.jsp" %>
	</head>

	<body>

		<div class="container mt-3">

			<h1>Cadastrar Projeto</h1>
			<form:form method="POST" action="/manager/projetos/salvar">

				<c:if test="${resultado.equals('sucesso')}">
					<div id="resultado" name="resultado" display="block"
						class="alert alert-success alert-dismissible fade show" role="alert">
						${mensagem}
					</div>
				</c:if>

				<div class="row">
					<div class="col-md-4 mb-3">
						<div class="form-group">
							<label for="nome">Nome Projeto</label> <input type="text" class="form-control"
								id="nomeProjeto" name="nome" placeholder="Informe o nome do projeto">
						</div>
					</div>
					<div class="col-md-6 mb-3">
						<div class="form-group">
							<label for="nome">Descrição</label> <input type="text" class="form-control" id="descricao"
								name="descricao" placeholder="Descrição do projeto">
						</div>
					</div>
					<div class="col-md-2 mb-3">
						<div class="form-group">
							<label for="opcao">Pessoa Responsável</label>
							<select name="pessoas" id="gerente_id" class="form-control">
								<c:forEach items="${pessoas}" var="proximo">
									<option value="${proximo.idPessoa}">${proximo.nome}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-2 mb-3">
						<div class="form-group">
							<label for="dataInicio">Data Inicio</label> <input type="date" class="form-control"
								id="dataInicio" name="dataInicio" placeholder="Data de inicio">
						</div class="valid-feedback">
					</div>

					<div class="col-md-2 mb-3">
						<div class="form-group">
							<label for="dataFim">Data Final</label> <input type="date" class="form-control"
								id="dataFim" name="dataFim" placeholder="Data Final">
						</div>
					</div>
					<div class="col-md-2 mb-3">
						<div class="form-group">
							<label for="dataPrevisaoFim">Previsão de Termino</label> <input type="date" class="form-control"
								id="dataPrevisaoFim" name="dataPrevisaoFim" placeholder="Previsão">
						</div>
					</div>
					<div class="col-md-2 mb-3">
						<div class="form-group">
							<label for="orcamento">Orçamento</label> <input type="text" class="form-control"
								id="orcamento" name="orcamento" placeholder="Orçamento">
						</div>
					</div>
					<div class="col-md-2 mb-3">
						<div class="form-group">
							<label for="status">Status do Projeto</label>
					  <select name="status" id="status" class="form-control">
						<option value="EM_ANALISE">EM_ANALISE</option>
						<option value="ANALISE_REALIZADA">ANALISE_REALIZADA</option>
						<option value="ANALISE_APROVADA">ANALISE_APROVADA</option>
						<option value="INICIADO">INICIADO</option>
						<option value="PLANEJADO">PLANEJADO</option>
						<option value="EM_ANDAMENTO">EM_ANDAMENTO</option>
						<option value="ENCERRADO">ENCERRADO</option>
						<option value="CANCELADO">CANCELADO</option>
					  </select>
						</div>
					</div>
					<div class="col-md-2 mb-3">
						<div class="form-group">
							<label for="classificacaoRisco">Classificação de Risco</label>
					  <select name="classificacaoRisco" id="classificacaoRisco" class="form-control">
						<option value="BAIXO_RISCO">BAIXO_RISCO</option>
						<option value="MEDIO_RISCO">MEDIO_RISCO</option>
						<option value="ALTO_RISCO">ALTO_RISCO</option>
					  </select>
						</div>
					</div>
				</div>

				<a href="${pageContext.request.contextPath}/projetos/listar" class="btn btn-warning"> Voltar </a>
				<input type="submit" value="Salvar" class="btn btn-primary">
			</form:form>
		</div>

	</body>

	</html>