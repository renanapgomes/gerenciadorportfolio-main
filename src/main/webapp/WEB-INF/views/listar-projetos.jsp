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

		<h1>Lista</h1>
		<div class="row">

			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Nome</th>
						<th scope="col">Ações</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="projeto" items="${projetos}">
						<tr>
							<td class="table-plus">${projeto.id}</td>
							<td class="table-plus">${projeto.nome}</td>
                            <td class="table-plus">
                                <div class="d-inline">
                                    <form:form method="GET" action="/manager/projetos/editar/${projeto.id}" style="display:inline;">
                                        <button type="submit" value="editar" class="btn btn-warning">
                                            <i id="boot-icon" class="bi bi-pencil"></i> Editar
                                        </button>
                                    </form:form>
                                </div>
                                <div class="d-inline">
                                    <form:form method="POST" action="/manager/projetos/remover/${projeto.id}" style="display:inline;">
                                        <button type="submit" value="excluir" class="btn btn-danger">
                                            <i id="boot-icon" class="bi bi-trash"></i> Excluir
                                        </button>
                                    </form:form>
                                </div>
                            </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

            <form:form method="GET" action="/manager/projetos/novo">
			<div>
			    <input type="submit" value="Novo Projeto" class="btn btn-primary">
			</div>
			</form:form>
		</div>
	</div>

</body>
</html>