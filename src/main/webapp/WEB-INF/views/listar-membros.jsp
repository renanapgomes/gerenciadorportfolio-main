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

		<h1>Lista Membros</h1>

		<div class="row">

			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Nome</th>
						<th scope="col">Atribuição</th>
						<th scope="col">Projeto</th>
						<th scope="col">Ações</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="membro" items="${membros}">
						<tr>
							<td class="table-plus">${membro.id}</td>
							<td class="table-plus">${membro.nome}</td>
							<td class="table-plus">${membro.atribuicao}</td>
							<td class="table-plus">${membro.projeto.nome}</td>
                            <td class="table-plus">
                            <div class="d-inline">
                                <form:form method="POST" action="/manager/membros/remover/{membro.id}">
                                    <button type="submit" value="excluir" class="btn btn-primary">
                                         <i id="boot-icon" class="bi bi-trash"></i> Excluir
                                     </button>
                                </form:form>
                            </div>
                            </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

            <form:form method="GET" action="/manager/membros/novo">
			<div>
			    <input type="submit" value="Novo Membro" class="btn btn-primary">
			</div>
			</form:form>
		</div>
	</div>

</body>
</html>