<!doctype html>
<%@page import="br.com.metraton.prova.model.User"%>
<%@page import="java.util.List"%>

<html lang="pt-BR">
<head>
<style>
input {
	padding: 5px;
	margin: 5px;
}

td {
	padding: 5px;
}
</style>
<script>
function remove(pi){
	if(window.confirm("Tem certeza que deseja excuir?")){
		location.href="questao3?action=remove&i="+pi;
	}
}

</script>
<%
User user = (User) request.getAttribute("user");
%>
</head>
<body>
	<div>
		<form method="post" action="questao3">
			<input hidden="true" value="<%=user.getId()%>" name="id"><br>
			Nome: <input type="text" value="<%=user.getName()%>" name="name"><br>
			Cargo: <input type="text" value="<%=user.getRole()%>" name="role"><br>
			Tel: <input type="text" value="<%=user.getPhone() %>" name="phone"> 	
			Adicionar	<input type="submit" name="add" valeu="Adicionar"><br>
			Pesquisar	<input type="submit" name="search" valeu="Pesquisar">
		</form>
	</div>
	<h2>Cadastro de Usuários</h2>
	<table border="1">
		<tr>
			<th>Nome</th>
			<th>Cargo</th>
			<th>Telefone</th>
			<th>Excuir</th>
			<th>Editar</th>
		</tr>
		
		<%
			List<User> userList = (List<User>) request.getAttribute("userList");
			for (User u : userList) {

				out.print("<tr><td>"+u.getName() + "</td>");
				out.print("<td>"+u.getRole() + "</td>");
				out.print("<td>"+u.getPhone()+ "</td>");
				out.print("<td><a href='javascript:remove("+ u.getId() + ")'>  X</a></td>");
				out.print("<td><a href='questao3?action=edit&i="+ u.getId() +"'>  X</a></td></tr>");
			}
		%>
		
		
	</table>
</body>
</html>

